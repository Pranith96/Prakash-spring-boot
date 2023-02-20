package com.ticket.booking.service;

import com.ticket.booking.entity.Booking;
import com.ticket.booking.request.FlightTicketBookingRequest;

public interface TicketBookingService {

	Booking bookTickets(Integer userId,FlightTicketBookingRequest flightTicketBookingRequest);

	Booking getTicketDetails(String ticketNumber);

	String deleteTicket(String ticketNumber);

}
