package be.ecam.builder;

public class WithoutStringBuilderScenario {
    public static void main(String[] args) {
        String hello = "hello";
        String world = "world";

        System.out.println(hello + world);

        String result = hello + world;
        for (int i = 0; i < 1_000_000; i++) {
            result += i;
        }
        System.out.println(result);
    }
}
