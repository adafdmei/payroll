<jsp:directive.include file="top.jsp" />

<div class="container-fluid">
	<div class="col-sm-1"></div>
	<div class="col-sm-4">
		<h2>Enter Employee Information</h2>
	</div>

</div>

<div class="container-fluid">
	<form:form method="POST" action="/payroll-spring-mvc/addEmployee"
		htmlEscape="true" cssClass="form-horizontal">
		<div class="form-group">
			<form:label cssClass="control-label col-sm-2" path="id">ID</form:label>
			<div class="col-sm-2">
				<form:input cssClass="form-control" path="id" />
			</div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label col-sm-2" path="name">Name</form:label>
			<div class="col-sm-2">
				<form:input cssClass="form-control" path="name" />
			</div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label col-sm-2" path="address">Address</form:label>
			<div class="col-sm-2">
				<form:input cssClass="form-control" path="address" />
			</div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label col-sm-2" path="type">Type</form:label>
			<div class="col-sm-2">
				<select class="form-control" name="type">
					<option value="salaried">Salaried</option>
					<option value="hourly">Hourly</option>
					<option value="commissioned">Commissioned</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label col-sm-2" path="salary">Salary</form:label>
			<div class="col-sm-2">
				<form:input cssClass="form-control" path="salary" />
			</div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label col-sm-2" path="basePay">Base Pay</form:label>
			<div class="col-sm-2">
				<form:input cssClass="form-control" path="basePay" />
			</div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label col-sm-2" path="commissionRate">Commission Rate</form:label>
			<div class="col-sm-2">
				<form:input cssClass="form-control" path="commissionRate" />
			</div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label col-sm-2" path="hourlyRate">Hourly Rate</form:label>
			<div class="col-sm-2">
				<form:input cssClass="form-control" path="hourlyRate" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2"></label>
			<div class="col-sm-2">
				<input type="submit" class="btn btn-primary btn-lg" value="Submit" />
			</div>
		</div>
	</form:form>

</div>

<jsp:directive.include file="bottom.jsp" />