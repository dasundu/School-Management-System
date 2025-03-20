
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if
	test="${empty sessionScope.loginuser || empty sessionScope.loginrole}">
	<c:redirect url="login.jsp" />
</c:if>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<style>
/* Hide the scrollbar */
body::-webkit-scrollbar {
	width: 0.0em;
}

body::-webkit-scrollbar-track {
	background-color: #f5f5f5;
}

body::-webkit-scrollbar-thumb {
	background-color: #4f52ff;
	outline: 1px solid slategrey;
}

/* Style for the modal */
.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.7);
	/* Semi-transparent black background */
}

.modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

/* Style for the close button */
.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}
/* Styles for the pop-up */
.popup {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.7);
}

.popup p {
	font-weight: 600;
}

.popup-content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: white;
	width: fit-content;
	height: fit-content;
	text-align: center;
	padding: 1%;
}

.popup-contentUpdate {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: white;
	width: 20vw;
	height: fit-content;
	text-align: center;
}

.popup-contentUpdate label {
	font-weight: 600;
	margin-right: 13vw;
}

h1 {
	color: #4f52ff;
	text-shadow: 1px 1px 1px #e8eefe;
	padding: 5%;
	margin-bottom: 2vh;
}

/* Blur effect for the background when the pop-up is displayed */
body.blur {
	filter: blur(4px);
}

input, select, option {
	width: 15vw;
	height: 4vh;
	border: none;
	box-shadow: 1px 1px 1px 1px #4f52ff;
	padding: 10px;
	background-color: #e8eefe;
	transition-duration: 0.3s;
}

input:focus, select:focus {
	scale: 1.0;
}

#searchInput {
	height: 4vh;
	background-color: white;
	border: none;
	box-shadow: none;
	font-size: 1.8vh;
}

#searchInput:focus {
	scale: 1;
	border: none;
	outline: none; /* Remove the default outline */
	box-shadow: none
}

h3 {
	margin-top: 4%;
	display: inline;
}

li {
	display: flex;
	align-items: center; /* Align items vertically in the center */
	list-style-type: none; /* Remove default list styles */
}

img {
	margin-right: 5px;
	/* Adjust the margin between the image and the paragraph */
}

.header {
	display: flex;
	flex-direction: row;
	align-self: center;
}

.header h1 {
	color: #4f52ff;
	font-size: 4vh;
	
}

.logo {
	display: flex;
	flex-direction: row;
	align-items: center;
	margin-left: 1%;
	transition-duration: 0.3s;
}

.tab {
	display: flex;
	flex-direction: row;
	margin: 3%;
	font-weight: 600;
	font-size: larger;
	transition-duration: 0.3s;
	color: #4f52ff;
	border-radius: 5%;
}

.tab label {
	margin-left: 25%;
}

.tab:hover {
	scale: 1.03;
	text-shadow: 1px 1px 1px #757787;
	cursor: pointer;
	box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5);
}

.dashboard {
	display: flex;
	flex-direction: row;
	width: 100%;
}

.dashboard .panel {
	display: flex;
	flex-direction: column;
	width: fit-content;
}

.dashboard .dataTable {
	display: flex;
	flex-direction: column;
}

#tblDetail th {
	width: 10vw;
	text-align: center;
}

#tblDetail tr {
	transition-duration: 0.2s;
}

#tblDetail tr:hover {
	background-color: #c5d6fe;
}

#tblDetail td {
	border: 2px solid #c5d6fe;
	text-align: center;
}

#tblDetail {
	max-width: 100%;
	margin: 2%;
	table-layout: fixed;
	box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5);
	margin-bottom: 5%;
}

#tblDetail #headlines {
	background-color: #ecf1ff;
	color: #4f52ff;
	font-size: 2vh;
	border: 2px solid #c5d6fe;
	height: fit-content;
}

#tblDetail td {
	font-weight: 500;
	width: fit-content;
}

#altBtn {
	background-color: #4f52ff;
	color: white;
	border: 2px solid #c5d6fe;
	height: 4vh;
	width: fit-content;
	transition-duration: 0.2s;
	font-weight: 600;
	margin-bottom: 1vh;
}

#altBtn:hover {
	scale: 1.02;
	box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5);
}

hr {
	color: #4f52ff;
}

