package com.bgurung.demoTest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bgurung.demoTest.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	List<Answer> findByQuestionIds(Long questionIds);
	@Transactional
	void deleteByQuestionIds(Long questionIds);
	
	@Query(value = "SELECT * FROM answers where answer_id=:aId and question_id=:qId", nativeQuery = true)
	List<Answer> findByAnswerIdAndQuestionId(@Param("aId") Long aId,@Param("qId") Long qId);
	
}
