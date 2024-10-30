package org.example;

public class InvalidIndexExeption extends RuntimeException {
    public InvalidIndexExeption() {
    }

    public InvalidIndexExeption(String message) {
        super(message);
    }

    public InvalidIndexExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIndexExeption(Throwable cause) {
        super(cause);
    }

    public InvalidIndexExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
