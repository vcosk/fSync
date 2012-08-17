package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.aetrion.flickr.uploader.UploadMetaData;
import com.aetrion.flickr.uploader.Uploader;

public class Facebook2Flickr {
	private String fbGrphURL = "https://graph.facebook.com/";
	private String PHOTOS_DATA = "/photos";
	private String accesToken = "?access_token="; 
	public void syncPics(String fbAlbumIds, String fbAccessTocken, FlickrAuth flickrAuthObj) throws Exception {		
		String[] fbAlbumIdArray = fbAlbumIds.split(",");
		List<AlbumDataBean> albumDataList = new LinkedList<AlbumDataBean>();
		System.out.println(fbAlbumIdArray.length);
		for(String fbAlbum : fbAlbumIdArray) {
			AlbumDataBean albumData = new AlbumDataBean();
			String[] fdAlbumDtls = fbAlbum.split("|");
			String albumPhotos = getAlbumJSON(fdAlbumDtls[0], fbAccessTocken);

			albumData.setAlbumID(fdAlbumDtls[0]);
			albumData.setCoverPhotoID(fdAlbumDtls[1]);
			albumData.setPhotosJSON(albumPhotos);

			albumDataList.add(albumData);
			
			uploadPhotos(albumDataList, flickrAuthObj);
		}
	}

	public void uploadPhotos(List<AlbumDataBean> albumDataList, FlickrAuth flickrAuthObj) throws Exception {
		
		for(AlbumDataBean albumData : albumDataList) {
			List<String> imageSrcURLs = getPhotoURLs(albumData);
			List<String> flickrPhotoIDList = uploadToFlickr(imageSrcURLs, flickrAuthObj);
		}

	}

	public void createAlbum(AlbumDataBean albumData, )
	public List<String> uploadToFlickr(List<String> imageSrcURLs, FlickrAuth flickrAuthObj) throws Exception {
		List<String> flickrPhotoIDList = new LinkedList<String>();
		
		for(String imgURL : imageSrcURLs) {
			uploadToFlickr(imgURL, flickrAuthObj);
		}
		
		return flickrPhotoIDList;
	}
	
	public String uploadToFlickr(String imgURL, FlickrAuth flickrAuthObj) throws Exception {
		URL u = new URL(imgURL);
		HttpURLConnection connection = (HttpURLConnection) u.openConnection();
		connection.connect();
		
		UploadMetaData uploadMetaData = new UploadMetaData();
		uploadMetaData.setPublicFlag(true);
		
		Uploader uploader = flickrAuthObj.getFlickrObj().getUploader();
		String photoId = uploader.upload(connection.getInputStream(), uploadMetaData);
		
		return photoId;
		
	}
	
	public List<String> getPhotoURLs(AlbumDataBean albumData) throws Exception {

		JSONObject jsonObject = new JSONObject(albumData.getPhotosJSON());
		JSONArray dataArray = jsonObject.getJSONArray("data");
		JSONObject content = dataArray.getJSONObject(0);
		albumData.setCoverPhotoID(content.getString("source"));

		JSONArray imagesArray = content.getJSONArray("images");
		List<String> imageSrcURL = new LinkedList<String>();

		for(int imageIndex=0; imageIndex < imagesArray.length(); imageIndex++) {
			JSONObject imageData = imagesArray.getJSONObject(imageIndex);
			imageSrcURL.add(imageData.getString("source"));
		}

		return imageSrcURL;
	}

	public String getAlbumJSON(String objecID, String fbAccessTocken) throws Exception {
		StringBuilder objectJSON = new StringBuilder();

		URL url = new URL(fbGrphURL+objecID+PHOTOS_DATA+accesToken+fbAccessTocken);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();

		InputStream is = connection.getInputStream();
		int dataByte;
		while((dataByte = is.read()) != -1) {
			objectJSON.append((char)dataByte);
		}

		return objectJSON.toString();
	}
}


