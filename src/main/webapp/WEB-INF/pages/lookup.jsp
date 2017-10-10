<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
    <link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"
			rel="stylesheet" >
</head>
<body>
<jsp:include page="menu.jsp" />

<h2>Look Page</h2>

<div class="container-fluid">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
	            <div class="row">
	              <div class="container-fluid col-md-4">
	            	<div id=type-choose class="form-group">
	            		<label for="lookup_type">Select type of Look Up:</label>
	              		<select id="lookup_type" class="form-control">
	                		<option value="absent">Choose type here</option>
	                		<option value="by-type">Looking up by type of Resource</option>
	                		<option value="by-owner">Looking up by Owner</option>
	              		</select>
	            	</div>
	               </div>
				</div>
                <br />
                <c:set var="typeSelectLabel" value="Resource Type" scope="request"/>
                <div class="row">
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
                <div id="no-inputs-error">
                </div>
                <br />

				<div id="lookup-result">
				<p>Result</p>
				
				</div>
				<div id="result-search">

				</div>
				
				
				<div id="new-search">
				<button type="button" id="myButton"  class="btn btn-primary">
				  Make a new Look Up
				</button>
				</div>
				


            </div>

        </div>

    </div>






</div>

<script src="../../resources/js/lookUpResource.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script>
  $('#myButton').on('click', function () {
    location.reload();
  })
</script>

</body>
</html>