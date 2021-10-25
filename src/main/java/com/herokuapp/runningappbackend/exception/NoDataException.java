package com.herokuapp.runningappbackend.exception;

public class NoDataException extends RuntimeException {
    public NoDataException() {
        super("No data found");
    }
}
