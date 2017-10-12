 <%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="../metadata.jsp"/>
</head>
<body>
<jsp:include page="../menu.jsp" />
<br>
<div class="container-fluid">
    <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
        <div class="container" >
            <ul class="nav nav-tabs">
                <li> <a href="${pageContext.request.contextPath}/users">Users</a></li>
                <li> <a href="${pageContext.request.contextPath}/roles">Roles</a></li>
                <li class="active"> <a href="${pageContext.request.contextPath}/privileges">Privileges</a></li>
            </ul>
        </div>
        <div class="container">
            <button id="add_resource_address_btn"
                    class="btn btn-primary"
                    type="button" data-toggle="modal"
            <%--data-target="#resourseAdressPopUp"--%>
                    onclick="getPrivileges()">
                Get Ajax data
            </button>
        </div>

        <div class="form-group">
            <div class="table-responsive">


                <div class="container">
                    <h2>System privileges</h2>
                    <table class="table">
                        <tbody id="sp"></tbody>
                    </table>
                </div>

                <div class="container">
                    <h2>Resource types privileges</h2>
                    <table class="table">
                        <thead>
                        <th>resourceTypeId</th>
                        <th>Read</th>
                        <th>Create</th>
                        <th>Update</th>
                        <th>Delete</th>
                        <th>All actions</th>
                        </thead>
                        <tbody id="rtp"></tbody>
                    </table>
                </div>

                <div class="container">
                    <h2>Resource properties privileges</h2>
                    <table class="table">
                        <thead>
                        <th></th>
                        <th>Read</th>
                        <th>Update</th>
                        </thead>
                        <tbody id="rpp"></tbody>
                    </table>
                </div>

            </div>
        </div>


        <div class="container" id="feedback">
        </div>

        </div>

    </div>
</div>
</body>
</html>