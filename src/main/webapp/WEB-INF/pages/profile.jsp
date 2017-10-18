<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                <div class="panel-body"><a class="menu-link" href="${contextPath}/bookmarks">Bookmarks</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4 float-left">
        <H3>Public profile</H3>
        <hr>

        <sf:form id="requestForm" modelAttribute="details" action="${contextPath}/profile" method="POST">
            <%--<sf:form id="requestForm" modelAttribute="details" action="${contextPath}/profile" method="POST" enctype="multipart/form-data">--%>
            <button name="submit" type="submit" id="submit" value="Submit" class="btn btn-primary">Save changes</button>
            <div class="form-group">
                <div class="font-bold">E-mail</div>
                <input class="form-control" value="${pageContext.request.userPrincipal.name}" readonly/>
                    <%--<input class="form-control" value="${userPrincipal.name}" readonly/>--%>
            </div>

            <div class="form-group">
                <sf:input path="id" cssClass="hidden"/>
                    <%--<sf:input path="user" cssClass="hidden"/>--%>
            </div>

            <div class="form-group">

                <div class="font-bold">First name</div>
                <sf:input type="text" path="firstName" class="form-control" placeholder="First name"/>

            </div>

            <div class="form-group">
                <div class="font-bold">Second name</div>
                <sf:input type="text" path="secondName" class="form-control" placeholder="Second name"/>
            </div>


            <div class="form-group">
                <div class="font-bold">Middle name</div>
                <sf:input type="text" path="middleName" class="form-control" placeholder="Middle name"/>
            </div>

            <div class="form-group">
                <spring:bind path="passportSeries">
                    <div class="font-bold">Passport series</div>
                    <sf:input type="text" path="passportSeries" name="${status.expression}"
                              value="${status.value}" class="form-control"
                              placeholder="Passport series"/>
                    <c:if test="${status.error}">
                        <c:forEach items="${status.errorMessages}" var="error">
                            <span class="red"> ${error} </span>
                        </c:forEach>
                    </c:if>
                </spring:bind>
            </div>

            <div class="form-group">
                <spring:bind path="passportNumber">
                    <div class="font-bold">Passport number</div>
                    <sf:input type="text" path="passportNumber" class="form-control" placeholder="Passport number"/>

                    <c:if test="${status.error}">
                        <c:forEach items="${status.errorMessages}" var="error">
                            <span class="red"> ${error} </span>
                        </c:forEach>
                    </c:if>
                </spring:bind>
            </div>

            <%--<div class="form-group">
                <div class="font-bold">gender</div>
                <sf:input type="text" path="gender" class="form-control" placeholder="gender"/>
            </div>
--%>
            <div class="form-group">
                <div class="font-bold">gender</div>
                <td>Gender :</td>
                <td><sf:radiobutton path="gender" value="M" label="M"/>
                    <sf:radiobutton path="gender" value="F" label="F"/></td>
            </div>


            <%--

                    <div class="form-group">
                        <div class="font-bold">issuedBy</div>
                        <sf:input type="text" path="issuedBy" class="form-control" placeholder="issuedBy"/>
                    </div>

                    <div class="form-group">
                        <div class="font-bold">dateOfIssue</div>
                        &lt;%&ndash;datetime-local&ndash;%&gt;
                        <sf:input type="date" path="dateOfIssue" class="form-control" placeholder="dateOfIssue"/>
                        &lt;%&ndash;<sf:input type="date" path="dateOfIssue" class="form-control" placeholder="dateOfIssue"/>&ndash;%&gt;
                    </div>

            --%>

            <div class="form-group">
                <div class="font-bold">ID address</div>
                <sf:input type="text" path="idAddress" class="form-control" placeholder="ID address"/>
            </div>

            <div class="form-group">
                <div class="font-bold">Phone</div>

                <td>Country :</td>

                    <%--<c:if test="${not empty message}">

                        <div class="alert alert-success alert-dismissible">

                            <button type="button" class="close" data-dismiss="alert">&times;</button>

                                ${message}
                        </div>

                    </c:if>--%>

                <td><sf:select path="phoneCountry">
                    <sf:option value="0" label="Select"/>
                    <sf:option value="+38" label="Ukraine"/>
                    <sf:option value="2" label="USA"/>
                    <sf:option value="3" label="UK"/>
                </sf:select></td>

                <td>Operator :</td>
                <td><sf:select path="phoneOperator">
                    <sf:option value="0" label="Select" placeholder="Phone"/>
                    <sf:option value="067" label="067 Kiyvstar"/>
                    <sf:option value="067" label="097 Kiyvstar"/>
                    <sf:option value="067" label="099 Life"/>
                </sf:select></td>

                <td>Number :</td>
                    <%--path="phoneNumber">--%>
                <sf:input type="text" path="phone" class="form-control"/>
            </div>

            <div class="form-group">
                <div class="font-bold">Bank ID</div>
                <sf:input type="text" path="bankId" class="form-control" placeholder="Bank ID"/>
            </div>
            <br>
            <button name="submit" type="submit" id="submit" value="Submit" class="btn btn-primary">Save changes</button>
        </sf:form>
    </div>

    <div class="col-md-3 float-left">
        <br>
        <div class="row">
            <div class="pull-right">
                <%--<img src="<c:url value="/resources/img/NoFoto.png"/> "--%>
                <img src="${contextPath}/resources/img/NoFoto.png"
                     alt="your advertisement could be here" width="200px" height="200"/>
            </div>
            <div><br></div>
            <br>
            <button class="btn btn-default pull-right">Upload foto</button>

            <div class="container">
                <c:if test="${not empty message}">

                    <div class="alert alert-success alert-dismissible">

                        <button type="button" class="close" data-dismiss="alert">&times;</button>

                            ${message}
                    </div>
                </c:if>
            </div>
        </div>

        Document

        <div class="form-group">
            <label for="file">Upload document</label>

            <%--            <spring:bind path="document.file">

                            <input type="file" path="document.file" name="${status.expression}" value="${status.value}"
                                   id="file"/>
                            <c:if test="${status.error}">
                                <c:forEach items="${status.errorMessages}" var="error">
                                    <span class="red"> ${error} </span>
                                </c:forEach>
                            </c:if>

                        </spring:bind>--%>
        </div>


    </div>


    <script src="${js}/jquery.validate.js"></script>

</div>

<!-- BEGIN JIVOSITE CODE {literal} -->
<%--<script type='text/javascript'>
    (function(){ var widget_id = 'G7wVfVFYNc';var d=document;var w=window;function l(){
        var s = document.createElement('script'); s.type = 'text/javascript'; s.async = true; s.src = '//code.jivosite.com/script/widget/'+widget_id; var ss = document.getElementsByTagName('script')[0]; ss.parentNode.insertBefore(s, ss);}if(d.readyState=='complete'){l();}else{if(w.attachEvent){w.attachEvent('onload',l);}else{w.addEventListener('load',l,false);}}})();</script>--%>
<!-- {/literal} END JIVOSITE CODE -->

</body>
</html>