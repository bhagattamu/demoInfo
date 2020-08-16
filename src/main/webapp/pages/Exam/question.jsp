<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/sidenav.jsp" />
<div class="container-fluid">
  	<h1 class="mt-4">Add Questions</h1>
  	<form:form action="addQuestion" method="POST" name="Form" onsubmit="" modelAttribute="question">
	  <div class="form-group row">
	    <form:label path = "questions" class="col-sm-2 col-form-label">Question</form:label>
	    <div class="col-sm-10">
	      <form:textarea class="form-control" path = "questions" placeholder="Enter Question" required="required" />
	    </div>
	  </div>
	  <div class="form-group row">
	    <form:label path = "weight" class="col-sm-2 col-form-label">Weight</form:label>
	    <div class="col-sm-10">
	      <form:input class="form-control" path = "weight" placeholder="Enter Weight For Question" type="number" required="required" />
	    </div>
	  </div>
	  <div class="form-group row">
	    <form:label path = "timeForAns" class="col-sm-2 col-form-label">Time for Answer</form:label>
	    <div class="col-sm-10">
	      <form:input class="form-control" path = "timeForAns" placeholder="Enter Time in minute" type="number" required="required" />
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
	  <div class="form-group row">
	    <div class="col-sm-10">
	      <input type="submit" name="Add Question" class="btn btn-primary" />
	    </div>
	  </div>
	</form:form>
	<table id="questionList" class="table">
	  <thead>
	    <tr>
	      <th scope="col">#Id</th>
	      <th scope="col">Question</th>
	      <th scope="col">Departments</th>
	      <th scope="col">Designations</th>
	      <th scope="col">Weights</th>
	      <th scope="col">Time</th>
	      <th scope="col">Created At</th>
	      <th scope="col">Created By</th>
	    </tr>
	  </thead>
	  <tbody>
	    
	  </tbody>
	</table>
</div>
<jsp:include page="../layout/postsidenav.jsp" />
<script type="text/javascript" src="<c:url value="/js/examquestion.js" />"></script>

<script>

var departmentObj=[];
<%-- exam.jsp --%>
//Fetch department name and id in key value

<c:forEach var="department" items="${departments }">
	departmentObj.push({
		deptId: "<c:out value="${department.deptId}"/>",
		deptName: "<c:out value="${department.deptName}"/>"
	})
</c:forEach>
	
</script>
<script type="text/javascript" src="<c:url value="/js/question.js" />"></script>
<jsp:include page="../layout/finalfooter.jsp" />

