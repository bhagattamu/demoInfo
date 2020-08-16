package com.bgurung.demoTest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bgurung.demoTest.model.User;

/**
 * @author bgurung
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	List<User> findByRoleId(Long roleId);

}
