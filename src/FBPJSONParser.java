import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class FBPJSONParser {

	public static void main(String[] args) throws FileNotFoundException, JSONException {
		// TODO Auto-generated method stub
		InputStream is = new FileInputStream("facebookphotoalbumdata");
		JSONTokener jsonTokener = new JSONTokener(is);
		JSONObject jsonObject = new JSONObject(jsonTokener);
		
		JSONArray dataArray = jsonObject.getJSONArray("data");
		JSONObject content = dataArray.getJSONObject(0);
		String coverPic = content.getString("source");
		
		JSONArray imagesArray = content.getJSONArray("images");
		List<String> imageSrcURL = new LinkedList<String>();
		
		for(int imageIndex=0; imageIndex < imagesArray.length(); imageIndex++) {
			JSONObject imageData = imagesArray.getJSONObject(imageIndex);
			imageSrcURL.add(imageData.getString("source"));
		}
		
		l(coverPic);
		l(imageSrcURL);
	}
	
	public static void l(Object msg) {
		System.out.println(msg);
	}

}
