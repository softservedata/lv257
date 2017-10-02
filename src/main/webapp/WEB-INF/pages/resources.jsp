<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@page session="true"%>--%>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />



<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
            <div class="container">

                <ul class="nav nav-tabs">
                    <li ><a href="${pageContext.request.contextPath}/resources/registration">Register resource</a></li>
                    <li id="sendRequest"><a href="${pageContext.request.contextPath}/resources/request">Send request</a></li>
                    <li ><a href="${pageContext.request.contextPath}/resources/story">History</a></li>
                </ul>

            </div>
        </div>
    </div>
</div>

                <br>
                <br>



</body>
</html>