<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <script type="text/javascript" src="js/images.js"></script>
 -->
</head>
<body >

 	 <c:forEach items="${imagesJsp}" var="image">
 	 		<img src="images/${image.imageName}" width="150" height="150" border="2"/>
	</c:forEach>
	

	
</body>
</html>