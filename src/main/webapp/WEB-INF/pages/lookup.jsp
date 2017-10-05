<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />

<h2>Look Page</h2>

<div class="container-fluid">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
	            <div class="row">
	              <div class="container-fluid col-md-4">
	            	<div class="form-group">
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
                <div class="row">
                    <div class="container-fluid col-md-4">
                        <div class="form-group"  id="div-for-types">
                            <label for="sel1">Select type of resources:</label>
                            <select class="form-control" id="sel1-resource-types">
                                <option>select type</option>
                               	<!-- here will be data inserted by ajax request -->
                            </select>
                        </div>
                    </div>
                </div>
                <form id="form-for-properties" class="form-horizontal">
                    <!-- here will be data inserted by ajax request -->
                </form>
                <div id="no-inputs-error">
                </div>




                <br />
                <button type="button" class="btn btn-primary">SEARCH</button>


            </div>

        </div>

    </div>






</div>

<script src="../../resources/js/lookUpResource.js"></script>


</body>
</html>