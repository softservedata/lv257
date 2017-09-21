<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Profile</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />

<div class="container">
    <div class="col-md-2 float-left">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">Personal settings</div>
                <div class="panel-body selected">Profile</div>
                <div class="panel-body"><a class="menu-link" href="${pageContext.request.contextPath}/account">Account</a></div>
                <div class="panel-body"><a class="menu-link" href="${pageContext.request.contextPath}/pendingRequests">Pending requests</a></div>
                <div class="panel-body"><a class="menu-link" href="${pageContext.request.contextPath}/bookmarks">Bookmarks</a></div>
            </div>
        </div>
    </div>
    <div class="col-md-4 float-left">
        <H3>Public profile</H3>
        <hr>
        <div class="row">
            <div class="font-bold">Email</div>
            <input class="form-control" value="${pageContext.request.userPrincipal.name}"/>
        </div>

        <div class="row">
            <div class="font-bold">First Name</div>
            <input class="form-control" value="${details.first_name}"/>
        </div>

        <div class="row">
            <div class="font-bold">Last Name</div>
            <input class="form-control" value="${details.second_name}"/>
        </div>

        <div class="row">
            <div class="font-bold">Middle Name</div>
            <input class="form-control" value="${details.middle_name}"/>
        </div>

        <div class="row">
            <div class="font-bold">Passport series</div>
            <input class="form-control" value="${details.passport_series}"/>
        </div>

        <div class="row">
            <div class="font-bold">Passport series</div>
            <input class="form-control" value="${details.passport_number}"/>
        </div>

        <div class="row">
            <div class="font-bold">Issued by</div>
            <input class="form-control" value="${details.issued_by}"/>
        </div>

        <div class="row">
            <div class="font-bold">Date of issue</div>
            <input class="form-control" value="${details.date_of_issue}"/>
        </div>

        <div class="row">
            <div class="font-bold">ID Bank</div>
            <input class="form-control" value="${details.bank_id}"/>
        </div>

        <div class="row">
            <br>
            <button type="submit" class="btn btn-default pull-right">Update profile</button>
        </div>
        <div class="row">
            <br>
        </div>
        <H3 class="font-bold">Document copies</H3>
        <hr>
        <div class="row">
            <span class="font-bold">Uploaded documents: </span><span id="uploadedDocsCount">0</span>
            <button class="btn btn-default pull-right">Upload</button>
            <button class="btn btn-default pull-right disabled">Review</button>
        </div>

    </div>
    <div class="col-md-3 float-left">
        <br>
        <div class="row">
            <div class="pull-right">
                <img src="<c:url value="/resources/img/NoFoto.png"/> "
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