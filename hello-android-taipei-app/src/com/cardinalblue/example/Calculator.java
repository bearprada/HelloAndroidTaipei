package com.cardinalblue.example;

import java.util.Random;

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
        long result = ((start + end) * (end - (start - 1))) / 2; 
        if (result<=0) {
            throw new OverNumberException("result = " +result);
        }
        return result;
    }
    
    public long multiple(int start, int end) 
            throws IllegalArgumentException, OverNumberException {
        if (start >= end || start <= 0 || end <= 0) {
            throw new IllegalArgumentException("start >= end");
        }
        long result = start;
        for(int i = start ; i<=end ; i++ ) {
            result *= i;
        } 
        if (result<=0) {
            throw new OverNumberException("result = " +result);
        }
        return result;
    }
    
    public long middle(long start, long end) 
            throws IllegalArgumentException {
        if (start>=end) {
            throw new IllegalArgumentException("start >= end");
        }
        return (start+end)/2;
    }
    
    public long max(long start, long end) 
            throws IllegalArgumentException {
        if (start>=end) {
            throw new IllegalArgumentException("start >= end");
        }
        return end;
    }
    
    public long min(long start, long end) 
            throws IllegalArgumentException{
        if (start>=end) {
            throw new IllegalArgumentException("start >= end");
        }
        return start;
    }
    
    public int random(int start, int end) 
            throws IllegalArgumentException, OverNumberException {
        if (start>=end) {
            throw new IllegalArgumentException("start >= end");
        }
        return new Random().nextInt((end - start))+start;
    }
}
