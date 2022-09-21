package com.bridgelabz.bookstore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BookNotFoundException extends RuntimeException{
    private int statusCode;
    private String statusMessage;

    public BookNotFoundException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
