<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<html>

<head>
<title>Supervisor Class Attendance</title>

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
		User:
		<security:authentication property="principal.username" />
	</h4>

	<div id="container">

		<div id="content">

			<form:form action="searchApprovedAttendance" method="GET" class="form">
			
				Search: 
				
				<div class="row">
					<div class="col">
						<input type="date" name="fromDate" class="form-control" />
					</div>
					<div class="col">
						<input type="date" name="toDate" class="form-control" />
					</div>

					<div class="col">

						<select name="studentregion" class="form-control">
							<option>Choose Region</option>
							<option>Hhohho</option>
							<option>Lubombo</option>
							<option>Manzini</option>
							<option>Shiselweni</option>
						</select>

					</div>

					<div class="col">

						<select name="studentschool" class="form-control">
							<option>Choose School</option>
							<option>Bekezela high School</option>
							<option>Ekudvwaleni high School</option>
							<option>Hawane high School</option>
							<option>Hlathikhulu Central School</option>
							<option>LaMawandla high School</option>
							<option>Lobamba National high School</option>
							<option>Ngwane Central High School</option>
							<option>Nkalashane high School</option>
							<option>Nyatsini High School</option>
							<option>Ndunayithini High School</option>
							<option>Our Lady of Sorrows</option>
							<option>Siyendle High School</option>
							<option>St. Francis high School</option>
							<option>St Phillips High</option>
							<option>Phonjwane high School</option>
							<option>Vuvulane high  School</option>
							<option>Zombodze high School</option>

						</select>
					</div>

					<div class="col">

						<select name="studentform" class="form-control">

							<option>Choose Class</option>
							<option>Form 1</option>
							<option>Form 2</option>
							<option>Form 3</option>
							<option>Form 4</option>
							<option>Form 5</option>

						</select>

					</div>

					

					<div class="col">

						<button class="btn btn-outline-success my-2 my-sm-0 form-control"
							type="submit">Search</button>
					</div>

				</div>
			</form:form>

			<table class="table">

				<tr>
					<th>#</th>
					<th scope="col">Date</th>
					
					<th scope="col">Student Name</th>
					<th scope="col">Student Pin</th>
					<th scope="col">Status</th>
					<th scope="col">School</th>
					<th scope="col">Class</th>
					<th scope="col">Subject</th>
					<th scope="col">Region</th>
					<th scope="col">Flag</th>

				</tr>

				<c:forEach var="tempAttendance" items="${attendance}">

					<!-- construct an "delete" link with attendance id -->
					<c:url var="resetLink" value="/attendance/reset">
						<c:param name="attendanceId" value="${tempAttendance.id}" />
					</c:url>

					<!-- construct an "delete" link with attendance id -->
					<c:url var="disqualifyLink" value="/attendance/disqualify">
						<c:param name="attendanceId" value="${tempAttendance.id}" />
					</c:url>

					<tr>
						<td scope="row"><c:out value="${tempAttendance.id}" /></td>
						<td><c:out
								value="${tempAttendance.deviceActionformattedDate}" /></td>
					
						<td><c:out value="${tempAttendance.studentfirstname}" /> <c:out
								value="${tempAttendance.studentsurname}" /></td>
						<td><c:out value="${tempAttendance.studentpin}" /></td>
						<td><c:out value="${tempAttendance.studentregion}" /></td>
						<td><c:out value="${tempAttendance.studentschool}" /></td>
						<td><c:out value="${tempAttendance.studentform}" /></td>
						<td><c:out value="${tempAttendance.studentsubject}" /></td>
						
						<td><c:out value="${tempAttendance.studentattendance}" /></td>
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









