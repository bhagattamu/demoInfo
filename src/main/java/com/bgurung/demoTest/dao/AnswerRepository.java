package com.bgurung.demoTest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bgurung.demoTest.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	List<Answer> findByQuestionIds(Long questionIds);
	@Transactional
	void deleteByQuestionIds(Long questionIds);
}
