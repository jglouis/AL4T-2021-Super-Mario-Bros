import di.DaggerGameEngineFactory;
import manager.GameEngine;

public class Main {
    public static void main(String... args) {
        GameEngine gameEngine = DaggerGameEngineFactory.create().gameEngine();
        gameEngine.start();
    }
}
