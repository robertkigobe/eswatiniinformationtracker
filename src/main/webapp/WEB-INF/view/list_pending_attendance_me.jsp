<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<html>

<head>
<title>Monitoring Class Attendance</title>

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

			
			

			<table class="table">

				<tr>
					<th>#</th>
					<th scope="col">Comment Date</th>
					<th scope="col">Student Name</th>
					<th scope="col">School</th>
					<th scope="col">Class</th>
					<th scope="col">Comment</th>
					<th scope="col">Actions</th>

				</tr>

				<c:forEach var="tempAttendance" items="${attendance}">

					<!-- construct a verify and reset link with attendance id -->
					<c:url var="resetLink" value="/attendanceMe/resetPendingMeAttendance">
						<c:param name="attendanceId" value="${tempAttendance.id}" />
					</c:url>
					
					<c:url var="verifyLink" value="/attendanceMe/verifyPendingMeAttendance">
						<c:param name="attendanceId" value="${tempAttendance.id}" />
					</c:url>


					<tr>
						<td scope="row"><c:out value="${tempAttendance.id}" /></td>
						<td><c:out value="${tempAttendance.meCommentDate}" /></td>
						<td><c:out value="${tempAttendance.studentfirstname}" /> <c:out
								value="${tempAttendance.studentsurname}" /></td>
						<td><c:out value="${tempAttendance.studentschool}" /></td>
						<td><c:out value="${tempAttendance.studentform}" /></td>
						<td><c:out value="${tempAttendance.meComment}" /></td>
						
						<td>
							<!-- display the disqualify link --> <a href="${resetLink}">Reset</a>|<a href="${verifyLink}">Verify</a>

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