.profileIcon {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

a {
	font-weight: 600;
}

#yesBtn {
	background-color: #4f52ff;
	color: white;
	border: 2px solid #c5d6fe;
	height: 4vh;
	width: 5vw;
	transition-duration: 0.2s;
	font-weight: 600;
	margin-bottom: 1vh;
}

#noBtn {
	background-color: #4f52ff;
	color: white;
	border: 2px solid #c5d6fe;
	height: 4vh;
	width: 5vw;
	transition-duration: 0.2s;
	font-weight: 600;
	margin-bottom: 1vh;
}

#noBtn:hover, #yesBtn:hover {
	scale: 1.02;
}

#upImage {
	border-radius: 100px;
}

#manageStudents, #classes, #res, #set#ass#subject, #addStudent, #login,
	#Teacher, #classes {
	padding: 5%;
	text-align: left;
	transition-duration: 0.3s;
}

#manageStudents:hover, #classes:hover, #res:hover, #set:hover, #ass:hover,
	#subject:hover, #addStudent:hover, #login:hover, #Teacher:hover,
	#classes:hover {
	background-color: #4f52ff;
	color: white;
	cursor: pointer;
}

.bar {
	height: 4vh;
	background-color: #4f52ff;
	transition: width 0.5s;
}

.loader {
	position: fixed;
	top: 0;
	left: 0;
	width: 100vw;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: #f7f9fb;
	transition: opacity 1s, visibility 1s;
}

.loader-hidden {
	opacity: 0;
	visibility: hidden
}

.loader::after {
	content: "";
	width: 75px;
	height: 75px;
	border: 15px solid #dddddd;
	border-top-color: #7449f5;
	border-radius: 50%;
	animation: loading 0.75s ease infinite;
}

@
keyframes loading {from { transform:rotate(0turn);
	
}

to {
	transform: rotate(1turn);
}
}

#dashBoard{
	height:90vh;
	width:100%;
	background-image: url("./images/dashbgp.svg");
	background-size: contain;
	background-repeat: no-repeat;
	background-position: center; 
}
#logoutLink{
	background-color:#273239;
	color:white;
	font-size:2vh;
	font-weight:600;
	border-radius:2%;
	transition-duration:0.3s;
}
#logoutLink:hover{
	scale:1.03;
	cursor:pointer;
}
</style>

</head>

