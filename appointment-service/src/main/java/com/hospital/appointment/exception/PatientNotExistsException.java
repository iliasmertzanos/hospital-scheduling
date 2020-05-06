package com.hospital.appointment.exception;


public class PatientNotExistsException extends RuntimeException {

	public PatientNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientNotExistsException(String message) {
		super(message);
	}

	public PatientNotExistsException(Throwable cause) {
		super(cause);
	}
}
