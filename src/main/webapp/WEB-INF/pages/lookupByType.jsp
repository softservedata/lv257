<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %> s
<%@page session="true"%>


<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />

<div class="container-fluid">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Choose type of ReSearch
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li class="active"><a href="${pageContext.request.contextPath}/lookup?lookupBy=byType">Looking up by type or Resource</a></li>
                        <li><a href="${pageContext.request.contextPath}/lookup?lookupBy=byOwner">Looking up by Owner</a></li>

                    </ul>
                </div>
                <br />
                <div class="row">
                    <div class="container-fluid col-md-4">
                        <div class="form-group">
                            <label for="sel1">Select type of resources:</label>
                            <select class="form-control" id="sel1">
                                <option>choose type</option>
                                <optgroup label="Transport">
                                    <option class="font-bold">Cars</option>
                                    <option selected>&nbsp;&nbsp;Vehicles</option>
                                    <option>&nbsp;&nbsp;Trucks</option>
                                </optgroup>
                                <optgroup label="Real Estate">
                                    <option class="font-bold">Residential Buildings</option>
                                    <option>&nbsp;&nbsp;Cottages</option>
                                    <option>&nbsp;&nbsp;Apartment Buildings</option>
                                </optgroup>
                            </select>
                        </div>
                    </div>
                </div>
                <form>
                    <div class="form-group row">
                        <label for="Email" class="col-sm-2 col-form-label">Марка</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="Email" placeholder="Марка">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputPassword" class="col-sm-2 col-form-label">Модель</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputPassword" placeholder="Модель">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="Email" class="col-sm-2 col-form-label">Реєстраційний номер</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="Email" placeholder="Реєстраційний номер">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputPassword" class="col-sm-2 col-form-label">Об'єм двигуна</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputPassword" placeholder="Об'єм двигуна">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputPassword" class="col-sm-2 col-form-label">Рік виробництва</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputPassword" placeholder="Рік виробництва">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputPassword" class="col-sm-2 col-form-label">Колір</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputPassword" placeholder="Колір">
                        </div>
                    </div>
                </form>




                <br />
                <button type="button" class="btn btn-primary">SEARCH</button> <a href="ResultsOfSearch.html">Go to Result of Search</a>


            </div>

        </div>

    </div>






</div>

</body>
</html>