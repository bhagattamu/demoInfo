package com.bgurung.demoTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bgurung.demoTest.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
