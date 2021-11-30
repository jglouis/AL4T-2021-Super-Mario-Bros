package be.ecam.chess;

import be.ecam.chess.rule.TurnIterator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void start() {
        Game game = new Game(new Board(), new TurnIterator());
        game.start();
    }

    @Test
    void move() {
        Game game = new Game(new Board(), new TurnIterator());
        fail();
    }
}