package com.qsp.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsp.employee.exception.UnauthorizedAccessException;
import com.qsp.employee.model.User;
import com.qsp.employee.repo.RoleRepository;
import com.qsp.employee.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<User> getAllUsers(Long requesterId) {
		User userRequester = userRepository.findById(requesterId)
				.orElseThrow(() -> new RuntimeException("user not found!!"));

		if (userRequester.getRole().getName().equals("ADMIN") || userRequester.getRole().getName().equals("HR")) {
			return userRepository.findAll();
		} else {

			throw new UnauthorizedAccessException("You do not have permistion to see all usres");

		}

	}

	public User getUserById(Long id, Long requesterId) {
		User userRequester = userRepository.findById(requesterId)
				.orElseThrow(() -> new RuntimeException("user not found"));
		if (userRequester.getRole().getName().equals("ADMIN") || userRequester.getRole().getName().equals("HR")
				|| userRequester.getId().equals(id)) {
			return userRepository.findById(id).orElseThrow(() -> new RuntimeException("not found id"));

		} else {
			throw new UnauthorizedAccessException("Access denied");
		}

	}

	public User createUser(User user, Long requesterId) {
	
		User requester = userRepository.findById(requesterId).get();

	
		if (requester.getRole().getName().equals("ADMIN")) {
		
			return userRepository.save(user);
		} else {
		
			throw new UnauthorizedAccessException("Access denied: Only admins can create users.");
		}
	}

	public User updateUser(Long id, User user, Long requesterId) {

		User userRequester = userRepository.findById(requesterId).orElseThrow();

		if (userRequester.getRole().getName().equals("ADMIN") || userRequester.equals(id)) {
			User existingUser = userRepository.findById(id).orElseThrow();
			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());
			existingUser.setRole(user.getRole());
			existingUser.setDepartment(user.getDepartment());

			return userRepository.save(existingUser);
		} else {
			throw new UnauthorizedAccessException("Access denied");
		}

	}

	public void deleteUser(Long id, Long requesterId) {
		User userRequester = userRepository.findById(requesterId).orElseThrow();

		if (userRequester.getRole().getName().equals("ADMIN")) {
			User user = userRepository.findById(id).orElseThrow();
			userRepository.delete(user);
		}

	}

}
