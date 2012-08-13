$(document).ready(function(){
	jQuery.ajax({url: "./albumjson.jsp?access_token="+accessToken,
			success: function(data) {
				load(data);
			}
		});
	$("#access_token").attr("value", accessToken);
});

function load(data) {
	loadAlbums(data);
}

function loadCoverPic(cover_photo)  {
	$.ajax({
		url : "./getCoverPic.jsp?cover_photo="+cover_photo+"&access_token="+accessToken,
		success : function(data) {
			updateCoverPics(data);
		}
	});
}

function updateCoverPics(coverData) {
	coverData = coverData.replace("\n\n","");
	coverData = coverData.replace("\n","");
	var coverDataObj;
	try {
		eval("coverDataObj="+coverData);
	}
	catch(e) {
		alert(e);
	}
	$("#"+coverDataObj.id).attr("src", coverDataObj.picture);
}

function loadAlbums(data) {
	data = data.replace("\n\n","");
	data = data.replace("\n","");
	var albumsArray;
	try {
		eval("albumsArray="+data);
	}
	catch(e) {
		alert(e);
	}
	albumsArray = albumsArray.data;
	var albumIdArray = new Array();
	var album = new Array(0);
	album[0] = "<table class='albumtable'><tr>";
	for(var i=0, counter=0; i<albumsArray.length; i++) {
		if(albumsArray[i].cover_photo) {
			if(counter !=0 && counter%3==0) {
				album[album.length] = "</tr><tr>";
			}
			albumIdArray[albumIdArray.length] = albumsArray[i].id;
			album[album.length] = "<td class='albumele'>";
			//album[album.length] = "<img src='./img/home2.jpg' class='albumthumbimg' id='"+albumsArray[i].cover_photo+"'/>";
			album[album.length] = "<img src='https://graph.facebook.com/"+albumsArray[i].id+"/picture?access_token="+accessToken+"' class='albumthumbimg' id='"+albumsArray[i].cover_photo+"'/>";			
			album[album.length] = "<br/>";
			album[album.length] = "<span class='albumtitle'>"+albumsArray[i].name+"</span>";
			album[album.length] = "";
			album[album.length] = "</span></td>";
			counter++;
			//loadCoverPic(albumsArray[i].cover_photo);
		}
	}
	album[album.length] = "</tr></table>"
	$('#albums').html(album.join(""));
	$('#albumIDs').attr("value", albumIdArray.join(","));
}
