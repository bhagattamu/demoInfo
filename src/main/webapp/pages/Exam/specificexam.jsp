<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/sidenav.jsp" />
<div class="container-fluid">
	exam form disable ${disableForm }
	<fmt:parseNumber var = "i" type = "number" integerOnly = "true" value = "${examtime}" />
	<fmt:parseNumber var = "marks" type = "number" integerOnly = "true" value = "${examMarks}" />
	<fmt:parseNumber var = "min" type = "number" integerOnly = "true" value = "${i/60 }" />
  	<h1 class="mt-4">Exam #${examid} Time ${min } : ${i%60 } min 	Total Marks: ${marks }</h1>
  	<h2 id="examReady">Exam passed to exam takers: ${examReady }</h2> <button onclick="readyExam('${examid}')">Click here to make Ready</button><button onclick="cancelExam('${examid}')">Click here to cancel exam</button>
  	<h2 class="mt-4">${examDesc}</h2>
  	<c:forEach var="question" items="${questions }">
  		<div class="card">
		  <div class="card-header">

		  </div>
		  <div class="card-body">
		    <h5 class="card-title">"${question.questions }"</h5>
		    <button onclick="deleteQuestion('${question.questionId}','${examid }')">Delete</button>
		    <div class="card-text">
		    <c:set var="count" value="${0}"/>
				<c:forEach var="answer" items="${answers }" varStatus="theCount">
					<c:choose>
						<c:when test="${answer.questionIds == question.questionId }" >
						<c:set var="count" value="${count + 1 }" />
							<div class="form-group row">
								<label for="answer">Answer</label>
								<input class="input${answer.questionIds }${answer.answerId}" name="answer" value="${answer.answer }" disabled/>
								<label >CorrectNess</label>
								<input class="input${answer.questionIds }${answer.answerId}" name="correctness${question.questionId }${answer.answerId }" type="radio" <c:choose><c:when test='${answer.correctness == true }'>checked</c:when><c:otherwise></c:otherwise></c:choose> value="true" disabled /><label for="correctness">True</label>
								<input class="input${answer.questionIds }${answer.answerId}" name="correctness${question.questionId }${answer.answerId }" type="radio" <c:choose><c:when test='${answer.correctness == true }'></c:when><c:otherwise>checked</c:otherwise></c:choose> value="false"  disabled /> <label for="correctness">False</label>
								<label for="weight">Weight</label>
								<input name="weight" type="number" value="${question.weight }" disabled />
								<label for="timeForAns">Time for Ans</label>
								<input name="timeForAns" type="number" value="${question.timeForAns }" disabled />
								<button onclick="updateForm('input${answer.questionIds}${answer.answerId }')">Update</button>
								<button id="input${answer.questionIds}${answer.answerId }" onclick="saveForm('input${answer.questionIds}${answer.answerId }','${answer.answerId }')" disabled>Save</button>
								<button onclick="cancelForm('input${answer.questionIds}${answer.answerId }')">Cancel</button>
								<button onclick="deleteAnswer('${answer.answerId}')">Delete</button>
							</div>
						</c:when>
						<c:otherwise>
				            
				         </c:otherwise>
					</c:choose>
				</c:forEach>
		    </div>
		    <c:choose>
		    	<c:when test="${count >= 4 }">
		    		<button type="button" class="btn btn-primary" onclick="setData('${question.questionId }')" data-toggle="modal" data-target="#exampleModal" disabled>
					  Add New Answer
					</button>
		    	</c:when>
		    	<c:otherwise>
			    	<button type="button" class="btn btn-primary" onclick="setData('${question.questionId }')" data-toggle="modal" data-target="#exampleModal">
					  Add New Answer
					</button>
		    	</c:otherwise>
		    </c:choose>
		    
		  </div>
		</div>
  	</c:forEach>
  	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Add Answer</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form>
	        <div class="form-group row">
		        <label for = "questionId" class="col-sm-2 col-form-label">Question Id</label>
			    <div class="col-sm-10">
			      <input class="form-control" id="questionId" name = "questionId" placeholder="Enter Question Id" required />
			    </div>
	        </div>
	        <div class="form-group row">
		        <label for = "answer" class="col-sm-2 col-form-label">Answer</label>
			    <div class="col-sm-10">
			      <textarea class="form-control" id="answer" name = "answer" placeholder="Enter Answer" required ></textarea>
			    </div>
	        </div>
	        <div class="form-group row">		        
			    <label class="col-sm-2 col-form-label">Correctness</label>
			  	<div class="col-sm-10">
				  <div class="form-check">
						  <input type="radio" class="form-check-input" onclick="document.getElementById('correctnessresult').value = true;" name="correctness" value="true" checked="checked" />
						  <label class="form-check-label" for="correctness" >
						    True
						  </label>
				  </div>
				  <div class="form-check">
					  <input type="radio" class="form-check-input" onclick="document.getElementById('correctnessresult').value = false;" name="correctness" value="false" />
					  <label class="form-check-label" for="correctness">
					    False
					  </label>
				  </div>
				 </div>
				 <input type="text" id="correctnessresult" value="true" hidden/>
	        </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" onclick="submitAnswer()" class="btn btn-primary">Add Answer</button>
	      </div>
	    </div>
	  </div>
	</div>
  	<form onsubmit="addQuestion(event)" name="Form" method="POST">
  	<c:choose>
  		<c:when test="${disableForm == true }">
  			<fieldset disabled= "disabled">
  		</c:when>
  		<c:otherwise>
  			<fieldset>
  		</c:otherwise>
  	</c:choose>
  	
  		<input type="text" id="testId" name="testId" value="${examid }" hidden />
  		<input type="text" id="userId" name="userId" value="<%=session.getAttribute("userid") %>" hidden />
  		<div class="form-group row">
	    	<label for = "question" class="col-sm-2 col-form-label">Question</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" id="question" name = "question" placeholder="Enter Question" required ></textarea>
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
				<input class="form-check-input" name="deptIds" id="department" type="hidden" required="required"/>
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
				<input class="form-check-input" id="designation" name="designIds" type="hidden" required="required" />
		    </div>
	  	</div>

		<div class="form-group">
		    <label for="weight">Weight</label>
		    <input class="form-control" name="weight" id="weight" type="number" required="required" />
  	  	</div>
  	  	<div class="form-group">
		    <label for="timeForAns">Time For Answer</label>
		    <input class="form-control" name="timeForAns" id="timeForAns" type="number" required="required" />
  	  	</div>
  	  	<div class="form-group row">
		    <div class="col-sm-10">
		      <input type="submit" name="Add Question" class="btn btn-primary" />
		    </div>
	  	</div>
	  </fieldset>
  	</form>
