
public class SQLQuery {

	public static final String INSERT_PUBLICATION = "";
	
	
	
	public static final String INSERT_INTO_PUBLICATIONS= "insert into Publications (trackID,title,abstract,journal,URL,contentType,topicTags,publisher,receivedDate,revisionDate,sitePage,year) values (?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_INTO_AUTHOR_INFO= "insert into AuthorInfo (firstName,middleName,lastName,email,affiliation,url) values (?,?,?,?,?,?)";
	public static final String GET_AUTHOR_ID = "select authorID from AuthorInfo where email like ? and firstname like ? and lastname like ?";
	public static final String UPDATE_PUB_YEAR = "update publications set year=? where trackID=? and URL like ?";


	public static final String GET_PAPER_ID = "Select paperID from publications where URL like ?";

	public static final String INSERT_INTO_AUTHORS = "insert into authors (paperID,authorID,authorOrder,isCorresponding) values (?,?,?,?)";

	public static final String GET_AUTHOR_ID_FROM_EMAIL = "select authorID from AuthorInfo where email like ?";



	public static final String INSERT_INTO_PAPER_TRACKS = "insert into papertracks(paperID,trackID) values (?,?)" ;



}
