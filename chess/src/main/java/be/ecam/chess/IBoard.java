package be.ecam.chess;

import be.ecam.chess.piece.Piece;

public interface IBoard {
    void addPiece(Piece piece, int x, int y) throws CellIsNotEmptyException, OutOfBoundException;

    Piece getPiece(int x, int y) throws OutOfBoundException;

    void move(int fromX, int fromY, int toX, int toY) throws CellIsEmptyException, CellIsNotEmptyException, OutOfBoundException;

    Piece remove(int x, int y) throws OutOfBoundException;

    class CellException extends Exception {
        protected final int x;
        protected final int y;

        public CellException(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class CellIsEmptyException extends CellException {
        public CellIsEmptyException(int x, int y) {
            super(x, y);
        }

        @Override
        public String getMessage() {
            return String.format("Cell (%d, %d) is empty", x, y);
        }
    }

    public static class CellIsNotEmptyException extends CellException {
        public CellIsNotEmptyException(int x, int y) {
            super(x, y);
        }

        @Override
        public String getMessage() {
            return String.format("Cell (%d, %d) is not empty", x, y);
        }
    }

    class OutOfBoundException extends CellException {
        public OutOfBoundException(int x, int y) {
            super(x, y);
        }

        @Override
        public String getMessage() {
            return String.format("Cell (%d, %d) is out of bound", x, y);
        }

    }
}
