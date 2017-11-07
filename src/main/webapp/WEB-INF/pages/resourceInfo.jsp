<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="menu.jsp" />
	<div class="wrapper">
		<div class="container-fluid">
			<div class="col-sm-12 col-md-12">
				<div class="container">

					<h2>Resource Details</h2>
					
					<c:set var="resource" value="${genericResource}"  scope="page"/>
					<c:set var="count" value="0"  scope="page"/>
				<div class="row">
				<div class="container-fluid col-md-8"> 
				<h3>Characteristics</h3>
					<table class="table table-hover">
						<thead>
						</thead>
						<tbody>
							<tr>
							</tr>
							<td>Resource type</td>
							<td>${resource.resourceTypeName}</td>
							
							<c:forEach items="${resource.resourcePropertyValues}" var="entryMap">
							<tr>
								<td><c:out value="${entryMap.key}"/></td>
								<td><c:out value="${entryMap.value}"/></td>
							</tr>
							</c:forEach>
							<tr>
								<td>Registration Address</td>
								<td><c:out value="${resource.address.customToString()}"/></td>
							</tr>
						</tbody>
					</table>
					</div>
				</div>
						
			    <div class="row">
				<div class="container-fluid col-md-8">
					<h3>Owners</h3>
					<table class="table table-hover">
						<thead>
							<tr>
								<!-- <th>Property</th>
								<th>Data</th> -->
							</tr>
						</thead>
						<tbody>
							<tr>
							</tr>
							
							<c:forEach items="${resource.owners}" var="owner">
								<c:if test="${owner.ownerType() == 'Company' }">
									<tr>
										<c:set var="count" value="${count + 1}" scope="page"/>
										<td>Company <c:out value="${count}" /></td>
										<td><c:out value="${owner.customToString()}"/></td>
									</tr>
								</c:if>
								
								<c:if test="${owner.ownerType() == 'Person' }">
									<tr>
										<c:set var="count" value="${count + 1}" scope="page"/>
										<td>Person <c:out value="${count}" /></td>
										<td><c:out value="${owner.customToString()}"/></td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
					</div>	
				</div>
								
				<br>
					<div class="row">
					<div class="container-fluid">
						
						<span>Save document as PDF or print:</span>

						
						
						<button id="printData" type="submit" class="btn btn-default">
							Save/Print
							<i class="glyphicon glyphicon-print" aria-hidden="true"></i>
						</button>
						
						<!-- TODO:implement in next spring -->
						<!-- <button id="printData" type="submit" class="btn btn-default">
							Edit
							<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
						</button> -->
						
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script> 
$('#printData').on('click', function () {
	window.print();
})
</script>

</body>
</html>

