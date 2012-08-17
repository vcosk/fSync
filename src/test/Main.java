package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.Permission;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.SearchParameters;
import com.aetrion.flickr.photos.upload.Ticket;
import com.aetrion.flickr.photos.upload.UploadInterface;
import com.aetrion.flickr.photosets.Photoset;
import com.aetrion.flickr.photosets.Photosets;
import com.aetrion.flickr.photosets.PhotosetsInterface;
import com.aetrion.flickr.uploader.UploadMetaData;
import com.aetrion.flickr.uploader.Uploader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FlickrAuth flickrAuth = new FlickrAuth();
		String apiKey = "ca99d670792126077417ee4a6b15f17f";
		String secret = "146b304e1cc4363c";
		Permission perm = Permission.DELETE;

		String authURL = flickrAuth.getForbAuthURL(apiKey, secret, perm);
		log(authURL);

		log("Press enter after authorizing the page");
		BufferedReader infile =
				new BufferedReader ( new InputStreamReader (System.in) );
		infile.readLine();

		Auth flickrAuthObj = flickrAuth.getFlickrUserAuthObj();
		log("Token: "+flickrAuthObj.getToken());
		log("nsid: " + flickrAuthObj.getUser().getId());
		log("Realname: " + flickrAuthObj.getUser().getRealName());
		log("Username: " + flickrAuthObj.getUser().getUsername());
		log("Permission: " + flickrAuthObj.getPermission().getType());
		RequestContext.getRequestContext().setAuth(flickrAuthObj); 
		
		PhotosetsInterface photosetsInterface = flickrAuth.getFlickrObj().getPhotosetsInterface();  

		Photosets photosets = photosetsInterface.getList(flickrAuthObj.getUser().getId());
		Collection<Photoset> collection = photosets.getPhotosets();
		for(Photoset photoset : collection) {
			log("Photo set ID: "+photoset.getId());
			log("Photo set title: "+photoset.getTitle());
			log("Photo count: "+ photoset.getPhotoCount());
		}

		InputStream is = new FileInputStream("/home/vicky/Downloads/wakeup.jpg");
		
		Uploader uploader = flickrAuth.getFlickrObj().getUploader();
		UploadMetaData metaData = new UploadMetaData();
		metaData.setTitle("API Upload 9");
		metaData.setPublicFlag(true);
//		metaData.setAsync(true);
//		String ticketId = uploader.upload(is, metaData);
//		log(ticketId);
//		
//		UploadInterface uploadInterface = flickrAuth.getFlickrObj().getUploadInterface();
//		
//		Set<String> ticketSet = new HashSet<String>();
//		ticketSet.add("Ticket ID: "+ticketId);
//		List<Ticket> ticketList = uploadInterface.checkTickets(ticketSet);
//		
//		Ticket ticket = ticketList.get(0);
//		while(ticket.getStatus() == Ticket.UNCOMPLETED) {
//			log("Uploading ...");
//			Thread.sleep(2000);
//		}
//		if(ticket.getStatus() == Ticket.COMPLETED) {
//			String photoId = ticket.getPhotoId();
//			log("Photo ID: "+photoId);
//			Thread.sleep(2000);
//			PhotosInterface photosInterface = flickrAuth.getFlickrObj().getPhotosInterface();
//			Photo photo = photosInterface.getPhoto(photoId);
//			log(photo.getTitle());
//		}
//		else if(ticket.getStatus() == Ticket.FAILED) {
//			log("Upload Failed!!!");
//		}
		UploadInterface uploadInterface = flickrAuth.getFlickrObj().getUploadInterface();
		String photoId = uploader.upload(is, metaData);
		log("Photo ID: "+photoId);
		PhotosInterface photosInterface = flickrAuth.getFlickrObj().getPhotosInterface();
		Photo photo = photosInterface.getPhoto(photoId);
		log(photo.getTitle());
		SearchParameters searchParameters = new SearchParameters();
		//searchParameters.setText("API Upload");
		searchParameters.setUserId(flickrAuthObj.getUser().getId());
		searchParameters.setSort(SearchParameters.DATE_POSTED_DESC);
		PhotoList photoList = photosInterface.search(searchParameters, 10, 0);
		log(photoList.size());
		Iterator<Photo> iterator = photoList.iterator();
		while(iterator.hasNext()) {
			Photo photoObj = iterator.next();
			log(photo.getTitle() + ": "+ photoObj.getId());
		}
		
	}

	public static void log(Object obj) {
		System.out.println(obj);
	}

}

