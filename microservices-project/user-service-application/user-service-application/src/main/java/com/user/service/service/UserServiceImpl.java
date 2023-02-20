package com.user.service.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.sharedlibrary.exceptions.BusinessException;
import com.application.sharedlibrary.model.Status;
import com.user.service.entity.User;
import com.user.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public String saveAccountDetails(User user) {
		logger.info("In service layer before dao call");
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setStatus(Status.ACTIVE);
		User userResponse = userRepository.save(user);
		logger.info("In service layer after dao call");
		if (userResponse == null) {
			return "Details not saved";
		}
		return "Details saved successfully";
	}

	@Override
	public User loginDetails(String loginId) {
		logger.info("In service layer before dao call");
		Optional<User> userResponse = userRepository.findByLoginId(loginId);
		if (!userResponse.isPresent()) {
			throw new BusinessException("Incorrect Credentials");
		}
		logger.info("In service layer after dao call");
		return userResponse.get();
	}

	@Override
	public Boolean getUserDetails(Integer userId) {
		logger.info("In service layer before dao call");
		Optional<User> userResponse = userRepository.findById(userId);
		if (!userResponse.isPresent()) {
			return false;
		}
		logger.info("In service layer after dao call");
		return true;
	}

}
