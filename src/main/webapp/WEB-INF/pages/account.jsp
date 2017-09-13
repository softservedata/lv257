<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
    <title>User Info</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />

<div class="container">
    <div class="col-md-2 float-left">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">Personal settings</div>
                <div class="panel-body"><a class="menu-link" href="${pageContext.request.contextPath}/profile">Profile</a></div>
                <div class="panel-body selected">Account</div>
                <div class="panel-body"><a class="menu-link" href="${pageContext.request.contextPath}/pendingRequests">Pending requests</a></div>
                <div class="panel-body"><a class="menu-link" href="${pageContext.request.contextPath}/bookmarks">Bookmarks</a></div>
            </div>
        </div>
    </div>
    <div class="col-md-4 float-left">

        <H3>Change password</H3>
        <hr>
        <div class="row">
            <div class="font-bold">Old password</div>
            <input type="password" class="col-md-3 form-control password"/>
        </div>
        <div class="row">
            <br>
            <div class="font-bold">New password</div>
            <input type="password" class="col-md-3 form-control password"/>
        </div>
        <div class="row">
            <br>
            <div class="font-bold">Confirm new password</div>
            <input type="password" class="col-md-3 form-control password"/>
        </div>
        <div class="row">
            <br>
            <button class="btn btn-default pull-right" style="align: right;">Update password</button>
        </div>
        <div class="row">
        </div>
        <div class="row">
            <br>
            <label for="notifBox">Receive notifications <input type="checkbox" id="notifBox"/></label>
            <button class="btn btn-default pull-right">Update</button>
        </div>

        <div class="row">
            <br>

        </div>

    </div>
</div>


</body>
</html>