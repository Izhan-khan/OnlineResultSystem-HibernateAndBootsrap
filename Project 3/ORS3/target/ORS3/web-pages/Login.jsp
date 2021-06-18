<!doctype html>
<%@page import="utility.ServletUtility"%>
<%@page import="utility.DataUtility"%>
<%@page import="controller.Login_Controller"%>
<%@page import="controller.ORSView"%>
<html lang="en">
<head>



<!-- StyleSheet Link -->
<link href="<%=ORSView.STYLESHEET%>" rel="stylesheet">


<jsp:useBean id="dto" class="dto.UserDTO" scope="request"></jsp:useBean>


<title></title>

</head>
<body>
	<div class="container" id="container">
		<div id="header">
			<jsp:include page="Header.jsp"></jsp:include>
		</div>




		<%
			if (ServletUtility.getErrorMessage(request) != "") {
		%>

		<div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
			<%=ServletUtility.getErrorMessage(request)%>
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		<%
			}
			if (ServletUtility.getSuccessMessage(request) != "") {
		%>
		<div class="alert alert-success alert-dismissible fade show text-center" role="alert">
			<%=ServletUtility.getSuccessMessage(request)%>
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		<%
			}
		%>



		<%
			if (request.getAttribute("logoutMessage") != null) {
		%>

		<div class="alert alert-success alert-dismissible fade show text-center" role="alert">
			<%=request.getAttribute("logoutMessage")%>
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		<%
			}
			if (request.getAttribute("sessionExpiredMessage") != null) {
		%>
		<div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
			<%=request.getAttribute("sessionExpiredMessage")%>
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		<%
			}
		%>





		<div class="ty-auto" id="App_Body">


			<div id="login_backgound">
				<div class="form-block text-white ">
					<div class="text-center mt-4">
						<h3>Login</h3>

					</div>


					<form action="<%=ORSView.LOGIN_CTL%>" method="post">


						<div class="form-group first text-white mb-0">
							<label for="username">Username</label>
							<input name="login" type="text" class="form-control" placeholder="Login Id" id="username" value="<%=DataUtility.getStringData(dto.getLogin())%>" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("login", request)%></font>
						</div>


						<div class="form-group last my-3">
							<label for="password">Password</label>
							<input name="password" type="password" class="form-control" placeholder="Password" id="password" value="<%=DataUtility.getStringData(dto.getPassword())%>" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("password", request)%></font>
						</div>

						<div class="d-grid gap-2 col-8 mx-auto my-2">
							<button type="submit" class="btn btn-outline-primary btn-block mb-2 " name="operation" value="<%=Login_Controller.OP_SIGN_IN%>">Login</button>
						</div>

						<div class="d-sm-flex mb-5 align-items-center" style="margin-left: 6vh;">
							<span class="ml-29px"><a href="<%=ORSView.FORGET_PASSWORD_CTL%>" class="forgot-pass text-white ">Forgot Password</a></span>
						</div>




					</form>


				</div>
			</div>



		</div>

		<div id="footer">
			<jsp:include page="Footer.jsp"></jsp:include>
		</div>
	</div>







	<!-- Hidden Fields -->

	<input type="hidden" name="id" value="<%=dto.getId()%>">
	<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
	<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
	<input type="hidden" name="createdDatetime" value="<%=dto.getCreatedDatetime()%>">
	<input type="hidden" name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">




</body>
</html>