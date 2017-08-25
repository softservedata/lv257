<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />

<br />
<br />
<br />
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
            <div class="container">
                <ul class="nav nav-tabs">
                    <li> <a href="${pageContext.request.contextPath}/users">Users</a></li>
                    <li class="active"> <a href="${pageContext.request.contextPath}/roles">Roles</a></li>
                    <li> <a href="UsersRequests.html">Request <span class="badge">3</span></a></li>
                </ul>
            </div>
            <br/>
            <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
                <div class="row">
                    <div class="container-fluid col-md-4">
                        <div class="form-group">

                            <h3>USER Role edit<h3>
                        </div>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-condensed text-center">
                        <thead>
                        <tr>
                            <th class="text-center">#</th>
                            <th data-field="name"><div
                                    class="text-center ">Opportunities</div>
                                <div class="fht-cell"></div></th>
                            <th data-field="owner"><div
                                    class="text-center ">Permission</div>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>Adding resources</td>
                            <td>
                                <label class="checkbox-inline"><input type="checkbox" value="">Give access</label>
                            </td>

                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Adding communities</td>
                            <td>
                                <label class="checkbox-inline"><input type="checkbox" value="">Give access</label>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Authenticating users</td>
                            <td>
                                <label class="checkbox-inline"><input type="checkbox" value="">Give access</label>
                            </td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>Editing communities</td>
                            <td>
                                <label class="checkbox-inline"><input type="checkbox" value="">Give access</label>
                            </td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>Editing resources</td>
                            <td>
                                <label class="checkbox-inline"><input type="checkbox" value="">Give access</label>
                            </td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>Registering owners</td>
                            <td>
                                <label class="checkbox-inline"><input type="checkbox" value="">Give access</label>
                            </td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td>Deleting communities</td>
                            <td>
                                <label class="checkbox-inline"><input type="checkbox" value="">Give access</label>
                            </td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td>Deleting resources</td>
                            <td>
                                <label class="checkbox-inline"><input type="checkbox" value="">Give access</label>
                            </td>
                        <tr>
                            <td>9</td>
                            <td>Deleting users</td>
                            <td>
                                <label class="checkbox-inline"><input type="checkbox" value="">Give access</label>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td>
                                <button type="button" class="btn btn-primary btn-md">Submit</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="container-fluid">
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>