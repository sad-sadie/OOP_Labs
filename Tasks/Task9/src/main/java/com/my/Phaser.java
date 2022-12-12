package com.my;

public class Phaser {
    private int parties;
    private int phase;
    private int partiesAwait;

    public Phaser(int parties) {
        this.parties = parties;
        this.phase = 0;
        this.partiesAwait = parties;
    }

    public synchronized int register() {
        ++parties;
        ++partiesAwait;
        return phase;
    }



    public synchronized int deregister() {
        --partiesAwait;
        --parties;

        if (partiesAwait == 0) {
            ++phase;
            partiesAwait = parties;
            notifyAll();
        }

        return phase;
    }

    public synchronized int await() throws InterruptedException {
        --partiesAwait;

        if (partiesAwait > 0) {
            wait();
        }

        partiesAwait = parties;
        ++phase;
        notifyAll();

        return phase;
    }
}