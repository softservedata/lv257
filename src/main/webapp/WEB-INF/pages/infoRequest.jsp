<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"></jsp:include>

</head>
<body>


<jsp:include page="resources.jsp" />
<br>

<div class="container">

    <h4>Requested resource type: ${theme}</h4>

    <h4>Description: ${info}</h4>
    <h4>Uploaded Document</h4>
    <hr>
    <c:if test = "${extension == 'jpeg'}">
    <div>
        <img class="documentImg-view" src="/resources/upload/${code}.jpg">
    </div>
    </c:if>
    <c:if test = "${extension == 'pdf'}">
    <div>
        <embed class="documentPdf-view" src="/resources/upload/${code}.pdf">
    </div>
    </c:if>
    <c:if test = "${extension == 'png'}">
        <div>
            <img class="documentImg-view" src="/resources/upload/${code}.png">
        </div>
    </c:if>
    <hr>
    <br>
    <button class="btn btn-primary" name="back" onclick="history.back()">Back</button>
</div>


</body>
</html>

