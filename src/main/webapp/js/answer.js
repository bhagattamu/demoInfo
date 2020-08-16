var userList = [];
var questionList = [];
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
  		  			questionList = result;
  		  			var value = document.getElementById("questionId").value === "" ? "0" : document.getElementById("questionId").value;
  		  			console.log("questionid value",document.getElementById("questionId").value)
		  			$.ajax({
		  				type: "GET",
		  		  		url: "http://localhost:8081/demoTest/getAnswerList/question/" + value ,
		  		  		success: function(result){		  		  			
		  					var tr = "";
		  					console.log("answer",result);
		  					result.map((answer) => {
		  					tr = tr + "<tr onclick='updateAnswers(event,this," + answer.answerId + ")'>" + renderTd(answer.answerId,answer.questionIds,answer.answer,answer.correctness,answer.createdAt,answer.userId) + "</tr>";
		  					})
		  					$('#answerList tbody').append(tr);
		  		  		}
		  			});
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
function getQuestionById(questionId){
	const question = questionList.filter((question) => question.questionId == questionId)[0].questions;
	return question;
}
function renderTd(id,questionId,answer,correctness,createdAt,userId){
	//getDepartmentById(deptIds);
	
	return `
	<td>${id}</td>
	<td>${getQuestionById(questionId)}</td>
	<td>${answer}</td>
	<td>${correctness}</td>
	<td>${createdAt}</td>
	<td>${getUserById(userId)}</td>
	`;
	//return "<td>" + id + "</td><td>" + examDesc  + "</td><td>" + getDepartmentById(deptIds) + "</td><td>" + designIds + " </td><td>" + createdAt + "</td>";
}

function updateAnswers(e,elem,answerId){}
