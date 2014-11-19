import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static DB db = new DB();
	// static File in= new
	// File("C:\\Users\\Pallavi\\Downloads\\Courses\\EECS 599\\data\\logs.htm");
	private static Track currentTrack ;
	private static int page;
	public static void main(String[] args) throws SQLException, IOException {
		//		 db.runSql2("DELETE from publications");
		//		 db.runSql2("DELETE from authorinfo");
		//		 db.runSql2("TRUNCATE Record");
		//		 db.runSql2("ALTER TABLE publications AUTO_INCREMENT=1");
		//		 db.runSql2("ALTER TABLE authorinfo AUTO_INCREMENT=1");

		for(Track track:Track.values()){
			if(track.trackID<12){
				continue;
			}
			currentTrack = track;
			System.out.println("Track: "+currentTrack.title + " Cat: "+currentTrack.categoryID);
			for(page=1;page<=currentTrack.lastPage;page++){
				if(track.trackID==12 && page<245){
					continue;
				}
				System.out.println("PAGE: "+page);
				processPage("http://asmedigitalcollection.asme.org/collection.aspx?categoryID="+currentTrack.categoryID+"&pageA="+page+"&contentType=0");
			}
		}
	}

	public static void processPage(String URL) throws SQLException, IOException {

		if (URL.matches("http://asmedigitalcollection\\.asme\\.org/collection\\.aspx\\?categoryID="+currentTrack.categoryID+"\\&pageA=[0-9]+\\&contentType=0")) {
			Document doc = null;
			try {
				doc = Jsoup.connect(URL).get();
				//								Elements questions = doc.select("a[href]");
				//								for (Element link : questions) {
				//									if (link.attr("href").contains("articleid=")) {
				////										processPage(link.attr("abs:href"));
				//									}
				//								}
				Elements elementsByClass = doc.getElementsByClass("aList");
				for(Element ele:elementsByClass){
					Elements hrefs = ele.select("a[href]");
					for(Element hr: hrefs){
						String pubURL = hr.attr("abs:href");
						if (isURLForAPublication(pubURL)) {
							Publication pub = new Publication();	
							pub.setYear(Integer.parseInt(ele.getElementsByClass("date").get(0).text()));
							// check if the given URL is already in database
							String sql = "select * from Records where URL = '" + pubURL + "'" + " and trackID = "+currentTrack.trackID;
							try {
								ResultSet rs = db.runSql(sql);
								if (!rs.next()) {
									// store the URL to database to avoid parsing again
									Document document = Jsoup
											.connect(pubURL)
											.timeout(0)
											.userAgent(
													"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36")
													.get();
									extractPublicationInformation(pubURL, document,pub);
									savePublication(pub,pubURL);
									System.out.println(pub);
								}else{
									// Already present, only update the publication year
									// FIX for storing the year of a publication ( new field in already populated DB )
									System.out.println("Updating:" + pubURL + " Year:" + pub.getYear());
									db.updatePublicationYear(pub.getYear(), pubURL);
								}
							} catch (Exception e) {
								// TODO : Log file
								System.out.println(e);
								e.printStackTrace();
							}
						}else{
//							System.out.println("Skipping************");
//							System.out.println(URL);
						}
					}
				}

			} catch (Exception e) {
				page--;
				System.out.println(e);
				e.printStackTrace();
			}
		} 
	}

	public static boolean isURLForAPublication(String URL){
		return URL.matches("http://asmedigitalcollection\\.asme\\.org/article\\.aspx\\?articleid=[0-9]+\\&journalid=[0-9]+")
				|| URL.matches("http://asmedigitalcollection\\.asme\\.org/proceeding\\.aspx\\?articleid=[0-9]+");
	}
	private static void extractPublicationInformation(String URL,Document doc, Publication publication) {

		// Set track ID
		publication.setTrackId(currentTrack.trackID);
		publication.setUrl(URL);

		// Extract title, content type, abstract, journal name, topic tags
		Elements eleTitle = doc.getElementsByClass("aTitle");
		Elements eleContentType = doc.getElementsByClass("contentType");
		Elements eleAbstract = doc.getElementsByClass("Abstract");
		Elements eleJournal = doc.getElementsByClass("siteVolume");
		Elements eleTopics = doc.getElementsByClass("divTopics");

		System.out.println(URL);
		// Set data
		if (eleTitle.size() > 0) {
			publication.setTitle(eleTitle.get(0).text().trim());
		}
		if (eleAbstract.size() > 0) {
			publication.setPaperAbstract(eleAbstract.get(0).text().trim());
		}
		if (eleJournal.size() > 0) {
			publication.setJournal(eleJournal.get(0).text().trim());
		}
		if (eleContentType.size() > 0) {
			publication.setContentType(eleContentType.get(0).text().trim());
		}else{
			// Save conference name in contentType field if it is null and this is a conference proceeding
			if(publication.getJournal()!=null){
				if(publication.getJournal().contains("ASME Proceedings")){
					Element confElements = doc.getElementById("scm6MainContent_bListConf");
					if(confElements!=null){
						Elements element = confElements.getAllElements();
						if(element.size()>1){
							publication.setContentType(element.get(1).text());
						}
					}
				}
			}
		}
		if (eleTopics.size() > 0) {
			String topicTags = eleTopics.get(0).text();
			topicTags = topicTags.replace("Topics:", "");
			publication.setTopicTags(topicTags.trim());
		}
		parseDisclosuresForAuthorDetails(doc, publication);
		// Extract dates
		if (doc.getElementsByClass("aShortDesc").size() > 0) {
			Element eleDateInfo = doc.getElementsByClass("aShortDesc").get(0)
					.getElementById("pageContent_lblHistory");
			if (eleDateInfo != null) {
				String dateInfo = eleDateInfo.text();
				String[] arrDates = dateInfo.trim().split(";");
				if (arrDates.length > 1) {
					if (arrDates[0].contains("Received")) {
						try {
							Date receivedDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(arrDates[0].replace("History: Received", " ").trim());
							publication.setReceivedDate(receivedDate);
						} catch (Exception e) {
							System.out.println("Exception while parsing date for publication:"
									+ publication);
						}
					}
					if (arrDates[1].contains("Revised")) {
						try {
							Date revisionDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(arrDates[1].replace("Revised", " ").trim());
							publication.setRevisionDate(revisionDate);
						} catch (Exception e) {
							System.out.println("Exception while parsing date for publication:"+ publication);
						}
					}
				}
			}
		}
	}

	private static void savePublication(Publication publication, String URL) {
		try {
			db.setAutoCommit(false);
			Integer existingPaperID = db.getPaperID(URL);
			if(existingPaperID == null ){
				existingPaperID = db.insertIntoPublications(publication.getTrackId(),
						publication.getTitle(), publication.getPaperAbstract(),
						publication.getJournal(), publication.getUrl(),
						publication.getContentType(), publication.getTopicTags(),
						publication.getPublisher(), publication.getReceivedDate(),
						publication.getRevisionDate(),page,publication.getYear());

				for (Author author : publication.getAuthors()) {
					Integer existingAuthorID = db.getAuthorID(author);
					if (existingAuthorID == null) {
						existingAuthorID = db.runSqlInsertAuthorInfo(
								author.getFirstName(), author.getMiddleName(),
								author.getLastName(), author.getEmail(),
								author.getAddress(),author.getAuthorURL());
					}

					// Insert data into Authors
					db.insertIntoAuthors(existingPaperID, existingAuthorID,author.getOrder(),author.isCorresponding());
				}
				db.insertIntoPaperTracks(existingPaperID,currentTrack.trackID);
				db.insertIntoRecords(URL, currentTrack.trackID);
				db.commit();
			}else{
				System.out.println("Only inserting into papertracks");
				db.insertIntoPaperTracks(existingPaperID,currentTrack.trackID);
				db.insertIntoRecords(URL, currentTrack.trackID);
				db.commit();
			}
		} catch (SQLException exception) {
			db.rollBack();
			System.out.println("Erroneous: "+publication);
			System.out.println("Authors: " + publication.getAuthors());
			exception.printStackTrace();
		} finally {
			db.setAutoCommit(true);
		}
	}

	private static void parseDisclosuresForAuthorDetails(Document doc,Publication publication) {

		Elements eleAffils = doc.getElementsByClass("authorAffiliation");
		List<Affil> affs = new ArrayList<>();
		for(Element element: eleAffils){
			String affiliation = element.text();
			//			Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(affiliation);
			Affil aff = new Affil();
			affs.add(aff);
			for(Element affil:element.getAllElements()){
				if(affil.hasAttr("href")){
					aff.setEmail(affil.text());
				}
			}
			if(aff.getEmail()!=null && !aff.getEmail().isEmpty()){
				String emailId = aff.getEmail();
				affiliation = affiliation.replace(emailId.trim(), " ").trim();
				String strEmail = "e-mail:";
				if (affiliation.contains(strEmail)) {
					affiliation = affiliation.replace(strEmail, " ");
					affiliation = affiliation.replace("?", " ").trim();
				}
				aff.setAff(affiliation);
			}else{
				aff.setAff(affiliation);
			}
		}
		List<Author> authors = new ArrayList<>();
		publication.setAuthors(authors);
		Elements all = doc.select("#scm6MainContent_lblAuthors");
		Elements els = all.first().children();
		String correspondingAuthor = null ;	
		HashMap<String, String> mapAuthorLinks = new HashMap<>();
		List<String> authorLinks = new ArrayList<>();
		for (int i = 0; i < els.size(); i++) {
			Element el = els.get(i);
			String attr = el.attr("abs:href");
			if ("a".equals(el.tagName())) {
				if(attr!=null && !attr.contains("#cor1") && !el.text().equals("1")){
					mapAuthorLinks.put(el.text(), attr);
					authorLinks.add(attr);
				}
				if (i + 1 < els.size() && "a".equals(els.get(i + 1).tagName())) {
					correspondingAuthor = el.text();
				}
			}
		}

		Element elementById = doc.getElementById("scm6MainContent_divAuthorAffiliation");
		if(elementById==null){
			elementById = doc.getElementById("pageContent_divAuthorAffiliation");
		}
		if(elementById == null){
			return;
		}
		Elements allElements = elementById.getAllElements();

		int i=0;
		Set<Element> unwantedElements = new HashSet<>();
		for(Element element:allElements){
			if(unwantedElements.contains(element)){
				continue;
			}
			if(element.hasClass("authorAffiliation")){
				i++;
				Elements allElements2 = element.getAllElements();
				unwantedElements.addAll(allElements2);
				continue;
			}
			if(element.isBlock()){
				continue;
			}

			if(element.hasClass("para")){
				continue;
			}
			if(!element.id().isEmpty()){
				continue;
			}
			if(element.hasAttr("href")){
				Elements toDelete = element.getAllElements();
				unwantedElements.addAll(toDelete);
				continue;
			}
			Author author = new Author();
			processAuthorName(author,element.text());
			author.setAddress(affs.get(i).getAff());
			author.setEmail(affs.get(i).getEmail());
			author.setOrder(i+1);
			if(authorLinks.size()>i){
				author.setAuthorURL(authorLinks.get(i));
			}
			if(correspondingAuthor!=null){
				if (correspondingAuthor.contains(author.getFirstName())){
					if(author.getLastName()!=null){
						if(correspondingAuthor.contains(author.getLastName())){
							author.setCorresponding(true);	
						}
					}else{
						author.setCorresponding(true);
					}
				}
			}
			authors.add(author);
		}
	}

	private static void processAuthorName(Author author, String fullName) {
		String[] names = fullName.trim().split(" ");
		switch (names.length) {
		case 0:
			break;
		case 1:
			author.setFirstName(names[0]);
			break;
		case 2:
			// Assuming that first and last name would compose them
			author.setFirstName(names[0].trim());
			author.setLastName(names[1].trim());
			break;
		case 3:
			author.setFirstName(names[0].trim());
			author.setMiddleName(names[1].trim());
			author.setLastName(names[2].trim());
			break;
		case 4:
			author.setFirstName(names[0].trim());
			author.setMiddleName(names[1].trim() + " " + names[2].trim());
			author.setLastName(names[3].trim());
			break;
		default:
			author.setFirstName(names[0].trim());
			author.setLastName(names[names.length-1]);
		}
	}
}

class Affiliation {
	private String aff;
	private String email;

	public String getAff() {
		return aff;
	}

	public void setAff(String aff) {
		this.aff = aff;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

