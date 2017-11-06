<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%-- <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"/> --%>

<c:set var="user" value="${currentUser}"/>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
<!--     <link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"
			rel="stylesheet" > -->
</head>
<body>
<jsp:include page="menu.jsp" />


	<div class="container">
		<div align="center">
			<h2>Resources Search</h2>
		</div>
		<div align="center">
			<img width="20%" height="auto" src="${contextPath}/resources/img/lookUpLogo.jpeg">
		</div>
	</div>


<div class="container-fluid">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
	            <div class="row text-center">
	              <div class="container-fluid col-md-4">
	            	<div id=type-choose class="form-group">
	            		<label for="lookup_type">Select type of Search:</label> <br> <c:out value="${pageContext.request.contextPath}"/>
	              		<select id="lookup_type" class="form-control">
	                		<option value="absent">Choose type here</option>
	                		<option value="by-type">Search by type of Resource</option>
	                		<c:if test="${ user != 'anonymousUser' }">
	                		<option value="by-owner">Search by Owner</option>
                			</c:if>
	              		</select>
	            	</div>
	               </div>
				</div>
                <br />

                <c:set var="typeSelectLabel" value="Resource Type" scope="request"/>
                <div class="row text-center">
                    <div class="container-fluid col-md-4">
                    	<div class="form-group"  id="div-for-types">
                    	<script> var showTypesInCategoryHierarchy = true</script>
                    	 <jsp:include page="components/resourceTypeSelect.jsp"/>
                       <!--  <div class="form-group"  id="div-for-types">
                            <label for="sel1">Select type of resources:</label>
                            <select class="form-control" id="sel1-resource-types">
                                <option>select type</option>
                               	here will be data inserted by ajax request
                            </select>
                        </div> -->
                        </div>
                    </div>
                </div>
                <form id="form-for-properties" class="form-horizontal">
                    <!-- here will be data inserted by ajax request -->
                </form>
                <div class="row">
                	<div class="container-fluid col-md-8"> 
		                <div class="form-group"  id="div-for-owners">
		                    <script> var lookUpOwner = true </script>
		                    <jsp:include page="components/searchOwner.jsp"/>
		                </div>
                	</div> 
                </div>
                
                <div id="no-inputs-error">
                </div>
                <br />
				<div class="row">
					<div class="container-fluid col-md-6" id="lookup-result-by-owner-grouped">

					</div>
				</div>

				<div class="container" id="result-search">

				</div>
				
				
				<div id="new-search">
				<button type="button" id="myButton"  class="btn btn-primary">
				  Make a new Search
				</button>
				</div>
				


            </div>

        </div>

    </div>






</div>

<script src="${contextPath}/resources/js/lookUpResource.js"></script>
<script src="${contextPath}/resources/js/ownerAndAddressManagement.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script>
  $('#myButton').on('click', function () {
    location.reload();
  })

  /* var navbarMenuAcnhorItems = $('#navbar').find('ul:first li:first');
  navbarMenuListItems.removeClass('active');
  navbarMenuAcnhorItems.addClass('active'); */

</script>

</body>
</html>