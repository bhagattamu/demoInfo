package com.bgurung.demoTest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="timer")
public class Timer {
	@Id
	@GeneratedValue
	@Column(name="timer_id")
	private Long timerId;
	@Column(name="result_id")
	private Long resultId;
	@Column(name="timefinish")
	private Boolean timeFinish;
	@Column(name="created_at")
	private Date createdAt;
	public Long getTimerId() {
		return timerId;
	}
	public void setTimerId(Long timerId) {
		this.timerId = timerId;
	}
	public Long getResultId() {
		return resultId;
	}
	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}
	
	public Boolean getTimeFinish() {
		return timeFinish;
	}
	public void setTimeFinish(Boolean timeFinish) {
		this.timeFinish = timeFinish;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
