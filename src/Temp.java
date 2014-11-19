import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Temp {

	public static void main(String[] args) {

		Document doc = null;
		try {
			String body = Jsoup
					.connect(
							"http://asmedigitalcollection.asme.org/collection.aspx?categoryID=9208&pageA=25&contentType=0")
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36")
					.execute().body();
			// doc =
			// Jsoup.connect("http://proceedings.asmedigitalcollection.asme.org/proceeding.aspx?articleid=1775911").get();
			
			System.out.println(body);
//			Elements select = b.select("[style=display:none;]");
//			System.out.println(select);
//			System.out.println("After removal:"+doc.toString());
			
			Elements eleTitle = doc.getElementsByClass("aTitle");
			Elements eleContentType = doc.getElementsByClass("contentType");
			Elements eleAbstract = doc.getElementsByClass("Abstract");
			Elements eleJournal = doc.getElementsByClass("siteVolume");
			Elements eleTopics = doc.getElementsByClass("divTopics");
			//
			// Publication publication= new Publication();
			//
			// // Set data
			// if (eleTitle.size() > 0) {
			// publication.setTitle(eleTitle.get(0).text().trim());
			// }
			// if (eleAbstract.size() > 0) {
			// publication.setPaperAbstract(eleAbstract.get(0).text().trim());
			// }
			// if (eleJournal.size() > 0) {
			// publication.setJournal(eleJournal.get(0).text().trim());
			// }
			// if (eleContentType.size() > 0) {
			// publication.setContentType(eleContentType.get(0).text().trim());
			// }else{
			// if(publication.getJournal().contains("ASME Proceedings")){
			// Element confElements =
			// doc.getElementById("scm6MainContent_bListConf");
			// if(confElements!=null){
			// Elements element = confElements.getAllElements();
			// if(element.size()>1){
			// publication.setContentType(element.get(1).text());
			// System.out.println(publication);
			// }
			// }
			// }
			// }

//			parseDisclosuresForAuthorDetails(doc);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	private static void parseDisclosuresForAuthorDetails(Document doc) {
		Elements elementsByClass = doc.getElementsByClass("authorNames");
		Elements elementsByClass2 = doc
				.getElementsByTag("a.disclosureLink special");
		Elements elementsByAttributeValue2 = doc.getElementsByAttributeValue(
				"href", "#cor1");
		// Elements select =
		// doc.select("<a href=\"#cor1\" class=\"disclosureLink special\"><sup>1</sup></a>");
		for (Element element : elementsByClass) {
			Elements elementsByTag = element
					.getElementsByClass("disclosureLink.special");
			System.out.println("");

		}
		// ***
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
		// ***
		
		Elements eleAffils = doc.getElementsByClass("authorAffiliation");
		List<Affil> affs = new ArrayList<>();
		for (Element element : eleAffils) {
			String affiliation = element.text();
			// Matcher m = Pattern.compile(
			// "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(
			// affiliation);
			// int patterns=0;
			Affil aff = new Affil();
			affs.add(aff);
			for (Element affil : element.getAllElements()) {
				if (affil.hasAttr("href")) {
					aff.setEmail(affil.text());
				}
			}
			if (aff.getEmail() != null && !aff.getEmail().isEmpty()) {
				// while (m.find()) {
				String emailId = aff.getEmail();
				affiliation = affiliation.replace(emailId.trim(), " ").trim();
				String strEmail = "e-mail:";
				if (affiliation.contains(strEmail)) {
					affiliation = affiliation.replace(strEmail, " ");
					affiliation = affiliation.replace("?", " ").trim();
				}
				aff.setAff(affiliation);
				// patterns++;
				// }
			} else {
				// if(patterns==0){
				aff.setAff(affiliation);
			}
		}
		Element elementById = doc
				.getElementById("scm6MainContent_divAuthorAffiliation");
		if (elementById == null) {
			elementById = doc
					.getElementById("pageContent_divAuthorAffiliation");
		}
		Elements allElements = elementById.getAllElements();

		List<Author> authors = new ArrayList<>();
		int i = 0;
		Set<Element> unwantedElements = new HashSet<>();
		for (Element element : allElements) {
			if (unwantedElements.contains(element)) {
				continue;
			}
			if (element.hasClass("authorAffiliation")) {
				i++;
				Elements allElements2 = element.getAllElements();
				unwantedElements.addAll(allElements2);
				continue;
			}
			if (element.isBlock()) {
				continue;
			}
			if (element.hasClass("para")) {
				continue;
			}
			if (!element.id().isEmpty()) {
				continue;
			}
			if (element.hasAttr("href")) {
				Elements toDelete = element.getAllElements();
				unwantedElements.addAll(toDelete);
				continue;
			}
			Author author = new Author();
			processAuthorName(author, element.text());
			author.setAddress(affs.get(i).getAff());
			author.setEmail(affs.get(i).getEmail());
			author.setOrder(i + 1);
			if(authorLinks.size()>i){
				author.setAuthorURL(authorLinks.get(i));
			}
			if (correspondingAuthor!=null && correspondingAuthor.contains(author.getFirstName())){
				if(author.getLastName()!=null){
					if(correspondingAuthor.contains(author.getLastName())){
						author.setCorresponding(true);	
					}
				}else{
					author.setCorresponding(true);
				}
			}
			authors.add(author);
		}
		for (Author author : authors) {
			System.out.println("First:" + author.getFirstName());
			System.out.println("Addr:" + author.getAddress());
			System.out.println("Email:" + author.getEmail());
			System.out.println("isCorr: " + author.isCorresponding() );
			System.out.println("link:" + author.getAuthorURL());
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
			author.setLastName(names[names.length - 1]);
		}
	}

}

class Affil {
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
