<!doctype html>
<%@page import="controller.UserRegistration_Controller"%>
<%@page import="utility.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="utility.ServletUtility"%>
<%@page import="utility.DataUtility"%>
<%@page import="controller.Login_Controller"%>
<%@page import="controller.ORSView"%>
<html lang="en">
<head>



<!-- StyleSheet Link -->
<link href="<%=ORSView.STYLESHEET%>" rel="stylesheet">


<jsp:useBean id="dto" class="dto.UserDTO" scope="request"></jsp:useBean>

<%
	String fName = null;
	String lName = null;

	if (dto != null && dto.getName() != null) {
		String fnlName = DataUtility.getStringData(dto.getName());
		String[] Name = fnlName.split(" ", 2);

		fName = Name[0];
		lName = Name[1];
	}
%>


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





		<div class="ty-auto" id="App_Body">


			<div id="login_backgound">
				<div class="form-block text-white ">
					<div class="text-center my-4 ">
						<h3>Registration</h3>

					</div>


					<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">


						<div class="form-group first text-white mb-0">
							<label for="username">First Name</label>
							<input name="firstName" type="text" class="form-control" placeholder="Enter First Name" id="firstname" value="<%=DataUtility.getStringData(fName)%>" required>
						</div>


						<div class="form-group first text-white mb-0">
							<label for="username">Last Name</label>
							<input name="lastName" type="text" class="form-control" placeholder="Enter Last Name" id="lastname" value="<%=DataUtility.getStringData(lName)%>" required>
						</div>


						<div class="form-group first text-white mb-0">
							<label for="username">Login Id</label>
							<input name="login" type="text" class="form-control" placeholder="Enter Login Id" id="username" value="<%=DataUtility.getStringData(dto.getLogin())%>" required>
						</div>


						<div class="form-group last mb-0">
							<label for="password">Password</label>
							<input name="password" type="password" class="form-control" placeholder="Enter Password" id="password" value="<%=DataUtility.getStringData(dto.getPassword())%>" required>
						</div>


						<div class="form-group last mb-0">
							<label for="password">Confirm Password</label>
							<input name="confirmPassword" type="password" class="form-control" placeholder="Confirm Password" id="confirmPassword" value="<%=DataUtility.getStringData(dto.getPassword())%>" required>
						</div>




						<div class="form-group last mb-0">
							<label for="password">Gender </label>

							<%
								HashMap<String, String> map = new HashMap<String, String>();
								map.put("Male", "Male");
								map.put("Female", "Female");

								String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
							%>

							<p class="form-group last mb-0"><%=htmlList%></p>

						</div>




						<div class="form-group first text-white mb-0">
							<label for="mobile">Mobile No.</label>
							<input name="mobile" type="text" class="form-control" maxlength="10" placeholder="Enter Mobile Number" id="mobile" value="<%=DataUtility.getStringData(dto.getMobileNo())%>" required>
						</div>



						<div class="form-group last mb-0">
							<label for="dob">Date of Birth</label>
							<input name="dob" type="text" class="form-control " placeholder="dd/mm/yyyy" id="datepicker1" value="<%=DataUtility.getDateString(dto.getDob())%>" readonly="readonly" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("dob", request)%></font>
						</div>




						<div class="d-grid gap-2 col-8 mx-auto my-2">
							<button type="submit" class="btn btn-outline-primary btn-block my-4 " name="operation" value="<%=UserRegistration_Controller.OP_SIGN_UP%>">Sign Up</button>
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