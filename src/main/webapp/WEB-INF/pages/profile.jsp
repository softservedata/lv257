<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"/>

<spring:url var="js" value="${contextPath}/resources/js"/>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="menu.jsp"/>

<img src="${contextPath}/resources/img/ajax-loader.gif" id="img" style="display:none"/>

<style>
    .red {
        color: red;
    }
</style>

<div class="container">
    <div class="col-md-2 float-left">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">Personal settings</div>
                <div class="panel-body selected">Profile</div>
                <div class="panel-body"><a class="menu-link"
                                           href="${contextPath}/account">Account</a></div>
                <div class="panel-body"><a class="menu-link" href="${contextPath}/pendingRequests">Pending
                    requests</a></div>
                <div class="panel-body"><a class="menu-link" href="${contextPath}/profileAvatar">profileAvatar</a>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-4 float-left">
        <H3>Public profile</H3>
        <hr>
        <sf:form id="profileForm" modelAttribute="details" action="${contextPath}/profile" method="POST"
                 enctype="multipart/form-data">

            <button name="submit" type="submit" id="submit" value="Submit" class="btn btn-primary">Save changes</button>
            <br><br>
            <div class="form-group">
                <div class="font-bold">E-mail</div>
                <input class="form-control" value="${pageContext.request.userPrincipal.name}" readonly/>
            </div>

            <div class="form-group">
                <sf:input path="id" cssClass="hidden"/>
            </div>

            <%--Field for First name--%>
            <div class="form-group">
                <label for="firstName" style="float: left;">First name</label>
                <sf:input type="text"
                          path="firstName"
                          name="${status.expression}"
                          value="${status.value}"
                          class="form-control"
                          placeholder="firstName"/>
                <c:if test="${status.error}">
                    <c:forEach items="${status.errorMessages}" var="error">
                        <span class="red"> ${error} </span>
                    </c:forEach>
                </c:if>

            </div>

            <%--Field for Second name--%>
            <div class="form-group">
                <label for="secondName" style="float: left;">Second name</label>
                <sf:input type="text"
                          path="secondName"
                          name="${status.expression}"
                          value="${status.value}"
                          class="form-control"
                          placeholder="passportSeries"/>
                <c:if test="${status.error}">
                    <c:forEach items="${status.errorMessages}" var="error">
                        <span class="red"> ${error} </span>
                    </c:forEach>
                </c:if>
            </div>

            <%--Field for Middle name--%>
            <div class="form-group">
                <label for="middleName" style="float: left;">Middle name</label>
                <sf:input type="text"
                          path="secondName"
                          name="${status.expression}"
                          value="${status.value}"
                          class="form-control"
                          placeholder="passportSeries"/>
                <c:if test="${status.error}">
                    <c:forEach items="${status.errorMessages}" var="error">
                        <span class="red"> ${error} </span>
                    </c:forEach>
                </c:if>
            </div>

            <%--Field for Passport series--%>
            <div class="form-group">
                <label for="passportSeries" style="float: left;">Passport series</label>
                <sf:input type="text"
                          path="passportSeries"
                          name="${status.expression}"
                          value="${status.value}"
                          class="form-control"
                          placeholder="passportSeries"/>
                <c:if test="${status.error}">
                    <c:forEach items="${status.errorMessages}" var="error">
                        <span class="red"> ${error} </span>
                    </c:forEach>
                </c:if>
            </div>

            <%--Field for Passport number--%>
            <div class="form-group">
                <label for="passportNumber" style="float: left;">Passport number</label>
                <sf:input type="text"
                          path="passportNumber"
                          name="${status.expression}"
                          value="${status.value}"
                          class="form-control"
                          placeholder="passportNumber"/>
                <c:if test="${status.error}">
                    <c:forEach items="${status.errorMessages}" var="error">
                        <span class="red"> ${error} </span>
                    </c:forEach>
                </c:if>
            </div>

            <%--Field for Phone number--%>
            <div class="form-group">
                <label for="phone" style="float: left;">Phone number</label>
                <sf:input type="text"
                          path="phone"
                          name="${status.expression}"
                          value="${status.value}"
                          class="form-control"
                          placeholder="phone"/>
                <c:if test="${status.error}">
                    <c:forEach items="${status.errorMessages}" var="error">
                        <span class="red"> ${error} </span>
                    </c:forEach>
                </c:if>
            </div>
        </sf:form>
    </div>

    <div class="col-md-3 float-left">
        <br>
        <div class="row">
            <div class="pull-right">
                <img src="${contextPath}/resources/img/NoFoto.png"
                     alt="your foto should be here" width="200px" height="200"/>
            </div>

        </div>
        <div><br></div>
        <div class="form-group">
            <sf:form id="profileFormFile" modelAttribute="details" action="${contextPath}/profileFile" method="POST"
                     enctype="multipart/form-data">
                <label for="file">Upload document</label>
                <spring:bind path="document.file">
                    <input type="file" path="document.file" name="${status.expression}" value="${status.value}"
                           id="file"/>
                    <c:if test="${status.error}">
                        <c:forEach items="${status.errorMessages}" var="error">
                            <span class="red"> ${error} </span>
                        </c:forEach>
                    </c:if>
                </spring:bind>
                <br>
                <button name="submit" type="submit" id="submit" value="Submit" class="btn btn-primary">Save File
                </button>
            </sf:form>
        </div>
    </div>

    <script src="${js}/jquery.validate.js"></script>
    <script src="${js}/profileValidate.js"></script>
    <%--<script src="${js}/messageTimeOut.js"></script>--%>

    <script>
        $('#submit').click(function () {
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

<!-- BEGIN JIVOSITE CODE {literal} -->
<%--<script type='text/javascript'>
    (function(){ var widget_id = 'G7wVfVFYNc';var d=document;var w=window;function l(){
        var s = document.createElement('script'); s.type = 'text/javascript'; s.async = true; s.src = '//code.jivosite.com/script/widget/'+widget_id; var ss = document.getElementsByTagName('script')[0]; ss.parentNode.insertBefore(s, ss);}if(d.readyState=='complete'){l();}else{if(w.attachEvent){w.attachEvent('onload',l);}else{w.addEventListener('load',l,false);}}})();</script>--%>
<!-- {/literal} END JIVOSITE CODE -->
</body>
</html>