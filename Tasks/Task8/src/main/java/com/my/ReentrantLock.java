package com.my;

class ReentrantLock {
    private int counter = 0;
    private final Object mutex = new Object();
    private Thread locked = null;

    public void unlock() {
        synchronized (mutex) {
            if (Thread.currentThread() == locked)
                if (--counter == 0) {
                    locked = null;
                    notify();
                }
        }
    }

    public void lock() throws InterruptedException {
        synchronized (mutex) {
            Thread thread = Thread.currentThread();
            while (locked != null && locked != thread)
                wait();
            locked = thread;
            ++counter;
        }
    }


}