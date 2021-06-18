<!doctype html>
<%@page import="controller.GetMarksheet_Controller"%>
<%@page import="controller.ChangePassword_Controller"%>
<%@page import="controller.MyProfile_Controller"%>
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


<jsp:useBean id="dto" class="dto.MarksheetDTO" scope="request"></jsp:useBean>


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

		<div class="d-flex flex-column justify-content-around" id="App_Body" style="margin-bottom: 15vh;">



			<div id="login_backgound">
				<div class="form-block text-white ">
					<div class="text-center my-4 ">
						<h3>Get Marksheet</h3>

					</div>


					<form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">



						<div class="form-group first text-white mb-0">
							<label for="username">Enter Roll Number</label>
							<input name="rollNo" type="text" class="form-control" placeholder="Enter Roll Number" id="rollNo" value="<%=DataUtility.getStringData(dto.getRollNo())%>" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("rollNo", request)%></font>
						</div>



						<div class="d-grid gap-2 col-8 mx-auto mt-3 mb-5 text-nowrap">
							<button type="submit" class="btn btn-outline-primary btn-block  " name="operation" value="<%=GetMarksheet_Controller.OP_GO%>">Get Marksheet</button>
						</div>

					</form>




				</div>
			</div>





			<%
				if (dto != null && dto.getRollNo() != null && dto.getPhysics() != null && dto.getMaths() != null
						&& dto.getChemistry() != null) {
			%>

			<div class="alert alert-light" role="alert">

				<h3>
					Roll Number :
					<%=dto.getRollNo()%></h3>
				<h3>
					Physics :
					<%=dto.getPhysics()%></h3>
				<h3>
					Chemistry :
					<%=dto.getChemistry()%></h3>
				<h3>
					Maths :
					<%=dto.getMaths()%></h3>

			</div>

			<%
				}
			%>






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