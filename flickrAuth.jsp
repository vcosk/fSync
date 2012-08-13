<%@ page import="com.aetrion.flickr.*"%>
<%@ page import="com.aetrion.flickr.auth.*"%>
<%@ page import="com.aetrion.flickr.photos.*"%>
<%@ page import="com.aetrion.flickr.photosets.*"%>
<%@ page import="com.aetrion.flickr.uploader.*"%>
<%@ page import="test.*"%>
<%
	String albumIDs = request.getParameter("albumIDs");
	String accessToken = request.getParameter("access_token");
	session.setAttribute("AlbumIDs", albumIDs);
	session.setAttribute("access_token", accessToken);
	//out.print("Albums: "+ albumIDs + "  accessToken: "+accessToken);
	FlickrAuth flickrAuth = new FlickrAuth();
	String apiKey = "ca99d670792126077417ee4a6b15f17f";
	String secret = "146b304e1cc4363c";
	Permission perm = Permission.DELETE;
	String authURL = flickrAuth.getForbAuthURL(apiKey, secret, perm);
	session.setAttribute("FlickrAuthObj", flickrAuth);
	response.sendRedirect(authURL);
%>
