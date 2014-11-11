/**
 * 
 * @author Pallavi
 *
 */
public class Author {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
	private String email;
	private int order;
	private boolean corresponding;
	private String authorURL;
	
	public boolean isCorresponding() {
		return corresponding;
	}
	public void setCorresponding(boolean corresponding) {
		this.corresponding = corresponding;
	}
	public String getAuthorURL() {
		return authorURL;
	}
	public void setAuthorURL(String authorURL) {
		this.authorURL = authorURL;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "Author [firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", address=" + address
				+ ", email=" + email + ", order=" + order + "]";
	}
}
