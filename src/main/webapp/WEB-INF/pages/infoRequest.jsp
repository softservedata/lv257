<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"></jsp:include>

</head>
<body>

<jsp:include page="_menu2.jsp"/>
<jsp:include page="resources.jsp" />
<br>


<div class="container">

    <h3>Additional information</h3>
    <p>${info}</p>
    <h3> Uploaded Document </h3>
    <hr>

    <img class="document-view" src="/resources/upload/${code}.jpg">
    <hr>
    <br />
    <button class="btn btn-primary" name="back" onclick="history.back()">Back</button>
</div>

</body>
</html>

