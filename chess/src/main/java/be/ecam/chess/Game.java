package be.ecam.chess;

import be.ecam.chess.piece.*;
import be.ecam.chess.rule.MoveIterator;
import be.ecam.chess.rule.TurnIterator;

/**
 * the {@link Game} class is responsible for enforcing games rules like
 * - Game Setup
 * - {@link Piece} movement
 * - player turn alternance.
 */
public class Game {
    private final IBoard board;
    private final TurnIterator turnIterator = new TurnIterator();

    public Game(IBoard board) {
        this.board = board;
    }

    public void start() {
        turnIterator.reset();
        try {
            board.addPiece(new King(Color.WHITE), 4, 0);
            board.addPiece(new King(Color.BLACK), 4, 7);
            board.addPiece(new Queen(Color.WHITE), 3, 0);
            board.addPiece(new Queen(Color.BLACK), 3, 7);
            board.addPiece(new Rook(Color.WHITE), 0, 0);
            board.addPiece(new Rook(Color.BLACK), 0, 7);
            board.addPiece(new Rook(Color.WHITE), 7, 0);
            board.addPiece(new Rook(Color.BLACK), 7, 7);
            board.addPiece(new Bishop(Color.WHITE), 2, 0);
            board.addPiece(new Bishop(Color.BLACK), 2, 7);
            board.addPiece(new Knight(Color.WHITE), 1, 0);
            board.addPiece(new Knight(Color.BLACK), 1, 7);
            for (int i = 0; i < 8; i++) {
                board.addPiece(new Pawn(Color.WHITE), i, 1);
                board.addPiece(new Pawn(Color.BLACK), i, 6);
            }
        } catch (Board.CellException ignored) {
        }
    }

    public void move(int fromX, int fromY, int toX, int toY) throws Board.CellException {
        Piece piece = board.getPiece(fromX, fromY);
        if (!turnIterator.getCurrentPlayer().equals(piece.getColor())) {
            return;
        }
        MoveIterator moveIterator = piece.getMoveIterator(fromX, fromY, toX, toY);
        if (moveIterator != null) {
            // Check for each step if the move is valid (no piece in-between)
            while (true) {
                int[] step = moveIterator.nextStep();
                if (step == null) break;
                if (board.getPiece(step[0], step[1]) != null) {
                    if (step[0] == toX && step[1] == toY) {
                        // remove enemy piece, if any
                        if (board.getPiece(step[0], step[1]).getColor() != piece.getColor()) {
                            board.remove(step[0], step[1]);
                        }
                    }
                    throw new RuntimeException("Illegal move");
                }
            }
            board.move(fromX, fromY, toX, toY);
            turnIterator.nextTurn();
        } else throw new RuntimeException("Illegal move");
    }
}
