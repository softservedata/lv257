<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Profile</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp"/>

<div class="container">
    <div class="col-md-4 float-left">
        <H3>Public profile</H3>
        <hr>

<%--        <sf:form sf:action="${pageContext.request.contextPath}/profile" sf:object="${userDetails}" modelAttribute="userDetails" method="post">
        <p><input type="submit" value="Submit"/><input type="reset" value="Reset"/></p>--%>

        <%--<sf:form id="requestForm" modelAttribute="userDetails" action="${pageContext.request.contextPath}/profile" method="POST">--%>

        <sf:form id="requestForm" modelAttribute="details" action="${pageContext.request.contextPath}/profile" method="POST">
        <button name="submit" type="submit" id="submit" value="Submit" class="btn btn-primary" >Send request</button>
        <%--<p><sf:input type="submit" value="Submit" path="first_name"/><input type="reset" value="Reset"/></p>--%>

        <div class="form-group">
            <sf:input path="id" cssClass="hidden"/>
            <sf:input type="text" path="firstName" class="form-control" placeholder="name"/>
        </div>

<%--        <div class="row">
            <div class="font-bold">Email</div>

            <sf:input name="id" class="form-control" value="${pageContext.request.userPrincipal.name}"/>

            &lt;%&ndash;<sf:input type="text" path="first_name" class="form-control" placeholder="Enter details"/>&ndash;%&gt;
            &lt;%&ndash;<sf:input type="text" path="description" class="form-control" placeholder="Enter details"/>&ndash;%&gt;
        </div>

       <div class="row">
            <div class="font-bold">First Name</div>
            <sf:input name="first_name" class="form-control" value="${details1.first_name}"/>
        </div>

        <div class="row">
            <div class="font-bold">Last Name</div>
            <sf:input name="second_name" class="form-control" value="${details1.second_name}"/>
        </div>

        <div class="row">
            <div class="font-bold">Middle Name</div>
            <sf:input name="middle_name" class="form-control" value="${details1.middle_name}"/>
        </div>--%>
    </div>

</div>
</sf:form>

</body>
</html>