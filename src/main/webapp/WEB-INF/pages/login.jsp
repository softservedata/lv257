<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head><title>Login</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="menu.jsp" />

<br/>
<br>

<div class="container-fluid">
    <div class="row" style="text-align: left;">

        <div class="col-md-8" style="display: inline-block; text-align: center;float: left">

            <br>
            <h1>Welcome to Resources</h1>
            <h5>Resources is non-commercial government service for managing and keeping information</br> about resources registered and located in Ukraine</h5>


<br>
            <img width="100%" height="auto" src="/resources/img/welcome.png">
        </div>


        <div class="col-md-4" style="float: left;">
    <center>
        <form style="border: 1px groove gray; border-radius: 5px; padding: 5px; width: 280px" name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
<br>
            <div class="form-group" style="padding-left: 5px; padding-right: 5px; width: 250px;">
                <label for="email" style="float: left;">Email address:</label>
                <input type="text" class="form-control" id="email" placeholder="Enter email" name="username">
            </div>
            <div class="form-group" style="padding-left: 5px; padding-right: 5px; width: 250px;">
                <label for="pwd" style="float: left;">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
            </div>
            <center>
                <button type="submit" class="btn btn-default">Sing in</button>
            </center>
<br>
        </form>
        <!-- /login?error=true -->
        <c:if test="${param.error == 'true'}">
            <div style="color:red;margin:10px 0px;">

                Login Failed!!!<br />
                Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

            </div>
        </c:if>

    </div>
    </div>
</div>
<jsp:include page="${contextPath}footer.jsp"/>

</body>
</html>