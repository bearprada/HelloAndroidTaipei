package com.cardinalblue.example;

public class OverNumberException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public OverNumberException(String msg) {
        super(msg);
    }

}
