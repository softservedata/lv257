<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page session="true" %>

<html>
<head>
    <title>User Info</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="menu.jsp"/>

<div class="container">
    <div class="col-md-2 float-left">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">Personal settings</div>

                <div class="panel-body"><a class="menu-link"
                                           href="${contextPath}/profile">Profile</a></div>
                <div class="panel-body selected">Change password</div>
                <%--                <div class="panel-body"><a class="menu-link"
                                                           href="${contextPath}/users1">All Users 1 (+button "Add new user")</a></div>--%>
                <div class="panel-body"><a class="menu-link"
                                           href="${contextPath}/users">All Users<%-- 2 (new View - Spring Table)--%></a>
                </div>
                <%--                <div class="panel-body"><a class="menu-link"
                                                           href="${contextPath}/changePassword">Change password</a></div>--%>

                <div class="panel-body"><a class="menu-link"
                                           href="${contextPath}/roles">Roles</a></div>
                <%--                <div class="panel-body"><a class="menu-link"
                                                           href="${contextPath}/changeRole">Change role</a></div>--%>
                <%--                <div class="panel-body"><a class="menu-link" href="${contextPath}/pendingRequests">Pending
                                    requests</a></div>--%>
                <%--                <div class="panel-body"><a class="menu-link" href="${contextPath}/profileAvatar">profileAvatar</a>
                                </div>--%>
            </div>
        </div>
    </div>
    <div class="col-md-4 float-left">

        <sf:form id="changePasswordForm" modelAttribute="passwordDTO" action="${contextPath}/changePassword"
                 method="POST">
            <H3>Change password</H3>
            <hr>
            <div class="row">
                <div class="font-bold">Old password</div>
                <input type="password" name="passwordOld"/>
            </div>

            <div class="row">
                <br>
                <div class="font-bold">New password</div>
                <input type="password" name="newPassword1"/>
                    <%--<input type="password" name="newPassword1" &lt;%&ndash;value="${NewPassword1}&ndash;%&gt;"/>--%>
                    <%--<input type="password" class="col-md-3 form-control password"/>--%>
            </div>

            <div class="row">
                <br>
                <div class="font-bold">Confirm new password</div>
                <input type="password" name="newPassword2"/>
                    <%--<input type="password" name="newPassword2" value="${NewPassword2}"/>--%>
            </div>
            <div class="row">
                <br>
                    <%--<button class="btn btn-default pull-right" style="align: right;">Update password</button>--%>
                <%--<button name="submit" type="submit" id="submit" value="Submit" class="btn btn-primary">Update password
                </button>--%>

                <input type="submit" value="send"/>
            </div>

        </sf:form>
        <%--        <div class="row">
                </div>
                <div class="row">
                    <br>
                    <label for="notifBox">Receive notifications <input type="checkbox" id="notifBox"/></label>
                    <button class="btn btn-default pull-right">Update</button>
                </div>--%>

        <div class="row">
            <br>

        </div>

    </div>
</div>


</body>
</html>