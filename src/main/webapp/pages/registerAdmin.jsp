<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!--Bootsrap 4 CDN-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    <!--Fontawesome CDN-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css" />">
</head>
<body>
	<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Register</h3>
				<!-- 
				<div class="d-flex justify-content-end social_icon">
					<span><i class="fab fa-facebook-square"></i></span>
					<span><i class="fab fa-google-plus-square"></i></span>
					<span><i class="fab fa-twitter-square"></i></span>
				</div>
				 -->
			</div>
			<div class="card-body">
				<form action="registerAdmin" name="myForm" enctype="multipart/form-data" method="POST">
					<div class="input-group form-group">
						<input type="text" name="firstName" class="form-control" placeholder="Enter First Name">
					</div>
					<div class="input-group form-group">
						<input type="text" name="lastName" class="form-control" placeholder="Enter Last Name">				
					</div>
					<div class="input-group form-group">
						<input type="text" name="username" class="form-control" placeholder="Enter User Name">
					</div>
					<div class="input-group form-group">
						<input type="email" name="email" class="form-control" placeholder="Enter Email">
					</div>
					<div class="input-group form-group">						
						<input type="password" name="password" class="form-control" placeholder="Enter Passwords">
					</div>
					<legend>Choose Departments</legend>
					<c:forEach var="dept" items="${ departments }">
						<div class="input-group form-check">
								<input type="checkbox" class="form-check-input" name="${dept.deptName }" onchange="changeDept(event,this)" value="${dept.deptId}"  />
								<label class="form-check-label" for="${dept.deptName }">${dept.deptName }</label>
						</div>
					</c:forEach>
					<div class="input-group form-group">						
						<input type="text" name="department" class="form-control" placeholder="Departments" hidden>						
					</div>
					<legend>Choose Designations</legend>
					<c:forEach var="design" items="${ designations }">
						<div class="input-group form-check">
								<input type="checkbox" class="form-check-input" name="${design.designShort }" onchange="changeDesign(event,this)" value="${design.designShort}"  />
								<label class="form-check-label" for="${design.designShort }">${design.designFull }</label>
						</div>
					</c:forEach>
					<div class="input-group form-group">						
						<input type="text" name="designation" class="form-control" placeholder="Designations" hidden>						
					</div>
					<div class="input-group form-group">						
						<input type="file" name="file" class="form-control-file" >						
					</div>
					<div class="form-group">
						<input type="submit" value="Sign Up" class="btn float-right login_btn">
					</div>
				</form>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					Already have an account?<a href="login">Sign In</a>
				</div>
				<!-- 
				<div class="d-flex justify-content-center">
					<a href="#">Forgot your password?</a>
				</div>
				 -->
			</div>
		</div>
	</div>
</div>
<script>
var department = [];
var designation = [];
function changeDept(e,elem){
	var deptString;
	if(elem.checked){
		department.push(elem.value);
	}else{
		department = department.filter(function(dept){
			return dept !== elem.value;
		})
	}
	department.forEach(function(dept){
		deptString = deptString ? deptString + '|' + dept : dept;
	})
	document.forms["myForm"]["department"].value = deptString === undefined ? "" : deptString;
}

function changeDesign(e,elem){
	var designString;
	if(elem.checked){
		designation.push(elem.value);
	}else{
		designation = designation.filter(function(design){
			return design !== elem.value;
		})
	}
	designation.forEach(function(design){
		designString = designString ? designString + '|' + design : design;
	})
	document.forms["myForm"]["designation"].value = designString === undefined ? "" : designString;
}

</script>
</body>
</html>