package com.ticket.booking.client;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight.application.entity.Flight;
import com.ticket.booking.config.LoadBalancerConfiguration;

@LoadBalancerClient(name = "FLIGHT-BOOKING-CLIENT", configuration = LoadBalancerConfiguration.class)
@FeignClient(name = "FLIGHT-BOOKING-CLIENT", url = "http://localhost:8083", path = "/flight")
public interface FlightBookingClient {

	@GetMapping("/get/details")
	public Flight getFlightDetailsByName(@RequestParam("flightName") String flightName);

	@GetMapping("/get/{fromPlace}/{toPlace}/{flightName}")
	public Flight getFlightDetails(@PathVariable("fromPlace") String fromPlace, @PathVariable("toPlace") String toPlace,
			@PathVariable("flightName") String flightName);

	@PutMapping("/update/details")
	public String updateFlightDetails(@RequestBody Flight flight);
}