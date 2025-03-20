
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
	text-shadow: 4px 4px 2px #c5d6fe;
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

form label {
	font-weight: 600;
	font-size: 1.7vh;
	color: #263339;
}

form legend {
	font-size: 3.5vh;
	color: #263339;
}

form input {
	background-color: white;
	border: none;
	box-shadow: 2px 2px 2px 2px #e0e0e0;
}

form option {
	font-weight: 600;
	box-shadow: 2px 2px 2px 2px #e0e0e0;
	background-color: white;
}

form select {
	font-weight: 600;
	box-shadow: 2px 2px 2px 2px #e0e0e0;
	background-color: white;
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
	background-color: #f5f5ff;
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
	border: 15px solid white;
	border-top-color: #4f52ff;
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
#wall {
	height: fit-content;
	width: 100%;
	background-image: url("./images/accupdate.svg");
	background-size: contain;
	background-repeat: no-repeat;
	background-position: right;
}

#logoutLink {
	background-color: #273239;
	color: white;
	font-size: 2vh;
	font-weight: 600;
	border-radius: 2%;
	transition-duration: 0.3s;
}

#logoutLink:hover {
	scale: 1.03;
	cursor: pointer;
}
</style>

</head>

<body style="margin: 1%; background-color: #273239;">
	<div class="loader"></div>


	<!-- Hidden Input to check  update status -->
	<input type="hidden" value="${creation}" id="loginCreationStatus">

	<!-- Hidden Input to check  student deletion -->
	<input type="hidden" value="${deletion}" id="deletionStatus">

	<!-- Update  success box -->
	<div id="loginSuccessBox" class="popup">
		<div class="popup-content" style="width: 25vw; height: 30vh">
			<h3 style="color: #4f52ff;">Profile Update Successful!</h3>
			<br> <img src="./images/appok.gif" height="200" width="200"><br>
			<button id="noBtn" onclick="closePopupLoginSuccess()">Ok</button>
			<br>
		</div>
	</div>


	<!-- Delete success box -->
	<div id="deleteSuccessBox" class="popup">
		<div class="popup-content" style="width: 25vw; height: fit-content">
			<h3 style="color: #4f52ff;">Wrong Password</h3>
			<br>
			<p style="font-weight: 600;">Your entered password is incorrect</p>
			<img src="./images/No.gif" height="200" width="200"><br>
			<button id="noBtn" onclick="closePopupDeleteSuccess()">Try
				Again</button>
			<br>
		</div>
	</div>


	<!--Header-->
	<div class="header"
		style="padding: 1%; background-color: white; border-radius: 71% 79% 0% 34%/100% 100% 10% 0%">
		<div class="logo">
			<img src="./images/mynew.svg" height="200" width="200">
			<h1 style="font-size: 8vh">EduHub</h1>
		</div>


		<!--Profile -->
		<div class="profileIcon" style="margin-left: 66%">
			<div>
				<img src="data:image/png;base64,${sessionScope.userImage}"
					alt="Profile Image" width="120" height="120"
					style="border-radius: 50%;">
			</div>
			<div>
				<button class="btn btn-secondary dropdown-toggle" type="button"
					data-bs-toggle="dropdown" aria-expanded="false"
					style="background-color: white; border: none; color: #4f52ff; text-shadow: 4px 4px 2px #c5d6fe; font-weight: 600; text-align: center;">${sessionScope.loginuser}</button>

				<ul class="dropdown-menu"
					style="padding: 1%; margin-top: 2%; flex-direction: column; justify-content: center; align-items: center; width: fit-content; text-align: center; border: none; box-shadow: 0 4px 18px rgba(79, 82, 255, 0.5); width: fit-content">

					<li><img src="data:image/png;base64,${sessionScope.userImage}"
						alt="Profile Image" width="300" height="300"
						style="border-radius: 50%;"></li>
					<br>
					<li><p
							style="color: white; font-weight: 600; text-align: center; font-size: 1.5vh; background-color: #4f52ff; padding: 4%;">${sessionScope.loginrole}</p></li>
					<li><p
							style="color: #273239; font-weight: 600; text-align: center; font-size: 2vh;">
							<img src="./images/goog.png" width="25" height="25">${sessionScope.loginEmail}</p></li>

					<li><a class="dropdown-item" href="logout" id="logoutLink">Log
							Out</a></li>
				</ul>
			</div>
		</div>
	</div>




	<!--Dashboard-->
	<div class="dashboard"
		style="displaye: flex; flex-direction: row; background-color: white; border-bottom-right-radius: 5%;">
		<div class="panel"
			style="display: flex; flex-direction: column; width: 20vw; padding: 2%; height: fit-content; background-color: #f4f4f5; border-top-left-radius: 0%; border-top-right-radius: 80%; border-bottom-left-radius: 0%; border-bottom-right-radius: 80%">

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



			<!-- Assignment Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".ass-collapse"
				aria-expanded="false" aria-controls="ass"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 18px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/assignment.gif" width="80" height="80"
					style="margin-right: 5%">Assignments
			</button>

			<div class="column" style="padding: 3%">
				<div class="col"
					onclick='redirectTo("http://localhost:8090/TestingPROJECT/student_new_assignment_loader")'>
					<div class="collapse multi-collapse ass-collapse" id="ass"
						style="padding: 3%">
						<h6>Announced Assignments</h6>
					</div>
				</div>
				<div class="col"
					onclick='redirectTo("http://localhost:8090/TestingPROJECT/student_assignment_marks_loader")'>
					<div class="collapse multi-collapse ass-collapse" id="ass"
						style="padding: 3%">
						<h6>Assignment Marks</h6>
					</div>
				</div>
			</div>


			<!-- Subjects Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".sub-collapse"
				aria-expanded="false" aria-controls="sub"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 18px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/dashboard.gif" width="80" height="80"
					style="margin-right: 5%">Subjects
			</button>

			<div class="column" style="padding: 3%">
				<div class="col"
					onclick='redirectTo("http://localhost:8090/TestingPROJECT/student_subject_info_loader")'>
					<div class="collapse multi-collapse sub-collapse" id="sub"
						style="padding: 3%">
						<h6>My Subjects</h6>
					</div>
				</div>
				<div class="col"
					onclick='redirectTo("http://localhost:8090/TestingPROJECT/student_enroll_for_subjects_info_loader")'>
					<div class="collapse multi-collapse sub-collapse" id="sub"
						style="padding: 3%">
						<h6>Enroll for a Subject</h6>
					</div>
				</div>
			</div>


			<!-- Results Manage -->
			<button class="btn btn-primary" type="button"
				data-bs-toggle="collapse" data-bs-target=".res-collapse"
				aria-expanded="false" aria-controls="res"
				style="font-weight: 600; text-align: left; background-color: white; color: #4f52ff; border: none; box-shadow: 0 4px 18px rgba(79, 82, 255, 0.5); font-size: 2vh;">
				<img src="./images/results.gif" width="80" height="80"
					style="margin-right: 5%">Results
			</button>

			<div class="column" style="padding: 3%">
				<div class="col"
					onclick='redirectTo("http://localhost:8090/TestingPROJECT/student_results_info")'>
					<div class="collapse multi-collapse res-collapse" id="res"
						style="padding: 3%">
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

			<div class="column" style="padding: 3%;">
				<div class="col" onclick='redirectTo("student_update_account.jsp")'>
					<div class="collapse multi-collapse set-collapse show" id="set"
						style="padding: 3%; background-color: #4f52ff; color: white; cursor: pointer;">
						<h6>Account Update</h6>
					</div>
				</div>
				<div class="column">
					<div class="col"
						onclick='redirectTo("student_account_deletion.jsp")'>
						<div class="collapse multi-collapse set-collapse show" id="set"
							style="padding: 3%">
							<h6>Account Deletion</h6>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div style="display: flex; flex-direction: row; width: 70vw;"
			id="wall">

			<form action="user_update_profile" method="post"
				enctype="multipart/form-data"
				style="margin: 5%; box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5); padding: 3%; width: fit-content; display: flex; flex-direction: column; justify-content: center; height: fit-content; background-color: rgba(255, 255, 255, 0.93);">

				<input type="hidden" value="${sessionScope.loginId}" name="userid">

				<div
					style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
					<img src="data:image/png;base64,${sessionScope.userImage}"
						alt="Profile Image" width="200" height="200"
						style="border-radius: 50%;">
				</div>
				<br>
				<br> <label style="font-weight: 600; font-size: 2vh;">User
					Name</label> <input type="text" required id="pw" name="username"
					style="font-weight: 600;" value="${sessionScope.loginuser}"><br />

				<label style="font-weight: 600; font-size: 2vh;">Email</label> <input
					type="email" required id="pw" name="email"
					style="font-weight: 600;" value="${sessionScope.loginEmail}"><br>


				<label style="font-weight: 600; font-size: 2vh;">Change
					Profile Image</label> <input type="file" name="pic"><br>
				<br>


				<div
					style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
					<button type="submit" id="altBtn" style="width: 8vw">Update</button>
				</div>

			</form>



			<form action="change_password" method="post"
				style="margin: 5%; box-shadow: 0 4px 8px rgba(79, 82, 255, 0.5); padding: 3%; width: 30vw; display: flex; flex-direction: column; justify-content: center; height: fit-content; background-color: rgba(255, 255, 255, 0.93);"
				onsubmit="return handleSubmit()">

				<input type="hidden" value="${sessionScope.loginId}" name="userid">

				<h1 style="text-align: center; color: black">Password Change</h1>

				<label style="font-weight: 600; font-size: 2vh;">Current
					Password</label> <input type="password" required name="currentPassword"
					id="currentPassword" style="font-weight: 600;"><br /> <label
					style="font-weight: 600; font-size: 2vh;">New Password</label> <input
					type="password" required name="newPassword" id="newPassword"
					style="font-weight: 600;"pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Password must contain at least one number, one uppercase and lowercase letter, and be at least 8 characters long"><br />



				<div
					style="display: flex; flex-direction: column; justify-content: center; align-items: left;">
					<button type="submit" id="altBtn" style="width: 8vw">Change
						Password</button>
				</div>

			</form>
		</div>



	</div>
	<script>
  // Enhanced hash function (not suitable for production)
  function enhancedHash(input) {
    let hash = 0;
    for (let i = 0; i < input.length; i++) {
      const charCode = input.charCodeAt(i);

      // Include both letters and symbols in the hashing process
      hash = (hash << 5) - hash + charCode;

      // Additionally, include the ASCII values of symbols
      if ((charCode >= 33 && charCode <= 47) || (charCode >= 65 && charCode <= 122)) {
        hash = (hash << 5) - hash + charCode;
      }
    }
    return hash;
  }

  // Function to handle form submission
  function handleSubmit() {
    // Get the password input value
    const passwordInput = document.getElementById('currentPassword').value;
    
    const newPassword = document.getElementById('newPassword').value;

    // Enhanced hash the password
    const hashedPassword = enhancedHash(passwordInput);
    
    const newHashedPassword = enhancedHash(newPassword);

    // Replace the plain password with the enhanced hashed one
    document.getElementById('currentPassword').value = hashedPassword;
    document.getElementById('newPassword').value = newHashedPassword;
    
    


    // Allow the form to continue with the submission
    return true;
  }
