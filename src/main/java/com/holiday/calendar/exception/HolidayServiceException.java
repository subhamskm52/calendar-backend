package com.holiday.calendar.exception;

public class HolidayServiceException extends RuntimeException {
    public HolidayServiceException(String message) {
        super(message);
    }

    public HolidayServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
