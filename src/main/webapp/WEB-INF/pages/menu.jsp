<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
            <a class="navbar-brand" rel="home" href="${pageContext.request.contextPath}/"
               title="Project Resources"> <img
                    style="max-width: 100px; margin-top: -10px;"
                    src="<c:url value="/resources/img/resources-logo.png"/> ">
            </a>
            <a class="navbar-brand" rel="home" href="${pageContext.request.contextPath}/"
               title="Project Resources"> <img style="max-width: 100px"
                                               src="<c:url value="/resources/img/text.png"/> "></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/lookup">Look up</a></li>
                <li><a href="${pageContext.request.contextPath}/resources">Resources</a></li>
                <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                    <li><a href="${pageContext.request.contextPath}/users">Administration</a></li>
                </c:if>
                <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                <li><a href="${pageContext.request.contextPath}/endPoints">ENDPOINTS !</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                    <li><li/>
                    <li class="language-panel"><a href="" class="dropdown-toggle"
                                                  data-toggle="dropdown" role="button" aria-haspopup="true"
                                                  aria-expanded="false"><span class="glyphicon glyphicon-globe"></span>
                        Language<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="">English</a></li>
                            <li><a href="">Ukrainian</a></li>
                            <li><a href="">Russian</a></li>
                        </ul>
                    </li>

                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li><a href="${pageContext.request.contextPath}/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li class="Account"><a href="" class="dropdown-toggle"
                                           data-toggle="dropdown" role="button" aria-haspopup="true"
                                           aria-expanded="false"><span class="glyphicon glyphicon-user"></span>
                        Profile <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-header">Signed in as ${pageContext.request.userPrincipal.name}</li>
                            <li role="separator" class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/profile">Your profile</a></li>
                            <li><a href="">Settings</a></li>
                            <li><a href="">Help</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/logout">Sign out</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<br/>
