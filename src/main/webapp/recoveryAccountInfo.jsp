<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
	background-image: url("./images/conPass.svg");
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
</style>
</head>

<body id="body1">

    <div class="login">
		<div class="image"></div>
        <div class="content">
            
		<h1 style="color:#263339;font-size:5vh;padding:0%;margin-left:4vh;">Confirm Your Account</h1><br><br><br>
            
            
            <form action="resetInfoRedirect" method="post">
            
                <label>User Name</label><br>
                <input type="email"  required id="pw" name="username" value="${username}" readonly><br /><br>
                
                <label>Email</label><br>
                <input type="email"  required id="pw" name="email" value="${Remail}" readonly><br /><br>
				
				 <label>Account Type</label><br>
                <input type="email"  required id="pw" name="type" value="${Rrole}" readonly><br /><br>
				
				<button id="loginbutton" >Confirm</button><br><br>  
				  
            </form>
        </div>
        
    </div>
</body>
</html>