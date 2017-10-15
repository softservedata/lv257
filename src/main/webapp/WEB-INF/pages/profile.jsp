<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"/>

<html>
<head>
    <title>Profile</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="menu.jsp"/>

<div class="container">
    <div class="col-md-2 float-left">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">Personal settings</div>
                <div class="panel-body selected">Profile</div>
                <div class="panel-body"><a class="menu-link"
                                           href="${contextPath}/account">Account</a></div>
                <div class="panel-body"><a class="menu-link" href="${contextPath}/pendingRequests">Pending
                    requests</a></div>
                <div class="panel-body"><a class="menu-link" href="${contextPath}/bookmarks">Bookmarks</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4 float-left">
        <H3>Public profile</H3>
        <hr>

        <sf:form id="requestForm" modelAttribute="details" action="${pageContext.request.contextPath}/profile"
                 method="POST">

            <div class="form-group">
                <div class="font-bold">E-mail</div>
                <input class="form-control" value="${pageContext.request.userPrincipal.name}" readonly/>
            </div>

            <div class="form-group">
                <sf:input path="id" cssClass="hidden"/>
            </div>

            <div class="form-group">
                <div class="font-bold">First name</div>
                <sf:input type="text" path="firstName" class="form-control" placeholder="First name"/>
            </div>

            <div class="form-group">
                <div class="font-bold">Second name</div>
                <sf:input type="text" path="secondName" class="form-control" placeholder="Second name"/>
            </div>

            <div class="form-group">
                <div class="font-bold">Middle name</div>
                <sf:input type="text" path="middleName" class="form-control" placeholder="Middle name"/>
            </div>

            <div class="form-group">
                <div class="font-bold">Passport series</div>
                <sf:input type="text" path="passportSeries" class="form-control" placeholder="Passport series"/>
            </div>

            <div class="form-group">
                <div class="font-bold">Passport number</div>
                <sf:input type="text" path="passportNumber" class="form-control" placeholder="Passport number"/>
            </div>
            <%--

                    <div class="form-group">
                        <div class="font-bold">issuedBy</div>
                        <sf:input type="text" path="issuedBy" class="form-control" placeholder="issuedBy"/>
                    </div>

                    <div class="form-group">
                        <div class="font-bold">dateOfIssue</div>
                        &lt;%&ndash;datetime-local&ndash;%&gt;
                        <sf:input type="date" path="dateOfIssue" class="form-control" placeholder="dateOfIssue"/>
                        &lt;%&ndash;<sf:input type="date" path="dateOfIssue" class="form-control" placeholder="dateOfIssue"/>&ndash;%&gt;
                    </div>

            --%>

            <div class="form-group">
                <div class="font-bold">ID address</div>
                <sf:input type="text" path="idAddress" class="form-control" placeholder="ID address"/>
            </div>

            <div class="form-group">
                <div class="font-bold">Phone</div>
                <sf:input type="text" path="phone" class="form-control" placeholder="Phone"/>
            </div>

            <div class="form-group">
                <div class="font-bold">Bank ID</div>
                <sf:input type="text" path="bankId" class="form-control" placeholder="Bank ID"/>
            </div>
            <br>
            <button name="submit" type="submit" id="submit" value="Submit" class="btn btn-primary">Save changes</button>
        </sf:form>
    </div>

    <div class="col-md-3 float-left">
        <br>
        <div class="row">
            <div class="pull-right">
                <%--<img src="<c:url value="/resources/img/NoFoto.png"/> "--%>
                    <img src="${contextPath}/resources/img/NoFoto.png"
                     alt="your advertisement could be here" width="200px" height="200"/>
            </div>
            <div><br></div>
            <br>
            <button class="btn btn-default pull-right">Upload foto</button>

            <div class="container">
                <c:if test="${not empty message}">

                    <div class="alert alert-success alert-dismissible">

                        <button type="button" class="close" data-dismiss="alert">&times;</button>

                            ${message}
                    </div>
                </c:if>
            </div>
        </div>
    </div>

</div>

</body>
</html>