<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EduSync</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<style>
.login {
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
}

.login .content {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 30vw;
	height: 100vh;
	background-color: white;
}

#email, #pw {
	width: 20vw;
	height: 5vh;
	border: none;
	box-shadow: 2px 2px 2px 2px #e0e0e0;
	padding: 10px;
	background-color: white;
	transition-duration: 0.3s;
	font-weight: 600;
}

label {
	font-weight: 600;
	font-size: 2vh;
	color: #5c5fe7;
}

.login .image {
	background-position: 100% center;
	background-size: cover;
	background-repeat: no-repeat;
}

.image img {
	width: 100%;
	height: 85vh;
	border-radius: 5px;
	box-shadow: 1px 1px 1px 1px #4f52ff;
}

#fpw {
	text-decoration: none;
	color: #7d96f5;
	font-weight: 600;
	margin-left: 4vw;
	transition-duration: 0.3s;
}

#fpw:hover {
	text-decoration: underline;
}

#signup {
	color: #4f52ff;
	font-size: 1.7vh;
}

/*Login button*/
#loginbutton {
	background-color: #4e51fa;
	color: white;
	border: none;
	width: 20vw;
	height: 5vh;
	font-weight: 600;
	transition-duration: 0.3s;
}

#loginbutton:hover {
	scale: 1.02;
	box-shadow: 1px 1px 1px 1px #4e51fa;
}

#altBtn {
	background-color: #f3f5f7;
	height: 4vh;
	width: 8vh;
	border: none;
	transition-duration: 0.5s;
}

#altBtn:hover {
	scale: 1.05;
	box-shadow: 1px 1px 1px 1px #575c6c;
}

.content h2 {
	color: #263339;
	font-size: 3.5vh;
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

#uname {
	width: 20vw;
	height: 5vh;
	border: none;
	padding: 10px;
	background-color: white;
	transition-duration: 0.3s;
	font-weight: 600;
	box-shadow: 2px 2px 2px 2px #e0e0e0;
}

.popup-content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: white;
	padding: 20px;
	width: fit-content;
	height: fit-content;
	text-align: center;
}

.popup-content h1 {
	color: #4e51fa;
	
}

/* Blur effect for the background when the pop-up is displayed */
body.blur {
	filter: blur(4px);
}

.login .image {
	width: 70vw;
	height: 100vh;
	background-color: #fafafa;
	background-image: url("./images/mynew.svg");
	background-size: contain;
	/* This will scale the background image to maintain its aspect ratio while fitting within the container */
	background-repeat: no-repeat;
	/* This prevents the image from repeating */
	background-position: center;
}

#rmb {
	font-size: 1.7vh;
	font-weight: 600;
	color: #565c62;
}

#fpw {
	font-size: 1.7vh;
	font-weight: 600;
	color: #565c62;
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




</style>
</head>

<body >

	<!-- Hidden Input to check  student deletion -->
	<input type="hidden" value="${deletion}" id="deletionStatus">

	<!-- Delete success box -->
	<div id="deleteSuccessBox" class="popup">
		<div class="popup-content" style="width:25vw;height:40vh">
				<h3 style="color:#4f52ff;">Your account has been successfully deleted!</h3><br>
				<img src="./images/appok.gif" height="200" width="200"><br>
				<button id="noBtn" onclick="closePopupDeleteSuccess()">Ok</button>
			<br>
		</div>
	</div>

	<!-- Hidden Pop-up Box -->
	<div id="popup" class="popup">
		<div class="popup-content">
			<h1>Invalid Login Credentials</h1>
			<img src="./images/No.gif" height="200" width="200">
			<p>
				The <b>user name</b> or <b>password</b> you entered is incorrect.<br>
				Please double-check your credentials and try again.<br> If you
				continue to experience issues, you may consider<br> <a
					href="forgotPWStep1.jsp" id="signup">resetting</a> your password or
				contact support for assistance.
			</p>
			<button id="loginbutton" onclick="closePopup()">Try again</button>
		</div>
	</div>


	<div class="login">
		<div class="image"></div>

		<div class="content">
			<img src="">
			<h2>Welcome To EduHub</h2>
			<p style="font-weight: 600; color: #4e51fa">"Planting seeds of
				knowledge, harvesting dreams of tomorrow."</p>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<form action="login" method="post" onsubmit="return handleSubmit()">
				<label>User Name</label><br> <input type="text" required
					id="uname" name="username"><br />
				<br>
				<br> <label>Password</label><br> <input type="password"
					required id="pw" name="password"><br />
				<br>
				<br> <input type="checkbox" id="rem" checked
					style="height: 1.5vh; width: 1.5vw"><span id="rmb">Remember
					Me</span> <a href="forgotPWStep1.jsp" id="fpw">Forgot Password?</a><br>
				<br>
				<br>

				<div
					style="display: flex; justify-content: center; align-items: center">
					<button id="loginbutton">Login</button>
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
    const passwordInput = document.getElementById('pw').value;

    // Enhanced hash the password
    const hashedPassword = enhancedHash(passwordInput);

    // Replace the plain password with the enhanced hashed one
    document.getElementById('pw').value = hashedPassword;
    
    console.log('Enhanced Hashed Password:', hashedPassword);


    // Allow the form to continue with the submission
    return true;
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
  
  
</script>
	<script src="login.js"></script>
	<script>
    		function redirectToSignup() 
    		{
        		window.location.href = "signup.jsp";
    		}
    		
    		// Add this function to show the pop-up and apply blur effect
    		function showPopup() {
    		    document.getElementById('popup').style.display = 'block';
    		    
    		}
    		
    		// Add this function to close the pop-up and remove blur effect
    		function closePopup() {
    		    document.getElementById('popup').style.display = 'none';
    		    document.body.classList.remove('blur');
    		}
    		
    		// Example: Show the pop-up if "cred" attribute is set to "invalid"
    		var credValue = '<%=request.getAttribute("cred")%>';

		if (credValue !== null && credValue.trim().toLowerCase() === 'invalid') {
			showPopup();

			// Optionally, set a timeout to reset the "cred" attribute after a certain period
			setTimeout(function() {
				request.setAttribute("cred", "valid");
			}, 5000); // 5000 milliseconds (adjust as needed)
		}
	</script>
</body>

</html>