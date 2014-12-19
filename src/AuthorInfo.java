/**
 * Author specific information
 * @author Pallavi
 *
 */
public class AuthorInfo {

	private int authorID;
	private double scale;
	private String gender;
	private String firstName;
	private String lastName;
	
	public AuthorInfo(int authorID, String firstName, String lastName){
		this.authorID = authorID;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public AuthorInfo() {
	}
	public AuthorInfo(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public double getScale() {
		return scale;
	}
	public void setScale(double scale) {
		this.scale = scale;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "AuthorInfo [authorID=" + authorID + ", scale=" + scale
				+ ", gender=" + gender + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}
	
	
}
