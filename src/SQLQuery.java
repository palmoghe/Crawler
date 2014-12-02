
public class SQLQuery {
	public static final String INSERT_INTO_PUBLICATIONS= "insert into Publications (trackID,title,abstract,journal,URL,contentType,topicTags,publisher,receivedDate,revisionDate,sitePage,year) values (?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_INTO_AUTHOR_INFO= "insert into AuthorInfo (firstName,middleName,lastName,email,affiliation,url) values (?,?,?,?,?,?)";
	public static final String GET_AUTHOR_ID = "select authorID from AuthorInfo where email like ? and firstname like ? and lastname like ?";
	public static final String UPDATE_PUB_YEAR = "update publications set year=? where URL like ?";
	public static final String GET_PAPER_ID = "Select paperID from publications where URL like ?";
	public static final String INSERT_INTO_AUTHORS = "insert into authors (paperID,authorID,authorOrder,isCorresponding) values (?,?,?,?)";
	public static final String GET_AUTHOR_ID_FROM_EMAIL = "select authorID from AuthorInfo where email like ?";
	public static final String INSERT_INTO_PAPER_TRACKS = "insert into papertracks(paperID,trackID) values (?,?)" ;
	public static final String GET_AUTHOR_NAMES = "select distinct(firstName) from authorInfo" ;
	public static final String INSERT_INTO_GENDER_NAMESOR = "insert into GenderNamesor (authorID,scale,gender) values (?,?,?)";
	public static final String GET_AUTHOR_FULL_NAMES = "select distinct(firstName),lastName from authorInfo" ;
	public static final String INSERT_INTO_MAPPING = "insert into mapping values (?,?,?)";
	public static final String UPDATE_MAPPING ="update mapping set gender = ?, scale = ? where name = ?";
	public static final String UPDATE_GENDER_MAPPING = "update gendernamesor set firstname = (select firstname from authorinfo where authorid=?) where authorid=?";
	public static final String GET_GENDER_MAPPING = "select * from mapping where name like ?";
}
 