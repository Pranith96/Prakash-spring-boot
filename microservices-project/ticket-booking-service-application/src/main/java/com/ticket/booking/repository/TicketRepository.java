package com.ticket.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.booking.entity.Booking;

@Repository
public interface TicketRepository extends JpaRepository<Booking, Integer> {

	Optional<Booking> findByTicketNumber(String ticketNumber);

}
