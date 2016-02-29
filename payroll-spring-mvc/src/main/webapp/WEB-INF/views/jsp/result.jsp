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
        <td>${emp.id}</td>
    </tr>
    <tr>
        <td>Name</td>
        <td>${emp.name}</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>${emp.address}</td>
    </tr>
     <tr>
        <td>Type</td>
        <td>${emp.type}</td>
    </tr>
     <tr>
        <td>Result</td>
        <td>${result}</td>
    </tr>
    
    
	</table>  
</body>
</html>