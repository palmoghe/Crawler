import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.parser.ParseException;

/**
 * Query request and reponse parsers for Genderize
 * @author Pallavi
 *
 */
public class GenderizeIO {

	private static DB db = new DB();
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36";
	private static final int RESPONSE_OK = 200;

	public static void main(String[] args) {
		try {
			parseGenderizeIOResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void parseGenderizeIOResponse(){
		File fin = new File("genderize-io.txt");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(fin.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String inputLine;
		BufferedReader in = new BufferedReader(fileReader);
		try {
			while ((inputLine = in.readLine()) != null) {
				String[] split = inputLine.split(";");
				for(int i=0;i<split.length;i++){
					AuthorInfo newAuthorInfo =new CustomJSONParser().parseIntoJSON(split[i]);
					// Get existing record from DB, compare the scale and update if necessary
					System.out.println(newAuthorInfo);
					try {
						AuthorInfo dbAuthorInfo = db.getAuthorInfo(newAuthorInfo.getFirstName());
						if(dbAuthorInfo==null){
							// Insert in DB
							db.insertIntoMapping(newAuthorInfo.getFirstName(), newAuthorInfo.getGender(), newAuthorInfo.getScale());
						}else{
							// Check probability and update if necessary
							if(dbAuthorInfo.getScale() < newAuthorInfo.getScale()){
								if(!dbAuthorInfo.getGender().equalsIgnoreCase(newAuthorInfo.getGender())){
									System.out.println("Update Required");
									System.out.println(dbAuthorInfo.getFirstName());
								}
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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
			authorName = URLEncoder.encode(authorName, "UTF-8");
			requestURL = requestURL.concat("name["+ i++ +"]"+"="+authorName+"&");
			if(requestURL.length()>=8100 || authorNames.indexOf(authorName)==authorNames.size()-1){
				System.out.println("Sending request for URL:"+requestURL);
				sendRequest(requestURL);
				requestURL=baseURL;
				i=0;
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
		File fout = new File("genderize-io.txt");
		FileWriter fileWriter = new FileWriter(fout.getName(),true);
		BufferedWriter bw = new BufferedWriter(fileWriter);
		if(responseCode == RESPONSE_OK){
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				bw.write(response.toString());
				bw.newLine();
			}
			in.close();
			bw.close();
			System.out.println(response.toString());

		}else{
			System.out.println("\nSending 'GET' request to URL : " + requestURL);
			System.out.println("Response Code : " + responseCode);
		}
		return response.toString();
	}
}
