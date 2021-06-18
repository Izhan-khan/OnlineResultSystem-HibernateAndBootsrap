<!doctype html>
<%@page import="controller.Login_Controller"%>
<%@page import="controller.ORSView"%>
<%@page import="dto.RoleDTO"%>
<%@page import="dto.UserDTO"%>
<html lang="en">
<head>

<!-- Font awsome plugin -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

<!-- BootStrap plugin-->

<!-- Required meta tags -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous"
>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
	crossorigin="anonymous"
></script>



<!-- StyleSheet Link -->
<link href="<%=ORSView.STYLESHEET%>" rel="stylesheet">


<!-- Date Picker widget -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker1").datepicker({

			maxDate : "0",
			dateFormat : "dd/mm/yy",
			changeYear : true,
			changeMonth : true,
			yearRange : "1950:c"
		});
	});
	$(function() {
		$("#datepicker2").datepicker({

			minDate : "0",
			dateFormat : "dd/mm/yy",
			changeYear : true,
			changeMonth : true,
			yearRange : "c:c+1"
		});
	});
</script>

<%
	UserDTO userDTO = null;
	RoleDTO roleDTO = null;
	String welcomeMsg = " Hi, ";

	if (session.getAttribute("user") != null) {
		userDTO = (UserDTO) session.getAttribute("user");
		roleDTO = (RoleDTO) session.getAttribute("role");

		String username = userDTO.getName();
		String role = roleDTO.getName();

		welcomeMsg += username + ", (" + role + ")";
	} else {
		welcomeMsg += "Guest";
	}
%>

<title></title>

</head>

<body>


	<nav class="navbar navbar-expand-sm navbar-expand-md navbar-dark bg-dark " id="navbar">

		<a class="navbar-brand " href="<%=ORSView.WELCOME_CTL%>"> <img id="image_logo" src="<%=ORSView.APP_CONTEXT%>/images/RaysLogo.png" alt="Welcome Page">
		</a>


		<%
			if (session.getAttribute("user") != null) {
		%>
		<!-- it generates menu button when size is small -->
		<button class="navbar-toggler " type="button" data-bs-toggle="collapse" data-bs-target="#header_container" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<%
			}
		%>


		<div class=" collapse navbar-collapse" id="header_container">


			<ul class=" nav navbar-nav px-3 " id="navbarCollapse ">

				<!-- it is items in the navbar -->
				<%
					if (userDTO != null) {

						if (roleDTO.getName().equalsIgnoreCase("Student")) {
				%>

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"> <span class="text-light "><i class="fas fa-file-alt">&nbsp;</i>Marksheet</span>
					</a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Merit-List</a>
						</li>
					</ul>
				</li>

				<%
					}
						if (roleDTO.getName().equalsIgnoreCase("College") || roleDTO.getName().equalsIgnoreCase("School")) {
				%>

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-file-alt">&nbsp;</i> Marksheet</span> </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Merit-List</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-award"></i>&nbsp; Course </span> </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.COURSE_CTL%>">Add Course</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-book"></i>&nbsp; Subject </span> </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-table"></i>&nbsp; Time-Table </span> </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.TIMETABLE_CTL%>">Add Time-Table</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.TIMETABLE_LIST_CTL%>">Time-Table List</a>
						</li>
					</ul>
				</li>


				<%
					}
						if (roleDTO.getName().equalsIgnoreCase("Admin")) {
				%>

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-file-alt">&nbsp;</i> Marksheet </span> </a>

					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li>
							<a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Merit-List</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-award"></i>&nbsp; Course </span>  </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.COURSE_CTL%>">Add Course</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-user-graduate">&nbsp;</i> Student</span> </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>">Add Student</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-book">&nbsp;</i> Subject</span> </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-table">&nbsp;</i> Time-Table</span> </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.TIMETABLE_CTL%>">Add Time-Table</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.TIMETABLE_LIST_CTL%>">Time-Table List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-university">&nbsp;</i> College</span> </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.COLLEGE_CTL%>">Add College</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-chalkboard-teacher">&nbsp;</i> Faculty</span> </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.FACULTY_CTL%>">Add Faculty</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-user-tag">&nbsp;</i> Role</span> </a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.ROLE_CTL%>">Add Role</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a>
						</li>
					</ul>
				</li>


				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light "><i class="fas fa-user-tie">&nbsp;</i> User</span> </a>

					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li>
							<a class="dropdown-item" href="<%=ORSView.USER_CTL%>">Add User</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.USER_LIST_CTL%>">User List</a>
						</li>
					</ul>
				</li>




				<%
					}
						/** if(role=admin ends here) */
				%>
			</ul>
		</div>

		<div class="mx-2 " id="">
			<ul class="nav navbar-nav ">

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"><span class="text-light align-text-bottom"><i class="fas fa-user">&nbsp;</i><%=welcomeMsg%></span>	
					</a>

					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>"><i class="fas fa-user-circle"> &nbsp;</i>My Profile</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class="fas fa-key"> &nbsp;</i>Change Password</a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=Login_Controller.OP_LOG_OUT%>"><i class='fas fa-sign-out-alt'> &nbsp;</i>Logout</a>
						</li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li>
							<a class="dropdown-item" target=" " href="<%=ORSView.JAVA_DOC_VIEW%>"><i class="fas fa-file-alt">&nbsp;&nbsp;</i>Java Doc</a>
						</li>
					</ul>
				</li>

				<%
					/** if session.user is active ends here */
					} else {
				%>
			</ul>
		</div>



		<div class="collapse navbar text-nowrap d-flex " id="navbarNav">
			<ul class="navbar-nav ">

				<li class="nav-item dropdown mx-3">
					<a class="nav-link navbar-toggler-icon" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"></a>

					<ul class="dropdown-menu dropdown-menu-lg-end" aria-labelledby="navbarDropdown">
						<li>
							<a class="dropdown-item" href="<%=ORSView.USER_REGISTRATION_CTL%>"><i class="fas fa-user-plus"></i> Sign Up </a>
						</li>
						<li>
							<a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>"><i class="fas fa-sign-in-alt"></i> Login </a></a>
						</li>
					</ul>
				</li>


				<%
					}
				%>

			</ul>
		</div>


	</nav>

</body>

</html>