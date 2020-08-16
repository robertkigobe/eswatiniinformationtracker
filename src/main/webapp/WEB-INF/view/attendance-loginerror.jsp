<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<title>Attendance Login</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Bantwana Attendance Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Login Details</h3>

		<form:form action="loginUsers" modelAttribute="user" method="POST">
		
		<h4 style="color: red;"> Please check your credentials</h4>


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










