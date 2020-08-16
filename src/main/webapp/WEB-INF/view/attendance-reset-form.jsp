<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Customer</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>

<jsp:include page="/WEB-INF/view/_menu.jsp"/>
	
	<div id="wrapper">
		<div id="header">
			<h2>CAM - Class Attendance Manager</h2>
		</div>
	</div>

	<div id="container">
		
	
		<form:form action="resetSuAttendance" modelAttribute="attendance" method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />
			
								
					
			<table class="table">
				<tbody>
				
				<tr>
						<td><label>Id:</label></td>
						<td><form:input path="id" /></td>
					</tr>
					<tr>
						<td><label>Session Date:</label></td>
						<td><form:input path="deviceActionformattedDate" /></td>
					</tr>
					
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="studentfirstname" /></td>
					</tr>
				
					<tr>
						<td><label>Surname:</label></td>
						<td><form:input path="studentsurname" /></td>
					</tr>

					<tr>
						<td><label>Pin:</label></td>
						<td><form:input path="studentpin" /></td>
					</tr>
					
					<tr>
						<td><label>Region:</label></td>
						<td><form:input path="studentregion" /></td>
					</tr>
					
					<tr>
						<td><label>School:</label></td>
						<td><form:input path="studentschool" /></td>
					</tr>
					
					<tr>
						<td><label>Form:</label></td>
						<td><form:input path="studentform" /></td>
					</tr>
					
					<tr>
						<td><label>Attendance:</label></td>
						<td><form:input path="studentattendance" /></td>
					</tr>
					
					<tr>
						<td><label>Comment:</label></td>
						<td><form:input path="supervisorComment" /></td>
					</tr>
					

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/attendanceMe/list_attendance_su">Back to List</a>
		</p>
	
	</div>

</body>

</html>










