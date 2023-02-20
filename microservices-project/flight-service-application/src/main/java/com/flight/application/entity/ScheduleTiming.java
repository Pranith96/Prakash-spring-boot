package com.flight.application.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "flight_schedule_timings")
public class ScheduleTiming {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scheduleTimingId;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date departureDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date startTime;
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date endTime;
	private Integer totalSeatCapacity;
	private double ticketPrice;

	public ScheduleTiming() {
	}

	public ScheduleTiming(Integer scheduleTimingId, Date departureDate, Date arrivalDate, Date startTime, Date endTime,
			Integer totalSeatCapacity, double ticketPrice) {
		this.scheduleTimingId = scheduleTimingId;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalSeatCapacity = totalSeatCapacity;
		this.ticketPrice = ticketPrice;
	}

	public Integer getTotalSeatCapacity() {
		return totalSeatCapacity;
	}

	public void setTotalSeatCapacity(Integer totalSeatCapacity) {
		this.totalSeatCapacity = totalSeatCapacity;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Integer getScheduleTimingId() {
		return scheduleTimingId;
	}

	public void setScheduleTimingId(Integer scheduleTimingId) {
		this.scheduleTimingId = scheduleTimingId;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
