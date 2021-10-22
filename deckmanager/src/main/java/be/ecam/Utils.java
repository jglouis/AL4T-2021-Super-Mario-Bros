package be.ecam;

import java.util.function.Function;

public class Utils {
    public static void doSomething() {
        new Thread(
                () -> {
                    //Do something in the thread
                });
    }
}
