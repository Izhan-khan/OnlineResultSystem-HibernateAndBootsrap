<!doctype html>
<%@page import="java.util.List"%>
<%@page import="controller.TimeTable_Controller"%>
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


<jsp:useBean id="dto" class="dto.TimeTableDTO" scope="request"></jsp:useBean>


<%
	List courseList = (List) request.getAttribute("courseList");

	List subjectList = (List) request.getAttribute("subjectList");
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
						<h3>Update TimeTable</h3>
						<%
							} else {
						%>
						<h3>Add TimeTable</h3>
						<%
							}
						%>


					</div>


					<form action="<%=ORSView.TIMETABLE_CTL%>" method="post">


						<div class="form-group last mb-0">
							<label for="examDate">Exam Date</label>
							<input name="examDate" type="text" class="form-control " placeholder="dd/mm/yyyy" id="datepicker2" value="<%=DataUtility.getDateString(dto.getExamDate())%>" readonly="readonly" required>
						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("examDate", request)%></font>
						</div>




						<div class="form-group last mb-0">
							<label for="examTime">Exam Time</label>

							<%
								HashMap<String, String> examTimeMap = new HashMap<String, String>();
								examTimeMap.put("10:00 to 12:30", "10:00 to 12:30");
								examTimeMap.put("14:00 to 16:30", "14:00 to 16:30");

								String examTime = HTMLUtility.getList("examTime", dto.getExamTime(), examTimeMap);
							%>

							<p class="form-group last mb-0"><%=examTime%></p>

						</div>

						<div class="form-group last mb-0">
							<label for="password">Course </label>

							<%
								String courseDrop = HTMLUtility.getList("courseId", String.valueOf(dto.getCourseId()), courseList);
							%>

							<p class="form-group last mb-0"><%=courseDrop%></p>

						</div>




						<div class="form-group last mb-0">
							<label for="password">Semester</label>

							<%
								HashMap<String, String> map = new HashMap<String, String>();
								map.put("1", "1");
								map.put("2", "2");
								map.put("3", "3");
								map.put("4", "4");
								map.put("5", "5");
								map.put("6", "6");
								map.put("7", "7");
								map.put("8", "8");

								String htmlList = HTMLUtility.getList("semester", String.valueOf(dto.getSemester()), map);
							%>

							<p class="form-group last mb-0"><%=htmlList%></p>

						</div>
						<div>
							<font style="color: red;"><%=ServletUtility.getMessage("duration", request)%></font>
						</div>






						<!-- Hidden Fields -->

						<input type="hidden" name="id" value="<%=dto.getId()%>">
						<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
						<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
						<input type="hidden" name="createdDatetime" value="<%=dto.getCreatedDatetime()%>">
						<input type="hidden" name="modifiedDatetime" value="<%=dto.getModifiedDatetime()%>">






						<div class="form-group last mb-0">
							<label for="password">Subject </label>

							<%
								String subjectDrop = HTMLUtility.getList("subjectId", String.valueOf(dto.getSubjectId()), subjectList);
							%>

							<p class="form-group last mb-0"><%=subjectDrop%></p>

						</div>




						<%
							if (dto.getId() > 0) {
						%>
						<div class="mx-auto my-2 flex-column">
							<button type="submit" class="btn btn-outline-primary btn-block my-4 mx-3" name="operation" value="<%=TimeTable_Controller.OP_SAVE%>">Update</button>
							<button type="submit" class="btn btn-outline-light btn-block my-4 mx-3" name="operation" value="<%=TimeTable_Controller.OP_CANCEL%>">Cancel</button>
						</div>
						<%
							} else {
						%>
						<div class="mx-auto my-2 flex-column">
							<button type="submit" class="btn btn-outline-primary btn-block my-4 mx-3" name="operation" value="<%=TimeTable_Controller.OP_SAVE%>">Add</button>
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