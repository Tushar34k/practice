package com.qsp.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qsp.employee.model.User;
import com.qsp.employee.model.UserPrinciple;
import com.qsp.employee.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		User user = userRepository.findByEmail(username).get();

		if (user == null) {
			throw new UsernameNotFoundException("user not found!!" + username);
		}

		return new UserPrinciple(user);
	}

}
