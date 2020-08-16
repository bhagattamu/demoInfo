package com.bgurung.demoTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bgurung.demoTest.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
