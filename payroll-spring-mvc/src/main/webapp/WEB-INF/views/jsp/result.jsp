<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Submitted Employee Information</h2>
   <table>
   <tr>
        <td>ID</td>
        <td>${employee.id}</td>
    </tr>
    <tr>
        <td>Name</td>
        <td>${employee.name}</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>${employee.address}</td>
    </tr>
     <tr>
        <td>Type</td>
        <td>${employee.type}</td>
    </tr>
     <tr>
        <td>Result</td>
        <td>${result}</td>
    </tr>
    
    
	</table>  
</body>
</html>