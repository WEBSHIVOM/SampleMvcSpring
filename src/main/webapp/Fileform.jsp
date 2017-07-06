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
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
</head>
<body>
	<div id="wrapper">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="adjust-nav">

				<div class="navbar-header">
					<span class="logout-spn"> <a href="#"
						style="color: FF0000;"> ${message}</a>

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

				<li><br> <a href="/NewWeb/download_file">Downloads</a> <br>
				</li>
				
			</ul>

		</div>

		</nav>


		<br> <br>


		<div id="page-wrapper" align="left">
			<div id="page-inner">

				<table  width="80%" border="1" cellspacing="0" cellpadding="5">
					<tr>
						<th width="4%">File_ID</th>
						<th width="17%" align="center">Filename</th>
						<th width="27%" align="center">Action</th>
						
					</tr>
					<c:choose>
						<c:when test="${files != null}">
							<c:forEach var="file" items="${files}" >
								<tr>
									
									<td>${file.id}</td>
									<td>File##${file.id}</td>
									<td> <img src="download.htm?id=${file.id}" height="75px" width="75px">    
									<div align="center">
											<a href="download.htm?id=${file.id}">Download</a> / <a
												href="delete.htm?id=${file.id}">Delete</a>
												
										</div></td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</table>
    <div>${filmsg}</div>
		
                      <div>${deletemsg}</div>		

                  <h2 align="center">Add New File</h2>
    <form action="/NewWeb/savefile" method="post" enctype="multipart/form-data">
        <table   border='1' align='center' width="auto" cellspacing="0">
            <tr>
                <td width="25%"><strong>File to upload</strong></td>
              </tr>
              <tr>  
            <td width="65%"><input type="file" name="file" /></td>
            </tr>
            <tr>
              <td width="35%"><input type="text" name="filetype" value="filetype" /></td>
              </tr>
              <tr>
              <td width="40%"><input type="submit" value="Upload File" /></td>
            </tr>
        </table>
    </form>
      <div align="center">${uploadreslt}</div>
      
      
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