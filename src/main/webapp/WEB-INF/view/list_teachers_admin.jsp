<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<html>

<head>
<title>Teachers</title>

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
		User:
		<security:authentication property="principal.username" />
	</h4>

	<div id="container">
	
	<!-- put new button: Add Customer -->
			<input type="button" value="Add Teacher"
				   onclick="window.location.href='showFormForAddTeacher'; return false;"
				   class="add-button"
			/>

		<div id="content">




			<table class="table">



				<tr>

					<th>#</th>
					<th>Pin</th>
					<th>Name</th>
					<th>Mobile</th>
					<th>School</th>
					<th>Username</th>
					<th>Password</th>
					<th>Role</th>
					<th>Actions</th>
				</tr>

				<c:forEach var="tempTeacher" items="${teachers}">

					<!-- construct an "delete" link with attendance id -->
					<c:url var="updateLink" value="/attendanceReport/showFormForUpdateTeacher">
						<c:param name="teacherId" value="${tempTeacher.id}" />
					</c:url>

					<!-- construct an "delete" link with attendance id -->
					<c:url var="deleteLink" value="/attendanceReport/deleteTeacher">
						<c:param name="teacherId" value="${tempTeacher.id}" />
					</c:url>

					<tr>

						<td><c:out
								value="${tempTeacher.id}" /></td>
						<td><c:out value="${tempTeacher.pin1}" /> </td>
						<td><c:out value="${tempTeacher.htname}" /></td>
						<td><c:out value="${tempTeacher.mobile}" /></td>
						<td><c:out value="${tempTeacher.schoolname}" /></td>
						<td><c:out value="${tempTeacher.username}" /></td>
						<td><c:out value="${tempTeacher.password}" /></td>
						<td><c:out value="${tempTeacher.role}" /></td>

						<td>
							<!-- display the disqualify link --> <a href="${updateLink}">Update</a>
							| <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>


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









