package com.bgurung.demoTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notification")
public class Notification {
	@Id
	@GeneratedValue
	@Column(name="notification_id")
	private Long notificationId;
	@Column(name="user_id")
	private Long userId;
	@Column(name="notification_message")
	private String notificationMessage;
	@Column(name="notification_seen")
	private Boolean notificationSeen;
	@Column(name="created_at")
	private String createdAt;
	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNotificationMessage() {
		return notificationMessage;
	}
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	public Boolean getNotificationSeen() {
		return notificationSeen;
	}
	public void setNotificationSeen(Boolean notificationSeen) {
		this.notificationSeen = notificationSeen;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
}
