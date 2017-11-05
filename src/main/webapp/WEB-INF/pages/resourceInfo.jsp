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

					<h2>Resource Info</h2>
					
					<c:set var="resource" value="${genericResource}"/>

					<table class="table table-hover">
						<thead>
							<tr>
								<th>Property</th>
								<th>Info</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							</tr>
							<td>Resource type</td>
							<td>Automobile</td>
							
							<c:forEach items="${resource.resourcePropertyValues}" var="entryMap">
							<tr>
								<td><c:out value="${entryMap.key}"/></td>
								<td><c:out value="${entryMap.value}"/></td>
							</tr>
							</c:forEach>
							
							<tr>
								<td>Country</td>
								<td><c:out value="${resource.address.country}"/></td>
							</tr>
							<tr>
								<td>City</td>
								<td>Lviv</td>
							</tr>
							<tr>
								<td>City</td>
								<td>Lviv</td>
							</tr>
							<tr>
								<td>City</td>
								<td>Lviv</td>
							</tr>
							
							<c:forEach items="${resource.owners}" var="owner">
								<c:if test="${owner.ownerType() == 'Company' }">
								<tr>
									<td>Company Owner</td>
									<td><c:out value="${owner.fullName}"/></td>
								</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>					

					<div class="">
						<span>You can:</span>

						<button type="submit" class="btn btn-default">
							Save
							<i class="glyphicon glyphicon-floppy-save" aria-hidden="true"></i>
						</button>
						<button type="submit" class="btn btn-default">
							Print
							<i class="glyphicon glyphicon-print" aria-hidden="true"></i>
						</button>
						<button type="submit" class="btn btn-default">
							Edit
							<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
						</button>
						

					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>