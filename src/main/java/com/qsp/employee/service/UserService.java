package com.qsp.employee.service;

import java.security.PublicKey;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.qsp.employee.exception.UnauthorizedAccessException;
import com.qsp.employee.model.Role;
import com.qsp.employee.model.User;
import com.qsp.employee.repo.RoleRepository;
import com.qsp.employee.repo.UserRepository;
import com.qsp.employee.dto.UserDto;
import com.qsp.employee.enums.RoleType;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

//	public User createUser(User user, Long requesterId) {
//
//		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
//
//		if (existingUser.isEmpty()) {
//			throw new RuntimeException("user is Already present!!");
//		}
//
//		if (userRepository.count() == 0) {
//			System.out.println("No users found. Seeding first user with ADMIN role.");
//			Role adminRole = roleRepository.findByName(RoleType.ADMIN).orElseThrow(() -> new RuntimeException(
//					"ADMIN role not found. Please ensure roles are seeded in the database."));
//			user.setRole(adminRole);
//			return userRepository.save(user);
//		} else {
//			System.out.println("Requester ID: " + requesterId);
//			User requester = userRepository.findById(requesterId)
//					.orElseThrow(() -> new RuntimeException("Requester not found"));
//
//			if (requester.getRole().getName() == RoleType.ADMIN) {
//				System.out.println("Requester is ADMIN. Proceeding to create user.");
//				Role userRole = roleRepository.findByName(user.getRole().getName())
//						.orElseThrow(() -> new RuntimeException("Role not found"));
//				user.setRole(userRole);
//				return userRepository.save(user);
//			} else {
//				throw new UnauthorizedAccessException("Only admins can create new users.");
//			}
//		}
//	}

//	here is user registration

	public User registrtUser(User user) {
		return userRepository.save(user);
	}
	

//	here try to verify the user
	public String login(UserDto user) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

		if (authentication.isAuthenticated()) {
			return "success";
		} else {
			return "fail";
		}

	}

	public User createUser(User user, Long requesterId) {
		// Check if a user with the same email already exists
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser.isPresent()) {
			throw new RuntimeException("A user with this email already exists.");
		}

		// If no users exist in the database, create the first user with ADMIN role
		if (userRepository.count() == 0) {
			System.out.println("No users found. Seeding first user with ADMIN role.");
			Role adminRole = roleRepository.findByName(RoleType.ADMIN).orElseThrow(() -> new RuntimeException(
					"ADMIN role not found. Please ensure roles are seeded in the database."));
			user.setRole(adminRole);
			return userRepository.save(user);
		} else {
			System.out.println("Requester ID: " + requesterId);
			User requester = userRepository.findById(requesterId)
					.orElseThrow(() -> new RuntimeException("Requester not found"));

			// Check if the requester is an ADMIN
			if (requester.getRole().getName() == RoleType.ADMIN || requester.getRole().getName() == RoleType.HR) {
				System.out.println("Attempting to fetch role: " + user.getRole().getName());
				Role userRole = roleRepository.findByName(user.getRole().getName())
						.orElseThrow(() -> new RuntimeException("Role not found: " + user.getRole().getName()));
				user.setRole(userRole);
				return userRepository.save(user);
			} else {
				throw new UnauthorizedAccessException("Only admins and Hr can create new users.");
			}
		}
	}

	public User updateUser(Long id, User user, Long requesterId) {
		User requester = userRepository.findById(requesterId)
				.orElseThrow(() -> new RuntimeException("Requester not found"));

		if (requester.getRole().getName() == RoleType.ADMIN) {
			User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());

			Role userRole = roleRepository.findByName(user.getRole().getName())
					.orElseThrow(() -> new RuntimeException("Role not found"));
			existingUser.setRole(userRole);

			return userRepository.save(existingUser);
		} else {
			throw new UnauthorizedAccessException("Only admins can update users.");
		}
	}

	public List<User> getAllUsers(Long requesterId) {
		User requester = userRepository.findById(requesterId)
				.orElseThrow(() -> new RuntimeException("Requester not found"));

		if (requester.getRole().getName() == RoleType.ADMIN || requester.getRole().getName() == RoleType.HR) {
			return userRepository.findAll();
		} else {
			throw new UnauthorizedAccessException("Only admins or HR can view all users");
		}
//		return Collections.emptyList();
	}

	public User getUserById(Long id) {
		User requester = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Requester not found"));

		if (requester.getRole().getName() == RoleType.ADMIN || requester.getRole().getName() == RoleType.HR
				|| requester.getId() == id) {

			return userRepository.findById(id).get();

		} else {
			throw new RuntimeException("usre not found");
		}

	}

}
