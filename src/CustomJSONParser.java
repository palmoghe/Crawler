import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class CustomJSONParser {
	
	AuthorInfo parseIntoJSON(String jsonString) throws ParseException{
		JSONObject json = (JSONObject)new JSONParser().parse(jsonString);
		System.out.println("name=" + json.get("name"));
		Object objGender = json.get("gender");
		AuthorInfo info = new AuthorInfo();
		info.setFirstName(String.valueOf(json.get("name")));
		if(objGender!=null){
			String gender = String.valueOf(objGender);
			if(gender.equalsIgnoreCase("female")){
				gender = "F";
			}else{
				gender = "M";
			}
			info.setGender(gender);
			info.setScale(Double.parseDouble(String.valueOf(json.get("probability"))));
		}
		return info;
	}

}
