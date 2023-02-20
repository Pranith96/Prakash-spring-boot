package com.user.service.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.user.service.entity.User;
import com.user.service.service.UserService;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	@Test
	public void testCreateUserAccount() {
		User user = new User();
		when(userService.saveAccountDetails(user)).thenReturn(Mockito.anyString());
		ResponseEntity<String> response = userController.createUserAccount(user);
		assertNotNull(response);
	}

	@Test
	public void testLoginDetails() {
		User user = new User();
		when(userService.loginDetails("1")).thenReturn(user);
		ResponseEntity<User> response = userController.loginDetails("1");
		assertNotNull(response);
	}

	@Test
	public void testGetUserDetails() {
		when(userService.getUserDetails(1)).thenReturn(true);
		ResponseEntity<Boolean> response = userController.getUserDetails(1);
		assertTrue(response.getBody());
	}

}
