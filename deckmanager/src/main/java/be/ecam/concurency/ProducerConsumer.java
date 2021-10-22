package be.ecam.concurency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer implements Runnable {
    private final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(10);

    private final Thread producer = new Thread(() -> {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    private final Thread consumer = new Thread(() -> {
        while (true) {
            try {
                System.out.println(buffer.take());
            } catch (InterruptedException e) {

            }
        }
    });

    @Override
    public void run() {
        producer.start();
        consumer.start();
    }
}
