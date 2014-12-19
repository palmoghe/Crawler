import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class GenderAnnotater {

	private static final int RESPONSE_OK = 200;
	static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36";
	static DB db = new DB();
	public static void main(String[] args) {
		try {
			sendGetToNameSor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void sendGetToGenderize() throws Exception {
		String baseURL = "http://api.genderize.io/?";
		String requestURL = "http://api.genderize.io/?";
		List<String> authorNames = db.getAuthorNames();
		int i=0;
		for(String authorName:authorNames){
			if(authorName.isEmpty()){
				continue;
			}
			requestURL = requestURL.concat("name["+ i++ +"]"+"="+authorName+"&");
			if(requestURL.length()>=8100 || authorNames.indexOf(authorName)==authorNames.size()-1){
				System.out.println("Sending request for URL:"+requestURL);
				sendRequest(requestURL);
				requestURL=baseURL;
				i=0;
			}
		}
	}

	static void sendGetToNameSor() throws SQLException, MalformedURLException, ProtocolException, IOException{
		String baseURL = "http://api.namsor.com/onomastics/api/gendre/";
		List<AuthorInfo> info = db.getAuthorInfo();
		int i=0;
		for(AuthorInfo newAuthorInfo: info){
			try{
				System.out.println("Requesting.. "+newAuthorInfo.getFirstName());
				String requestURL = baseURL + "/"+newAuthorInfo.getFirstName()+"/"+newAuthorInfo.getLastName();
				String response = sendRequest(requestURL);
				if(response==null){
					
					continue;
				}
				double scale = Double.parseDouble(response);
				if(scale>=0.40){
					newAuthorInfo.setGender("F");
				}else if(scale<=-0.40){
					newAuthorInfo.setGender("M");
				}else{
//					AuthorInfo dbAuthorInfo = db.getAuthorInfo(newAuthorInfo.getFirstName());
//					if(dbAuthorInfo.getGender() == null){
//						System.out.println("Yeah!!");
//					}else{
//						System.out.println(" :( ");
//					}
					continue;
				}
				newAuthorInfo.setScale(Math.abs(scale));
				try {
					AuthorInfo dbAuthorInfo = db.getAuthorInfo(newAuthorInfo.getFirstName());
					if(dbAuthorInfo==null){
						// Insert into DB
						db.insertIntoMapping(newAuthorInfo.getFirstName(), newAuthorInfo.getGender(), newAuthorInfo.getScale());
						System.out.println("Inserted:"+i++);
					}else if( dbAuthorInfo.getGender() == null){
						db.updateMapping(newAuthorInfo.getFirstName(), newAuthorInfo.getGender(), newAuthorInfo.getScale());
						System.out.println("Updated:"+i++);
					}
					else{
						// Check probability and update if necessary
						if(dbAuthorInfo.getScale() < newAuthorInfo.getScale()){
							if(!dbAuthorInfo.getGender().equalsIgnoreCase(newAuthorInfo.getGender())){
								System.out.println("Update Required");
								db.updateMapping(newAuthorInfo.getFirstName(), newAuthorInfo.getGender(), newAuthorInfo.getScale());
							}
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}catch(Exception exception){
				System.out.println(exception);
			}
		}
	}

	private static String sendRequest(String requestURL) throws MalformedURLException, IOException, ProtocolException {
		URL obj = new URL(requestURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		StringBuilder response = new StringBuilder();		
		if(responseCode == RESPONSE_OK){
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			return response.toString();
		}else{
			System.out.println("\nSending 'GET' request to URL : " + requestURL);
			System.out.println("Response Code : " + responseCode);
		}
		return null;
	}
}
