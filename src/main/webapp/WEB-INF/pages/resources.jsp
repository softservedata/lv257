<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>






                <ul class="nav nav-tabs">
                    <li ><a href="${pageContext.request.contextPath}/resources/registration">Register resource</a></li>
                    <li id="sendRequest"><a href="${pageContext.request.contextPath}/resources/request">Send request</a></li>
                    <li ><a href="${pageContext.request.contextPath}/resources/story">History</a></li>
                </ul>



                <br>
                <br>



</body>
</html>