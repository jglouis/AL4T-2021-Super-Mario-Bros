package be.ecam.builder;

public class StringBuilderScenario implements Runnable {
    @Override
    public void run() {
        String hello = "hello";
        String world = "world";

        System.out.println(hello + world);

        StringBuilder builder = new StringBuilder(hello + world);
        for (int i = 0; i < 10; i++) {
            builder.append(i);
        }
        System.out.println(builder.toString());
    }

}
