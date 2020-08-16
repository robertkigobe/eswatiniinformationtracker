<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<html>

<head>
<title>Delete Class Attendance</title>

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



		<form:form action="adminAttendanceDeleteAllRange" method="GET" class="form">
			
				Search: 
				
				<div class="row">
				<div class="col-md-2">
					<input type="number" name="fromRange" class="form-control" />
				</div>
				<div class="col-md-2">
					<input type="number" name="toRange" class="form-control" />
				</div>


				<div class="col-md-2">

					<button class="btn btn-outline-danger my-2 my-sm-0 form-control"
						type="submit" onclick="return confirm('Are you sure you want to delete all the entiries listed?')">Delete All</button>
				</div>
				<div class="col-md-6">

					<span>This action is irreversible</span>
				</div>
				

			</div>
		</form:form>

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









