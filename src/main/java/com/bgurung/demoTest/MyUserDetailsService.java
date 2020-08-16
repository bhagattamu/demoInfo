package com.bgurung.demoTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bgurung.demoTest.dao.UserRepository;
import com.bgurung.demoTest.dao.UserRoleRepository;
import com.bgurung.demoTest.model.User;
import com.bgurung.demoTest.model.UserRole;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	@Autowired
	private UserRoleRepository roleRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User 404");
		}
		UserRole role = roleRepo.findByRoleId(user.getRoleId());
		return new UserPrincipal(user,role);
	}

}
