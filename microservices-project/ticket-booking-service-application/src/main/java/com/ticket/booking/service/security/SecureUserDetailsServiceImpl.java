package com.ticket.booking.service.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ticket.booking.client.UserDetailsClient;
import com.user.service.entity.User;

@Service
public class SecureUserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LogManager.getLogger(SecureUserDetailsServiceImpl.class);

	@Autowired
	private UserDetailsClient userDetailsClient;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		logger.info("in  userdetails service");
		User userDetails = userDetailsClient.loginDetails(loginId);

		if (userDetails == null) {
			throw new UsernameNotFoundException("Please Enter Valid Credentials");
		}
		return new SecureUserDetails(userDetails);
	}
}
