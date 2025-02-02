package be.ecam.builder;

public class StringBuilderScenario {
    public static void main(String[] args) {
        String hello = "hello";
        String world = "world";

        System.out.println(hello + world);

        StringBuilder builder = new StringBuilder(hello + world);
        for (int i = 0; i < 1_000_000; i++) {
            builder.append(i);
        }
        System.out.println(builder.toString());
    }

}
