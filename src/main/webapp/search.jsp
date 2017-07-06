<%@page import="org.springframework.util.Base64Utils"%>
<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="sun.misc.CharacterDecoder.*" %>

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
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"> 
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script> 
<script type="text/javascript">
var myapp=angular.module("myapp",[]);

myapp.controller('searchcontroller',function($scope, $http){
	$scope.user={};
	$scope.submitForm=function(){
		$http({
			method:'POST',
	        url:'searchdb',
	        data:$scope.user
	       })
	       .success(function(data){
	    	   $scope.items=data;
	    	  
	    	  
	    	  
	       });
	       };
	       });
</script>
</head>
<body>
	<div id="wrapper">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="adjust-nav">

				<div class="navbar-header">

					<div>
						<span class="logout-spn"> <a href="#"
							style="color: FF0000;"> ${message}</a>

						</span>


					</div>
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".sidebar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

				</div>
			</div>
			<span class="logout-spn"> <a href="Logout.jsp"
				style="color: #fff;">LOGOUT</a>

			</span>
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
            <%   %>


		<br> <br>


		<div id="page-wrapper" align="left">
			<div id="page-inner">
				<div>
					<form action="search" method="get">
						<input type='text' name="searchdata">
						<button type="submit" value="submit">Search-By-NAME</button>
					</form>
				</div>
				<br>

				<div>
					<form action="search" method="get">
						<input type='text' name="searchdata">
						<button type="submit" value="submit">Search-By-Id</button>
					</form>
				</div>
				<br>
				<div>
					<form action="search" method="get">
						<input type='text' name="searchdata">
						<button type="submit" value="submit">Search-By-email</button>
					</form>
				</div>
				<br>
				<div>
					<form action="search" method="get">
						<input type='text' name="searchdata">
						<button type="submit" value="submit">Search-By-address</button>
					</form>
				</div>

				<br> <br> <br>
				<div  ng-app="myapp" ng-controller="searchcontroller">
				<div class="container">
					<div class="col-sm-8 col-sm-offset-2">

						<!-- FORM -->
						<form name="userForm" ng-submit="submitForm()">
							<div class="form-group">
								<label>Name</label> <input type="text" name="searchdata"
									class="form-control" ng-model="user.searchdata"> 
							</div>
							

							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
						
						
						<table class='table table-striped table-bordered table-hover'
					border='1' align='center'>
					 <thead>
            <tr>
                <th>NAME</th>
                <th>ID</th>
                <th>ADDRESS</th>
                <th>country</th>
                <th>Gender</th>
            </tr>
        </thead>
        <tbody>
            <tr ng-repeat="i in items">
            <td>{{i.name}}</td>
            <td>{{i.id}}</td>
            <td>{{i.address}}</td>
            <td>{{i.country}}</td>
            <td>{{i.gender}}</td>
             
               
            </tr>
        </tbody>
					
					
					
					
					</table>
					</div>
				</div>
				</div>
				<br>

				<table class='table table-striped table-bordered table-hover'
					border='1' align='center'>

					<c:forEach items="${dbdata}" var="SearchData">

						<tr>
							<td><input type="text" id='name' readonly
								value="${SearchData.name}" name="name"></td>
							<td><input type='text' id='name'
								value="${SearchData.address}" name="address"></td>
							<td><input type='text' id='name' value="${SearchData.id}"
								name="id"></td>
							<td><input type='text' id='name' value="${SearchData.email}"
								name="email"></td>

							<td>
								<button type="submit" value="submit">Update</button>
							</td>
						</tr>


					</c:forEach>

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