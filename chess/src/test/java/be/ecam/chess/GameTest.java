package be.ecam.chess;

import be.ecam.chess.rule.TurnIterator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameTest {

    @Test
    void start() {
        Game game = new Game(new MockBoard(), new MockTurnIterator());
        game.start();
        // We should probably verify that all pieces are in place.
    }

    @Test
    void move() throws IBoard.CellIsNotEmptyException, IBoard.OutOfBoundException {
        MockBoard board = new MockBoard();
        board.addPiece(new MockPiece(Color.WHITE), 0, 0);
        Game game = new Game(board, new TurnIterator());

        assertDoesNotThrow(() -> game.move(0, 0, 1, 1));
    }
}