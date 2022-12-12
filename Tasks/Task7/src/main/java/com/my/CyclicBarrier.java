package com.my;

public class CyclicBarrier {
    int initialParties;
    int partiesAwait;
    Runnable runnable;

    public CyclicBarrier(int parties, Runnable runnable) {
        initialParties = parties;
        partiesAwait = parties;
        this.runnable = runnable;
    }

    public synchronized void await() throws InterruptedException {
        --partiesAwait;

        if(partiesAwait > 0) {
            this.wait();
        } else {
            partiesAwait = initialParties;
            runnable.run();
            notifyAll();
        }
    }
}
