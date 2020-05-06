package com.hospital.appointment.exception.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIExceptionResponse {

    private Date timeStamp;
    private String message;
    private String details;
    private int status;

}
