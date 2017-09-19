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

    <h4>Requested category: ${theme}</h4>

    <h4>Additional information: ${info}</h4>
    <h4>Uploaded Document</h4>
    <hr>
    <div>
        <img class="documentImg-view" src="/resources/upload/${code}.jpg"></div>
    <%--<div>--%>
        <%--<embed class="documentPdf-view" src="/resources/upload/${code}.pdf">--%>
    <%--</div>--%>


    <hr>
    <br>
    <button class="btn btn-primary" name="back" onclick="history.back()">Back</button>
</div>


</body>
</html>

