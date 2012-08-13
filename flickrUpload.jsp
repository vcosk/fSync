<%@ page import="java.util.*"%>
<%@ page import="com.aetrion.flickr.*"%>
<%@ page import="com.aetrion.flickr.auth.*"%>
<%@ page import="com.aetrion.flickr.photos.*"%>
<%@ page import="com.aetrion.flickr.photosets.*"%>
<%@ page import="com.aetrion.flickr.uploader.*"%>
<%@ page import="test.*"%>
<%
	String frob = request.getParameter("frob");
	out.print(frob);
	FlickrAuth flickrAuth = (FlickrAuth)session.getAttribute("FlickrAuthObj");
	out.print("<br />");
	out.print(flickrAuth.getFrob());
	flickrAuth.setFrob(frob);
	Auth flickrAuthObj = flickrAuth.getFlickrUserAuthObj();
	out.print("Token: "+flickrAuthObj.getToken());
	out.print("<br />");
	out.print("nsid: " + flickrAuthObj.getUser().getId());
	out.print("<br />");
	out.print("Realname: " + flickrAuthObj.getUser().getRealName());
	out.print("<br />");
	out.print("Username: " + flickrAuthObj.getUser().getUsername());
	out.print("<br />");
	out.print("Permission: " + flickrAuthObj.getPermission().getType());
	RequestContext.getRequestContext().setAuth(flickrAuthObj);

	String albumIDs = (String)session.getAttribute("AlbumIDs");
	String accessToken = (String)session.getAttribute("access_token");
	out.print("albumIDs: "+albumIDs);
	out.print("<br />");
	out.print("accessToken: "+accessToken);
	out.print("<br />");
	Facebook2Flickr f2fObj = new Facebook2Flickr();
	out.print(f2fObj.syncPics(albumIDs, accessToken, flickrAuth));
%>
