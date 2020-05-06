package com.hospital.appointment.enums;

public enum AppointmentState {
	PENDING,
    APPROVED,
    REJECTED,
    RESCHEDULED,
    CANCELED,
    // temporary approved after receiving ok from financial service
    // it must be from treatment service also approved
    // in this case the state changes to FINAL_APPROVED
    TREATMENT_APPROVED 
}
