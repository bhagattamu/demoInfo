package com.bgurung.demoTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue
	@Column(name="question_id")
	private Long questionId;
	@Column(name="dept_id")
	private String deptIds;
	@Column(name="design_id")
	private String designIds;
	@Column(name="user_id")
	private Long userId;
	@Column(name="question")
	private String questions;
	@Column(name="weight")
	private float weight;
	@Column(name="timeforans")
	private float timeForAns; // in sec
	@Column(name="created_at")
	private String createdAt;
	
	public Question (Question question) {
		super();
		this.questionId = question.getQuestionId();
		this.deptIds = question.getDeptIds();
		this.designIds = question.getDesignIds();
		this.userId = question.getUserId();
		this.questions = question.getQuestions();
		this.weight = question.getWeight();
		this.timeForAns = question.getTimeForAns();
		this.createdAt = question.getCreatedAt();
	}
	public Question() {
		
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
	public String getDesignIds() {
		return designIds;
	}
	public void setDesignIds(String designIds) {
		this.designIds = designIds;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getTimeForAns() {
		return timeForAns;
	}
	public void setTimeForAns(float timeForAns) {
		this.timeForAns = timeForAns;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", deptIds=" + deptIds + ", designIds=" + designIds + ", userId="
				+ userId + ", questions=" + questions + ", weight=" + weight + ", timeForAns=" + timeForAns
				+ ", createdAt=" + createdAt + "]";
	}
}
