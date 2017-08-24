<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">

    <a href="${pageContext.request.contextPath}/welcome">Home</a>

    | &nbsp;

    <a href="${pageContext.request.contextPath}/userInfo">User Info</a>

    | &nbsp;

    <a href="${pageContext.request.contextPath}/admin">Admin</a>

    <c:if test="${pageContext.request.userPrincipal.name != null}">

        | &nbsp;
        <a href="${pageContext.request.contextPath}/logout">Logout</a>

    </c:if>

</div>--%>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" rel="home" href="${pageContext.request.contextPath}/welcome"
               title="Project Resources"> <img
                    style="max-width: 100px; margin-top: -10px;"
                    src="<c:url value="resources-logo.png"/> ">
            </a>
            <a class="navbar-brand" rel="home" href="${pageContext.request.contextPath}/welcome"
               title="Project Resources"> <img style="max-width: 100px"
                                               src="<c:url value="text.png"/> "></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="LookUpResources.html">Look up</a></li>
                <li><a href="ManageUsersAndRoles.html">Users</a></li>
                <li><a href="ResourcesView.html">Resources</a></li>
                <li><a href="CommunityView.html">Community</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="${pageContext.request.contextPath}/userInfo">User Info</a></li>
                <li><a href="${pageContext.request.contextPath}/admin">Admin Page</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li><a href="signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li><a href="${pageContext.request.contextPath}/userInfo"><span class="glyphicon glyphicon-user"></span> Signed as ${pageContext.request.userPrincipal.name}</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                </c:if>

                <li>
                <li class="language-panel"><a href="" class="dropdown-toggle"
                                              data-toggle="dropdown" role="button" aria-haspopup="true"
                                              aria-expanded="false"><span class="glyphicon glyphicon-globe"></span>
                    Language<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="">English</a></li>
                        <li><a href="">Ukrainian</a></li>
                        <li><a href="">Russian</a></li>
                    </ul></li>
            </ul>
        </div>
    </div>
</nav>
<br/>