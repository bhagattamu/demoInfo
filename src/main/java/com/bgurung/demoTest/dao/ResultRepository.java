package com.bgurung.demoTest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bgurung.demoTest.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
	List<Result> findByTestId(Long testId);
}
