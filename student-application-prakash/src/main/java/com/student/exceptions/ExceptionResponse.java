package com.student.exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {

	private String message;
	private String details;
	private LocalDateTime dateTme;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDateTime getDateTme() {
		return dateTme;
	}

	public void setDateTme(LocalDateTime dateTme) {
		this.dateTme = dateTme;
	}

}
