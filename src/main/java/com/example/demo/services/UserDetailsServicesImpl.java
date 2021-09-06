package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.User;
import com.example.demo.repository.UserRespo;



public class UserDetailsServicesImpl implements UserDetailsService {
	@Autowired
	UserRespo userRespo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username == null) {

			System.out.println("NULLLLLLL");

		}

		User user = userRespo.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthorities(Arrays.asList("ROLE_"+user.getRole())));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(List<String> role) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		for (String r : role) {
			authorities.add(new SimpleGrantedAuthority(r.toString()));

		}

		return authorities;

	}

}
