<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<html>

<head>
<title>Class Attendance</title>

<!-- reference our style sheet -->

<!-- reference our style sheet -->

<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />

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
			<h5>Records returned: ${attendanceCount}</h5>
			<hr>

			<form:form action="searchAdminAttendanceResetAll" method="GET"
				class="form">
			
				Search: 
				
				<div class="row">
					<div class="col">
						<input type="text" name="fromDate" value="${fromDate}"
							class="form-control" />
					</div>
					<div class="col">
						<input type="text" name="toDate" value="${toDate}"
							class="form-control" />
					</div>



					<div class="col">
						<input type="text" name="studentregion" value="${studentregion}"
							class="form-control" />
					</div>

					<div class="col">
						<input type="text" name="studentschool" value="${studentschool}"
							class="form-control" />
					</div>

					<div class="col">
						<input type="text" name="studentform" value="${studentform}"
							class="form-control" />
					</div>

					<div class="col">
						<input type="text" name="studentsubject" value="${studentsubject}"
							class="form-control" />
					</div>

					<div class="col">

						<button class="btn btn-outline-success my-2 my-sm-0 form-control" type="submit" name="action" value="resetAll">Reset All</button>
					</div>



					<div class="col">
						<a
							href="${pageContext.request.contextPath}/attendanceAdmin/list_attendance_admin"
							class="btn btn-outline-info my-2 my-sm-0 form-control">Clear
							Search</a>
					</div>
				</div>
			</form:form>

			<table class="table">

				<tr>


					<th>#</th>
					<th scope="col">Date</th>
					<th scope="col">Import Date</th>
					<th scope="col">Student Name</th>
					<th scope="col">School</th>
					<th scope="col">Class</th>
					<th scope="col">Subject</th>
					<th scope="col">Flag</th>
					<th scope="col">Action</th>


				</tr>

				<c:forEach var="tempAttendance" items="${attendance}">

					<!-- construct an "delete" link with attendance id -->
					<c:url var="deleteLink" value="/attendanceAdmin/deleteAdminAttendance">
						<c:param name="attendanceId" value="${tempAttendance.id}" />
					</c:url>

					<tr>

						<td scope="row"><c:out value="${tempAttendance.id}" /></td>
						<td><c:out
								value="${tempAttendance.deviceActionformattedDate}" /></td>
						<td><c:out value="${tempAttendance.dateOfImport}" /></td>
						<td><c:out value="${tempAttendance.studentfirstname}" /> <c:out
								value="${tempAttendance.studentsurname}" /></td>
						<td><c:out value="${tempAttendance.studentschool}" /></td>
						<td><c:out value="${tempAttendance.studentform}" /></td>
						<td><c:out value="${tempAttendance.studentsubject}" /></td>
						<td><c:out value="${tempAttendance.flag}" /></td>

						<td>
							<!-- display the disqualify link --> <a href="${deleteLink}">Delete</a>

						</td>


					</tr>

				</c:forEach>

			</table>

		</div>

	</div>

	<footer>

		<nav class="navbar navbar-light" style="background-color: #e3f2fd;">

		</nav>
		<div class="col-12">
			<div class="section_footer__copyright">
				<span>Bantwana Class Tracker 2019©. All rights reserved.</span>
			</div>
		</div>
		<nav class="navbar navbar-light"
			style="background-color: #e3f2fd; align-content: center"></nav>
	</footer>


</body>

</html>









