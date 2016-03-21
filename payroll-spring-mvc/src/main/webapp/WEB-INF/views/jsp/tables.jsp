<jsp:directive.include file="top.jsp" />

<div class="container-fluid">
	<div class="col-sm-1"></div>
	<div class="col-sm-4">
		<h2>Enter Employee Information</h2>
	</div>

</div>

<div class="container-fluid">
	<!-- Default panel contents -->
	<div class="panel-heading">
		<span class="lead">List of Users </span>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Address</th>
				<th>Type</th>
				<th width="100"></th>
				<th width="100"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="employee">
				<tr>
					<td>${employee.id}</td>
					<td>${employee.name}</td>
					<td>${employee.address}</td>
					<td>${employee.type}</td>
					<td><a href="<c:url value='/edit-user-${employee.id}' />"
						class="btn btn-success custom-width">edit</a></td>
					<td><a href="<c:url value='/delete-user-${employee.id}' />"	class="btn btn-danger custom-width">delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>


<jsp:directive.include file="bottom.jsp" />