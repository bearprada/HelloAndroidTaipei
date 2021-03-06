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
            assertEquals(5050L, mCalculator.fastSum(0, 100));
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
            assertEquals(1501500L, mCalculator.fastSum(1000, 2000));
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
    
    /**
     * test middle()
     * */
    public void test00007() {
        try {
            assertEquals(50L, mCalculator.middle(0, 100));
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    public void test00008() {
        try {
            assertEquals(50L, mCalculator.middle(100, 0));
            fail();
        } catch (IllegalArgumentException e) {
        }
    }
    
    /**
     * test max()
     * */
    public void test00009() {
        try {
            assertEquals(100L, mCalculator.max(0, 100));
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
    
    public void test00010() {
        try {
            assertEquals(Long.MAX_VALUE, mCalculator.max(0, Long.MAX_VALUE));
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    public void test00011() {
        try {
            assertEquals(Long.MAX_VALUE, mCalculator.max(Long.MAX_VALUE, 0));
            fail();
        } catch (IllegalArgumentException e) {
            
        }
    }
    
    /**
     * test multiple()
     * */
    public void test00012() {
        try {
            assertEquals(3628800L, mCalculator.multiple(1, 10));
        } catch (IllegalArgumentException e) {
            fail();
        } catch (OverNumberException e) {
            fail();
        }
    }
    
    public void test00013() {
        try {
            assertEquals(3628800L, mCalculator.multiple(1, 10000));
            fail();
        } catch (IllegalArgumentException e) {
            fail();
        } catch (OverNumberException e) {
        }
    }

    public void test00014() {
        try {
            assertEquals(0, mCalculator.multiple(100, 1));
            fail();
        } catch (IllegalArgumentException e) {
        } catch (OverNumberException e) {
            fail();
        }
    }
    
    /**
     * test min
     * */
    public void test00015() {
        try {
            assertEquals(0L, mCalculator.min(0L, 100L));
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

}
