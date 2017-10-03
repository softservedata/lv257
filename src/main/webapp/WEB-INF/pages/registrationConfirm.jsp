<html>
<head>
    <title>Successful user creation</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp"/>


<div>

    <%--<h1 class="alert alert-info" th:text="#{message.regSucc}">success</h1>--%>
    <%--<br/>--%>
    <%--<span th:if="${param.token != null}" th:text="#{token.message}">token</span>--%>
    <%--<span th:text="${param.token}">token</span>--%>
    <%--<br/>--%>
    <a class="btn btn-primary" th:href="@{/login}" th:text="#{label.login}">login</a>
    <h1>Your registration confirmed</h1>
</div>
</body>
</html>