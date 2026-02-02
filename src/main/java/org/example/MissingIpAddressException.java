package org.example;

public class MissingIpAddressException extends RuntimeException {
    public MissingIpAddressException(String message) {
        super(message);
    }
}
