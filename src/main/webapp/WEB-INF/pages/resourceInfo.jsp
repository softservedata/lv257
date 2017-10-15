<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
							<tr>
								<td>Name</td>
								<td>Honda Civic LX 2016</td>
							</tr>
							<tr>
								<td>Production year</td>
								<td>2016</td>
							</tr>
							<tr>
								<td>Engine volume</td>
								<td>2 liters</td>
							</tr>
							<tr>
								<td>Color</td>
								<td>Red</td>
							</tr>
							<tr>
								<td>City</td>
								<td>Lviv</td>
							</tr>
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