</script>


	<script>
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

		//Search box function
		function filterTable() {
			// Get the search input, table, and table rows
			var input, filter, table, tr, tdId, txtValueId;
			var tdEmail, txtEmail;
			input = document.getElementById("searchInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("tblDetail");
			tr = table.getElementsByTagName("tr");

			// Loop through each table row, starting from index 1 (to skip the header row)
			for (var i = 1; i < tr.length; i++) {

				// Get the appointment id
				tdId = tr[i].getElementsByTagName("td")[0];

				// Get the text content of the ID and Username cells
				txtValueId = tdId.textContent || tdId.innerText;

				// Check if the search filter is found an id
				var matchFound = txtValueId.toUpperCase().includes(filter);

				// Set the display property of the current row based on the search result
				tr[i].style.display = matchFound ? "" : "none";
			}

		}
		
		//Show login update success box
		 var x = document.getElementById("loginCreationStatus").value;
		if(x=="success")
			{
			showLoginSuccess();
			}
		function showLoginSuccess() {

			document.getElementById('loginSuccessBox').style.display = 'block';
		}
		
		//Close login success box
		function closePopupLoginSuccess() {
			document.getElementById('loginSuccessBox').style.display = 'none';
			document.body.classList.remove('blur');
		}
		
		//Show delete success box
		var x = document.getElementById("deletionStatus").value;
		if(x=="success")
			{
				showDeleteSuccess();
			}
		
		function showDeleteSuccess() {

			document.getElementById('deleteSuccessBox').style.display = 'block';
		}
		
		
		//Close delete success box
		function closePopupDeleteSuccess() {
			document.getElementById('deleteSuccessBox').style.display = 'none';
			document.body.classList.remove('blur');
		}
		
		window.addEventListener("load", () => {
			  const loader = document.querySelector(".loader");

			  loader.classList.add("loader-hidden");

			  loader.addEventListener("transitionend", () => {
			    document.body.removeChild("loader");
			  });
			});
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