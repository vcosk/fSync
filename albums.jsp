<html>
	<head>
		<title>f-Sync Facebook Albums</title>
		<link rel="stylesheet" type="text/css" href="./css/site.css" />
		<script  type="text/javascript">
			var accessToken = '<%=request.getParameter("access_token")%>';
		</script>
		<script src="./js/jquery-1.7.1.min.js" type="text/javascript"></script>
		<script src="./js/album.js" type="text/javascript"></script>
	</head>
	<body>
		<div id="header" class ="header">
		f - Sync
		</div>
		<br />
		<br />
		<div class="youralbums">
			Your Facebook Albums
		</div>
		<form name="albumForm" method="post" action="flickrAuth.jsp">
			<input type="Submit" name="Synchronize" value="Synchronize">
			<div id="albums" class="albums"><span/></div>
			<input id="albumIDs" name="albumIDs" type="hidden" value="" />
			<input id="access_token" name="access_token" type="hidden" value="" />
		</form>
	</body>
</html>
