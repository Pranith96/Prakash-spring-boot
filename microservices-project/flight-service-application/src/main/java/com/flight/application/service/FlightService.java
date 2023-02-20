package com.flight.application.service;

import java.util.List;

import com.flight.application.entity.Flight;

public interface FlightService {

	String saveFlightDetails(Flight flight);

	List<Flight> getAllFlightList();

	Flight getFlightDetailsByName(String flightName);

	String updateFlightDetails(Flight flight);

	String deleteFlightDetailsByName(String flightName);

	Flight getFlightDetails(String fromPlace, String toPlace,String flightName);

}
