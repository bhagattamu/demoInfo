package com.bgurung.demoTest.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tests")
public class Exam {
	@Id
	@GeneratedValue
	@Column(name="test_id")
	private Long testId;
	@Column(name="test_desc")
	private String testDesc;
	@Column(name="dept_ids")
	private String deptIds;
	@Column(name="design_ids")
	private String designIds; // | seperated value of design_short Jr|M|Sr
	@Column(name="testmaker_id")
	private Long testMakerId;
	@Column(name="testtaker_ids")
	private String testTakerIds; // | seperated value of id
	@Column(name="test_complete")
	private Boolean testComplete = false; // default false i.e test not yet taken
	@Column(name="test_full_weight")
	private float testFullWeight;
	@Column(name="test_pass_weight")
	private float testPassWeight;
	public float getTestFullWeight() {
		return testFullWeight;
	}
	public void setTestFullWeight(float testFullWeight) {
		this.testFullWeight = testFullWeight;
	}
	public float getTestPassWeight() {
		return testPassWeight;
	}
	public void setTestPassWeight(float testPassWeight) {
		this.testPassWeight = testPassWeight;
	}
	@Column(name="test_questions")
	private String testQuestions = "NO"; // | seperated value of question id
	@Column(name="test_time")
	private float testTime = 0; // default 0 it will be updated when adding questions
	@Column(name="created_at")
	private Timestamp createdAt; // Time of creating
	public Long getTestId() {
		return testId;
	}
	public String getTestDesc() {
		return testDesc;
	}
	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
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
	public void setTestId(Long testId) {
		this.testId = testId;
	}
	public Long getTestMakerId() {
		return testMakerId;
	}
	public void setTestMakerId(Long testMakerId) {
		this.testMakerId = testMakerId;
	}
	public String getTestTakerIds() {
		return testTakerIds;
	}
	public void setTestTakerIds(String testTakerIds) {
		this.testTakerIds = testTakerIds;
	}
	public Boolean getTestComplete() {
		return testComplete;
	}
	public void setTestComplete(Boolean testComplete) {
		this.testComplete = testComplete;
	}
	public String getTestQuestions() {
		return testQuestions;
	}
	public void setTestQuestions(String testQuestions) {
		this.testQuestions = testQuestions;
	}
	public float getTestTime() {
		return testTime;
	}
	public void setTestTime(float testTime) {
		this.testTime = testTime;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Exam [testId=" + testId + ", testDesc=" + testDesc + ", deptIds=" + deptIds + ", designIds=" + designIds
				+ ", testMakerId=" + testMakerId + ", testTakerIds=" + testTakerIds + ", testComplete=" + testComplete
				+ ", testQuestions=" + testQuestions + ", testTime=" + testTime + ", createdAt=" + createdAt + "]";
	}
}
