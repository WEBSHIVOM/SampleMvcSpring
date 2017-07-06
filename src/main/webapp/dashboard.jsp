<%@page import="com.inno.utility.RegisterModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ pageimport="com.inno.*"  %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Dashboard Admin</title>
<!-- BOOTSTRAP STYLES-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="assets/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href="assets/css/ss.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/underscore.js" type="text/javascript"></script>
<script type="text/javascript">
	function search_func(value) {

		$.ajax({
			url : '/NewWeb/search1',
			data : {
				'searchdata' : value
			},
			error : function() {
				$('.intro').html('<p>An error has occurred</p>');
			},

			success : function(data) {

				if (data.length == 0 || data== null || data== "") {
					$('.intro').html('<p>No Data Match</p>');
				} else {
					$(".intro").html(_.template($("#list-template").html())({
						"empList" : data
					}));
				}
				console.log(data)

				/*   var  templatehtml = _.template($("#list-template").html())
				   $(".intro").html(templatehtml({"response":data}));  */
			},
			type : 'GET'
		});

	}
</script>
<script type="text/template" id="list-template">	


	<@ _.each(empList,function(emp){@>
	 <div >	 

                   <li>	
		<span> Name:  <@= emp.name @></span>
                  <br>
		<span>	Address:  <@= emp.address @>  </span>
                   <br>
		<span>	ID:  <@= emp.id @>	</span>
                    <br>
       <span> Mail: <@= emp.email @>	</span>
	                <br>
	              </li>
     </div>

	<@ }); @>
		
				
       
</script>

<script type="text/javascript">
	function getchangedp() {
		$('.changedpform').show();
		//alert("succcess")

	}
</script>
<script type="text/javascript">
	
</script>


</head>
<body>


	<span class="changedpform"
		style="position: absolute; height: 300px; width: 250px; background: rgb(153, 102, 153); margin: 300px 0px 0px 700px; z-index: 5;">
		<form action="/NewWeb/changedp?id=${empId}" method="post"
			enctype="multipart/form-data">
			<input type="file" name="file"> <input type="submit"
				value="upload">
		</form>
	</span>
	<script type="text/javascript">
		$('.changedpform').hide();
	</script>

	<div id="wrapper">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="adjust-nav">
				<span class="logout-spn"> <a href="#" style="color: FF0000;">
						${message}</a>

				</span>


				<div class="navbar-header">

					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".sidebar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

					<div class="g-signin2" data-onsuccess="onSignIn"></div>
				</div>


				<span class="logout-spn"> <a href="Logout.jsp"
					style="color: #fff;">LOGOUT</a>

				</span>
				<h1 style="color: #fff;">${cookie.ckid.value}</h1>



			</div>
		</div>
		<!-- /. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">



					<li class="active-link"><a href="dashboard.jsp"><i
							class="fa fa-desktop "></i>Dashboard <span class="badge">Included</span></a>
					</li>
					<br>
					<span> <input type="button" id="button" value="RegisterNow" />
					</span>
					<div class='login--form' align="center">
						<div class='login--username-container' align="center">
							<form name="regform" id="regform">
								<div id="yourDialog"
									style="height: 100px; width: 170px; background-color: green; display: none">
									<input type="text" placeholder="Name" name="name"> <input
										type="text" placeholder="Address" name="address"> <input
										type="text" placeholder="id" name="id"> <input
										type="text" placeholder="email" name="email"> <input
										type="hidden" value="0" name="status">
									<button type="submit" id="login-button">Register</button>
								</div>
							</form>
						</div>
					</div>

					<li><br> <span> <img src="downloadDp.do?id=${empId}"
							height="75px" width="75px"> <a onclick="getchangedp()">Change
								DP</a>

					</span>
						<div>${msg}</div>
				</ul>


			</div>


		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">

			<div id="page-inner">



				<hr />
				<%
					String data = (String) request.getSession().getAttribute("websessn");
				String dataw = (String) request.getSession().getAttribute("id");
				
				%>

				<c:set var="rol" scope="application" value="<%=data%>">
				</c:set>
				<c:set var="empId" scope="application" value="<%=dataw%>"></c:set>

				<c:choose>
					<c:when test="${rol== 'admin'}">



						<!-- /. ROW  -->
						<div class="row text-center pad-top">
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="div-square">
									<a href="view"> <i class="fa fa-circle-o-notch fa-5x"></i>
										<h4>Check User</h4>
									</a>
								</div>



							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="div-square">
									<a href="view"> <i class="fa fa-users fa-5x"></i>
										<h4>Update-User</h4>
									</a>
								</div>


							</div>


							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="div-square">
									<a href='<c:url value="/dropdowndata"/>' /> <i
										class="fa fa-user fa-5x"></i>
									<h4>Register User</h4>
									</a>
								</div>


							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="div-square">
									<a href="search.jsp"> <i class="fa fa-user fa-5x"></i>
										<h4>search User</h4>
									</a>
								</div>


							</div>




							<div id="result">
								<input type="hidden" name="name" value="arr[0].name"> <input
									type="hidden" name="address" value="arr[0].address"> <input
									type="hidden" name="id" value="arr[0].id"> <input
									type="hidden" name="email" value="arr[0].email">
							</div>

							<h3>Search Directory</h3>
							<input type="text" class="fname"
								onkeyup="search_func(this.value);"> <br>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">

								<div class="intro"></div>

							</div>
							<br>


						</div>


					</c:when>
					<c:when test="${rol== 'user'}">

						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
							<div class="div-square">
								<a href="/NewWeb/viewprofile"> <i
									class="fa fa-circle-o-notch fa-5x"></i>
									<h4>View User data</h4>
								</a>
							</div>


						</div>
					</c:when>
				</c:choose>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					<div class="div-square">
						<a href="Fileform"> <i class="fa fa-circle-o-notch fa-5x"></i>
							<h4>File Directory</h4>
						</a>
					</div>


				</div>






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

	<script src="assets/js/click.js">
		
	</script>

</body>

</html>
