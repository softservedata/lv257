<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<c:set var="req" value="${pageContext.request}" />
	<c:set var="url">${req.requestURL}</c:set>
	<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}/" />
	<c:set var="context" value="${ req.contextPath  }" />

<html>
<head>
	<!--
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	  -->
	<tiles:insertAttribute name="meta" />
</head>
<body>
	<br/>FIRST_PAGE_MESSAGE: <br />
		<h1>Attribute_firstPageMessage: <%= request.getAttribute("firstPageMessage") %></h1>
	<br/>
		<h1>URL: <%= request.getRequestURL() %></h1>
	<br/>
		<h1>URI: <%= request.getRequestURI() %></h1>
	<br/>
		context: <c:out value="${context}" />
	<br/>
		base: <c:out value="${base}" />
	<br/>
	<div id="header"> 
		<tiles:insertAttribute name="header" /> 
	</div> 
	<div id="body"> 
		<tiles:insertAttribute name="body" /> 
	</div> 
	<div id="footer"> 
		<tiles:insertAttribute name="footer" /> 
	</div> 

</body>
</html>
