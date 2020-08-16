package com.bgurung.demoTest;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bgurung.demoTest.dao.UserRoleRepository;
import com.bgurung.demoTest.model.User;
import com.bgurung.demoTest.model.UserRole;

public class UserPrincipal implements UserDetails {

	private User user;
	private UserRole role;
	
	@Autowired
	private UserRoleRepository roleRepo;
	
	public UserPrincipal(User user, UserRole role) {
		super();
		this.user = user;
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
//		System.out.println("User from grantedAuthority" + user + "RoleId" + user.getRoleId() + role.getUserRole().toUpperCase());
//		UserRole userRole = roleRepo.findByRoleId(user.getRoleId());
//		System.out.println("Roles of users" + userRole);
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.getUserRole().toUpperCase()));
//		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
