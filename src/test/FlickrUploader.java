package test;

import java.io.FileInputStream;
import java.io.InputStream;

import com.aetrion.flickr.uploader.UploadMetaData;
import com.aetrion.flickr.uploader.Uploader;

public class FlickrUploader {

	private Uploader uploader;
	
	
	public static void main(String[] args) throws Exception {
		String apiKey = "ca99d670792126077417ee4a6b15f17f";
		String secret = "146b304e1cc4363c";
		
		UploadMetaData metaData = new UploadMetaData();
		metaData.setTitle("API Upload");
		FlickrUploader flickrUploader = new FlickrUploader();
		Uploader uploader = flickrUploader.getUplaoder(apiKey, secret);
		
		InputStream is = new FileInputStream("/home/vicky/Downloads/wakeup.jpg");
		
		uploader.upload(is, metaData);
	}
	
	public Uploader getUplaoder(String apiKey, String secret) {
		if(uploader == null) { 
			uploader = new Uploader(apiKey, secret);
		}
		return uploader;
	}
}
