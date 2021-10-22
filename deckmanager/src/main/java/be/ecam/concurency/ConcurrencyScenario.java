package be.ecam.concurency;

public class ConcurrencyScenario implements Runnable {

    private int counter = 0;
    private static final Object LOCK = new Object();

    @Override
    public void run() {

        Thread thread1 = new Thread(() -> {
            synchronized (LOCK) {
                counter++;
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (LOCK) {
                counter++;
            }
        }
        );

        thread1.start();
        thread2.start();
    }
}
