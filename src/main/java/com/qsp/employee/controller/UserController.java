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

import com.qsp.employee.model.User;
import com.qsp.employee.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public List<User> getAllUser(@RequestParam long id) {
		return userService.getAllUsers(id);
	}

	@PostMapping("/create/{requesterId}")
	public ResponseEntity<User> createdUsers(@RequestBody User user, @PathVariable Long requesterId) {
	    User createdUser = userService.createUser(user, requesterId);
	    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}


}
