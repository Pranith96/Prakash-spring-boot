package com.flight.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.flight.application.entity.Flight;
import com.flight.application.service.FlightServiceImpl;

@ExtendWith(SpringExtension.class)
public class FlightControllerTest {

	@Mock
	FlightServiceImpl flightServiceImpl;

	@InjectMocks
	FlightController flightController;

	@Test
	public void testGetFlightDetailsByName() {
		Flight flight = new Flight();
		flight.setFlightName("TEST");
		when(flightServiceImpl.getFlightDetailsByName("TEST")).thenReturn(flight);
		ResponseEntity<Flight> response = flightController.getFlightDetailsByName("TEST");
		assertEquals("TEST", response.getBody().getFlightName());
	}

	@Test
	public void testGetFlightDetails() {
		Flight flight = new Flight();
		flight.setFlightName("TEST");
		when(flightServiceImpl.getFlightDetails("TEST", "TEST1", "TEST2")).thenReturn(flight);
		ResponseEntity<Flight> response = flightController.getFlightDetails("TEST", "TEST1", "TEST2");
		assertEquals("TEST", response.getBody().getFlightName());

	}
}
