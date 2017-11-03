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
                <div class="panel-body"><a class="menu-link" href="${contextPath}/bookmarks">Bookmarks</a>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-4 float-left">
        <%--@declare id="passportseries"--%><H3>Public profile </H3>
        <hr>

        <sf:form id="profileForm" modelAttribute="details" action="${contextPath}/profile" method="POST" enctype="multipart/form-data">
            <%--<sf:form id="profileForm" modelAttribute="details" action="${contextPath}/profile" method="POST" enctype="multipart/form-data">--%>
            <button name="submit" type="submit" id="submit" value="Submit" class="btn btn-primary">Save changes</button>
            <div class="form-group">
                <div class="font-bold">E-mail</div>
                <input class="form-control" value="${pageContext.request.userPrincipal.name}" readonly/>
            </div>

            <div class="form-group">
                <sf:input path="id" cssClass="hidden"/>
                    <%--<sf:input path="user" cssClass="hidden"/>--%>
            </div>

            <%--Field for First name--%>
            <div class="form-group">
                    <%--
                                    <div class="font-bold">First name</div>
                                    <sf:input type="text" path="firstName" class="form-control" placeholder="First name"/>--%>

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
            <div class="form-group"><%--
                <div class="font-bold">Second name</div>
                <sf:input type="text" path="secondName" class="form-control" placeholder="Second name"/>--%>

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
            <div class="form-group"><%--
                <div class="font-bold">Middle name</div>
                <sf:input type="text" path="middleName" class="form-control" placeholder="Middle name"/>--%>

                <label for="middleName" style="float: left;">Passport series</label>
                <sf:input type="text"
                          path="middleName"
                          name="${status.expression}"
                          value="${status.value}"
                          class="form-control"
                          placeholder="Middle name"/>
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

            <div class="form-group">
                <div class="font-bold">gender</div>
                <td>Gender :</td>
                <td><sf:radiobutton path="gender" value="M" label="M"/>
                    <%--<sf:radiobutton path="gender" value="F" label="F"/>--%>
                    <%--<sf:radiobutton path="gender" value="Other" label="Other"/>--%>
                </td>
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

            <%--            <div class="form-group">
                            <div class="font-bold">ID address</div>
                            <sf:input type="text" path="idAddress" class="form-control" placeholder="ID address"/>
                        </div>--%>

            <div class="form-group">
                <div class="font-bold">Phone</div>

                <td>Country :</td>

                    <%--<c:if test="${not empty message}">

                        <div class="alert alert-success alert-dismissible">

                            <button type="button" class="close" data-dismiss="alert">&times;</button>

                                ${message}
                        </div>

                    </c:if>--%>

                    <%--<td><sf:select path="phoneCountry">
                        <sf:option value="0" label="Select"/>
                        <sf:option value="+38" label="Ukraine"/>
                        <sf:option value="2" label="USA"/>
                        &lt;%&ndash;<sf:option selected = value="3" label="UK"/>&ndash;%&gt;

                    </sf:select></td>

                    <c:set var="grades" value="1,2,3,A,B,C,D,E" scope="application"/>
                    <select class="grade" title="Grade Obtained">
                        <c:forEach items="${fn:split(grades, ',')}" var="grade">
                            <option value="${grade}" ${qd.grade == grade ? 'selected' : ''}>${grade}</option>
                        </c:forEach>
                    </select>

                    <td>Operator :</td>
                    <td><sf:select path="phoneOperator">
                        <sf:option value="0" label="Select" placeholder="Phone"/>
                        <sf:option value="067" label="067 Kiyvstar"/>
                        <sf:option value="067" label="097 Kiyvstar"/>
                        <sf:option value="067" label="099 Life"/>
                    </sf:select></td>--%>

                <td>Phone number :</td>
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
                <img src="${contextPath}/resources/img/NoFoto.png"
                     alt="your foto should be here" width="200px" height="200"/>
            </div>

        </div>
        <div><br></div>
        <div class="form-group">
            <div class="font-bold">code</div>
            тут має бути кнопка завантаження файлу. В коді закоментовано

            <%--<label for="code" style="float: left;">code</label>--%>
<%--            <sf:input type="text"
                      path="file"
                      name="${status.expression}"
                      value="${status.value}"
                      class="form-control"
                      placeholder="code"/>
            <c:if test="${status.error}">
                <c:forEach items="${status.errorMessages}" var="error">
                    <span class="red"> ${error} </span>
                </c:forEach>
            </c:if>--%>
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
            <%--<sf:input type="text" path="code" placeholder="code" class="form-control"/>--%>
            <%--<sf:input type="text" path="code" placeholder="code" class="form-control"/>--%>
        </div>
        <%--<div class="form-group">
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
        </div>--%>
        <br>
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