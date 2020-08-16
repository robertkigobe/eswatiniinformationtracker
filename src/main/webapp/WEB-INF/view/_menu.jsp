<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>

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

	<header>

		<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		</nav>

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<img
				src="${pageContext.request.contextPath}/resources/img/bantwanalogo.png"
				style="width: 20%; height: 20%"></img>


			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Attendance and Packs </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<security:authorize access="hasRole('ADMIN')">
								<a class="nav-link"
									href="${pageContext.request.contextPath}/attendanceAdmin/list_attendance_admin">Attendance</a>
								
								<a class="nav-link" href="${pageContext.request.contextPath}/attendanceAdmin/list_dg_admin">Dg Packs</a>
								<a class="nav-link" href="${pageContext.request.contextPath}/attendanceAdmin/delete_list_admin">Delete Attendance</a>
								

							</security:authorize>
							<security:authorize access="hasRole('MONITORING')">

								<a class="nav-link"
									href="${pageContext.request.contextPath}/attendanceMe/list_attendance_me">Attendance</a>
								<a class="nav-link"
									href="${pageContext.request.contextPath}/attendanceMe/list_dg_me">Dg
									Packs</a>
							</security:authorize>
							<security:authorize access="hasRole('SUPERVISOR')">
								<a class="nav-link"
									href="${pageContext.request.contextPath}/attendanceSupervisor/list_attendance_su">Attendance</a>
								

								<a class="nav-link"
									href="${pageContext.request.contextPath}/attendanceSupervisor/list_dg_su">Dg
									Packs</a>
								
							</security:authorize>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> School Details </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">

							<a class="nav-link"
								href="${pageContext.request.contextPath}/attendanceReport/list_teacher_admin">Teachers</a>
							<a class="nav-link"
								href="${pageContext.request.contextPath}/attendanceReport/list_student_admin">Students</a>
							<a class="nav-link"
								href="${pageContext.request.contextPath}/attendanceReport/list_school_admin">Schools</a>

						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Quick Stats </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">

							<a class="dropdown-item" href="${pageContext.request.contextPath}/attendanceSupervisor/list_attendance_su_approved">Approved Detailed Entries</a> 
							<a class="dropdown-item" href="${pageContext.request.contextPath}/attendanceReport/attendance_statistics_submitted_approved">Approved Summary Entries</a> 								
							<a class="dropdown-item" href="${pageContext.request.contextPath}/attendanceSupervisor/list_attendance_su_verified">Verified Detailed Entries</a> 
							<a class="dropdown-item" href="${pageContext.request.contextPath}/attendanceReport/attendance_statistics_submitted_verified">Verified Summary Entries</a> 								
								
							<a class="dropdown-item" href="${pageContext.request.contextPath}/attendanceReport/attendance_statistics_present">Students Present by Gender</a> 
								
								<a class="dropdown-item"
								href="${pageContext.request.contextPath}/attendanceReport/attendance_statistics_absent">Students
								Absent by Gender</a> 
								
								<a class="dropdown-item"
								href="${pageContext.request.contextPath}/attendanceReport/attendance_statistics_submitted">School
								Attendance Submission</a> 
								
								<a class="dropdown-item"
								href="${pageContext.request.contextPath}/attendanceReport/dg_statistics_submitted">School
								Dg Packs Submission</a> 
								
								<a class="dropdown-item"
								href="${pageContext.request.contextPath}/attendanceMe/list_pending_attendance_me">Pending
								Attendance</a> 
								
								<a class="dropdown-item"
								href="${pageContext.request.contextPath}/attendanceMe/list_pending_dg_me">Pending
								Dignity Packs</a>

						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Help </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">

							<a class="dropdown-item" href="${pageContext.request.contextPath}/attendanceSupervisor/userManual">User Manual</a> 


						</div></li>


					<!-- Add a logout button -->
					<form:form action="${pageContext.request.contextPath}/logout"
						method="POST">

						<input type="submit" value="Logout" />

					</form:form>
				</ul>

			</div>
		</nav>

		<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		</nav>
	</header>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>