<body style="margin:1%;background-color:#273239;">

	<div class="loader"></div>


	<!--Header-->
	<div class="header" style="padding:1%;background-color:white;border-radius:71% 79% 0% 34% / 100% 100% 10% 0% ">
		<div class="logo">
			<img src="./images/mynew.svg" height="200" width="200">
				<h1 style="font-size:8vh">EduHub</h1>
		</div>


		<!--Profile -->
		<div class="profileIcon" style="margin-left:66%">
			<div>
				<img src="data:image/png;base64,${sessionScope.userImage}"
					alt="Profile Image" width="120" height="120"
					style="border-radius: 50%;">
			</div>
			<div>
				<button class="btn btn-secondary dropdown-toggle" type="button"
					data-bs-toggle="dropdown" aria-expanded="false"
					style="background-color: white; border: none; color: #4f52ff; text-shadow: 4px 4px 2px #c5d6fe; font-weight: 600; text-align: center;">${sessionScope.loginuser}</button>
				
				<ul class="dropdown-menu" style="padding: 1%; margin-top: 2%; flex-direction: column; justify-content: center; align-items: center; width: fit-content;text-align:center;border:none;box-shadow: 0 4px 18px rgba(79, 82, 255, 0.5);width:fit-content">
					
					<li><img src="data:image/png;base64,${sessionScope.userImage}" alt="Profile Image" width="300" height="300" style="border-radius: 50%;"></li><br>
					<li><p style="color:white; font-weight: 600;text-align:center;font-size:1.5vh;background-color:#4f52ff;padding:4%;">${sessionScope.loginrole}</p></li>
					<li><p style="color: #273239; font-weight: 600;text-align:center;font-size:2vh;"><img src="./images/goog.png" width="25" height="25">${sessionScope.loginEmail}</p></li>

					<li><a class="dropdown-item" href="logout" id="logoutLink">Log Out</a></li>
				</ul>
			</div>
		</div>
	</div>


	
	
	<!--Dashboard-->
	<div class="dashboard" style="displaye: flex; flex-direction: row;background-color:white;border-bottom-right-radius: 5%;">
		<div class="panel" style="display: flex; flex-direction: column; width: 20vw; padding: 2%; height: fit-content; background-color: #f4f4f5;border-top-left-radius: 0%;border-top-right-radius: 80%;border-bottom-left-radius: 0%;border-bottom-right-radius: 80%">



			<!-- Dashboard -->
			<button class="btn btn-primary" type="button"
				onclick='redirectTo("http://localhost:8090/TestingPROJECT/DashboardLoader")'
				data-bs-toggle="collapse" data-bs-target=".no-collapse"
				aria-expanded="false"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 18px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/dashboard.gif" width="80" height="80"
					style="margin-right: 5%">Dashboard
			</button>

			<div class="column" style="padding: 3%"></div>


			<!-- Logins Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".login-collapse"
				aria-expanded="false" aria-controls="login"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/loginm.gif" width="80" height="80"
					style="margin-right: 5%">Logins
			</button>

			<div class="column" style="padding: 3%">
				<div class="col">
					<div class="collapse multi-collapse login-collapse" id="login"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/PageLoadUsersManagement")'
						style="padding: 2%">
						<h6>Manage Logins</h6>
					</div>
				</div>
				<div class="col">
					<div class="collapse multi-collapse login-collapse" id="login"
						onclick='redirectTo("add_new_login.jsp")' style="padding: 2%">
						<h6>Add New Login</h6>
					</div>
				</div>
			</div>


			<!-- Student Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".students-collapse"
				aria-expanded="false" aria-controls="addStudent manageStudents"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/Students.gif" width="80" height="80"
					style="margin-right: 5%">Students
			</button>

			<div class="column" style="padding: 3%">
				<div class="col">
					<div class="collapse multi-collapse students-collapse"
						id="addStudent"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/add_new_student_page_loader")'
						style="padding: 1%">
						<h6>Enroll a new Student</h6>
					</div>
				</div>
				<div class="col">
					<div class="collapse multi-collapse students-collapse"
						id="manageStudents"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/studentsPageLoader")'
						style="padding: 1%">
						<h6>Manage Students</h6>
					</div>
				</div>
			</div>

			<!-- Teachers Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".teachers-collapse"
				aria-expanded="false" aria-controls="Teacher"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/teacherm.gif" width="80" height="80"
					style="margin-right: 5%">Teachers
			</button>

			<div class="column" style="padding: 3%">
				<div class="col">
					<div class="collapse multi-collapse teachers-collapse" id="Teacher"
						onclick='redirectTo("add_new_teacher.jsp")' style="padding: 2%">
						<h6>Add Teacher</h6>
					</div>
				</div>
				<div class="col">
					<div class="collapse multi-collapse teachers-collapse" id="Teacher"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/teachersPageLoader")'
						style="padding: 2%">
						<h6>Manage Teachers</h6>
					</div>
				</div>
			</div>


			<!-- Classes Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".classes-collapse"
				aria-expanded="false" aria-controls="classes"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/classm.gif" width="80" height="80"
					style="margin-right: 5%">Classes
			</button>

			<div class="column" style="padding: 3%">
				<div class="col">
					<div class="collapse multi-collapse classes-collapse" id="classes"
						style="padding: 2%"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/admin_classes_loader")'>
						<h6>Manage Classes</h6>
					</div>
				</div>
				<div class="col">
					<div class="collapse multi-collapse classes-collapse" id="classes"
						style="padding: 2%" onclick='redirectTo("add_new_class.jsp")'>
						<h6>Add new class</h6>
					</div>
				</div>
				<div class="col">
					<div class="collapse multi-collapse classes-collapse" id="classes"
						style="padding: 2%"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/assign_class_teacher_loader")'>
						<h6>Assign teachers</h6>
					</div>
				</div>
				<div class="col">
					<div class="collapse multi-collapse classes-collapse" id="classes"
						style="padding: 2%"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/assign_class_rep_loader")'>
						<h6>Assign representatives</h6>
					</div>
				</div>
			</div>

			<!-- Subject Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".subject-collapse"
				aria-expanded="false" aria-controls="subject"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/subjectm.gif" width="80" height="80"
					style="margin-right: 5%">Subjects
			</button>

			<div class="column" style="padding: 3%">
				<div class="col">
					<div class="collapse multi-collapse subject-collapse" id="subject"
						style="padding: 2%"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/adminSubjectsPageLoader")'>
						<h6>Manage Subjects</h6>
					</div>
				</div>
				<div class="subject">
					<div class="collapse multi-collapse subject-collapse" id="subject"
						style="padding: 2%" onclick='redirectTo("add_new_subject.jsp")'>
						<h6>Add New Subject</h6>
					</div>
				</div>
				<div class="col">
					<div class="collapse multi-collapse subject-collapse" id="subject"
						style="padding: 2%"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/subject_assign_loader")'>
						<h6>Assign Teachers</h6>
					</div>
				</div>
			</div>

			<!-- Assignment Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".ass-collapse"
				aria-expanded="false" aria-controls="ass"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/assm.gif" width="80" height="80"
					style="margin-right: 5%">Assignments
			</button>

			<div class="column" style="padding: 3%">
				<div class="col">
					<div class="collapse multi-collapse ass-collapse" id="ass"
						style="padding: 2%"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/admin_assignment_loader")'>
						<h6>Manage Assignments</h6>
					</div>
				</div>
				<div class="col">
					<div class="collapse multi-collapse ass-collapse" id="ass"
						style="padding: 2%"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/admin_submitted_assignment_manage_loader")'>
						<h6>Assignment Submissions</h6>
					</div>
				</div>
			</div>



			<!-- Results Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".res-collapse"
				aria-expanded="false" aria-controls="res"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/results.gif" width="80" height="80"
					style="margin-right: 5%">Results
			</button>

			<div class="column" style="padding: 3%">
				<div class="col">
					<div class="collapse multi-collapse res-collapse" id="res"
						style="padding: 2%"
						onclick='redirectTo("http://localhost:8090/TestingPROJECT/admin_published_results_loader")'>
						<h6>Published Results</h6>
					</div>
				</div>
			</div>

			<!-- Settings Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".set-collapse"
				aria-expanded="false" aria-controls="set"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/gSettings.gif" width="80" height="80"
					style="margin-right: 5%">Settings
			</button>

			<div class="column" style="padding: 3%">
				<div class="col">
					<div class="collapse multi-collapse set-collapse" id="set"
						style="padding: 2%"
						onclick='redirectTo("admin_update_account.jsp")'>
						<h6>Account Update</h6>
					</div>
				</div>
				<div class="column">
					<div class="col">
						<div class="collapse multi-collapse set-collapse" id="set"
							style="padding: 2%"
							onclick='redirectTo("admin_profile_deletion.jsp")'>
							<h6>Account Deletion</h6>
						</div>
					</div>
				</div>
			</div>
		</div>



		<div style="width:85vw;margin:2%;" id ="dashBoard">
			<div style="display: flex; flex-flow: nowrap;">

				<div
					style="displaY: flex; flex-direction: column; width: 15vw; height: 25vh; margin: 1%; box-shadow: 0 4px 8px #e0e0e0; padding: 1%; border-radius: 2%; justify-content: center; align-items: center; background-color: white;text-align:center;">
					<p
						style="font-size: 3vh; font-weight: 600;color: #455b65;">
						<img src="./images/loginm.gif" width="100" height="100"><br>Users
					</p>
					<div
						style="displaY: flex; flex-direction: column; justify-content: left; align-items: center;">
						<p style="font-size: 5vh;">${requestScope.numberofusers}</p>
					</div>
				</div>


				<div
					style="displaY: flex; flex-direction: column; width: 15vw; height: 25vh; margin: 1%; box-shadow: 0 4px 8px #e0e0e0; padding: 1%; border-radius: 2%; justify-content: center; align-items: center; background-color: white;text-align:center;">
					<p
						style="font-size: 3vh; font-weight: 600; color: #455b65;">
						<img src="./images/teacherm.gif" width="100" height="100"><br>Teachers
					</p>
					<div
						style="displaY: flex; flex-direction: column; justify-content: left; align-items: center;">
						<p style="font-size: 5vh;">${requestScope.number_of_teachers}</p>
					</div>
				</div>

				<div
					style="displaY: flex; flex-direction: column; width: 15vw; height: 25vh; margin: 1%; box-shadow: 0 4px 8px #e0e0e0; padding: 1%; border-radius: 2%; justify-content: center; align-items: center; background-color: white;text-align:center;">
					<p
						style="font-size: 3vh; font-weight: 600; color: #455b65;">
						<img src="./images/Students.gif" width="100" height="100"><br>Students
					</p>
					<div
						style="displaY: flex; flex-direction: column; justify-content: left; align-items: center;">
						<p style="font-size: 5vh;">${requestScope.number_of_students}</p>
					</div>
				</div>

				<div
					style="displaY: flex; flex-direction: column; width: 15vw; height: 25vh; margin: 1%; box-shadow: 0 4px 8px #e0e0e0; padding: 1%; border-radius: 2%; justify-content: center; align-items: center; background-color: white;text-align:center;">
					<p
						style="font-size: 3vh; font-weight: 600; color: #455b65;">
						<img src="./images/classm.gif" width="100" height="100"><br>Classes
					</p>
					<div
						style="displaY: flex; flex-direction: column; justify-content: left; align-items: center;">
						<p style="font-size: 5vh;">${requestScope.number_of_classes}</p>
					</div>
				</div>

				<div
					style="displaY: flex; flex-direction: column; width: 15vw; height: 25vh; margin: 1%; box-shadow: 0 4px 8px #e0e0e0; padding: 1%; border-radius: 2%; justify-content: center; align-items: center; background-color: white">
					<p
						style="font-size: 3vh; font-weight: 600; color: #455b65;">
						<img src="./images/subjectm.gif" width="100" height="100"><br>Subjects
					</p>
					<div
						style="displaY: flex; flex-direction: column; justify-content: left; align-items: center;">
						<p style="font-size: 5vh;">${requestScope.number_of_subjects}</p>
					</div>
				</div>
			</div>

		
			<div
				style="width: 74.5vw; margin: 1%; background-color: white; padding: 2%; box-shadow: 0 4px 8px #e0e0e0; border-radius: 1%;background-color: rgba(255, 255, 255, 0.9);">

				<h1
					style="text-align: center; font-size: 5vh;color: #263339; margin-bottom: -5vh;">- Classwise
					Student Distribution Chart -</h1>


				<table style="margin: 2%">
				
					<c:forEach var="classr" items="${Classes}">
						<tr
							style="displaY: flex; flex-direction: row; align-items: center; height: 10vh">
							<td
								style="width: 5vw; font-size: 2.6vh; font-weight: 600; color: #263339;">${classr.getClassName()}</td>
							<td style="diplay: flex; flex-direction: row"><div
									class="bar" style="width: ${classr.getNumberOfStudents()}cm;"></div></td>
							<td
								style="width: 2vw; font-size: 2vh; font-weight: 600; color: white; background-color: #263238; height: 4vh; text-align: center">${classr.getNumberOfStudents()}</td>
						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>



	<script>
	window.addEventListener("load", () => {
		  const loader = document.querySelector(".loader");

		  loader.classList.add("loader-hidden");

		  loader.addEventListener("transitionend", () => {
		    document.body.removeChild("loader");
		  });
		});	
	
	
	
		function redirectTo(link) {
			window.location.href = link;
		}

		//Servlet Redirection
		function redirectToServlet(servlet) {
			var servletURL = 'http://localhost:8090/TestingPROJECT/' + servlet;
			window.location.href = servletURL;
		}

		//Show delete box
		function showPopupDelete(appId) {
			// Set the user ID in the popup content
			document.getElementById("userIdSpan").value = appId;

			document.getElementById('delete').style.display = 'block';
		}

		//Show approve box
		function showPopupUpdate(appid) {
			document.getElementById("appid").value = appid;

			document.getElementById('update').style.display = 'block';
		}

		//Close update box
		function closePopupUpdate() {
			document.getElementById('update').style.display = 'none';
			document.body.classList.remove('blur');
			event.preventDefault();
		}

		//CLose delete
		function closePopupDelete() {
			document.getElementById('delete').style.display = 'none';
			document.body.classList.remove('blur');
			event.preventDefault();
		}
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
</body>

</html>