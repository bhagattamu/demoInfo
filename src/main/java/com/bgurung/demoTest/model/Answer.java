package com.bgurung.demoTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="answers")
public class Answer {
	@Id
	@GeneratedValue
	@Column(name="answer_id")
	private Long answerId;
	@Column(name="question_id")
	private Long questionIds;
	@Column(name="user_id")
	private Long userId;
	@Column(name="answer")
	private String answer;
	@Column(name="correctness")
	private Boolean correctness;
	@Column(name="created_at")
	private String createdAt;
	public Long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(Long questionIds) {
		this.questionIds = questionIds;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Boolean getCorrectness() {
		return correctness;
	}
	public void setCorrectness(Boolean correctness) {
		this.correctness = correctness;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", questionIds=" + questionIds + ", userId=" + userId + ", answer="
				+ answer + ", correctness=" + correctness + ", createdAt=" + createdAt + "]";
	}
}
