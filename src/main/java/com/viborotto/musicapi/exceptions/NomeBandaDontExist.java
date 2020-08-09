package com.viborotto.musicapi.exceptions;

public class NomeBandaDontExist extends Exception{
    public NomeBandaDontExist() {
    }

    public NomeBandaDontExist(String message) {
        super(message);
    }

    public NomeBandaDontExist(String message, Throwable cause) {
        super(message, cause);
    }
}
