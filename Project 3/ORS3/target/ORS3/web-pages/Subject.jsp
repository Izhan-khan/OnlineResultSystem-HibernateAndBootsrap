<!doctype html>
<%@page import="java.util.List"%>
<%@page import="controller.Subject_Controller"%>
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


<jsp:useBean id="dto" class="dto.SubjectDTO" scope="request"></jsp:useBean>

<%
	List courseList = (List) request.getAttribute("courseList");
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


			<div id="login_backgound" class="card">
				<div class="form-block text-white ">
					<div class="text-center my-4 ">

						<%
							if (dto.getId() > 0) {
						%>
						<h3>Update Subject</h3>
						<%
							} else {
						%>
						<h3>Add Subject</h3>
						<%
							}
						%>


					</div>


					<form action="<%=ORSView.SUBJECT_CTL%>" method="post">


						<div class="form-group first text-white mb-0">
							<label for="name">Subject Name</label>
							<input name="name" type="text" class="form-control" placeholder="Enter Subject Name" id="name" value="<%=DataUtility.getStringData(dto.getName())%>" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("name", request)%></font>
						</div>


						<div class="form-group first text-white mb-0">
							<label for="description">Description</label>
							<input name="description" type="text" class="form-control" placeholder="Enter Subject Description" id="description" value="<%=DataUtility.getStringData(dto.getDescription())%>" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("description", request)%></font>
						</div>




						<div class="form-group last mb-0">
							<label for="password">Course </label>

							<%
								String courseDrop = HTMLUtility.getList("courseId", String.valueOf(dto.getCourseId()), courseList);
							%>

							<p class="form-group last mb-0"><%=courseDrop%></p>

						</div>






						<!-- Hidden Fields -->

						<input type="hidden" name="id" value="<%=dto.getId()%>">
						<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
						<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
						<input type="hidden" name="createdDatetime" value="<%=dto.getCreatedDatetime()%>">
						<input type="hidden" name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">






						<%
							if (dto.getId() > 0) {
						%>
						<div class="mx-auto my-2 flex-column">
							<button type="submit" class="btn btn-outline-primary btn-block my-4 mx-3" name="operation" value="<%=Subject_Controller.OP_SAVE%>">Update</button>
							<button type="submit" class="btn btn-outline-light btn-block my-4 mx-3" name="operation" value="<%=Subject_Controller.OP_CANCEL%>">Cancel</button>
						</div>
						<%
							} else {
						%>
						<div class="mx-auto my-2 flex-column">
							<button type="submit" class="btn btn-outline-primary btn-block my-4 mx-3" name="operation" value="<%=Subject_Controller.OP_SAVE%>">Add</button>
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