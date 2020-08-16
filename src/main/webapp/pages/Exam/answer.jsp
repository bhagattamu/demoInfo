<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/sidenav.jsp" />
<div class="container-fluid">
  	<h1 class="mt-4">${question.questions}</h1>
<form:form action="/demoTest/addAnswer" method="POST" onsubmit="" modelAttribute="answers"> 
	  <div class="form-group row">
	    <form:label path = "questionIds" class="col-sm-2 col-form-label">Question Id</form:label>
	    <div class="col-sm-10">
	    <c:choose>
			<c:when test="${question.questionId == null }">
				<form:select class="form-control" path = "questionIds" id="questionId" required="required" >
					<form:option value="">Select question</form:option>
					<c:forEach var="question" items="${questions}">
						<form:option value="${question.questionId}">${question.questions }</form:option>
					</c:forEach>
				</form:select>
			</c:when>
			<c:otherwise>
				<form:input class="form-control" path = "questionIds" id="questionId" placeholder="Enter Question Id" type="text" value="${question.questionId}" required="required" />
			</c:otherwise>
		</c:choose>
	      
	    </div>
	  </div>
	  <div class="form-group row">
	    <form:label path = "answer" class="col-sm-2 col-form-label">Answer</form:label>
	    <div class="col-sm-10">
	      <form:textarea class="form-control" path = "answer" placeholder="Enter Answer" required="required" />
	    </div>
	  </div>
	  <div class="form-group row">
	  	<label class="col-sm-2 col-form-label">Correctness</label>
	  	<div class="col-sm-10">
		  <div class="form-check">
				  <form:radiobutton class="form-check-input" path="correctness" value="true" checked="checked" />
				  <form:label class="form-check-label" path="correctness" >
				    True
				  </form:label>
		  </div>
		  <div class="form-check">
			  <form:radiobutton class="form-check-input" path="correctness" value="false" />
			  <form:label class="form-check-label" path="correctness">
			    False
			  </form:label>
		  </div>
		 </div>
	  </div>
	  <div class="form-group row">
	    <div class="col-sm-10">
	      <input type="submit" name="Add Answer" class="btn btn-primary" />
	    </div>
	  </div>
	</form:form>
	<table id="answerList" class="table">
	  <thead>
	    <tr>
	      <th scope="col">#Id</th>
	      <th scope="col">Question</th>
	      <th scope="col">Answers</th>
	      <th scope="col">Correctness</th>
	      <th scope="col">Created At</th>
	      <th scope="col">Created By</th>
	    </tr>
	  </thead>
	  <tbody>
	  <!-- 
	  	<c:forEach var="ans" items="${answer}" >
	  		<tr>
	  			<td scope="col">${ans.answerId }</td>
	  			<td scope="col">${ans.questionIds }</td>
	  			<td scope="col">${ans.answer }</td>
	  			<td scope="col">${ans.correctness }</td>
	  			<td scope="col">${ans.createdAt }</td>
	  			<td scope="col">${ans.userId }</td>
	  		</tr>
	  	</c:forEach>
	  	 -->
	  </tbody>
	</table>
</div>
<jsp:include page="../layout/postsidenav.jsp" />
<script type="text/javascript" src="<c:url value="/js/answer.js" />"></script>
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
<jsp:include page="../layout/finalfooter.jsp" />

