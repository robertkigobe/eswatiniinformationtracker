<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<html>

<head>
<title>Admin Class Stastics</title>

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
		User:
		<security:authentication property="principal.username" />

	</h4>
	
	<hr>
	<h6>School Dignitary Packs submissions by dates</h6>

	<div id="container">

		<div id="content">

			<table class="table">

				<tr>

					<th scope="col">Session Date</th>
					<th scope="col">Region</th>
					<th scope="col">School</th>
					<th scope="col">Class</th>
					<th scope="col">Subject</th>
					<th scope="col">Flag</th>

				</tr>

				<c:forEach var="tempAttendance" items="${attendanceCount}">


					<tr>

						<td><c:out
								value="${tempAttendance.deviceActionformattedDate}" /></td>
						<td><c:out value="${tempAttendance.studentregion}" /></td>
						<td><c:out value="${tempAttendance.studentschool}" /></td>
						<td><c:out value="${tempAttendance.studentform}" /></td>
						<td><c:out value="${tempAttendance.studentsubject}" /></td>
						<td><c:out value="${tempAttendance.flag}" /></td>




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









