<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<%--<spring:url var="js" value="${contextPath}/resources/js"/>--%>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>

    <style>
        #img {
            position:fixed;
            top: 40%;
            left: 50%;
            width:12em;
            height:12em;
            margin-top: -6em; /*set to a negative number 1/2 of your height*/
            margin-left: -6em; /*set to a negative number 1/2 of your width*/
            z-index: 99
        }
    </style>
</head>
<body>
<jsp:include page="menu.jsp"/>

<img src="${contextPath}/resources/img/ajax-loader.gif" id="img" style="display:none"/>

<style>
    .red    {
        color: red;
    }
</style>

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">

            <div class="container">

                <ul class="nav nav-tabs">
                    <li><a href="${contextPath}/resources/registration">Register
                        resource</a></li>
                    <li class="active"><a href="${contextPath}/resources/request">Send request</a></li>
                    <li><a href="${contextPath}/resources/story">History</a></li>
                </ul>
                <br>

                <c:if test="${not empty message}">

                    <div class="alert alert-success alert-dismissible">

                        <button type="button" class="close" data-dismiss="alert">&times;</button>

                            ${message}
                    </div>

                </c:if>
                <%--@elvariable id="request" type=""--%>
                <form id="requestForm" action="${contextPath}/resources/request"
                         method="POST" enctype="multipart/form-data">

                    <div class="form-group" >
                        <spring:bind path="mRequest.resourceName">

                           <label>Requested resource type</label>
                           <input type="text" path="mRequest.resourceName"  name="${status.expression}" value="${status.value}" class="form-control" placeholder="Enter new kind of resource"/>
                           <c:if test="${status.error}">
                               <c:forEach items="${status.errorMessages}" var="error">

                                   <span class="red"> ${error} </span>

                           </c:forEach>
                           </c:if>

                       </spring:bind>
                       </div>

                       <div class="form-group">
                       <spring:bind path="mRequest.description">

                           <label>Description</label>
                           <input type="text" path="mRequest.description" name="${status.expression}" value="${status.value}" class="form-control" placeholder="Enter details"/>

                           <c:if test="${status.error}">
                               <c:forEach items="${status.errorMessages}" var="error">
                           <span class="red"> ${error} </span>
                               </c:forEach>
                           </c:if>

                       </spring:bind>
                       </div>

                       <div class="form-group">
                           <label for="file">Upload document</label>

                       <spring:bind path="document.file">

                           <input type="file" path="document.file" name="${status.expression}" value="${status.value}" id="file"/>
                           <c:if test="${status.error}">
                               <c:forEach items="${status.errorMessages}" var="error" >
                                   <span class="red"> ${error} </span>
                               </c:forEach>
                           </c:if>

                       </spring:bind>
                       </div>
                       <br>

                   <button name="submit" type="submit" id="submit" value="Submit" class="btn btn-primary" >Send request</button>

                   </form>


               </div>
           </div>
       </div>


       <script src="${contextPath}/resources/js/jquery.validate.js"></script>

       <script src="${contextPath}/resources/js/messageTimeOut.js"></script>
       <%--<script src="${js}/messageTimeOut.js"></script>--%>

       <script>
           $('#submit').click(function() {
               $('#img').show(); //<----here
               $.ajax({
                   type: "POST",
                   url: projectPathPrefix + "spinnerRequest",
                   accept: "application/json",
                   data: {id: id},
                   success: function (result) {
                       $('#img').hide();  //<--- hide again
                   }
               })
           })
       </script>
   </div>

   </body>

   </html>
