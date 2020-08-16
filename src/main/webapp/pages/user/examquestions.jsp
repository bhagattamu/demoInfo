<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<c:choose>
				<c:when test="${timeForExam == 0 }" >
						<div class="card">
						  <div class="card-header">
							<h5>"${questions }"</h5>
						  </div>
						  <div class="card-body">
						    
						  </div>
						</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="question" items="${questions }">
						<div class="card">
						  <div class="card-header">
							<h5>${question.questions }</h5>
						  </div>
						  <div class="card-body">
						    <div class="card-text">
							    <c:forEach var="answer" items="${answers }" varStatus="theCount">
									<c:choose>
										<c:when test="${answer.questionIds == question.questionId }" >
											<div class="form-group row">
												<input class="input${answer.questionIds }${answer.answerId}" id="input${answer.questionIds }${answer.answerId}" onchange="changeInput('${answer.questionIds }','${answer.answerId }')"type="radio" name="answer${answer.questionIds }" value="${answer.answer }" />
												<label for="answer">${answer.answer }</label>
											</div>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						    </div>
						  </div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>