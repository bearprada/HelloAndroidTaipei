package com.cardinalblue.example;

public class Calculator {
    
    public long sum(long start, long end) 
            throws IllegalArgumentException, OverNumberException {
        if (start>=end) {
            throw new IllegalArgumentException("start >= end");
        }
        long result = 0;
        for(long i = start ; i<=end ; i++ ) {
            result += i;
        }
        if (result<=0) {
            throw new OverNumberException("result = " +result);
        }
        return result;
    }
    
    public long fastSum(long start, long end) 
            throws IllegalArgumentException, OverNumberException {
        if (start>=end) {
            throw new IllegalArgumentException("start >= end");
        }
        long result = ( start+end ) * ( end-start ) / 2; 
        if (result<=0) {
            throw new OverNumberException("result = " +result);
        }
        return result;
    }
}
