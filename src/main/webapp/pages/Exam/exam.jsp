<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/sidenav.jsp" />
<div class="container-fluid">
  	<h1 class="mt-4">Create Exam</h1>
  	<form:form action="addExam" method="POST" name="Form" onsubmit="return validateForm()" modelAttribute="exam">
	  <div class="form-group row">
	    <form:label path = "testDesc" class="col-sm-2 col-form-label">Exam Description</form:label>
	    <div class="col-sm-10">
	      <form:textarea class="form-control" path = "testDesc" placeholder="Enter Exam Description" required="required" />
	    </div>
	  </div>
	  <div class="form-group row">
	    <form:label path = "testFullWeight" class="col-sm-2 col-form-label">Exam Full Weight</form:label>
	    <div class="col-sm-10">
	      <form:input class="form-control" path = "testFullWeight" type="number" placeholder="Enter Full Weight" required="required" />
	    </div>
	  </div>
	  <div class="form-group row">
	    <form:label path = "testPassWeight" class="col-sm-2 col-form-label">Exam Pass Weight</form:label>
	    <div class="col-sm-10">
	      <form:input class="form-control" path = "testPassWeight" type="number" placeholder="Enter Pass Weight" required="required" />
	    </div>
	  </div>
	  <div class="form-group row">
	    <div class="col-sm-2">Departments</div>
	    <div class="col-sm-10">
		    <c:forEach var="dept" items="${departments }">
				<div class="form-check">
						<input type="checkbox" class="form-check-input" name="${dept.deptName }" onchange="changeDept(event,this)" value="${dept.deptId}"  />
						<label class="form-check-label" for="${dept.deptName }">${dept.deptName }</label>
				</div>
			</c:forEach>
			<form:input class="form-check-input" path="deptIds" id="department" type="hidden" required="required"/>
	    </div>
	  </div>
	  <div class="form-group row">
	    <div class="col-sm-2">Designations</div>
	    <div class="col-sm-10">
		    <c:forEach var="design" items="${designations }">
				<div class="form-check">
						<input type="checkbox" class="form-check-input" name="${design.designShort}" onchange="changeDesign(event,this)" value="${design.designShort}"  />
						<label class="form-check-label" for="${design.designShort }">${design.designFull }</label>
				</div>
			</c:forEach>
			<form:input class="form-check-input" id="designation" path="designIds" type="hidden" required="required" />
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="testtakers">Test Takers</label>
	    <select multiple class="form-control" id="testtakers" onchange="showValue(event,this)" required>
	      <option value="0">Select Test Takers</option>
	    </select>
	    <form:input class="form-control" path="testTakerIds" id="testtaker" type="hidden" required="required" />
  	  </div>
	  
	  <div class="form-group row">
	    <div class="col-sm-10">
	      <input type="submit" name="Add Exam" class="btn btn-primary" />
	    </div>
	  </div>
	</form:form>
	<table id="examList" class="table">
  <thead>
    <tr>
      <th scope="col">#Id</th>
      <th scope="col">Exam Desc</th>
      <th scope="col">Departments</th>
      <th scope="col">Designations</th>
      <th scope="col">Created At</th>
    </tr>
  </thead>
  <tbody>
    
  </tbody>
</table>
</div>
<jsp:include page="../layout/postsidenav.jsp" />
<script type="text/javascript" src="<c:url value="/js/examquestion.js" />"></script>
<script>
var userList = [];
$(document).ready(function(){
    var userId = <%= session.getAttribute("userid")%>
	
	// Fetch data and add table
	$.ajax({
		type: "GET",
  		url: "http://localhost:8081/demoTest/getExamList/" + userId,
  		success: function(result){
  			console.log("Result",result);
			var tr = "";
			result.map((exam) => {
			tr = tr + "<tr onclick='redirectToAddQuestions(event,this," + exam.testId + ")'>" + renderTd(exam.testId,exam.testDesc,exam.deptIds,exam.designIds,exam.createdAt) + "</tr>";
			})
			$('#examList tbody').append(tr);
  		}
	});
	$.ajax({
		type: "GET",
  		url: "http://localhost:8081/demoTest/getNormUsers",
  		success: function(result){
  			userList = result;
			var o;
			console.log("UserList",userList);
			userList.map((user) => {
			o = new Option("(" + user.designation + ") " + user.firstName + " (" + user.username + ")", user.userId);
			$(o).html("(" + user.designation + ") " + user.firstName + " "  + user.lastName +" (" + user.username + ")");
			$("#testtakers").append(o);
		})

  		}
	})
});
function renderTd(id,examDesc,deptIds,designIds,createdAt){
	//getDepartmentById(deptIds);
	console.log("deptObj",departmentObj)
	return `
	<td>\${id}</td>
	<td>\${examDesc}</td>
	<td>\${getDepartmentById(deptIds)}</td>
	<td>\${designIds}</td>
	<td>\${createdAt}</td>
	`;
	//return "<td>" + id + "</td><td>" + examDesc  + "</td><td>" + getDepartmentById(deptIds) + "</td><td>" + designIds + " </td><td>" + createdAt + "</td>";
}
var departmentObj=[];
<%-- exam.jsp --%>
//Fetch department name and id in key value

<c:forEach var="department" items="${departments }">
	departmentObj.push({
		deptId: "<c:out value="${department.deptId}"/>",
		deptName: "<c:out value="${department.deptName}"/>"
	})
	
</c:forEach>
	
	

	function redirectToAddQuestions(e,elem,id){
		window.location.replace("http://localhost:8081/demoTest/question/exam/" + id);
	}

	function showValue(e,elem){
		var selected = [...elem.selectedOptions].map(option => option.value).filter(value => value !== "0");	
		$("#testtaker").val(selected);
	}
</script>
<jsp:include page="../layout/finalfooter.jsp" />


