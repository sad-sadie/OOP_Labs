package com.my;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class ThreadPool implements Executor {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;
    private final AtomicBoolean shutdown;

    public ThreadPool(int numberOfThreads) {
        this.threads = new Thread[numberOfThreads];
        this.tasks = new LinkedBlockingQueue<>();
        this.shutdown = new AtomicBoolean(false);

        IntStream.range(0, numberOfThreads).forEach(i -> {threads[i] = new Thread(new ThreadForPool(tasks, shutdown)); threads[i].start();});
    }



    @Override
    public void execute(Runnable command) {
        if (!shutdown.get()) {
            try {
                tasks.put(command);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {
        shutdown.set(true);
    }
}