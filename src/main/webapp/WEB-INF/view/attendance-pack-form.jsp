<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

<head>
<title>Save Customer</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>

	<jsp:include page="/WEB-INF/view/_menu.jsp" />

	<h4>
		You are logged in as:
		<security:authentication property="principal.username" />
	</h4>

	<div id="container">

		<div id="content">

			<hr>


			<form:form action="pendMeAttendance" modelAttribute="attendance"
				method="POST">

				<!-- need to associate this data with customer id -->


				<td><form:hidden path="deviceActionformattedDate" /></td>

				<td><form:hidden path="deviceActionandroid_id" /></td>

				<td><form:hidden path="deviceActionusername" /></td>

				<td><form:hidden path="deviceActionuserrole" /></td>

				<td><form:hidden path="deviceActionlatValue" /></td>

				<td><form:hidden path="deviceActionlngValue" /></td>

				<td><form:hidden path="deviceActionactions" /></td>

				<td><form:hidden path="teacherusername1t" /></td>

				<td><form:hidden path="teacherfirstnamet" /></td>

				<td><form:hidden path="teachersecondnamet" /></td>

				<td><form:hidden path="teachersurnamet" /></td>

				<td><form:hidden path="teacherschoolt" /></td>

				<td><form:hidden path="dateOfAttendance" /></td>

				<td><form:hidden path="dateOfImport" /></td>

				<td><form:hidden path="deviceActionlngValue" /></td>

				<td><form:hidden path="deviceActionlngValue" /></td>


				<form:hidden path="id" />



				<table class="table">
					<tbody>

						<tr>

							<td><label>Pin:</label></td>
							<td><form:input path="studentpin" /></td>


							<td><label>First name:</label></td>
							<td><form:input path="studentfirstname" /></td>

							<td><label>Surname:</label></td>
							<td><form:input path="studentsurname" /></td>

						</tr>

						<tr>
							<td><label>Session Date:</label></td>
							<td><form:input path="deviceActionformattedDate" /></td>


							<td><label>Region:</label></td>
							<td><form:input path="studentregion" /></td>

							<td><label>School:</label></td>
							<td><form:input path="studentschool" /></td>

						</tr>

						<tr>

							<td><label>Form:</label></td>
							<td><form:input path="studentform" /></td>


							<td><label>Attendance:</label></td>
							<td><form:select path="studentattendance">

									<form:option value="Present"></form:option>
									<form:option value="Absent"></form:option>


								</form:select></td>

							<td><label>Status:</label></td>
							<td><form:select path="flag">
									<form:option value="Pending"></form:option>
									<form:option value="New"></form:option>
									<form:option value="Verified"></form:option>


								</form:select></td>

						</tr>

						<tr>

							<td><label>Subject</label></td>
							<td><form:input path="studentsubject" /></td>



							<td><label>Comment:</label></td>
							<td><form:input path="meComment" /></td>
						</tr>


						<tr>
							<td><label></label></td>
							<td><input type="submit" value="Update" class="save" /></td>
						</tr>


					</tbody>
				</table>


			</form:form>

			<div style=""></div>

			<p>
				<a
					href="${pageContext.request.contextPath}/attendanceMe/list_attendance_me">Back
					to List</a>
			</p>

		</div>
	</div>
</body>

</html>










