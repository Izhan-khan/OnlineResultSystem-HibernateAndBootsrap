<!doctype html>
<%@page import="controller.ORSView"%>
<html lang="en">
<head>

<!-- StyleSheet Link -->
<link href="<%=ORSView.STYLESHEET%>" rel="stylesheet">


<title></title>

</head>
<body>

	<div class="container" id="container">
		<div id="header">
			<jsp:include page="Header.jsp"></jsp:include>
		</div>

		<div class="ty-auto" id="App_Body">




			<div id="welcome_String" style="margin-bottom: 40vh;">
				<font id="Welcome_font">Welcome To Online Result System </font>
			</div>



		</div>

		<div id="footer">
			<jsp:include page="Footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>