<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="com.inno.daoImpl.*"%>
<%@ page import="javax.sql.DataSource"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/underscore.js" type="text/javascript"></script>
<script type="text/javascript">
	function contrydata() {
		alert("sometext");
		
		$.ajax({
			url:"/NewWeb/dropdowndata" ,
			error : function() {
				alert("failed")
			},
			success:function(data){
				alert(data)
				console.log(data)
  var condata=data;
				
				var name=_.pluck(condata,'country_name');
				
				var contentt=_.map(name,function(){'<option>'+name + '</option>';}).join();
				
			},
			
			type :'POST' 
			});
		
		
		
		
		
		
	}
</script>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Simple Responsive Admin</title>
<!-- BOOTSTRAP STYLES-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="assets/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

</head>
<body>
	<h1>${ status }</h1>

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
				<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">



				<li class="active-link"><a href="dashboard.jsp"><i
						class="fa fa-desktop "></i>Dashboard <span class="badge">Included</span></a>
				</li>
				</ul>
				</div>
				
                   
                   
                    
			</div>
		</div>
			
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">

				<hr />
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<h5>Register User</h5>

						<br />
						<div id="formdiv">
							<form action="register" method="post"
								enctype="multipart/form-data">

								<div class="input-group">

									<input type="text" placeholder="Name" name="name"> <br>
									<br> <input type="text" placeholder="Address"
										name="address"> <br> <br> <input type="text"
										placeholder="id" name="id"> <br> <br> <input
										type="text" placeholder="email" name="email"> <br>


									<div>
										<input type="radio" name="Gender" value="1"> <label>Male</label>

										<input type="radio" name="Gender" value="0"> <label>Female</label>
									</div>
									<br> <b>Country</b> <select id="selectcontry"
										name="country" ondblclick="contrydata()">

										<c:forEach items="${condata}" var="Ctrdb">
											<option value="${Ctrdb.country_name}">${Ctrdb.country_name}</option>

										</c:forEach>
									</select> <br>
									<div>
										<input type="file" name="image" />
									</div>

									<button type="submit" id="login-button">Register</button>

								</div>
							</form>
							${message}
						</div>





					</div>

					<%-- <table class='table table-striped table-bordered table-hover'
						border='1' align='center'>
						<%
							out.println("<tr>");
							out.println("<th>NAME</th>");
							out.println("<th>Address</th>");
							out.println("<th>EmployeeID</th>");
							out.println("<th>EMAIL </th>");
							out.println("<th></th>");
							out.println("<th></th>");
							out.println("</tr>");
						%>



						<!--                     <table class='table table-striped table-bordered table-hover' border='1' align='center'> -->
						<c:forEach items="${dbdata}" var="CtrlAuth">
							<tr>
								<th>${CtrlAuth.name}</th>
								<th>${CtrlAuth.address}</th>
								<th>${CtrlAuth.id}</th>
								<th>${CtrlAuth.email}</th>
							</tr>



						</c:forEach>
					</table> --%>


<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.9";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<div class="fb-like" data-href="https://developers.facebook.com/docs/plugins/" data-width="28" data-layout="standard" data-action="like" data-size="small" data-show-faces="true" data-share="true"></div>




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


</body>
</html>
