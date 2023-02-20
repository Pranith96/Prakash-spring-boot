package com.application.sharedlibrary.exceptions;

public class FlightDetailsNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public FlightDetailsNotFoundException(String message) {
		super(message);
	}

}
