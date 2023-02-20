package com.flight.application.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.application.entity.Flight;
import com.flight.application.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {
	private static final Logger logger = LogManager.getLogger(FlightController.class);

	@Autowired
	FlightService flightService;

	@Autowired
	AuthenticationManager authentication;

	@PostMapping("/add")
	public ResponseEntity<String> addFlightDetails(@RequestBody Flight flight,
			@RequestHeader("login-id") String loginId, @RequestHeader("auth-password") String password) {
		logger.info("before service call in flight service application");
		authenticateUserForAccess(loginId, password);
		String response = flightService.saveFlightDetails(flight);
		logger.info("after service call in flight service application");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<Flight>> getAllFlightList() {
		logger.info("before service call in feching flight details");
		List<Flight> response = flightService.getAllFlightList();
		logger.info("after service call in feching flight details");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/details")
	public ResponseEntity<Flight> getFlightDetailsByName(@RequestParam("flightName") String flightName) {
		logger.info("before service call in feching flight details");
		Flight response = flightService.getFlightDetailsByName(flightName);
		logger.info("after service call in feching flight details");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/{fromPlace}/{toPlace}/{flightName}")
	public ResponseEntity<Flight> getFlightDetails(@PathVariable("fromPlace") String fromPlace,
			@PathVariable("toPlace") String toPlace, @PathVariable("flightName") String flightName) {
		logger.info("before service call in feching flight details");
		Flight response = flightService.getFlightDetails(fromPlace, toPlace, flightName);
		logger.info("after service call in feching flight details");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update/details")
	public ResponseEntity<String> updateFlightDetails(@RequestBody Flight flight) {
		logger.info("before service call updating flight details");
		String response = flightService.updateFlightDetails(flight);
		logger.info("after service call updating flight details");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/delete/details")
	public ResponseEntity<String> deleteFlightDetailsByName(@RequestParam("flightName") String flightName,
			@RequestHeader("login-id") String loginId, @RequestHeader("auth-password") String password) {
		logger.info("before service call deleting flight details");
		authenticateUserForAccess(loginId, password);
		String response = flightService.deleteFlightDetailsByName(flightName);
		logger.info("after service call deleting flight details");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public void authenticateUserForAccess(String loginId, String password) {
		logger.info("authenticating user details");
		Authentication auth = authentication.authenticate(new UsernamePasswordAuthenticationToken(loginId, password));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
