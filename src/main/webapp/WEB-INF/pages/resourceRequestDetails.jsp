<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.09.2017
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"></jsp:include>

</head>
<body>

<jsp:include page="_menu2.jsp"/>
<br>


<div class="container">

    <h3>Additional information</h3>
    <p>${info}</p>
    <h3>Document example</h3>
    <p>

    <hr>
    <div>
       ${file}
    </div>
    <hr>
    </p>
    <br />
    <button class="btn btn-primary" name="back" onclick="history.back()">back</button>
</div>





</body>
</html>


