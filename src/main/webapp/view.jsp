<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>VIEWdata</title>
<!-- BOOTSTRAP STYLES-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="assets/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.js"></script>
</head>
<body>
	<div id="wrapper">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="adjust-nav">

				<div class="navbar-header">
					<span class="logout-spn"> <a href="#" style="color: FF0000;">
							${message}</a>

					</span>
					<div>
						<span class="logout-spn"> <a href="Logout.jsp"
							style="color: #fff;">LOGOUT</a>

						</span>

					</div>
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".sidebar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>


				</div>
			</div>
		</div>
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">



				<li class="active-link"><a href="dashboard.jsp"><i
						class="fa fa-desktop "></i>Dashboard <span class="badge">Included</span></a>
				</li>
			</ul>

		</div>

		</nav>


		<br> <br>


		<div id="page-wrapper" align="left">
			<div id="page-inner">




				<table width="80%" border="1" cellspacing="0" cellpadding="5">
					<tr>
						<th width="4%">Name</th>
						<th width="6%" align="center">id</th>
						<th width="8%" align="center">address</th>
						<th width="8%" align="center">email</th>
						<th width="8%" align="center">Gender</th>
						<th width="8%" align="center">country</th>
						<th width="8%" align="center">Profile pic</th>
						<th width="10%" align="center">Action</th>
					</tr>
					<c:choose>
						<c:when test="${dbdata != null}">

							<c:forEach var="filedata" items="${dbdata}">
								<tr>
									<form action="/NewWeb/update" method="post">
										<td><input type='text' value="${filedata.name}"
											name="name"></td>
										
											<td><input type='text' readonly value="${filedata.id}"
												name="id"></td>
											<td><input type='text' value="${filedata.address}"
												name="address"></td>
											<td><input type='text' value="${filedata.email}"
												name="email"></td>
											<div>
												<c:choose>
													<c:when test="${filedata.gender==True}">
														<td>Male</td>

													</c:when>
													<c:otherwise>
														<td>Female</td>
													</c:otherwise>
												</c:choose>

											</div>

											<td>${filedata.country}</td>
											<td><img src="/NewWeb/downloaddp?id=${filedata.id}"
												height="75px" width="75px" alt="Red dot"></td>
											<td><input type="submit" value="update"></td>
									</form>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</table>

			</div>
		</div>


	</div>

	<!-- /. WRAPPER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="assets/js/custom.js"></script>

</body>
</html>