<!doctype html>
<%@page import="java.util.List"%>
<%@page import="controller.Marksheet_Controller"%>
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


<%
	List studentList = (List) request.getAttribute("studentList");
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

		<div class="ty-auto h-auto" id="App_Body">


			<div id="login_backgound" class="card">
				<div class="form-block text-white ">
					<div class="text-center my-4 ">

						<%
							if (dto.getId() > 0) {
						%>
						<h3>Update Marksheet</h3>
						<%
							} else {
						%>
						<h3>Add Marksheet</h3>
						<%
							}
						%>


					</div>


					<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">



						<!-- Hidden Fields -->

						<input type="hidden" name="id" value="<%=dto.getId()%>">
						<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
						<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
						<input type="hidden" name="createdDatetime" value="<%=dto.getCreatedDatetime()%>">
						<input type="hidden" name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">





						<div class="form-group last mb-0">
							<label for="password">Student </label>

							<%
								String studentDrop = HTMLUtility.getList("studentId", String.valueOf(dto.getStudentId()), studentList);
							%>

							<p class="form-group last mb-0"><%=studentDrop%></p>

						</div>




						<div class="form-group first text-white mb-0">
							<label for="rollNo">Roll-Number </label>
							<input name="rollNo" type="text" class="form-control" placeholder="Enter Roll-Number" id="rollNo" value="<%=DataUtility.getStringData(dto.getRollNo())%>" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("rollNo", request)%></font>
						</div>


						<div class="form-group first text-white mb-0">
							<label for="physics">Physics Marks</label>
							<input name="physics" type="text" class="form-control" maxlength="3" placeholder="Enter Physics Marks" id="physics" value="<%=DataUtility.getStringData(dto.getPhysics())%>" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("physics", request)%></font>
						</div>




						<div class="form-group first text-white mb-0">
							<label for="chemistry">Chemistry Marks</label>
							<input name="chemistry" type="text" class="form-control" maxlength="3" placeholder="Enter Chemistry Marks" id="chemistry" value="<%=DataUtility.getStringData(dto.getChemistry())%>" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("chemistry", request)%></font>
						</div>





						<div class="form-group first text-white mb-0">
							<label for="maths">Maths Marks</label>
							<input name="maths" type="text" class="form-control" maxlength="3" placeholder="Enter Maths Marks" id="maths" value="<%=DataUtility.getStringData(dto.getMaths())%>" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("maths", request)%></font>
						</div>









						<%
							if (dto.getId() > 0) {
						%>
						<div class="mx-auto my-2 flex-column">
							<button type="submit" class="btn btn-outline-primary btn-block my-4 mx-3" name="operation" value="<%=Marksheet_Controller.OP_SAVE%>">Update</button>
							<button type="submit" class="btn btn-outline-light btn-block my-4 mx-3" name="operation" value="<%=Marksheet_Controller.OP_CANCEL%>">Cancel</button>
						</div>
						<%
							} else {
						%>
						<div class="mx-auto my-2 flex-column">
							<button type="submit" class="btn btn-outline-primary btn-block my-4 mx-3" name="operation" value="<%=Marksheet_Controller.OP_SAVE%>">Add</button>
							<button type="reset" class="btn btn-outline-light btn-block my-4 mx-3" name="operation" value="Reset">Reset</button>
						</div>
						<%
							}
						%>




					</form>


				</div>
			</div>



		</div>

		<div id="footer">
			<jsp:include page="Footer.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>