package com.bgurung.demoTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bgurung.demoTest.model.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long> {

}
