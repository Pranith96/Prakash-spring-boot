package com.user.service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.user.service.entity.User;
import com.user.service.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	public void testSaveAccountDetails() {
		User user = new User();
		user.setName("TEST");
		when(userRepository.save(user)).thenReturn(user);
		String userResponse = userServiceImpl.saveAccountDetails(user);
		assertNotNull(userResponse);
		assertEquals("Details saved successfully", userResponse);
	}

	@Test
	public void testLoginDetails() {
		User user = new User();
		when(userRepository.findByLoginId("1")).thenReturn(Optional.of(user));
		User userResponse = userServiceImpl.loginDetails("1");
		assertNotNull(userResponse);
	}

	@Test
	public void testGetUserDetails() {
		User user = new User();
		user.setUserId(1);
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		boolean userResponse = userServiceImpl.getUserDetails(1);
		assertTrue(userResponse);
	}
}