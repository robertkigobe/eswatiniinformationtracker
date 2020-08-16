<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<html>

<head>
<title>Schools</title>

<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery.js" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />



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

		<div id="content">

			<table class="table">
				<tr>

					<th scope="col">#</th>

					<th scope="col">Region</th>
					<th scope="col">School Name</th>
					<th scope="col">School Center</th>
					<th scope="col">Level</th>

				</tr>

				<c:forEach var="tempSchool" items="${schools}">



					<tr>

						<td><c:out value="${tempSchool.id}" /></td>

						<td><c:out value="${tempSchool.region}" /></td>
						<td><c:out value="${tempSchool.schoolName}" /></td>
						<td><c:out value="${tempSchool.center}" /></td>
						<td><c:out value="${tempSchool.level}" /></td>

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









