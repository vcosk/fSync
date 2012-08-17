package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class TestFlickerMain {

	private String TIMESTAMP = ";#TIMESTAMP;#";
	private String AUTHKEY = ";#AUTHKEY;#";
	private String SIGNATURE = ";#SIGNATURE;#";
	private String AUTH = ";#AUTH;#";
	
	private String FLICKER_TOKEN_URL = "http://www.flickr.com/services/oauth/request_token";// +
//			"?oauth_nonce=;#AUTH;#" +
//			"&oauth_timestamp=;#TIMESTAMP;#" +
//			"&oauth_consumer_key=;#AUTHKEY;#" +
//			"&oauth_signature_method=HMAC-SHA1" +
//			"&oauth_version=1.0" +
//			"&oauth_signature=;#SIGNATURE;#" +
//			"&oauth_callback=http%3A%2F%2Fwww.example.com";
	
	private String MY_KEY = "ca99d670792126077417ee4a6b15f17f";
	private String MY_OAUTH = "146b304e1cc4363c";
	private String MY_SIG = "146b304e1cc4363c";
	
	public static void main(String[] args) {
		TestFlickerMain t = new TestFlickerMain();
		try {
			log(t.getFlickerToken());
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		
	}
	
	public String getFlickerToken() throws MalformedURLException, IOException {
		return getFlickerToken(MY_KEY, MY_SIG);
	}
	
	public String getFlickerToken(String key, String signature) throws MalformedURLException, IOException {
		
		String token = "";
		
		String requestURL = FLICKER_TOKEN_URL;
//				.replace(AUTHKEY, key)
//				.replace(SIGNATURE, signature)
//				.replace(TIMESTAMP, Long.toString(new Date().getTime()));
		
		URL url = new URL(requestURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		
		InputStream is = connection.getInputStream();
		
		int size = is.available();
		byte[] dataByteArray = new byte[size];
		is.read(dataByteArray);
		
		token = new String(dataByteArray);
		
		return token;
	}
	
	
	
	public static void log(Object msg) {
		System.out.println(msg);
	}
}
