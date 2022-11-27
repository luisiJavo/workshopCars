package utils_json;

import org.json.JSONArray;

import org.json.JSONObject;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException; 


public class ParseJSONFiles {
	
	public JSONObject readJSONFile(String file_url) {
		String loc = new String(file_url);
        File file = new File(loc);
        JSONObject jsonObj = null;
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            jsonObj = new JSONObject(content);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObj;
	}
	
}
