<%@ page import="java.net.*"%>
<%@ page import="java.io.*"%>
<%
	try {
		String accessToken = request.getParameter("access_token");
		String cover_photo = request.getParameter("cover_photo");
		String u = "https://graph.facebook.com/"+cover_photo+"?access_token="+accessToken;
		URL url = new URL(u);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream is = connection.getInputStream();
		int data;
		while((data = is.read()) != -1) {
			out.print(((char)data));
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
%>
