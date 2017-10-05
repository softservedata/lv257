<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <h2>Details</h2>

    <h3>Requested resource type:<span> ${theme}</span></h3>

    <h3>Description:<span> ${info}</span> </h3>
    <h3>Uploaded Document:</h3>
    <hr>
    <c:if test = "${extension == 'jpeg'}">
    <div>
        <img class="documentImg-view" src="${documentURL}">
    </div>
    </c:if>
    <c:if test = "${extension == 'pdf'}">
    <div>
        <iframe style="width: 100%; height: 600px; border: none;" src="${documentURL}"></iframe>
        <%--<embed class="documentPdf-view" src="${documentURL}">--%>
    </div>
    </c:if>
    <c:if test = "${extension == 'png'}">
        <div>
            <img class="documentImg-view" src="${documentURL}">
        </div>
    </c:if>
    <hr>
    <br>
    <button class="btn btn-primary" name="back" onclick="history.back()">Back</button>
</div>


</body>
</html>

