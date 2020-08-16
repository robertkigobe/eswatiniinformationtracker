<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

<head>
<title>Save Teacher</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
</head>

<body>


	
		<jsp:include page="/WEB-INF/view/_menu.jsp" />

	<h4>
		User:
		<security:authentication property="principal.username" />
	</h4>

	<div id="container">
		<h3>Save Teacher</h3>

		<form:form action="saveTeacher" modelAttribute="teacher" method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />

			<table class="table">
				<tbody>





					<tr>
						<td><label>Date:</label></td>
						<td><form:input type="date" path="formattedDate" /></td>
					</tr>


					<tr>
						<td><label>Pin:</label></td>
						<td><form:input path="pin1" /></td>
					</tr>

					<tr>
						<td><label>Name:</label></td>
						<td><form:input path="htname" /></td>
					</tr>

					<tr>
						<td><label>Mobile:</label></td>
						<td><form:input path="mobile" /></td>
					</tr>

					<tr>
						<td><label>Region:</label></td>

						<td><form:select path="region">
								<form:option value="Hhohho">Hhohho</form:option>
								<form:option value="Lubombo">Lubombo</form:option>
								<form:option value="Manzini">Manzini</form:option>
								<form:option value="Shiselweni">Shiselweni</form:option>
							</form:select></td>
					</tr>

					<tr>
						<td><label>School:</label></td>
						<td><form:input path="schoolname" /></td>
					</tr>

					<tr>
						<td><label>Username:</label></td>
						<td><form:input path="username" /></td>
					</tr>

					<tr>
						<td><label>Password:</label></td>
						<td><form:input path="password" /></td>
					</tr>

					<tr>
						<td><label>Role:</label></td>
						<td><form:select path="role">
								<form:option value="Focus Teacher">Focus Teacher</form:option>
								<form:option value="Head Teacher">Head Teacher</form:option>

							</form:select></td>
					</tr>


					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>

		<div style=""></div>

		<p>
			<a
				href="${pageContext.request.contextPath}/attendanceReport/list_teacher_admin">Back
				to List</a>
		</p>

	</div>

</body>

</html>










