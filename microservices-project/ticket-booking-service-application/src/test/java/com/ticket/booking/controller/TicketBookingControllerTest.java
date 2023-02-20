package com.ticket.booking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticket.booking.entity.Booking;
import com.ticket.booking.service.TicketBookingService;

@ExtendWith(SpringExtension.class)
public class TicketBookingControllerTest {

	@Mock
	TicketBookingService ticketBookingService;

	@InjectMocks
	TicketBookingController ticketBookingController;

	@Test
	public void testGetTicketDetails() {
		Booking booking = new Booking();
		booking.setFlightName("TEST");
		booking.setTicketNumber("1");
		when(ticketBookingService.getTicketDetails("1")).thenReturn(booking);
		ResponseEntity<Booking> response = ticketBookingController.getTicketDetails("1");
		assertEquals("TEST", response.getBody().getFlightName());
	}

	@Test
	public void testDeleteTicket() {
		when(ticketBookingService.deleteTicket("1")).thenReturn("Deleted");
		ResponseEntity<String> response = ticketBookingController.deleteTicket("1");
		assertEquals("Deleted", response.getBody());
	}
}
