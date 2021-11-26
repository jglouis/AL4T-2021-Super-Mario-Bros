package be.ecam.chess;

import be.ecam.chess.piece.Piece;
import be.ecam.chess.rule.MoveIterator;


/**
 * Mock piece for testing.
 * Accepts any move.
 */
public class MockPiece extends Piece {
    public MockPiece(Color color) {
        super(color);
    }

    @Override
    public MoveIterator getMoveIterator(int fromX, int fromY, int toX, int toY) {
        return new MoveIterator() {
            int currentStep = 0;
            final int[][] steps = new int[][]{{fromX, fromY}, {toX, toY}};

            @Override
            public int[] nextStep() {
                if (this.currentStep >= 1) return null;
                return this.steps[++this.currentStep];
            }
        };
    }
}
