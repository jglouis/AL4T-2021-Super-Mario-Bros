package be.ecam.lambda;

import java.util.concurrent.atomic.AtomicInteger;

public class NaiveClosure {
    private static int counter = 0;

    public static void countUp() {
        System.out.println(counter++);
    }

    public static Runnable getCounter() {
        final AtomicInteger counter = new AtomicInteger(0);
        return () -> System.out.println(counter.incrementAndGet());
    }
}
