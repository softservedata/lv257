
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" rel="home" href="Main.html"
               title="Project Resources"> <img
                    style="max-width: 100px; margin-top: -10px;"
                    src="resources-logo.png">
            </a> <a class="navbar-brand" rel="home" href="Main.html"
                    title="Project Resources"> <img style="max-width: 100px"
                                                    src="text.png"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li ><a href="LookUpResources2.html">Look up</a></li>
                <li><a href="ManageUsersAndRoles.html">Users</a></li>
                <li class="active"><a href="ResourcesRequest.html">Resources</a></li>
                <li ><a href="#">Community</a></li>
                <li><a href="#about">About</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="language-panel"><a href="" class="dropdown-toggle"
                                              data-toggle="dropdown" role="button" aria-haspopup="true"
                                              aria-expanded="false"><span class="glyphicon glyphicon-globe"></span>
                    Language<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="">English</a></li>
                        <li><a href="">Ukrainian</a></li>
                        <li><a href="">Russian</a></li>
                    </ul></li>
                <li class="Account"><a href="" class="dropdown-toggle"
                                       data-toggle="dropdown" role="button" aria-haspopup="true"
                                       aria-expanded="false"><span class="glyphicon glyphicon-user"></span>
                    Profile (Register) <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Profile.html">Signed in as Register</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Profile.html">Your profile</a></li>
                        <li><a href="Account.html">Settings</a></li>
                        <li><a href="">Help</a></li>
                        <li><a href="Signin.html">Sign out</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="dropdown-header">Debug information</li>
                        <li><a href="">A.5</a></li>
                    </ul></li>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br />

<div class="wrapper">
    <div class="container-fluid">
        <div class="col-sm-12 col-md-12">

            <div class="container">


                <ul class="nav nav-tabs">
                    <li ><a href="ResourcesAdd.html">Register resource</a></li>
                    <li ><a href="ResourcesSendRequest.html">Send request</a></li>
                    <li class="active"><a href="ResourcesHistory.html">History</a></li>
                </ul>
                <br>

                <hr>
                <ul class="nav nav-pills">
                    <li class="active"><a href="#NeedMoreDetails" data-toggle="pill">Need more details</a></li>
                    <li ><a href="#NotReviewed" data-toggle="pill">Not reviewed</a></li>
                    <li><a href="#Accepted" data-toggle="pill">Accepted</a></li>
                    <li><a href="#Declined" data-toggle="pill">Declined</a></li>
                </ul>

                <div class="tab-content" id="TabContent">

                    <div class="tab-pane fade in active" id="NeedMoreDetails">
                        <div class="table-responsive">
                            <h3>List of requests</h3>
                            <table data-toggle="table"
                                   class="table table-striped table-condensed text-center">
                                <thead>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th data-field="requestedCategory">
                                        <div class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register">
                                        <div class="text-center">Who reviewed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="documentLink">
                                        <div class="text-center">Information</div></th>
                                    <th data-field="date">
                                        <div class="text-center">Date</div></th>
                                    <th ></th>

                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Мішані ліси</td>
                                    <td>ResourceAdmin1</td>
                                    <td><a href="DetailsResourсesHistory.html">documentLink1</a></td>
                                    <td>04.05.2017</td>

                                    <td>
                                        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal">Responce</button>
                                        <div  class="modal fade">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        <h4 class="modal-title " style="text-align: left">Response</h4>
                                                        <h6 class="modal-title"  style="text-align: left">Comment:</h6>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form>
                                                            <div class="form-group">

                                                                <textarea class="form-control" rows="5"></textarea>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer" >

                                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Send</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Легкові авто </td>

                                    <td>ResourceAdmin1</td>
                                    <td><a href="DetailsResourсesHistory.html">documentLink2</a></td>
                                    <td>12.02.2017</td>
                                    <td>

                                        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal">Responce</button>
                                        <div class="modal fade">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        <h4 class="modal-title">Response</h4>
                                                        <h6 class="modal-title">Comment:</h6>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form>
                                                            <div class="form-group">

                                                                <textarea class="form-control" rows="5"></textarea>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">

                                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Send</button>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Вантажні авто </td>

                                    <td>ResourceAdmin2</td>
                                    <td><a href="DetailsResourсesHistory.html">documentLink3</a></td>
                                    <td>04.02.2017</td>
                                    <td>

                                        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal">Responce</button>
                                        <div id="myModal" class="modal fade">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        <h4 class="modal-title">Response</h4>
                                                        <h6 class="modal-title">Comment:</h6>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form>
                                                            <div class="form-group">

                                                                <textarea class="form-control" rows="5"></textarea>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">

                                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Send</button>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>

                                </tbody>

                            </table>
                            </table>

                        </div>

                        <br />
                        <ul class="pager">
                            <li class="previous"><a href="#">Previous</a></li>
                            <li class="previous"><a href="#">Next</a></li>
                        </ul>
                    </div>

                    <div class="tab-pane fade" id="Accepted">

                        <div class="table-responsive">
                            <h3>List of processed requests</h3>
                            <table data-toggle="table"
                                   class="table table-striped table-condensed text-center">
                                <thead>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th data-field="requestedCategory"><div
                                            class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register"><div
                                            class="text-center">Who requested</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="resourcesAdmin"><div
                                            class="text-center">Who processed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="status"><div
                                            class="text-center">Date</div>
                                        <div class="fht-cell"></div></th>

                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Квартира</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>24.07.2017</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Особняк </td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin3</td>
                                    <td>20.03.2017</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Одяг </td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>08.01.2017</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <ul class="pager">
                            <li class="previous"><a href="#">Previous</a></li>
                            <li class="previous"><a href="#">Next</a></li>
                        </ul>
                    </div>

                    <div class="tab-pane fade" id="Declined">

                        <div class="table-responsive">
                            <h3>List of processed requests</h3>
                            <table data-toggle="table"
                                   class="table table-striped table-condensed text-center">
                                <thead>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th data-field="requestedCategory"><div
                                            class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register"><div
                                            class="text-center">Who requested</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="resourcesAdmin"><div
                                            class="text-center">Who processed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="status"><div
                                            class="text-center">Date</div>
                                        <div class="fht-cell"></div></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Вода</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>01.07.2017</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Повітряний простір</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin3</td>
                                    <td>06.06.2017</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Теща</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>19.02.2017</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <ul class="pager">
                            <li class="previous"><a href="#">Previous</a></li>
                            <li class="previous"><a href="#">Next</a></li>
                        </ul>
                    </div>
                    <div class="tab-pane fade" id="NotReviewed">

                        <div class="table-responsive">
                            <h3>List of requests</h3>
                            <table data-toggle="table"
                                   class="table table-striped table-condensed text-center">
                                <thead>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th data-field="requestedCategory"><div
                                            class="text-center">RequestedCategory</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="register"><div
                                            class="text-center">Who requested</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="resourcesAdmin"><div
                                            class="text-center">Who processed</div>
                                        <div class="fht-cell"></div></th>
                                    <th data-field="status"><div
                                            class="text-center">Date</div>
                                        <div class="fht-cell"></div></th>


                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Велосипед</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>02.08.2017</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Самокат</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin3</td>
                                    <td>31.06.2017</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Надра землі</td>
                                    <td>Register1</td>
                                    <td>ResourceAdmin1</td>
                                    <td>05.06.2017</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <ul class="pager">
                            <li class="previous"><a href="#">Previous</a></li>
                            <li class="previous"><a href="#">Next</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>