<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- <script type="text/javascript" src="js/images.js"></script> -->

<script type="text/javascript">
$(document).ready(function(){

	$(".images a").click(function(){
	
		var largePath = $(this).attr("href");
		
		$("#largeImg").attr({ src: largePath});
		
	return false;
	});
	
});
</script>
</head>
<body>

	<c:forEach items="${imagesJsp}" var="image">
		<img src="images/${image.imageName}" width="150" height="150" border="2" />
	</c:forEach>

	<p>
		<img id="largeImg" src="" >
	</p>

	<p class = "images">
		<c:forEach items="${imagesJsp}" var="image">
			<a href="images/${image.imageName}" >
				<img src="images/${image.imageName}" width="150" height="150" border="2" />
			</a>
		</c:forEach>
	</p>
	
	
	<form method="POST" action="uploadImage" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"><br /> 
		
                <input type="submit" value="Upload"> 
		Press here to upload the file!
	</form>
</body>
</html>