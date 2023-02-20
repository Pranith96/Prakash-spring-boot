package com.flight.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.flight.application.entity.Flight;
import com.flight.application.repository.FlightRepository;

@ExtendWith(SpringExtension.class)
public class FlightServiceImplTest {

	@InjectMocks
	FlightServiceImpl flightServiceImpl;

	@Mock
	FlightRepository flightRepository;

	@Test
	public void testSaveFlightDetails() {
		Flight flight = new Flight();
		flight.setFlightName("TEST");
		when(flightRepository.save(flight)).thenReturn(flight);
		String response = flightServiceImpl.saveFlightDetails(flight);
		assertEquals("Details saved successfully", response);
	}

	@Test
	public void testGetFlightDetailsByName() {
		Flight flight = new Flight();
		flight.setFlightName("TEST");
		when(flightRepository.findByFlightName("TEST")).thenReturn(Optional.of(flight));
		Flight response = flightServiceImpl.getFlightDetailsByName("TEST");
		assertEquals("TEST", response.getFlightName());
	}

	@Test
	public void testGetFlightDetails() {
		Flight flight = new Flight();
		flight.setFlightName("TEST");
		when(flightRepository.findByFromPlaceAndToPlaceAndFlightName("TEST", "TEST1", "TEST2"))
				.thenReturn(Optional.of(flight));
		Flight response = flightServiceImpl.getFlightDetails("TEST", "TEST1", "TEST2");
		assertEquals("TEST", response.getFlightName());
	}

	@Test
	public void testDeleteFlightDetailsByName() {
		Flight flight = new Flight();
		flight.setFlightName("TEST");
		when(flightRepository.findByFlightName("TEST")).thenReturn(Optional.of(flight));
		doNothing().when(flightRepository).delete(flight);
		String response = flightServiceImpl.deleteFlightDetailsByName("TEST");
		assertEquals("Deleted Successfully", response);
	}
}
