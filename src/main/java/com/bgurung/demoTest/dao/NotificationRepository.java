package com.bgurung.demoTest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bgurung.demoTest.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByUserId(Long userId);
}
