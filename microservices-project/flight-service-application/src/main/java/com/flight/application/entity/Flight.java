package com.flight.application.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.application.sharedlibrary.model.Status;

@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer flightId;
	private String flightName;
	private String fromPlace;
	private String toPlace;
	@Enumerated(EnumType.STRING)
	private Status flightStatus;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "flight_schedule", joinColumns = { @JoinColumn(name = "flightId") }, inverseJoinColumns = {
			@JoinColumn(name = "scheduleTimingId") })
	private List<ScheduleTiming> scheduleTimings;

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public Status getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(Status flightStatus) {
		this.flightStatus = flightStatus;
	}

	public List<ScheduleTiming> getScheduleTimings() {
		return scheduleTimings;
	}

	public void setScheduleTimings(List<ScheduleTiming> scheduleTimings) {
		this.scheduleTimings = scheduleTimings;
	}

}
