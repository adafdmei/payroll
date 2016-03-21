<jsp:directive.include file="top.jsp" />

<div class="container-fluid">
	<div class="col-sm-1"></div>
	<div class="col-sm-4">
		<h2>Enter Employee Information</h2>
	</div>

</div>

<div class="container-fluid">
	<form:form method="POST" action="/payroll-spring-mvc/findEmployee"
		htmlEscape="true" cssClass="form-horizontal">
		<div class="form-group">
			<form:label cssClass="control-label col-sm-2" path="id">ID</form:label>
			<div class="col-sm-2">
				<form:input cssClass="form-control" path="id" />
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