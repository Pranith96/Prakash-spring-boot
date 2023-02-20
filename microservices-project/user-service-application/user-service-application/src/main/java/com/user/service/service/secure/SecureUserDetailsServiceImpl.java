package com.user.service.service.secure;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.service.entity.User;
import com.user.service.repository.UserRepository;

@Service
public class SecureUserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LogManager.getLogger(SecureUserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		logger.info("in  userdetails service");
		Optional<User> userDetails = userRepository.findByLoginId(loginId);

		if (!userDetails.isPresent()) {
			throw new UsernameNotFoundException("Please Enter Valid Credentials");
		}
		return new SecureUserDetails(userDetails.get());
	}
}
