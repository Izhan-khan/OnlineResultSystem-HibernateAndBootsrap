<!doctype html>
<%@page import="java.util.Iterator"%>
<%@page import="dto.MarksheetDTO"%>
<%@page import="java.util.List"%>
<%@page import="controller.MarksheetList_Controller"%>
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


		<div class="ty-auto flex-column h-90vh" id="App_Body">
			<div class="ty-auto m-2 form-block text-white text-center w-100 flex-column">

				<h1 class="mb-4">Merit List</h1>

				<br>
				<div class="col-12 d-flex justify-content-center ">
					<a class="btn btn-outline-light" target="blank" href="<%=ORSView.JASPER_CTL%>">Click Here to Print MeritList</a>
					<%
						System.out.println("hi i am marksheet merit list view");
					%>
				</div>



				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);

					List list = ServletUtility.getList(request);
					int i = 1;
					if (list != null && list.size() != 0) {
						Iterator it = list.iterator();
				%>


				<div class="table-responsive">
					<table class="table table-fill  table-striped my-5">
						<thead class="table-dark">
							<tr>
								<th class="" scope="col">Rank</th>
								<th class="" scope="col">Roll-Number</th>
								<th class="" scope="col">Name</th>
								<th class="" scope="col">Student-Id</th>
								<th class="" scope="col">Physics</th>
								<th class="" scope="col">Chemistry</th>
								<th class="" scope="col">Maths</th>
							</tr>
						</thead>
						<tbody class="table-light">
							<%
								while (it.hasNext()) {
										dto = (MarksheetDTO) it.next();
							%>


							<tr>
								<td class="" scope="row"><%=i%></td>
								<td class=""><%=dto.getRollNo()%></td>
								<td class=""><%=dto.getName()%></td>
								<td class=""><%=dto.getStudentId()%></td>
								<td class=""><%=dto.getPhysics()%></td>
								<td class=""><%=dto.getChemistry()%></td>
								<td class=""><%=dto.getMaths()%></td>

							</tr>

							<%
								i++;
									}
								}
							%>

						</tbody>
					</table>
				</div>
				<br>

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