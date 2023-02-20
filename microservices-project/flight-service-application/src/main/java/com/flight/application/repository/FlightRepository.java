package com.flight.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.application.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	Optional<Flight> findByFlightName(String flightName);

	Optional<Flight> findByFromPlaceAndToPlaceAndFlightName(String fromPlace, String toPlace, String flightName);

}
