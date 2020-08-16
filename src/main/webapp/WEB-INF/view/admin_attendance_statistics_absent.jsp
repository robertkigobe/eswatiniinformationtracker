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
		You are logged in as:
		<security:authentication property="principal.username" />
	</h4>

	<div id="container">



		<form:form action="searchAttendanceStatisticsAbsent" method="GET"
			class="form">
			
				Search: 
				
				<div class="row">
				<div class="col">
					<input type="date" name="fromDate" class="form-control" />
				</div>
				<div class="col">
					<input type="date" name="toDate" class="form-control" />
				</div>



				<div class="col">

					<select name="studentschool" class="form-control">
						<c:forEach items="${schools}" var="school">
							<option><c:out value="${school}" /></option>
						</c:forEach>

					</select>
				</div>



				<div class="col">

					<button class="btn btn-outline-success my-2 my-sm-0 form-control"
						type="submit">Search</button>
				</div>

			</div>
		</form:form>

		<div id="container">

			<div id="content">

				<table class="table">

					<tr>

						<th scope="col">Session Date</th>
						<th scope="col">Region</th>
						<th scope="col">School</th>
						<th scope="col">Class</th>
						<th scope="col">Subject</th>
						<th scope="col">Attendance</th>
						<th scope="col">Gender</th>
						<th scope="col">Count</th>

					</tr>

					<c:forEach var="tempAttendance" items="${attendanceCount}">


						<tr>

							<td><c:out
									value="${tempAttendance.deviceActionformattedDate}" /></td>
							<td><c:out value="${tempAttendance.studentregion}" /></td>
							<td><c:out value="${tempAttendance.studentschool}" /></td>
							<td><c:out value="${tempAttendance.studentform}" /></td>
							<td><c:out value="${tempAttendance.studentsubject}" /></td>
							<td><c:out value="${tempAttendance.studentattendance}" /></td>
							<td><c:out value="${tempAttendance.studentgender}" /></td>
							<td><c:out value="${tempAttendance.flag}" /></td>




						</tr>

					</c:forEach>

				</table>

			</div>

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









