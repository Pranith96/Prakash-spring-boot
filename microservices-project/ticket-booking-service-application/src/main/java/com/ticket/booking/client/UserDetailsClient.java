package com.ticket.booking.client;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.booking.config.LoadBalancerConfiguration;
import com.user.service.entity.User;

@LoadBalancerClient(name = "USER-DETAILS-CLIENT", configuration = LoadBalancerConfiguration.class)
@FeignClient(name = "USER-DETAILS-CLIENT", url = "http://localhost:8082", path = "/user")
public interface UserDetailsClient {

	@GetMapping("/get/{userId}")
	public Boolean getUserDetails(@PathVariable("userId") Integer userId);

	@GetMapping("/get/login/details")
	public User loginDetails(@RequestParam("loginId") String loginId);
}
