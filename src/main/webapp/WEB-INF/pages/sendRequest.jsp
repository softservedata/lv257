<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Request</title>
</head>
<body>
<jsp:include page="_menu2.jsp" />
<jsp:include page="resources.jsp" />


<div class="container-fluid">
    <div class="row">

        <c:if test="${not empty message}">

        <div class="col-xs-12">

            <div class="alert alert-success alert-dismissible">

                <button type="button" class="close" data-dismiss="alert">&times;</button>

                ${message}

            </div>
        </div>
        </c:if>

        <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
            <div class="container">

                <%--@elvariable id="request" type=""--%>
                <sf:form modelAttribute="request" action="${pageContext.request.contextPath}/resources/request" method="POST">

                    <div class="form-group">
                        <label>Requested category</label>
                        <sf:input type="text" path="theme" class="form-control" placeholder="Enter new kind of resource"/>
                    </div>


                    <div class="form-group">
                        <label>Details</label>
                        <sf:input type="text" path="details" class="form-control" placeholder="Enter details"/>
                        <%--<label class="control-label col-md-4" for="theme">Details about new resource</label>--%>
                        <%--<sf:textarea class="form-control" path="details" id="details"--%>
                                     <%--placeholder="Enter details about new resource" rows="3"/>--%>
                    </div>

                    <%--<div class="form-group">--%>
                        <%--<label for="exampleInputFile">Upload document</label>--%>
                        <%--<input type="file"  class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp">--%>
                    <%--</div>--%>

                    <%--<sf:hidden path="id"/>--%>
                    <%--<sf:hidden path="register"/>--%>
                    <%--<sf:hidden path="resourcesAdmin"/>--%>
                    <%--<sf:hidden path="document"/>--%>
                    <%--<sf:hidden path="status"/>--%>
                    <%--<sf:hidden path="update"/>--%>
                    <%--<sf:hidden path="message"/>--%>
                    <%--<sf:hidden path="notifyExecutor"/>--%>


                <button name="submit" type="submit" id="submit" value="Submit" class="btn btn-primary" >Send request</button>
                </sf:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
