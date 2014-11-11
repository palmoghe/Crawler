import java.util.Date;
import java.util.List;

/**
 * 
 * @author Pallavi
 *
 */
public class Publication {

	private String title;
	private int trackId;
	private String paperAbstract;
	String contentType;
	private String journal;
	private Date receivedDate;
	private Date revisionDate;
	private String topicTags;
	private String publisher = "ASME";
	private List<Author> authors;
	private String url;
	private int year;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	public String getPaperAbstract() {
		return paperAbstract;
	}
	public void setPaperAbstract(String paperAbstract) {
		this.paperAbstract = paperAbstract;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Date getRevisionDate() {
		return revisionDate;
	}
	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}
	public String getTopicTags() {
		return topicTags;
	}
	public void setTopicTags(String topicTags) {
		this.topicTags = topicTags;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setYear(int year){
		this.year = year;
	}
	
	public int getYear(){
		return year;
	}
	@Override
	public String toString() {
		return "Publication [title=" + title + ", trackId=" + trackId
				+ ", paperAbstract=" + paperAbstract + ", contentType="
				+ contentType + ", journal=" + journal + ", receivedDate="
				+ receivedDate + ", revisionDate=" + revisionDate
				+ ", topicTags=" + topicTags + ", publisher=" + publisher
				+ ", authors=" + authors + ", url=" + url + ", year=" + year
				+ "]";
	}
	

	

	

}
