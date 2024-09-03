package com.qsp.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.employee.dto.UserDto;
import com.qsp.employee.model.User;
import com.qsp.employee.repo.UserRepository;
import com.qsp.employee.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
//
//	@GetMapping("/{id}")
//	public List<User> getAllUser(@RequestParam long id) {
//		return userService.getAllUsers(id);
//	}

	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return userService.registrtUser(user);
	}

	@PostMapping("/logins")
	public String login(@RequestBody UserDto userDto) {
		return userService.login(userDto);
	}

	@PostMapping("/create/{requesterId}")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user, @PathVariable Long requesterId) {
		User createdUser = userService.createUser(user, requesterId);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("/allUser/{id}")
	public ResponseEntity<List<User>> getAllUser(@PathVariable Long id) {
		List<User> usresList = userService.getAllUsers(id);
		return new ResponseEntity<List<User>>(usresList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
