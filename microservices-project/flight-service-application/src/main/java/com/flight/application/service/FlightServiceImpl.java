package com.flight.application.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.sharedlibrary.exceptions.FlightDetailsNotFoundException;
import com.application.sharedlibrary.model.Status;
import com.flight.application.entity.Flight;
import com.flight.application.entity.ScheduleTiming;
import com.flight.application.repository.FlightRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	private static final Logger logger = LogManager.getLogger(FlightServiceImpl.class);

	public static final String FLIGHT_SERVICE = "flightService";

	@Autowired
	FlightRepository flightRepository;

	@Override
	public String saveFlightDetails(Flight flight) {
		logger.info("in flight service implementation before db call");
		flight.setFlightStatus(Status.ACTIVE);
		Flight response = flightRepository.save(flight);
		logger.info("in flight service implementation after db call");
		if (response == null) {
			return "Details not save";
		}
		return "Details saved successfully";
	}

	@Override
	@CircuitBreaker(name = FLIGHT_SERVICE, fallbackMethod = "getAvailableFlights")
	public List<Flight> getAllFlightList() {
		logger.info("in flight service implementation before db call");
		List<Flight> response = flightRepository.findAll();
		logger.info("in flight service implementation after db call");
		if (response == null || response.isEmpty()) {
			throw new FlightDetailsNotFoundException("Flights details empty");
		}
		return response;
	}

	@Override
	public Flight getFlightDetailsByName(String flightName) {
		logger.info("in flight service implementation before db call");
		Optional<Flight> response = flightRepository.findByFlightName(flightName);
		logger.info("in flight service implementation after db call");
		if (!response.isPresent()) {
			throw new FlightDetailsNotFoundException("Flights Details Not Exists for " + flightName);
		}
		return response.get();
	}

	@Override
	public String updateFlightDetails(Flight flight) {
		logger.info("in flight service implementation before updating");
		Optional<Flight> response = flightRepository.findById(flight.getFlightId());
		if (!response.isPresent()) {
			throw new FlightDetailsNotFoundException("Flights Details Not found for " + flight.getFlightId());
		}

		if (null != flight.getFlightName() && !flight.getFlightName().isBlank()) {
			response.get().setFlightName(flight.getFlightName());
		}
		if (null != flight.getFlightStatus() && !flight.getFlightStatus().toString().isBlank()) {
			response.get().setFlightStatus(flight.getFlightStatus());
		}
		if (null != flight.getFromPlace() && !flight.getFromPlace().isBlank()) {
			response.get().setFromPlace(flight.getFromPlace());
		}
		if (null != flight.getToPlace() && !flight.getToPlace().isBlank()) {
			response.get().setToPlace(flight.getToPlace());
		}

		if (null != flight.getScheduleTimings() && !flight.getScheduleTimings().isEmpty()) {
			for (ScheduleTiming scheduleTimingResponse : response.get().getScheduleTimings()) {
				for (ScheduleTiming scheduleTiming : flight.getScheduleTimings()) {
					if (scheduleTimingResponse.getScheduleTimingId().equals(scheduleTiming.getScheduleTimingId())) {
						if (scheduleTiming.getArrivalDate() != null) {
							scheduleTimingResponse.setArrivalDate(scheduleTiming.getArrivalDate());
						}
						if (scheduleTiming.getDepartureDate() != null) {
							scheduleTimingResponse.setDepartureDate(scheduleTiming.getDepartureDate());
						}
						if (scheduleTiming.getEndTime() != null) {
							scheduleTimingResponse.setEndTime(scheduleTiming.getEndTime());
						}
						if (scheduleTiming.getStartTime() != null) {
							scheduleTimingResponse.setStartTime(scheduleTiming.getStartTime());
						}
						if (scheduleTiming.getTotalSeatCapacity() != null
								&& scheduleTiming.getTotalSeatCapacity() > 0) {
							scheduleTimingResponse.setTotalSeatCapacity(scheduleTiming.getTotalSeatCapacity());
						}
						if (scheduleTiming.getTicketPrice() > 0.0) {
							scheduleTimingResponse.setTicketPrice(scheduleTiming.getTicketPrice());
						}

					}
				}
			}
		}

		flightRepository.save(response.get());
		logger.info("in flight service implementation after updating");
		return "updated Successfully";
	}

	@Override
	public String deleteFlightDetailsByName(String flightName) {
		Flight response = getFlightDetailsByName(flightName);
		logger.info("in flight service implementation deleting");
		flightRepository.delete(response);
		return "Deleted Successfully";
	}

	@Override
	public Flight getFlightDetails(String fromPlace, String toPlace, String flightName) {
		logger.info("in flight service implementation before db call");
		Optional<Flight> response = flightRepository.findByFromPlaceAndToPlaceAndFlightName(fromPlace, toPlace,
				flightName);
		logger.info("in flight service implementation after db call");
		if (!response.isPresent()) {
			throw new FlightDetailsNotFoundException("Flights details empty");
		}
		return response.get();
	}

	public List<Flight> getAvailableFlights(Exception ex) throws ParseException {
		logger.info("before fetching fall back records");
		List<Flight> flightList = new ArrayList<>();
		SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm:ss");

		List<ScheduleTiming> scheduleTimingList = new ArrayList<>();
		scheduleTimingList.add(new ScheduleTiming(1, dateFormate.parse("2022-03-12"), dateFormate.parse("2022-03-15"),
				timeFormate.parse("15:30:14"), timeFormate.parse("18:25:18"), 50, 950.0));
		scheduleTimingList.add(new ScheduleTiming(2, dateFormate.parse("2022-04-11"), dateFormate.parse("2022-04-13"),
				timeFormate.parse("15:30:14"), timeFormate.parse("18:25:18"), 40, 850.0));
		scheduleTimingList.add(new ScheduleTiming(3, dateFormate.parse("2022-05-12"), dateFormate.parse("2022-05-15"),
				timeFormate.parse("15:30:14"), timeFormate.parse("18:25:18"), 50, 950.0));

		Flight flight = new Flight();
		flight.setFlightId(1);
		flight.setFlightName("AIR INDIA");
		flight.setFlightStatus(Status.ACTIVE);
		flight.setFromPlace("HYDERABAD");
		flight.setToPlace("BANGALURU");
		flight.setScheduleTimings(scheduleTimingList);
		flightList.add(flight);
		logger.info("after fetching fall back records");
		return flightList;
	}

}
