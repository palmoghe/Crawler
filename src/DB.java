import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * All DB interactions
 * @author Pallavi
 *
 */
public class DB {

	public Connection conn = null;

	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pubdatanew";
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("conn built");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}

	public boolean runSql2(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.execute(sql);
	}

	public void updatePublicationYear(int year,String URL) throws SQLException{
		PreparedStatement sta = conn.prepareStatement(SQLQuery.UPDATE_PUB_YEAR);
		sta.setInt(1, year);
		sta.setString(2, URL);
		sta.executeUpdate();
	}

	public void insertIntoGenderNamesor(int authorID, double scale,String gender) throws SQLException{
		PreparedStatement sta = conn.prepareStatement(SQLQuery.INSERT_INTO_GENDER_NAMESOR);
		sta.setInt(1, authorID);
		DecimalFormat df = new DecimalFormat("####0.00");
		System.out.println("Value:"+df.format(scale));
		sta.setDouble(2, Double.valueOf(df.format(scale)));
		sta.setString(3, gender);
		sta.executeUpdate();
	}
	public int insertIntoPublications(int trackID,String title,String paperAbstract,String journal,String URL,String contentType,String topicTags,String publisher, java.util.Date receivedDate,java.util.Date revisionDate, int sitePage,int year) throws SQLException {
		PreparedStatement sta = conn.prepareStatement(SQLQuery.INSERT_INTO_PUBLICATIONS, Statement.RETURN_GENERATED_KEYS);
		sta.setInt(1, trackID);
		sta.setString(2, title);
		sta.setString(3, paperAbstract);
		sta.setString(4, journal);
		sta.setString(5,URL);
		sta.setString(6, contentType);
		sta.setString(7,topicTags);
		sta.setString(8,publisher);
		sta.setInt(9, year);
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		if(receivedDate!=null){
			sta.setDate(9, Date.valueOf(format.format(receivedDate)));
		}else{
			sta.setDate(9, null);
		}
		if(revisionDate!=null){
			sta.setDate(10, Date.valueOf(format.format(revisionDate)));
		}else{
			sta.setDate(10, null);
		}
		sta.setInt(11, sitePage);
		sta.setInt(12, year);
		sta.executeUpdate();
		ResultSet generatedKeys = sta.getGeneratedKeys();
		generatedKeys.next();
		return generatedKeys.getInt(1);
	}
	public void setAutoCommit(boolean disable){
		try {
			conn.setAutoCommit(disable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void commit(){
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void rollBack(){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int runSqlInsertAuthorInfo(String firstName,String middleName,String lastName,String email, String affiliation, String url) throws SQLException{
		PreparedStatement sta = conn.prepareStatement(SQLQuery.INSERT_INTO_AUTHOR_INFO,Statement.RETURN_GENERATED_KEYS);
		sta.setString(1,firstName);
		sta.setString(2,middleName);
		sta.setString(3, lastName);
		sta.setString(4,email);
		sta.setString(5, affiliation);
		sta.setString(6, url);
		sta.executeUpdate();
		ResultSet generatedKeys = sta.getGeneratedKeys();
		generatedKeys.next();
		return generatedKeys.getInt(1);
	}

	public Integer getAuthorID(Author author) throws SQLException{
		PreparedStatement sta=null;
		if(author.getEmail()!=null && !author.getEmail().isEmpty()){
			sta = conn.prepareStatement(SQLQuery.GET_AUTHOR_ID_FROM_EMAIL);
			sta.setString(1, author.getEmail());
		}else{
			sta = conn.prepareStatement(SQLQuery.GET_AUTHOR_ID);
			sta.setString(1,author.getEmail());
			sta.setString(2, author.getFirstName());
			sta.setString(3, author.getLastName());
		}
		ResultSet resultSet = sta.executeQuery();
		if(resultSet.next()){
			System.out.println("Duplicate detected****");
			System.out.println(author);
			return resultSet.getInt("authorID");
		}else{
			return null;
		}
	}
	public Integer getPaperID(String URL) throws SQLException{

		PreparedStatement sta = conn.prepareStatement(SQLQuery.GET_PAPER_ID);
		sta.setString(1, URL);
		ResultSet resultSet = sta.executeQuery();
		if(resultSet.next()){
			System.out.println("Duplicate paper detected: "+resultSet.getInt("paperID"));
			return resultSet.getInt("paperID");
		}
		return null;

	}

	public int insertIntoAuthors(int paperID,int authorID, int authorOrder, boolean isCorresponding) throws SQLException{
		PreparedStatement sta = conn.prepareStatement(SQLQuery.INSERT_INTO_AUTHORS);
		sta.setInt(1, paperID);
		sta.setInt(2, authorID);
		sta.setInt(3, authorOrder);
		if(isCorresponding){
			sta.setInt(4, 1);
		}else{
			sta.setInt(4, 0);
		}
		return sta.executeUpdate();
	}

	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}

	public List<String> getAuthorNames() throws SQLException{
		PreparedStatement sta = conn.prepareStatement(SQLQuery.GET_AUTHOR_NAMES);
		ResultSet rs = sta.executeQuery();
		List<String> result= new ArrayList<>();
		while(rs.next()){
			result.add(rs.getString(1));
		}
		return result;
	}

	public List<AuthorInfo> getAuthorInfo() throws SQLException{
		PreparedStatement sta = conn.prepareStatement(SQLQuery.GET_AUTHOR_FULL_NAMES);
		ResultSet rs = sta.executeQuery();
		List<AuthorInfo> result= new ArrayList<>();
		while(rs.next()){
			result.add(new AuthorInfo(rs.getString(1), rs.getString(2)));
		}
		return result;
	}
	public AuthorInfo getAuthorInfo(String name) throws SQLException{
		PreparedStatement sta = conn.prepareStatement(SQLQuery.GET_GENDER_MAPPING);
		sta.setString(1, name);
		ResultSet rs = sta.executeQuery();
		while(rs.next()){
			AuthorInfo info = new AuthorInfo();
			info.setFirstName(rs.getString(1));
			info.setGender(rs.getString(2));
			info.setScale(rs.getDouble(3));
			return info;
		}
		return null;
	}
	public int insertIntoMapping(String name, String gender, Double scale) throws SQLException {
		PreparedStatement sta = conn.prepareStatement(SQLQuery.INSERT_INTO_MAPPING);
		sta.setString(1, name);
		sta.setString(2, gender);
		sta.setDouble(3, scale);
		return sta.executeUpdate();
	}
	public int updateMapping(String name, String gender, Double scale) throws SQLException {
		PreparedStatement sta = conn.prepareStatement(SQLQuery.UPDATE_MAPPING);
		sta.setString(1, gender);
		sta.setDouble(2, scale);
		sta.setString(3, name);
		return sta.executeUpdate();
	}
	public int insertIntoPaperTracks(Integer paperID, int trackID) throws SQLException {
		PreparedStatement sta = conn.prepareStatement(SQLQuery.INSERT_INTO_PAPER_TRACKS);
		sta.setInt(1, paperID);
		sta.setInt(2, trackID);
		return sta.executeUpdate();
	}

	public int insertIntoRecords(String URL, int trackID) throws SQLException{
		String sql = "INSERT INTO  `pubdatanew`.`Records` "
				+ "(`URL`,`trackID`) VALUES " + "(?,?);";
		PreparedStatement stmt = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, URL);
		stmt.setInt(2, trackID);
		return stmt.executeUpdate();
	}
}