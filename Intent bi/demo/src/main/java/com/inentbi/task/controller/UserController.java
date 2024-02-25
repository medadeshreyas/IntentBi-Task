package com.inentbi.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inentbi.task.model.User;
import com.inentbi.task.services.UserServiceImpl;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		if (userServiceImpl.login(user)) {
			return ResponseEntity.ok("Login successful");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user) {
		System.out.println("I am here");
		if (userServiceImpl.signup(user)) {
			return ResponseEntity.ok("Signup Successful");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username already exists");
		}
	}

}
