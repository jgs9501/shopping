<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<style type="text/css">
	.myMenu{
		position: static;
		font-size: x-small;
		list-style-type: none;
		float: right;
	}
	.myMenu-li{
		width: 80px;
		float: right;
	}
</style>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand" href="#">JS Shop</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"	data-target="#navToggle" aria-controls="navToggle" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navToggle">
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="text"	placeholder="Search">
					<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
				</form>
				<ul class="navbar-nav my-auto">
					<li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" id="themes">Themes</a>
						<div class="dropdown-menu" aria-labelledby="themes">
						    <a class="dropdown-item">Default</a> 
						    <a class="dropdown-item">Cerulean</a>
						 	<a class="dropdown-item">Cosmo</a> 
						 	<a class="dropdown-item">Cyborg</a>
							<a class="dropdown-item">Darkly</a> 
							<a class="dropdown-item">Flatly</a>
							<a class="dropdown-item">Journal</a> 
							<a class="dropdown-item">Litera</a>
							<a class="dropdown-item">Lumen</a>
						</div>
				    </li>
					<li class="nav-item"><a class="nav-link" href="#">Pricing</a></li>
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
				</ul>
			</div>
		</div>
		<div>
		<ul class="myMenu navbar-nav">
			<li class="myMenu-li nav-item"><a class="nav-link" href="#">로 그 인</a></li>
			<li class="myMenu-li nav-item"><a class="nav-link" href="#">회 원 가 입</a></li>
	    </ul>
		</div>
	</nav>
</body>
</html>