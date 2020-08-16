<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:parseNumber var = "timeForExamInt" type = "number" integerOnly = "true" value = "${timeForExam}" />
<fmt:parseNumber var = "timeForExamIntMin" type = "number" integerOnly = "true" value = "${timeForExam / 60}" />

<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/sidenav.jsp" />
<div class="container-fluid">
  	<h1 class="mt-4">Exam #${examid }</h1>
  	<c:choose>
  		<c:when test="${removeStartButton == true }">
  		<h2 id="timer">Time For Exam: ${timeForExamIntMin} : ${timeForExamInt}</h2>
  		</c:when>
  		<c:otherwise>
  			<button id="startexam" onclick="startExam('${examid}')">Start Exam</button>
  			<h2 id="timerstart" hidden>Time For Exam: ${timeForExamIntMin} : ${timeForExamInt}</h2>
  		</c:otherwise>
  	</c:choose>
	
	<jsp:include page="./examquestions.jsp" />
</div>

<jsp:include page="../layout/postsidenav.jsp" />
<script type="text/javascript" src="<c:url value="/js/config.js" />"></script>
<script>
		var timeForExam = "${timeForExam}";
		console.log("Timedor exam",timeForExam/60);
		var fullResult;
		var removeStartButton = "${removeStartButton}";
		if(removeStartButton){
			runTimerFrontEnd(timeForExam)
			localStorage.setItem("resultId","${resultId}");
			fullResult = "${fullResult}";
			console.log("Full result : " , fullResult)
			// Now set it to frontend to show prev paila ko exam ma gareko sab aafai bharina ko lagi
			fullResult.split("|").map(function(qas){
				var makeInputId = "input";
				console.log(qas)
				qas.split(",").map(function(qa){
					makeInputId = makeInputId + qa;
				});
				$("#" + makeInputId).prop("checked",true)
				
			})
		}
		
	function startExam(examId){
		//document.getElementById("timer").hidden = false;
		document.getElementById("startexam").hidden=true;
		
		//$("#examquestions").append(examHTML);
		
		// timer start in backend
		runTimerFrontEnd(timeForExam);
		$("#timerstart").prop("hidden",false);
		$.ajax({
			method: "POST",
			data: JSON.stringify({
				testId: examId,
				fullResult: "started",
				marks: 0
			}),
			contentType: "application/json",
			url: DOMAIN + "/user/postResult",
			success: function(result){
				console.log("Success posting result",result);
				localStorage.setItem("resultId",result.resultId);
				//$("#timer").text("Time For Exam: " + timeForExam / 60 + " : " + timeForExam % 60);
				$.ajax({
					method: "PUT",
					data: JSON.stringify({
						resultId: result.resultId,
						timeFinish: false
					}),
					url: DOMAIN + "/user/startTimer",
					contentType: "application/json",
					success: function(result){
						console.log("Exam time finisshed",result);
					}
				})
				
			}
		})
	}
	function changeInput(questionId,answerId){
		console.log(questionId,answerId);
		fullResult = questionId + "," + answerId;
		resultId = localStorage.getItem("resultId");
		// put in result
		$.ajax({
			method: "PUT",
			url: DOMAIN + "/user/putResult/" + resultId,
			data: JSON.stringify({
				fullResult: fullResult
			}),
			contentType: "application/json",
			success: function(result){
				console.log("updated success");
			}
		})
		
	}
	function runTimerFrontEnd(time){
		console.log("running timer")
		const intervalId = setInterval(updateCountDown,1000);
		function updateCountDown(){
			const minutes = Math.floor(time / 60);
			let seconds = time % 60;
			seconds = seconds < 10 ? "0" + seconds : seconds; 
			$("#timer").text("Time For Exam: " + minutes + " : " + seconds);
			
			time--;
			if(time < 0){
				clearInterval(intervalId);
				setTimeout(function(){
					console.log("Redirecting to home after 3 sec");
					window.location.replace(DOMAIN + "/home");
				},3000)
			}	
		}
	}
	
</script>
<jsp:include page="../layout/finalfooter.jsp" />
</body>