package be.ecam.lambda;

import java.util.concurrent.atomic.AtomicInteger;

public class LambdaScenario implements Runnable {
    @Override
    public void run() {
        final AtomicInteger counter = new AtomicInteger(0);

        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println(counter.incrementAndGet());
            }
        });
        thread.start();
    }
}
