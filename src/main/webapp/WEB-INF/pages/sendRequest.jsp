<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:url var="js" value="/resources/js"></spring:url>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />
<jsp:include page="resources.jsp" />


<div class="container-fluid">
    <div class="row">

        <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">

            <div class="container">
                <c:if test="${not empty message}">

                    <div class="alert alert-success alert-dismissible">

                        <button type="button" class="close" data-dismiss="alert">&times;</button>

                            ${message}
                    </div>

                </c:if>
                <%--@elvariable id="request" type=""--%>
                <sf:form id="requestForm" modelAttribute="request" action="${pageContext.request.contextPath}/resources/request"
                         method="POST" enctype="multipart/form-data">

                    <div class="form-group">
                        <label>Requested category</label>
                        <sf:input type="text" path="resourceType" class="form-control" placeholder="Enter new kind of resource"/>
                        <sf:errors path="resourceType" cssClass="help-block" element="em" cssStyle="color: #ff0000;"></sf:errors>
                    </div>


                    <div class="form-group">
                        <label>Details</label>
                        <sf:input type="text" path="description" class="form-control" placeholder="Enter details"/>
                        <sf:errors path="description" cssClass="help-block" element="em" cssStyle="color: #ff0000;"></sf:errors>
                        <%--<label class="control-label col-md-4" for="theme">Details about new resource</label>--%>
                        <%--<sf:textarea class="form-control" path="details" id="details"--%>
                                     <%--placeholder="Enter details about new resource" rows="3"/>--%>
                    </div>


                        <label for="file">Upload document</label>

                        <sf:input type="file" path="file" id="file" class="form-control"/>
                        <sf:errors path="file" cssClass="help-block errHighlight"
                                   element="em" cssStyle="color: #ff0000;"/>


                    <br>
                    <br>
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


    <script src="${js }/jquery.validate.js"></script>

    <script src="${js }/messageTimeOut.js"></script>
</div>
</body>
</html>
