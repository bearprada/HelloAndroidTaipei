package com.cardinalblue.example;

import android.test.AndroidTestCase;

public class MainActivityTest extends AndroidTestCase {
    
    private Calculator mCalculator;
    
    @Override
    protected void setUp() throws Exception{
        super.setUp();
        mCalculator = new Calculator();
        
    }
    
    /**
     * normal test
     */
    public void test00001() {
        try {
            assertEquals(5000L, mCalculator.fastSum(0, 100));
        } catch (IllegalArgumentException e) {
            fail();
        } catch (OverNumberException e) {
            fail();
        }
    }
    
    /**
     * add the large number
     */
    public void test00002() {
        try {
            assertEquals(1500000L, mCalculator.fastSum(1000, 2000));
        } catch (IllegalArgumentException e) {
            fail();
        } catch (OverNumberException e) {
            fail();
        }
    }
    
    
    /**
     * test the IllegalArgumentException
     */
    public void test00003() {
        try {
            mCalculator.fastSum(2000,1000);
            fail();
        } catch (IllegalArgumentException e) {
        } catch (OverNumberException e) {
            fail();
        }
    }
    
    /**
     * test the OverNumberException
     */
    public void test00004() {
        try {
            mCalculator.fastSum(1,Long.MAX_VALUE);
            fail();
        } catch (IllegalArgumentException e) {
            fail();
        } catch (OverNumberException e) {
        }
    }
    
    private class CalculatorThread extends Thread {
        public boolean isFinished = false;
        private final long mStart;
        private final long mEnd;
        private final boolean mIsFastAlg;
        CalculatorThread(long start, long end, boolean isFastAlg){
            mStart = start;
            mEnd = end;
            mIsFastAlg = isFastAlg;
        }
        @Override
        public void run() {
            try {
                if (mIsFastAlg) {
                    mCalculator.fastSum(mStart, mEnd);
                } else {
                    mCalculator.sum(mStart, mEnd);
                }
                isFinished = true;
            } catch (IllegalArgumentException e) {
                fail();
            } catch (OverNumberException e) {
                fail();
            }
        }
    }
    
    /**
     * test the performance : if the process is over 1 sec .. it will be fail
     */
    public void test00005() {
        CalculatorThread thread = new CalculatorThread(1, Long.MAX_VALUE/3, false);
        thread.start();
        synchronized(this) {
            try {
                this.wait(1000);
            } catch (InterruptedException e) {
                fail();
            }
            assertTrue(thread.isFinished);
        }
    }
    
    /**
     * test the performance : if the process is over 1 sec .. it will be fail
     */
    public void test00006() {
        CalculatorThread thread = new CalculatorThread(1, Long.MAX_VALUE/3, true);
        thread.start();
        synchronized(this) {
            try {
                this.wait(1000);
            } catch (InterruptedException e) {
                fail();
            }
            assertTrue(thread.isFinished);
        }
    }
}
