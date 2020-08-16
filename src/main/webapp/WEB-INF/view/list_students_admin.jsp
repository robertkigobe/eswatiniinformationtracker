<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<html>

<head>
<title>Students</title>

<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />



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
		You are logged in as:
		<security:authentication property="principal.username" />
	</h4>



	<div id="container">
	

		<div id="content">

			<table class="table">
			
				<tr>

					<th>#</th>
					<th>Pin</th>
					<th>Firstname</th>
					<th>Surname</th>
					<th>School</th>
					<th>Gender</th>
					<th>Date of Birth</th>
					<th>form</th>
					<th>status</th>
					
				</tr>

				<c:forEach var="tempStudent" items="${students}">

					<!-- construct an "delete" link with attendance id -->
					<c:url var="updateLink" value="/attendance/showFormForUpdateTeacher">
						<c:param name="teacherId" value="${tempStudent.id}" />
					</c:url>

					<!-- construct an "delete" link with attendance id -->
					<c:url var="deleteLink" value="/attendance/deleteTeacher">
						<c:param name="teacherId" value="${tempStudent.id}" />
					</c:url>

					<tr>

						<td><c:out value="${tempStudent.id}" /></td>
						<td><c:out value="${tempStudent.pin}" /> </td>
						<td><c:out value="${tempStudent.firstname}" /></td>
						<td><c:out value="${tempStudent.surname}" /></td>
						<td><c:out value="${tempStudent.school}" /></td>
						<td><c:out value="${tempStudent.gender}" /></td>
						<td><c:out value="${tempStudent.dateofbirth}" /></td>
						<td><c:out value="${tempStudent.form}" /></td>
						<td><c:out value="${tempStudent.status}" /></td>


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









