<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head><title>Login</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />

<br/>

<br><br><br><br>
<div class="container-fluid">
    <center>
        <form style="border: 1px groove gray; border-radius: 5px; padding: 5px; width: 450px" name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
            <br>
            <div class="form-group" style="padding-left: 5px; padding-right: 5px; width: 400px;">
                <label for="email" style="float: left;">Email address:</label>
                <input type="text" class="form-control" id="email" placeholder="Enter email" name="username">
            </div><br>
            <div class="form-group" style="padding-left: 5px; padding-right: 5px; width: 400px;">
                <label for="pwd" style="float: left;">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
            </div><br>
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
    </center>
</div>
</body>
</html>