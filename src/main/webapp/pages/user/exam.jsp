<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/sidenav.jsp" />
<div class="container-fluid">
  	<h1 class="mt-4">Exam List</h1>
	<table>
	<thead>
		<tr>
			<th>Exam Id</th>
			<th>Exam</th>
			<th>Time Scheduled</th>
			<th>Time For Exam</th>
			<th>Created At</th>
			<th>Created By</th>
		<tr>
	<thead>
	<tbody>
		<c:forEach var="exam" items="${examlist }">
			<tr onclick="redirectToExam('${exam.testId}')">
				<td>${exam.testId }</td>
				<td>${exam.testDesc }</td>
				<td>###</td>
				<td>###</td>
				<td>${exam.createdAt }</td>
				<td>userId: ${exam.testMakerId }</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>  	
</div>

<jsp:include page="../layout/postsidenav.jsp" />
<script type="text/javascript" src="<c:url value="/js/config.js" />"></script>
<script>
	function redirectToExam(testId){
		window.location.replace(DOMAIN + "/exam/" + testId);
	}
</script>
<jsp:include page="../layout/finalfooter.jsp" />
</body>