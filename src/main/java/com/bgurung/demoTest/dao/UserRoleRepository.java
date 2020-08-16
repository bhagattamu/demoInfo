package com.bgurung.demoTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bgurung.demoTest.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	UserRole findByRoleId(Long roleId);
	UserRole findByUserRole(String userRole);
}
