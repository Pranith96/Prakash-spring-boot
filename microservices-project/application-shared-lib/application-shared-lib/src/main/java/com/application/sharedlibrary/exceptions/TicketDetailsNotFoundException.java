package com.application.sharedlibrary.exceptions;

public class TicketDetailsNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	public TicketDetailsNotFoundException(String message) {
		super(message);
	}

}
