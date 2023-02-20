package com.application.sharedlibrary.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	private final String FLIGHT_DETAILS_NOT_FOUND = "Flight details not found";
	private final String TICKET_DETAILS_NOT_FOUND = "Ticket details not found";

	@ExceptionHandler(FlightDetailsNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleFlightDetailsNotFoundException(FlightDetailsNotFoundException ex,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDetails(FLIGHT_DETAILS_NOT_FOUND);
		response.setMessage(ex.getLocalizedMessage());
		response.setStatusCode(HttpStatus.NOT_FOUND.toString());
		response.setTimeStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(TicketDetailsNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleTicketDetailsNotFoundException(TicketDetailsNotFoundException ex,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDetails(TICKET_DETAILS_NOT_FOUND);
		response.setMessage(ex.getLocalizedMessage());
		response.setStatusCode(HttpStatus.NOT_FOUND.toString());
		response.setTimeStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDetails("Business Exception. Please contact Admin");
		response.setMessage(ex.getLocalizedMessage());
		response.setStatusCode(HttpStatus.NOT_FOUND.toString());
		response.setTimeStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
