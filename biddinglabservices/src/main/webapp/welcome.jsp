<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="CSRF_TOKEN" value="${_csrf.token}" />


<!doctype html>
<html lang="en">
<head>
<meta name="_csrf_parameter" content="_csrf" />
<meta name="_csrf_header" content="X-CSRF-TOKEN" />
<meta name="_csrf" content="${CSRF_TOKEN}" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<title>Easy way to food oder</title>



<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>





<link rel="stylesheet" type="text/css"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css">


<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/js/libs/datatables/media/css/jquery.dataTables.css">


<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/js/libs/datatables/media/css/jquery.dataTables.css">
<%-- <link
	href="${contextPath}/resources/js/libs/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet"> --%>
<link
	href="${contextPath}/resources/js/libs//metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/timeline.css" rel="stylesheet">
<link href="${contextPath}/resources/css/sb-admin-2.css"
	rel="stylesheet">
<link
	href="${contextPath}/resources/js/libs/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/js/libs/ng-grid/ng-grid.css" />
<link rel="${contextPath}/resources/js/libs/angular-ui-grid/ui-grid.js"
	type="text/css">




<link
	rel="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css"
	type="text/css">


<link
	rel="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	type="text/css">

</head>

<body ng-app="expenseApp" ng-controller="expManagementController"
	ng-init="">
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><b>Notify Manager</b></a>
			</div>
			<!-- /.navbar-header -->
			<ul class="nav navbar-top-links navbar-right">
				<!-- /.dropdown -->
				<span class="label label-primary">${pageContext.request.userPrincipal.name}</span>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"> <i class="fa fa-user fa-fw"></i><i
						class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li>
							<div align="center">
								<input type="hidden"
									value="${pageContext.request.userPrincipal.name}"
									id="loginUser">
								<c:if test="${pageContext.request.userPrincipal.name != null}">
									<form id="logoutForm" method="POST"
										action="${contextPath}/logout">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
									</form>
								</c:if>
								<button type="submit" class="btn btn-warning"
									onclick="document.forms['logoutForm'].submit()">
									<i class="glyphicon glyphicon-log-out"></i> Logout
								</button>
							</div>
						</li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="#overview"><i class="fa fa-dashboard fa-fw"></i>
								DashBoard</a></li>
						<li><a href="#"><i class="fa fa-user fa-fw"></i> Profile<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#profile">Update User Profile</a></li>
								<li><a href="#viewUserProfile">View User Profile</a></li>
							</ul></li>


						<!-- <li><a href="#createExpense"><i class="fa fa-edit fa-fw"></i>
								Create Expense</a></li> -->
								
								
								<li><a href="#menu"><i class="fa fa-dashboard fa-fw"></i>
								Menu</a></li>
								<li><a href="#Orders"><i class="fa fa-dashboard fa-fw"></i>
								Orders</a></li>
								<li><a href="#release"><i class="glyphicon glyphicon-th-list"></i>
								Release Version Info</a></li>					

						<!-- <li><a href="#menu"><i class="fa fa-cutlery fa-fw"></i> Menu
								<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#menu">View Menu</a></li>
								<li><a href="#addItem">Add Item</a></li>
								<li><a href="#edit">Edit Menu</a></li>
								<li><a href="#delete">Delete From Menu</a></li>
							</ul></li>
 -->
						<!-- <li><a href="#"><i class="fa fa-bell fa-fw"></i> Notify <span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#">View Notify</a></li>
								<li><a href="#">Add Notify</a></li>
								<li><a href="#">Edit Notify</a></li>
							</ul></li> -->
		
<!-- 
						<li><a href="#"><i class="fa fa-spoon fa-fw"></i> Order <span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#">View Order</a></li>
								<li><a href="#">Add Order</a></li>
								<li><a href="#">Edit Order</a></li>
							</ul></li>

 -->
						<!-- 
						<li><a href="#"><i class="fa fa-wrench fa-fw"></i>Settings<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#settings">Update Password</a></li>
								<li><a href="#viewHistory">History</a></li>
							</ul> /.nav-second-level</li> -->
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>



		<div id="page-wrapper" ng-view></div>

	</div>
	<!-- /#wrapper -->

	<script src="${contextPath}/resources/js/libs/angular/angular.js"></script>
	<script
		src="${contextPath}/resources/js/libs/angular-touch/angular-touch.js"></script>
	<script
		src="${contextPath}/resources/js/libs/angular-animate/angular-animate.js"></script>
	<script src="${contextPath}/resources/js/libs/ng-grid/ng-grid.min.js"></script>
	<script
		src="${contextPath}/resources/js/libs/angular/angular-route.min.js"></script>
	<script
		src="${contextPath}/resources/js/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="${contextPath}/resources/js/libs/metisMenu/dist/metisMenu.min.js"></script>
	<script src="${contextPath}/resources/js/libs/sb-admin-2/sb-admin-2.js"></script>
	<script src="${contextPath}/resources/js/jquery.dataTables.min.js"></script>


	<!-- Application code java script files -->
	<script type="text/javascript"
		src="${contextPath}/resources/js/appjs/rmexpapp.js"></script>
	<script type="text/javascript"
		src="${contextPath}/resources/js/appjs/routers.js"></script>
	<script type="text/javascript"
		src="${contextPath}/resources/js/appjs/homeCtrl.js"></script>
	<script type="text/javascript"
		src="${contextPath}/resources/js/appjs/profileCtrl.js"></script>
	<script type="text/javascript"
		src="${contextPath}/resources/js/appjs/settingCtrl.js"></script>
	<script type="text/javascript"
		src="${contextPath}/resources/js/appjs/updatepasswordhistory.js"></script>
	<script type="text/javascript"
		src="${contextPath}/resources/js/appjs/createNewExpense.js"></script>
	<script type="text/javascript"
		src="${contextPath}/resources/js/appjs/menuHandler.js"></script>

<script type="text/javascript"
		src="${contextPath}/resources/js/appjs/releaseVersionController.js"></script>

</body>
</html>

