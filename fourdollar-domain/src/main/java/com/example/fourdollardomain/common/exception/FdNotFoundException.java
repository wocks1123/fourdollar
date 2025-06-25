package com.example.fourdollardomain.common.exception;

public class FdNotFoundException extends FdException {

    public FdNotFoundException() {
        super("Resource not found");
    }

}
