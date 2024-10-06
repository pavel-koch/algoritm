package org.example;

public class TableIsFullExeption extends RuntimeException {
    public TableIsFullExeption() {
    }

    public TableIsFullExeption(String message) {
        super(message);
    }

    public TableIsFullExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public TableIsFullExeption(Throwable cause) {
        super(cause);
    }

    public TableIsFullExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
