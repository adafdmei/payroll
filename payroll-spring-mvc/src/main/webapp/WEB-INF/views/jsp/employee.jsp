<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Employee Information</h2>
<form:form method="POST" action="/payroll-spring-mvc/addEmployee">
   <table>
   <tr>
        <td><form:label path="id">ID</form:label></td>
        <td><form:input path="id"  /></td>
    </tr>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="address">Address</form:label></td>
        <td><form:input path="address" /></td>
    </tr>
    <tr>
        <td><form:label path="type">Type</form:label></td>
        <td><form:input path="type" /></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>