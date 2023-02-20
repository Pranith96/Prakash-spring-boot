package com.user.service.service;

import com.user.service.entity.User;

public interface UserService {

	String saveAccountDetails(User user);

	User loginDetails(String loginId);

	Boolean getUserDetails(Integer userId);

}
