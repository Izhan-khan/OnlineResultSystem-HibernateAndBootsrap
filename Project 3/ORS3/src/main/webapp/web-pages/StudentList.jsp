<!doctype html>
<%@page import="java.util.Iterator"%>
<%@page import="dto.StudentDTO"%>
<%@page import="java.util.List"%>
<%@page import="controller.StudentList_Controller"%>
<%@page import="controller.Student_Controller"%>
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


<jsp:useBean id="dto" class="dto.StudentDTO" scope="request"></jsp:useBean>



<title></title>

</head>
<body>
	<div class="container" id="container">


		<div id="header">
			<jsp:include page="Header.jsp"></jsp:include>
		</div>



		<div class="ty-auto flex-column h-90vh" id="App_Body">

			<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post" class="d-flex flex-column w-100 mx-3 my-5 justify-content-evenly">

				<div class="ty-auto m-2 form-block text-white text-center w-100 flex-column">

					<h1 class="mb-4">Student List</h1>

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


	
					<div class="d-flex flex-wrap justify-content-evenly">


						<div class="text-white">
							<label for="name">Student Name</label>
							<input name="name" type="text" class="form-control mx-3" placeholder="Enter Student Name" id="name" value="<%=DataUtility.getStringData(dto.getName())%>">
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("name", request)%></font>
						</div>



						<div class="text-white">
							<label for="email">Email</label>
							<input name="email" type="text" class="form-control mx-3" placeholder="Enter Email Id" id="email" value="<%=DataUtility.getStringData(dto.getEmail())%>">
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("email", request)%></font>
						</div>



						<div class="">
							<button type="submit" class="btn btn-outline-primary btn-block my-4 " name="operation" value="<%=StudentList_Controller.OP_SEARCH%>">Search</button>
						</div>
						<div class="">
							<button type="reset" class="btn btn-outline-light btn-block my-4 " name="operation" value="Reset">Reset</button>
						</div>
					</div>


					<%
						int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
					%>

					<!-- Hidden Fields -->

					<input type="hidden" name="id" value="<%=dto.getId()%>">
					<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
					<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
					<input type="hidden" name="createdDatetime" value="<%=dto.getCreatedDatetime()%>">
					<input type="hidden" name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">
					<input type="hidden" name="pageNo" value="<%=pageNo%>">
					<input type="hidden" name="pageSize" value="<%=pageSize%>">

					<%
						List list = ServletUtility.getList(request);

						if (list != null && list.size() != 0) {
							Iterator it = list.iterator();
					%>

					<div class="table-responsive">
						<table class="table table-fill  table-striped my-5">
							<thead class="table-dark">
								<tr>
									<th class="" scope="col">Select</th>
									<th class="" scope="col">College Id</th>
									<th class="" scope="col">Student Name</th>
									<th class="" scope="col">Email</th>
									<th class="" scope="col">Date of Birth</th>
									<th class="" scope="col">Mobile Number</th>
									<th class="" scope="col">Update</th>
								</tr>
							</thead>
							<tbody class="table-light">
								<%
								while (it.hasNext()) {
										dto = (StudentDTO) it.next();
							%>


								<tr>
									<td align="center">
										<input type="checkbox" name="ids" value="<%=dto.getId()%>">
									</td>
									<td class="" scope="row"><%=dto.getCollegeId()%></td>
									<td class=""><%=dto.getName()%></td>
									<td class=""><%=dto.getEmail()%></td>
									<td class=""><%=DataUtility.getDateString(dto.getDob())%></td>
									<td class=""><%=dto.getMobileNo()%></td>
									<td class="">
										<a href="<%=ORSView.STUDENT_CTL%>?id=<%=dto.getId()%>">Edit</a>
									</td>
								</tr>
								<%
								}
								}
							%>

							</tbody>
						</table>
					</div>

				</div>

				<table width="100%">
					<tr class="d-flex justify-content-around w-100 flex-wrap">
						<td>
							<input type="submit" name="operation" class="btn btn-outline-light btn-md m-3 " style="font-size: 17px" value="<%=StudentList_Controller.OP_PREVIOUS%>" <%=pageNo > 1 ? "" : "disabled"%>>
						</td>
						<td>
							<input type="submit" name="operation" class="btn btn-outline-light btn-md m-3 " style="font-size: 17px" value="<%=StudentList_Controller.OP_NEW%>">
						</td>
						<td>
							<input type="submit" name="operation" class="btn btn-outline-light btn-md m-3 " style="font-size: 17px" value="<%=StudentList_Controller.OP_DELETE%>">
						</td>
						<td>
							<input type="submit" name="operation" class="btn btn-outline-light btn-md m-3" style="font-size: 17px" value="<%=StudentList_Controller.OP_NEXT%>"
								<%=(list.size() >= pageSize) ? "" : "disabled"%>
							>
						</td>
					</tr>
					<tr></tr>
				</table>

			</form>
		</div>


		<div id="footer">
			<jsp:include page="Footer.jsp"></jsp:include>
		</div>


	</div>

</body>
</html>