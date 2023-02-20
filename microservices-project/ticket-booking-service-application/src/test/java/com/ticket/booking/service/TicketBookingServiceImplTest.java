package com.ticket.booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticket.booking.entity.Booking;
import com.ticket.booking.repository.TicketRepository;

@ExtendWith(SpringExtension.class)
public class TicketBookingServiceImplTest {

	@InjectMocks
	TicketBookingServiceImpl ticketBookingServiceImpl;

	@Mock
	TicketRepository ticketRepository;

	@Test
	public void testGetTicketDetails() {
		Booking booking = new Booking();
		when(ticketRepository.findByTicketNumber("1")).thenReturn(Optional.of(booking));
		booking = ticketBookingServiceImpl.getTicketDetails("1");
		assertNotNull(booking);
	}

	@Test
	public void testDeleteTicket() {
		Booking booking = new Booking();
		booking.setTicketNumber("1");
		when(ticketRepository.findByTicketNumber("1")).thenReturn(Optional.of(booking));
		doNothing().when(ticketRepository).delete(booking);
		String response = ticketBookingServiceImpl.deleteTicket("1");
		assertEquals("Deleted Successfully", response);
	}
}
