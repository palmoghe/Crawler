import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class NameGenderMapping {

	static DB db = new DB();

	public static void main(String[] args) throws IOException {
		File file = new File("completeset.txt");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		Set<String> names = new HashSet<>();
		while ((line = br.readLine()) != null) {
			try {
				String[] split = line.split(":");
				if(!names.contains(split[0])){
					names.add(split[0]);
					if(split[1].equals("male")){
						db.insertIntoMapping(split[0], "M",1.0);
					}else{
						db.insertIntoMapping(split[0], "F",1.0);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		br.close();
	}



}
