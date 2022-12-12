package com.my;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadForPool implements Runnable {

    private final BlockingQueue<Runnable> tasks;
    private final AtomicBoolean shutdown;

    public ThreadForPool(BlockingQueue<Runnable> tasks, AtomicBoolean shutdown) {
        this.tasks = tasks;
        this.shutdown = shutdown;
    }

    @Override
    public void run() {
        while (!shutdown.get() || !tasks.isEmpty()) {
            Runnable task = tasks.poll();

            if (task != null) {
                task.run();
            }
        }
    }
}