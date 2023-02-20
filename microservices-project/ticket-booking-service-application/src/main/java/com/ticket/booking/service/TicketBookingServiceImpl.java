package com.ticket.booking.service;

import java.util.Optional;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import com.application.sharedlibrary.exceptions.BusinessException;
import com.application.sharedlibrary.exceptions.TicketDetailsNotFoundException;
import com.application.sharedlibrary.model.Status;
import com.flight.application.entity.Flight;
import com.flight.application.entity.ScheduleTiming;
import com.ticket.booking.client.FlightBookingClient;
import com.ticket.booking.client.UserDetailsClient;
import com.ticket.booking.entity.Booking;
import com.ticket.booking.repository.TicketRepository;
import com.ticket.booking.request.FlightTicketBookingRequest;

@Service
@Transactional
public class TicketBookingServiceImpl implements TicketBookingService {
	private static final Logger logger = LogManager.getLogger(TicketBookingServiceImpl.class);

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	FlightBookingClient flightBookingClient;

	@Autowired
	UserDetailsClient userDetailsClient;

	@Transactional
	@Override
	public Booking bookTickets(Integer userId, FlightTicketBookingRequest flightTicketBookingRequest) {
		Flight flightResponse = null;
		try {
			logger.info("before calling flight application client");
			flightResponse = flightBookingClient.getFlightDetails(flightTicketBookingRequest.getFromPlace(),
					flightTicketBookingRequest.getToPlace(), flightTicketBookingRequest.getFlightName());
			logger.info("after calling flight application client");
			logger.info("before calling user application client");
			Boolean userResponse = userDetailsClient.getUserDetails(userId);
			logger.info("after calling user application client");
			if (userResponse.equals(false)) {
				throw new BusinessException("Please provide correct id for ticket booking");
			}

		} catch (ResourceAccessException ex) {
			logger.error("resourse not found " + ex.getMessage());
		}

		if (flightResponse == null) {
			throw new RuntimeException("Server Unavailable for Booking");
		}

		Booking booking = null;
		for (ScheduleTiming scheduleTiming : flightResponse.getScheduleTimings()) {
			if (flightTicketBookingRequest.getDepartureDate().equals(scheduleTiming.getDepartureDate())) {
				validateSeats(scheduleTiming, flightTicketBookingRequest);
				Double totalTicketPrice = calculateTotalTicketPrice(scheduleTiming, flightTicketBookingRequest);
				booking = new Booking();
				booking.setBookingStatus(Status.ACTIVE);
				booking.setDepartureDate(scheduleTiming.getDepartureDate());
				booking.setFlightName(flightResponse.getFlightName());
				booking.setStartTime(scheduleTiming.getStartTime());
				booking.setTicketNumber(generateTicketNumber());
				booking.setTotalBookingSeatCount(flightTicketBookingRequest.getTotalBookingSeatCount());
				booking.setTotalTicketPrice(totalTicketPrice);
				booking.setUserId(userId);
				updateFlightDetails(scheduleTiming, flightTicketBookingRequest);
			}
		}
		logger.info("before saving booking details");
		Booking bookingResponse = ticketRepository.save(booking);
		logger.info("after saving booking details");
		logger.info("before updating flight details through client call");
		String updatedFlightRespone = flightBookingClient.updateFlightDetails(flightResponse);
		logger.info("after updating flight details through client call {}", updatedFlightRespone);
		return bookingResponse;
	}

	private void updateFlightDetails(ScheduleTiming scheduleTiming,
			FlightTicketBookingRequest flightTicketBookingRequest) {

		Integer updatedSeatCount = scheduleTiming.getTotalSeatCapacity()
				- flightTicketBookingRequest.getTotalBookingSeatCount();
		scheduleTiming.setTotalSeatCapacity(updatedSeatCount);
	}

	private String generateTicketNumber() {
		Random rnd = new Random();
		Integer ticketNumber = 100000 + rnd.nextInt(900000);
		return String.valueOf(ticketNumber);
	}

	private Double calculateTotalTicketPrice(ScheduleTiming scheduleTiming,
			FlightTicketBookingRequest flightTicketBookingRequest) {
		Double totalPrice = flightTicketBookingRequest.getTotalBookingSeatCount() * scheduleTiming.getTicketPrice();
		return totalPrice;
	}

	private void validateSeats(ScheduleTiming scheduleTiming, FlightTicketBookingRequest flightTicketBookingRequest) {
		if (flightTicketBookingRequest.getTotalBookingSeatCount() > scheduleTiming.getTotalSeatCapacity()) {
			throw new TicketDetailsNotFoundException("Seats Unavailable");
		}
	}

	@Override
	public Booking getTicketDetails(String ticketNumber) {
		logger.info("before fetching ticket booking details");
		Optional<Booking> ticketBookingResponse = ticketRepository.findByTicketNumber(ticketNumber);
		logger.info("after fetching ticket booking details");
		if (!ticketBookingResponse.isPresent()) {
			throw new TicketDetailsNotFoundException("Ticket details Unavailable");
		}
		return ticketBookingResponse.get();
	}

	@Override
	public String deleteTicket(String ticketNumber) {
		logger.info("before deleting ticket booking details");
		Booking ticketResponse = getTicketDetails(ticketNumber);
		ticketRepository.delete(ticketResponse);
		logger.info("after deleting ticket booking details");
		return "Deleted Successfully";
	}

}
