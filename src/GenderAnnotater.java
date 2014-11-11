import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mashape.unirest.http.exceptions.UnirestException;
//import com.mashape.unirest.http.HttpResponse;


public class GenderAnnotater {

	static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36";
	
	public static void main(String[] args) {
		try {
			sendGet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void sendPost1()throws Exception{

		// Define the server endpoint to send the HTTP request to
		URL serverUrl = 
				new URL("http://www.techcoil.com/process/proof-of-concepts/userNameAndLuckyNumberTextFileGeneration");
		HttpURLConnection urlConnection = (HttpURLConnection)serverUrl.openConnection();

		// Indicate that we want to write to the HTTP request body
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");

		// Writing the post data to the HTTP request body
		BufferedWriter httpRequestBodyWriter = 
				new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
		httpRequestBodyWriter.write("visitorName=Johnny+Jacobs&luckyNumber=1234");
		httpRequestBodyWriter.close();

		// Reading from the HTTP response body
		Scanner httpResponseScanner = new Scanner(urlConnection.getInputStream());
		while(httpResponseScanner.hasNextLine()) {
			System.out.println(httpResponseScanner.nextLine());
		}
		httpResponseScanner.close();


	}
	static void sendPost() throws Exception{
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("param-1", "12345"));
		params.add(new BasicNameValuePair("param-2", "Hello!"));
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		//Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream = entity.getContent();
		    try {
		        // do something useful
		    } finally {
		        instream.close();
		    }
		}
	}
	
	static void sendUnirest() throws UnirestException{
//		HttpResponse<JsonNode> response = Unirest.get("https://genderize.p.mashape.com/?country_id=US&language_id=en&name=peter")
//				.header("X-Mashape-Key", "DWOBzmk5Ibmsh0NqXgiOY7V0dP6gp1qavrhjsnIX3qkk03JPzD")
//				.asJson();
//		
//		JsonNode body = response.getBody();
//		JSONArray array = body.getArray();
//		System.out.println(array);
	}
	static void sendGet() throws Exception {
		 
//		String url = "http://www.google.com/search?q=mkyong";
		String url = "http://api.genderize.io/?name[0]=peter&name[1]=lois&name[2]=stevie";
//		String url = "http://api.namsor.com/onomastics/api/gendre/John";
//		String url = "http://www.gpeters.com/names/baby-names.php?name=smith&button=Go";
		URL obj = new URL(url);
		System.out.println("Get URL:"+obj.getFile());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		con.setRequestMethod("GET");
		
		con.setRequestProperty("User-Agent", USER_AGENT);
		System.out.println("##########"+con.getResponseCode());
		// optional default is GET
 
		//add request header
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
	
	static void sendGet3(){
		try {
			URL url = new URL("http://api.namsor.com/onomastics/api/json/gendre/Pallavi/Smith");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String strTemp = "";
			while (null != (strTemp = br.readLine())) {
				System.out.println(strTemp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	static void sendGet2() throws IOException{
		Document doc = null;
		System.out.println(Jsoup
				.connect("http://api.genderize.io/?name=peter").ignoreContentType(true).execute().body());
//				.timeout(0)
//				.userAgent(
//						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36")
//				.get();
	}
}
