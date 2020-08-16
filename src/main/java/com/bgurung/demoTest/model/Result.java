package com.bgurung.demoTest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="results")
public class Result {
	@Id
	@GeneratedValue
	@Column(name="result_id")
	private Long resultId;
	@Column(name="test_id")
	private Long testId;
	@Column(name="user_id")
	private Long userId;
	@Column(name="fullresult")
	private String fullResult;
	@Column(name="marks")
	private float marks;
	@Column(name="created_at")
	@CreationTimestamp
	private Date createdAt;
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Long getResultId() {
		return resultId;
	}
	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}
	public Long getTestId() {
		return testId;
	}
	public void setTestId(Long testId) {
		this.testId = testId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFullResult() {
		return fullResult;
	}
	public void setFullResult(String fullResult) {
		this.fullResult = fullResult;
	}
	public float getMarks() {
		return marks;
	}
	public void setMarks(float marks) {
		this.marks = marks;
	}
	
}
