package com.bgurung.demoTest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bgurung.demoTest.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
	List<Exam> findByTestMakerId(Long testMakerId);
}
