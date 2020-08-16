// Fetch data and add table
var userList = [];
$(document).ready(function(){
	$.ajax({
		type: "GET",
  		url: "http://localhost:8081/demoTest/getAdminUsers",
  		success: function(result){
  			userList = result;
  			$.ajax({
  				type: "GET",
  		  		url: "http://localhost:8081/demoTest/getQuestionList",
  		  		success: function(result){
  		  			
  		  			console.log("Result",result);
  		  			console.log(userList);
  		  			
  					var tr = "";
  					result.map((question) => {
  					tr = tr + "<tr onclick='redirectToAddAnswers(event,this," + question.questionId + ")'>" + renderTd(question.questionId,question.questions,question.deptIds,question.designIds,question.weight,question.timeForAns,question.createdAt,question.userId) + "</tr>";
  					})
  					$('#questionList tbody').append(tr);
  		  		}
  			});
  		}
	})
})	
function getUserById(userId){
	console.log("get user",userList)
	const userName = userList.filter((user) => user.userId == userId)[0].username;
	return userName;
}
function renderTd(id,question,deptIds,designIds,weight,timeForAns,createdAt,userId){
	//getDepartmentById(deptIds);
	
	return `
	<td>${id}</td>
	<td>${question}</td>
	<td>${getDepartmentById(deptIds)}</td>
	<td>${designIds}</td>
	<td>${weight}</td>
	<td>${timeForAns} min</td>
	<td>${createdAt}</td>
	<td>${getUserById(userId)}</td>
	`;
	//return "<td>" + id + "</td><td>" + examDesc  + "</td><td>" + getDepartmentById(deptIds) + "</td><td>" + designIds + " </td><td>" + createdAt + "</td>";
}
function redirectToAddAnswers(e,elem,questionId){
	window.location.replace("http://localhost:8081/demoTest/answers/question/" + questionId);
}