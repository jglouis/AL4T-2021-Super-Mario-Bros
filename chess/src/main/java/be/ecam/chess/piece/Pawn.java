package be.ecam.chess.piece;

import be.ecam.chess.Color;
import be.ecam.chess.rule.MoveIterator;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public MoveIterator getMoveIterator(int fromX, int fromY, int toX, int toY) {
        int deltaX = toX - fromX;
        int deltaY = toY - fromY;
        if (deltaX == 0) {
            boolean isStartingCoordinates = (fromY == 1 && getColor() == Color.WHITE)
                    || (fromY == 6 && getColor() == Color.BLACK);
            if (Math.abs(deltaY) == 1 || Math.abs(deltaY) == 2 && isStartingCoordinates) {
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
        // TODO: diagonal attack move
        return null;
    }

    @Override
    public String toString() {
        return getColor() == Color.WHITE ? "♙" : "♟";
    }
}
