<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body> 
		<tiles:insertAttribute name="body" />
</body>
</html>