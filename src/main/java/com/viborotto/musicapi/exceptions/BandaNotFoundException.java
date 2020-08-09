package com.viborotto.musicapi.exceptions;

public class BandaNotFoundException extends RuntimeException{

    public BandaNotFoundException(String message) {
        super(message);
    }

    public BandaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
