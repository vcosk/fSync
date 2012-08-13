<%@ page import="java.net.*"%>
<%@ page import="java.io.*"%>
<%
	try {
		String accessToken = request.getParameter("access_token");
		URL url = new URL("https://graph.facebook.com/me/albums?access_token="+accessToken);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream is = connection.getInputStream();
		//int size = is.available();
		//out.print(size);
		//byte[] dataByteArray = new byte[size];
		//is.read(dataByteArray);		
		//String data = new String(dataByteArray);
		//out.print(data);
		int data;
		//out.print("load(");	
		while((data = is.read()) != -1) {
			out.print(((char)data));
		}
		//out.print(");");
	}
	catch(Exception e) {
		e.printStackTrace();
	}
%>
