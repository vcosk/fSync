package test;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.AuthInterface;
import com.aetrion.flickr.auth.Permission;

public class FlickrAuth {
	
	private AuthInterface authInterface;
	private String frob;
	private Auth authObj;
	private Flickr flickr;
	
	public String getForbAuthURL(String apiKey, String secret, Permission permission) throws ParserConfigurationException, IOException, SAXException, FlickrException {
		flickr = new Flickr(apiKey, secret, new REST());
		authInterface = flickr.getAuthInterface();
		frob = authInterface.getFrob();
		
		URL url = authInterface.buildAuthenticationUrl(permission, frob);
		
		return url.toExternalForm();
	}
	
	public Auth getFlickrUserAuthObj() throws IOException, SAXException, FlickrException {
		authObj = authInterface.getToken(frob);
		return authObj;
	}
	
	public AuthInterface getAuthInterface() {
		return authInterface;
	}
	
	public void setFrob(String frob) {
		this.frob = frob;
	}
	
	public String getFrob() {
		return frob;
	}
	
	public Auth getAuthObj() {
		return authObj;
	}
	
	public Flickr getFlickrObj() {
		return flickr;
	}
}
