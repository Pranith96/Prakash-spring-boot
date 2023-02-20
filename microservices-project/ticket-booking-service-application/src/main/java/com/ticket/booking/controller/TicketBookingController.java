package com.ticket.booking.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.booking.entity.Booking;
import com.ticket.booking.request.FlightTicketBookingRequest;
import com.ticket.booking.service.TicketBookingService;

@RestController
@RequestMapping("/ticket")
public class TicketBookingController {
	private static final Logger logger = LogManager.getLogger(TicketBookingController.class);

	@Autowired
	TicketBookingService ticketBookingService;

	@Autowired
	AuthenticationManager authentication;

	@PostMapping("/booking/{userId}")
	public ResponseEntity<Booking> bookTickets(@PathVariable("userId") Integer userId,
			@RequestBody FlightTicketBookingRequest flightTicketBookingRequest,
			@RequestHeader("login-id") String loginId, @RequestHeader("auth-password") String password) {
		logger.info("before calling service call in Ticket booking service");
		authenticateUserForAccess(loginId, password);
		Booking bookingResponse = ticketBookingService.bookTickets(userId, flightTicketBookingRequest);
		logger.info("after calling service call in Ticket booking service");
		return ResponseEntity.status(HttpStatus.OK).body(bookingResponse);
	}

	@GetMapping("/details/get")
	public ResponseEntity<Booking> getTicketDetails(@RequestParam("ticketNumber") String ticketNumber) {
		logger.info("before calling service call in Ticket booking service");
		Booking ticketDetailsResponse = ticketBookingService.getTicketDetails(ticketNumber);
		logger.info("after calling service call in Ticket booking service");
		return ResponseEntity.status(HttpStatus.OK).body(ticketDetailsResponse);
	}

	@DeleteMapping("/details/delete")
	public ResponseEntity<String> deleteTicket(@RequestParam("ticketNumber") String ticketNumber) {
		logger.info("before calling service call in Ticket booking service");
		String ticketDetailsResponse = ticketBookingService.deleteTicket(ticketNumber);
		logger.info("after calling service call in Ticket booking service");
		return ResponseEntity.status(HttpStatus.OK).body(ticketDetailsResponse);
	}

	public void authenticateUserForAccess(String loginId, String password) {
		logger.info("authenticating user details");
		Authentication auth = authentication.authenticate(new UsernamePasswordAuthenticationToken(loginId, password));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
