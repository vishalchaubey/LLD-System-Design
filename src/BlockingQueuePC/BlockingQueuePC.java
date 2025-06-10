
package BlockingQueuePC;

import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                queue.put(i);
                System.out.println("Produced: " + i);
                Thread.sleep(100); // Simulate time taken to produce an item
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Integer item = queue.take();
                System.out.println("Consumed: " + item);
                Thread.sleep(150); // Simulate time taken to consume an item
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class BlockingQueuePC{
public static void main(String[] args) {
    BlockingQueue<Integer> queue = new java.util.concurrent.ArrayBlockingQueue<>(5);
    Thread producerThread = new Thread(new Producer(queue));
    Thread consumerThread = new Thread(new Consumer(queue));
    producerThread.start();
    consumerThread.start();
    try {
        producerThread.join();
        consumerThread.join();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    System.out.println("Processing complete.");
}

}