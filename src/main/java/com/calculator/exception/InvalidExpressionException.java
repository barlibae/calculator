package com.calculator.exception;

public class InvalidExpressionException extends RuntimeException {

    public InvalidExpressionException(String message) {
        super(message);
    }

    public InvalidExpressionException(String message, Throwable t) {
        super(message, t);
    }
}
