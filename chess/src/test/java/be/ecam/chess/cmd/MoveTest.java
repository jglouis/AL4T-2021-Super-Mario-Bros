package be.ecam.chess.cmd;

import be.ecam.chess.IGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveTest {

    @Test
    void execute() {
        IGame mockGame = new IGame() {
            @Override
            public void start() {

            }

            @Override
            public void move(int fromX, int fromY, int toX, int toY) {
                assertEquals(0, fromX);
                assertEquals(0, fromY);
                assertEquals(1, toX);
                assertEquals(1, toY);
            }
        };

        Command move = new Move(mockGame);
        move.execute("a1", "b2");    }
}