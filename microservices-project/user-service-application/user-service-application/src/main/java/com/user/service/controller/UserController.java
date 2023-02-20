package com.user.service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.sharedlibrary.dto.LoginDto;
import com.user.service.entity.User;
import com.user.service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authentication;

	@PostMapping("/save")
	public ResponseEntity<String> createUserAccount(@RequestBody User user) {
		logger.info("In controller before calling service");
		String response = userService.saveAccountDetails(user);
		logger.info("In controllerafter calling service");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
		logger.info("In controller before login");
		Authentication auth = authentication
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLogindId(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		logger.info("In controller after login");
		return ResponseEntity.status(HttpStatus.OK).body("login Successfully");
	}

	@GetMapping("/get/login/details")
	public ResponseEntity<User> loginDetails(@RequestParam("loginId") String loginId) {
		logger.info("In controller before calling service for details");
		User response = userService.loginDetails(loginId);
		logger.info("In controller after calling service for details");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<Boolean> getUserDetails(@PathVariable("userId") Integer userId) {
		logger.info("In controller before calling service for details");
		Boolean response = userService.getUserDetails(userId);
		logger.info("In controller after calling service for details");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
