<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Home</title>

<head>



</head>

<body>

	<jsp:include page="/WEB-INF/view/_menu.jsp"/>


	<!-- display user name and role -->

	<h4>
		User:
		<security:authentication property="principal.username" />
	</h4>



	<div class="header">
	</div>


	<div class="card-body">
		<p class="card-text">We reach over 600,000 children and their
			caregivers annually across six countries in sub-Saharan Africa. We
			work with more than 90 local organizations, clinics, and local and
			national governments in Eswatini, Malawi, Mozambique, Tanzania,
			Uganda, and Zimbabwe..</p>

		<p class="card-text">Part-time and Continuing Education: We
			support ministries of education to deliver part-time and continuing
			education/non-formal education to out-of-school children and teen
			mothers, who are part of the millions of young people in Africa who
			have left the formal school system. As a part of DREAMS programming,
			we deliver evidence-based Mentor Programs to provide critical
			supports needed by out-of-school youth to keep them on track and
			in-school. Mentors lend support to students, liaise and advocate with
			caregivers and teachers, and support the re-matriculation of youth
			into the formal school system where possible..</p>

		<p class="card-text">Early Warning Systems: We work with
			ministries of education to develop, pilot and roll out Early Warning
			Systems to retain children in school and reduce school drop-out.
			Based on the core ABC metrics (Attendance, Behavior, Coursework), our
			EWS model identifies students at risk of dropping out and provides
			robust Response Protocols at student, school, caregiver and community
			level to retain children in school. Our Zimbabwe EWS has been adopted
			and scaled nationally by the Ministry of Education. We are currently
			developing an innovative mobile-based EWS to identify and support
			at-risk secondary school students..</p>
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
