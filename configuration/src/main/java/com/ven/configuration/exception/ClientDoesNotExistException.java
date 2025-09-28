package com.ven.configuration.exception;

/**
 * Custom exception thrown when a client does not exist.
 */
public class ClientDoesNotExistException extends RuntimeException {
    public ClientDoesNotExistException(String message) {
        super(message);
    }
}