</div>
<jsp:include page="../layout/postsidenav.jsp" />
<script type="text/javascript" src="<c:url value="/js/config.js" />"></script>
<script>
var testTakers = "${testtakers}";
var testTakerArray = testTakers.split(",");
console.log("Test taker array",testTakerArray);
var department = [];
var designation = [];
var questionList = [];
<c:forEach var="question" items="${questions }">
	questionList.push("<c:out value="${question.questionId }" />");
	/*questionList.push(
				{
					questionId: "<c:out value="${question.questionId }" />",
					question: "<c:out value="${question.questions }" />",
					weight: "<c:out value="${question.weight }" />",
					timeForAns: "<c:out value="${question.timeForAns }" />",
					createdAt: "<c:out value="${question.createdAt }" />"
				}
			);*/
</c:forEach>
console.log(questionList);
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
	document.forms["Form"]["deptIds"].value = deptString === undefined ? "" : deptString;
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
	document.forms["Form"]["designIds"].value = designString === undefined ? "" : designString;
}
	function addQuestion(e) {
		e.preventDefault();
		formData = {
				"testId": document.getElementById("testId").value,
				"userId": document.getElementById("userId").value,
				"questions": document.getElementById("question").value,
				"deptIds": document.getElementById("department").value,
				"designIds": document.getElementById("designation").value,
				"weight": document.getElementById("weight").value,
				"timeForAns": document.getElementById("timeForAns").value
		}
		$.ajax({
			method: "POST",
			url: DOMAIN + "/admin/postQuestion",
			data: JSON.stringify(formData),
			contentType: "application/json",
			success: function(result){
				console.log("Post result",result);
				var question = result;
				questionList.push(result.questionId); // questionList.join("|") for | seperated value
				console.log("questionList",questionList.join("|"));
				$.ajax({
					method: "PUT",
					url: DOMAIN + "/admin/putQuestionInExam/" + formData.testId,
					data: JSON.stringify({
							"testQuestions": questionList.join("|")
						}),
					contentType: "application/json",
					success: function(result){
						console.log("Updated exam",result,question);
						window.location.replace(DOMAIN + "/question/exam/" + formData.testId);
					}
				})
				
			}
		})
	}
	
	function updateForm(className){
		$("." + className).prop( "disabled", false );
		$("#" + className).prop( "disabled", false);
	}
	function cancelForm(className){
		$("." + className).prop( "disabled", true );
		$("#" + className).prop( "disabled", true);
	}
	function saveForm(className, answerId){
		var a= $("." + className);
		var dummyData;
		var dummyData2 = [];
		var formData;
		dummyData = a.map(elem => a[elem].name.startsWith("correctness") ? (a[elem].checked? dummyData2.push(["correctness",a[elem].value]): console.log("false") ) : dummyData2.push([a[elem].name,a[elem].value]));
		console.log(dummyData2)
		formData = Object.fromEntries(dummyData2);
		console.log(formData)
		$.ajax({
			method: "PUT",
			url: DOMAIN + "/admin/putAnswer/" + answerId,
			contentType: "application/json",
			data: JSON.stringify(formData),
			success: function(result){
				console.log("Success updated",result);
				window.location.replace(DOMAIN + "/question/exam/" + "<c:out value="${examid}" />");
			}
		})
	}
	
	function setData(questionId){
		console.log(questionId)
		document.getElementById("questionId").value = questionId;
	}
	function validateAnswer(formData){
		if(formData.questionIds == "")
			return false;
		if(formData.answer == "")
			return false;
		if(formData.correctness == "")
			return false;
		
		return true;
	}
	function submitAnswer(){
		var formData = {
				"questionIds": document.getElementById("questionId").value,
				"answer": document.getElementById("answer").value,
				"correctness": document.getElementById("correctnessresult").value
		}
		console.log(formData)
		if(validateAnswer(formData)){
			// add to database
			$.ajax({
				method: "POST",
				url: DOMAIN + "/admin/postAnswer",
				contentType: "application/json",
				data: JSON.stringify(formData),
				success: function(result){
					console.log("Success",result)
					window.location.replace(DOMAIN + "/question/exam/" + "<c:out value="${examid}" />");
				}
			})
		}else{
			alert("incorrect data");
		}
	}
	function deleteQuestion(questionId,examId){
		console.log(questionId);
		$.ajax({
			method: "DELETE",
			url: DOMAIN + "/admin/deleteQuestion/" + questionId,
			success: function(result){
				console.log("Success delete", result);
				$.ajax({
					method: "PUT",
					url: DOMAIN + "/admin/putExamAfterDeleteQuestion/" + examId,
					data: JSON.stringify({"testQuestions": questionId}),
					contentType: "application/json",
					success: function(result){
						console.log("Success update test question");
						window.location.replace(DOMAIN + "/question/exam/" + "<c:out value="${examid}" />");
					}
				})
			}
		})
	}
	function deleteAnswer(answerId){
		$.ajax({
			method: "DELETE",
			url: DOMAIN + "/admin/deleteAnswer/" + answerId,
			success: function(result){
				console.log("Success delete", result);
				window.location.replace(DOMAIN + "/question/exam/" + "<c:out value="${examid}" />");
			}
		})
	}
	function readyExam(examId){
		$.ajax({
			method: "PUT",
			url: DOMAIN + "/admin/putExam/" + examId,
			data: JSON.stringify({"testComplete": true}),
			contentType: "application/json",
			success: function(result){
				console.log("Success update");
				$("#examReady").text("Exam passed to exam takers: true" );
				testTakerArray.map(function(userId){
					// send notification to the exam takers;
					$.ajax({
						method: "POST",
						url: DOMAIN + "/admin/postNotification",
						contentType: "application/json",
						data: JSON.stringify({
							"userId": userId,
							"notificationMessage": "You have new exam:" + "<c:out value="${examDesc}" />",
							"notificationSeen": false
						}),
						success: function(result){
							console.log("Notification sent to userId" + userId);
						}
						
					})
				})
				
				
			}
		})
	}
	function cancelExam(examId){
		$.ajax({
			method: "PUT",
			url: DOMAIN + "/admin/putExam/" + examId,
			data: JSON.stringify({"testComplete": false}),
			contentType: "application/json",
			success: function(result){
				console.log("Success update")
				$("#examReady").text("Exam passed to exam takers: false");
			}
		})
	}
</script>
<jsp:include page="../layout/finalfooter.jsp" />


