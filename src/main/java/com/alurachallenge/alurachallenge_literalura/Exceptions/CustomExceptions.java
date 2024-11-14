package com.alurachallenge.alurachallenge_literalura.Exceptions;

public class CustomExceptions extends RuntimeException {
    public static class BookNotFoundException extends RuntimeException {
        public BookNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateBookException extends RuntimeException {
        public DuplicateBookException(String message) {
            super(message);
        }
    }

    public static class APIConnectionException extends RuntimeException {
        public APIConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
