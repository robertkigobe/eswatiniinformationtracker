<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web User Manual</title>
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


	<div id="content">

		<div class="accordion" id="accordionExample275">


			<div class="card z-depth-0 bordered">
				<div class="card-header" id="headingOne">
					<h5 class="mb-0">
						<button class="btn btn-link" type="button" data-toggle="collapse"
							data-target="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne">System Overview</button>
					</h5>
				</div>
				<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
					data-parent="#accordionExample275">
					<div class="card-body">
						<p>The System is made of three components that work together.
							The components are the mobile, web and database systems all
							working together to form one system to track the users'
							attendance in classrooms. The Mobile Application system is in
							charge of data entry.</p>
						<p>The web application system is in charge of data
							manipulation. The database system is in charge of data storage.
							The mobile application system is used at the data entry points
							while the web application system is used at the reporting
							points.The database system is interacted with by both the mobile
							apps when synchronizing data and the web system when generating
							reports or manipulating the data.</p>
						</p>
						<p>The Monitoring and Evaluation specialists have to set up
							the system to be used by the rest of the data entry stake
							holders. Before the data capturing devices can be taken to the
							school, The monitoring and evaluation specialists will login and
							create a school, a head teacher and a teacher. The monitoring
							will have to use the data for the head teacher to login at the
							school for the head teacher as well as the teacher.</p>
						<p>This user guide serves the following users on the system.</p>
						<ul class="list-group list-group-flush">
							<li class="list-group-item">Monitoring and Evaluation
								specialists on the mobile application</li>
							<li class="list-group-item">Head teachers on the mobile
								application</li>
							<li class="list-group-item">Teachers on the mobile
								application</li>
							<li class="list-group-item">Administrator of the entire
								system on the web application</li>
							<li class="list-group-item">Monitoring and Evaluation
								specialists on the web application system</li>
							<li class="list-group-item">Supervisor of the Monitoring and
								Evaluation specialists on the web application system</li>
						</ul>
					</div>
				</div>
			</div>
			<hr>
			<div class="card z-depth-0 bordered">
				<div class="card-header" id="headingTwo">
					<h5 class="mb-0">
						<button class="btn btn-link collapsed" type="button"
							data-toggle="collapse" data-target="#collapseTwo"
							aria-expanded="false" aria-controls="collapseTwo">
							Administrator Tasks - Web</button>
					</h5>
				</div>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionExample275">
					<div class="card-body">
						<p>When logged in as an administrator, the admin can do the
							following tasks</p>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><b>Reset all attendance
									records by: </b> ===> Click attendance ===> Select the appropriate
								filters to ensure the right records according the date, school,
								region, form and subject are displayed ===> click reset all.
								This action will change the flag of all entries shown to new.
								Only non approved entries can be reset</li>
							<li class="list-group-item"><b>Reset all DG packs
									records by: </b> ===> Click attendance ===> Select the appropriate
								filters to ensure the right records according the date, school,
								region, form and subject are displayed ===> click reset all.
								This action will change the flag of all entries shown to new.
								Only non approved entries can be reset</li>

							<li class="list-group-item"><b>Delete many records by: </b>===>
								Identify the range of record id's to be deleted ===> click on
								delete entries under the attendance and packs ===> inert
								starting entry id ===> insert last entry id ===> click Delete
								all. This action is <b>Irreversible</b></li>

							<li class="list-group-item"><b>View Teachers by: </b>===>
								Click school details on the menu ===> Click Teachers. This
								action lists all Teachers synchronized between the server and
								the various school Teachers captured in their tablets.</li>

							<li class="list-group-item"><b>View Students by: </b>===>
								Click school details on the menu ===> Click Students. This
								action lists all Students synchronized between the server and
								the various school Students captured in their tablets.</li>

							<li class="list-group-item"><b>View Schools by: </b>===>
								Click school details on the menu ===> Click Schools. This action
								lists all students synchronized between the server and the
								various Schools captured in the tablets.</li>

							<li class="list-group-item"><b>View Reports by: </b>===>
								Click Quick Stats ===> Choose report to be viewed.</li>

							<li class="list-group-item"><b>Log out of the system by:
							</b>===> Click Log out on the menu ===> This action redirects the
								user to the login form.</li>



						</ul>
					</div>
				</div>
			</div>


			<hr>
			<div class="card z-depth-0 bordered">
				<div class="card-header" id="headingThree">
					<h5 class="mb-0">
						<button class="btn btn-link collapsed" type="button"
							data-toggle="collapse" data-target="#collapseThree"
							aria-expanded="false" aria-controls="collapseThree">
							Monitoring and Evaluation Tasks - Web</button>
					</h5>
				</div>
				<div id="collapseThree" class="collapse"
					aria-labelledby="headingThree" data-parent="#accordionExample275">
					<div class="card-body">
						<p>When logged in as M &amp; E the M &amp; E can do the
							following tasks.</p>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><b>Verify all attendance
									records by: </b> ===> Click attendance ===> Select the appropriate
								filters to ensure the right records according the date, school,
								region, form and subject are displayed ===> click verify all.
								This action will change the flag of all entries shown to
								verified. The action will make verified entries visible to the
								supervisor profile. The administrator and supervisor can reset
								the entries to new</li>
							<li class="list-group-item"><b>Verify all Dg entries
									records by: </b> ===> Click attendance ===> Select the appropriate
								filters to ensure the right records according the date, school,
								region, form and subject are displayed ===> click verify all.
								This action will change the flag of all entries shown to
								verified. The action will make verified entries visible to the
								supervisor profile. The administrator and supervisor can reset
								the entries to new</li>

							<li class="list-group-item"><b>Pack a record by: </b> ===>
								Click attendance ===> Select the appropriate filters to ensure
								the right records according the date, school, region, form and
								subject are displayed ===> Click pack entry. This action allows
								the M &amp; E to make changes to a record as needed. All packed
								entries can be viewed in the Quick stats ===> Pending entries</li>

							<li class="list-group-item"><b>View Teachers by: </b>===>
								Click school details on the menu ===> Click Teachers. This
								action lists all Teachers synchronized between the server and
								the various school Teachers captured in their tablets.</li>

							<li class="list-group-item"><b>View Students by: </b>===>
								Click school details on the menu ===> Click Students. This
								action lists all Students synchronized between the server and
								the various school Students captured in their tablets.</li>

							<li class="list-group-item"><b>View Schools by: </b>===>
								Click school details on the menu ===> Click Schools. This action
								lists all students synchronized between the server and the
								various Schools captured in the tablets.</li>

							<li class="list-group-item"><b>View Reports by: </b>===>
								Click Quick Stats ===> Choose report to be viewed.</li>

							<li class="list-group-item"><b>Log out of the system by:
							</b>===> Click Log out on the menu ===> This action redirects the
								user to the login form.</li>

						</ul>
					</div>
				</div>
			</div>
			<hr>
			<div class="card z-depth-0 bordered">
				<div class="card-header" id="headingFour">
					<h5 class="mb-0">
						<button class="btn btn-link collapsed" type="button"
							data-toggle="collapse" data-target="#collapseFour"
							aria-expanded="false" aria-controls="collapseFour">
							Supervisor Tasks - Web</button>
					</h5>
				</div>
				<div id="collapseFour" class="collapse"
					aria-labelledby="headingFour" data-parent="#accordionExample275">
					<div class="card-body">
						<p>supervision the supervisor can do the following tasks.</p>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><b>Approve all attendance
									records by: </b> ===> Click attendance ===> Select the appropriate
								filters to ensure the right records according the date, school,
								region, form and subject are displayed ===> click verify all.
								This action will change the flag of all entries shown to
								verified. The action will make verified entries visible to the
								supervisor profile. The administrator and supervisor can reset
								the entries to new</li>
							<li class="list-group-item"><b>Approve all Dg entries
									records by: </b> ===> Click attendance ===> Select the appropriate
								filters to ensure the right records according the date, school,
								region, form and subject are displayed ===> click verify all.
								This action will change the flag of all entries shown to
								verified. The action will make verified entries visible to the
								supervisor profile. The administrator and supervisor can reset
								the entries to new</li>

							<li class="list-group-item"><b>Reset a record by: </b> ===>
								Click attendance ===> Select the appropriate filters to ensure
								the right records according the date, school, region, form and
								subject are displayed ===> Click reset entry. This action
								changes the entry flag to New and can be processed by the M
								&amp; E</li>

							<li class="list-group-item"><b>View Teachers by: </b>===>
								Click school details on the menu ===> Click Teachers. This
								action lists all Teachers synchronized between the server and
								the various school Teachers captured in their tablets.</li>

							<li class="list-group-item"><b>View Students by: </b>===>
								Click school details on the menu ===> Click Students. This
								action lists all Students synchronized between the server and
								the various school Students captured in their tablets.</li>

							<li class="list-group-item"><b>View Schools by: </b>===>
								Click school details on the menu ===> Click Schools. This action
								lists all students synchronized between the server and the
								various Schools captured in the tablets.</li>

							<li class="list-group-item"><b>View Reports by: </b>===>
								Click Quick Stats ===> Choose report to be viewed.</li>

							<li class="list-group-item"><b>Log out of the system by:
							</b>===> Click Log out on the menu ===> This action redirects the
								user to the login form.</li>

						</ul>
					</div>
				</div>
			</div>
			<hr>
			<div class="card z-depth-0 bordered">
				<div class="card-header" id="headingFive">
					<h5 class="mb-0">
						<button class="btn btn-link collapsed" type="button"
							data-toggle="collapse" data-target="#collapseFive"
							aria-expanded="false" aria-controls="collapseFive">M
							&amp; E Tasks - Mobile</button>
					</h5>
				</div>
				<div id="collapseFive" class="collapse"
					aria-labelledby="headingFour" data-parent="#accordionExample275">
					<div class="card-body">
						<p>When logged in as an M &amp; E, the M &amp; E can do the
							following tasks.</p>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><b>Create a new School by: </b>
								===> Click new school ===> enter school details as required ===>
								click create school. Only one school should be created per
								tablet</li>
							<li class="list-group-item"><b>Create a new Head Teacher
									by: </b> ===> Click Head teacher ===> enter Head teacher details as
								required ===> click create Head teacher. Only one Head Teacher
								should be created per tablet</li>
							<li class="list-group-item"><b>Create a new Teacher by:
							</b> ===> Click Teachers ===> enter Teacher details as required ===>
								click create Teacher.</li>
							<li class="list-group-item"><b>Edit a head Teacher
									details by: </b> ===> Click Edit HeadTeacher ===> enter edited Head
								Teacher details as required ===> click submit Head Teacher.</li>
							<li class="list-group-item"><b>View school details by: </b>
								===> Click school. You can click home to return to M &amp; E
								home page</li>
							<li class="list-group-item"><b>View Head Teacher details
									by: </b> ===> Click Head teacher. You can click home to return to M
								&amp; E home page</li>
							<li class="list-group-item"><b>Synch students to the
									online server by: </b> ===> Enable Internet on the tablet===> Click
								Synch students. You can click home to return to M &amp; E home
								page</li>
							<li class="list-group-item"><b>Import students from the
									online server by: </b> ===> Enable Internet on the tablet===> Click
								Import students. This action wipes out the students in the
								tablet and imports new ones. You can click home to return to M
								&amp; E home page</li>
							<li class="list-group-item"><b>Reset attendance by: </b>
								===> Click Reset attendance. This action is only to be done at
								the beginning of the school year after all the records have been
								synchronized to the online server. This action wipes out all
								attendance related data from the tablet. You can click home to
								return to M &amp; E home page</li>
						</ul>
					</div>
				</div>
			</div>
			<hr>
			<div class="card z-depth-0 bordered">
				<div class="card-header" id="headingsix">
					<h5 class="mb-0">
						<button class="btn btn-link collapsed" type="button"
							data-toggle="collapse" data-target="#collapsesix"
							aria-expanded="false" aria-controls="collapsesix">
							Teacher Tasks - Mobile</button>
					</h5>
				</div>
				<div id="collapsesix" class="collapse" aria-labelledby="headingsix"
					data-parent="#accordionExample275">
					<div class="card-body">
						<p>When logged in as Teacher, the Teacher can do the
							following tasks.</p>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><b>Create a New Student by:
							</b> ===> Click New Student ===> enter the Student details as
								required ===> click Submit Student. You can click home to return to Teacher home
								page</li>

							<li class="list-group-item"><b>Edit Students by: </b> ===>
								Click Edit Student ===> choose student id ===> enter the Student
								details as required ===> click Submit Student. You can click home to return to Teacher home
								page</li>

							<li class="list-group-item"><b>View students details by:
							</b> ===> Click View Students. You can click home to return to Teacher home
								page</li>

							<li class="list-group-item"><b>Delete Students by: </b> ===>
								Click Delete Student ===> choose student id ===> click Delete
								Student. You can click home to return to Teacher home
								page</li>


							<li class="list-group-item"><b>Capture Attendance by: </b>
								===> Click Start Attendance ===> Select Class ===> Select date
								of class ===> Select Subject ===> Register Attendance. You can click home to return to Teacher home
								page</li>

							<li class="list-group-item"><b>View Attendance Report
									by: </b> ===> Click View Report ===> Select Class ===> Select date
								of class ===> Select Subject ===> View Report. You can click home to return to Teacher home
								page</li>



							<li class="list-group-item"><b>Capture DG Pack Issuance
									by: </b> ===> Click DG Packs ===> Select Class ===> Select date of
								class ===> Select Subject ===> Register DG Packs. You can click home to return to Teacher home
								page</li>

							<li class="list-group-item"><b>View DG Packs Report by:
							</b> ===> Click View DG Packs Report ===> Select Class ===> Select
								date of class ===> Select Subject ===> View Report. You can click home to return to Teacher home
								page</li>
								
								<li class="list-group-item"><b>Synch students to the
									online server by: </b> ===> Enable Internet on the tablet===> Click
								Synch students. You can click home to return to Teacher home
								page</li>
								
								
									<li class="list-group-item"><b>Synch Attendance to the
									online server by: </b> ===> Enable Internet on the tablet===> Click
								Synchronize. You can click home to return to Teacher home
								page</li>
								
								
									<li class="list-group-item"><b>Synch DG Packs to the
									online server by: </b> ===> Enable Internet on the tablet===> Click
								Synchronize DG Packs. You can click home to return to  Teacher home
								page</li>


						</ul>
					</div>
				</div>
			</div>
			<hr>
			<div class="card z-depth-0 bordered">
				<div class="card-header" id="headingSeven">
					<h5 class="mb-0">
						<button class="btn btn-link collapsed" type="button"
							data-toggle="collapse" data-target="#collapseSeven"
							aria-expanded="false" aria-controls="collapseSeven">Head
							Teacher Tasks - Mobile</button>
					</h5>
				</div>
				<div id="collapseSeven" class="collapse"
					aria-labelledby="headingSeven" data-parent="#accordionExample275">
					<div class="card-body">
						<p>When logged in as Teacher, the Teacher can do the
							following tasks.</p>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><b>Create a New Teacher by:
							</b> ===> Click New Teacher ===> enter the Teacher details as
								required ===> click Create Teacher. You can click home to return to the Head Teacher home page</li>
								
								<li class="list-group-item"><b>Edit a Teacher by:</b> ===> Click Edit Teacher ===> Select the National ID of Teacher ===> click Edit Teacher ===> enter the Teacher details as
								required ===> click Update Teacher. You can click home to return to the Head Teacher home page</li>
								
								<li class="list-group-item"><b>Edit a Student by:</b> ===> Click Edit Student ===> Select the National ID of Student ===> click Edit Student ===> enter the Student details as
								required ===> click Update Student. You can click home to return to the Head Teacher home page</li>
								
								
								<li class="list-group-item"><b>Delete Teacher by: </b> ===>
								Click Delete Teacher ===> choose Teacher id ===> click Delete
								Teacher. You can click home to return to the Head Teacher home page</li>
								
								
								<li class="list-group-item"><b>View students details by:
							</b> ===> Click View Students. You can click home to return to the Head Teacher home page</li>
								
								<li class="list-group-item"><b>View Teachers details by:
							</b> ===> Click View Teachers. You can click home to return to the Head Teacher home page</li>
								
								<li class="list-group-item"><b>View Attendance details by:
							</b> ===> Click View Attendance. You can click home to return to the Head Teacher home page</li>
								
								
								

						</ul>
					</div>
				</div>
			</div>

		</div>
	</div>


</body>
</html>