<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/sidenav.jsp" />
<div class="container-fluid">
  	<h1 class="mt-4">Exam #${examid }</h1>
	<button id="startexam" onclick="startExam('${examid}')">Start Exam</button>
</div>

<jsp:include page="../layout/postsidenav.jsp" />
<script type="text/javascript" src="<c:url value="/js/config.js" />"></script>
<script>
		var timeForExam = "${timeForExam}";
		console.log("Timedor exam",timeForExam/60)
		
	function startExam(examId){
		//document.getElementById("timer").hidden = false;
		window.location.replace("http://localhost:8081/demoTest/exam-started/" + examId);
	}
	
	
</script>
<jsp:include page="../layout/finalfooter.jsp" />
</body>