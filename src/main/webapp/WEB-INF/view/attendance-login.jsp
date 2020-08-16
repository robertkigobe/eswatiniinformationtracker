<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<title>Attendance Login</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script 	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
</head>

<body>



	<header>

		<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		</nav>

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<img
				src="${pageContext.request.contextPath}/resources/img/bantwanalogo.png"
				style="width: 20%; height: 20%"></img>

			<h2>Student Attendance Manager</h2>
		</nav>
	</header>
	
		<div id="wrapper">
		<div id="header">			
		</div>
	</div>

	<div id="container">
		<h3>Login Details</h3>

		<form:form action="loginUsers" modelAttribute="user" method="POST">


			<table>
				<tbody>
					<tr>
						<td><label>Username:</label></td>
						<td><form:input path="username" /></td>
					</tr>

					<tr>
						<td><label>Password:</label></td>
						<td><form:input path="password" /></td>
					</tr>

					<tr>
						<td><label>User Profile:</label></td>
						<td><form:select path="profile">
								<form:option value="admin">Administration</form:option>
								<form:option value="monitoring">Monitoring</form:option>
								<form:option value="supervisor">Supervision</form:option>
							</form:select></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Login" class="save" /></td>
					</tr>

				</tbody>
			</table>


		</form:form>



	</div>

</body>

</html>










