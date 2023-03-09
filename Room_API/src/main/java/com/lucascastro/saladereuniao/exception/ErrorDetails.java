package com.lucascastro.saladereuniao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetails {

    private Date timeStamp;
    private String message;
    private String details;
}
