package be.ecam.chess;

import be.ecam.chess.piece.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void addPiece() throws Board.CellIsNotEmptyException, Board.OutOfBoundException {
        Board board = new Board();
        Piece mockPiece = new MockPiece(Color.WHITE);
        board.addPiece(mockPiece, 2, 3);
        assertEquals(mockPiece, board.getPiece(2, 3));
    }

    @Test
    void addPiece_OutOfBound() {
        Board board = new Board();
        Piece mockPiece = new MockPiece(Color.BLACK);
        assertThrows(Board.OutOfBoundException.class, () -> board.addPiece(mockPiece, 8, 3));
    }

    @Test
    void addPiece_CellIsNotEmpty() throws Board.CellIsNotEmptyException, Board.OutOfBoundException {
        Board board = new Board();
        Piece mockPiece = new MockPiece(Color.BLACK);
        board.addPiece(mockPiece, 2, 3);
        assertThrows(Board.CellIsNotEmptyException.class, () -> board.addPiece(mockPiece, 2, 3));
    }

    @Test
    void getPiece() throws Board.CellIsNotEmptyException, Board.OutOfBoundException {
        Board board = new Board();
        Piece mockPiece = new MockPiece(Color.WHITE);
        board.addPiece(mockPiece, 2, 3);
        assertEquals(mockPiece, board.getPiece(2, 3));
    }

    @Test
    void getPiece_OutOfBound() {
        Board board = new Board();
        assertThrows(Board.OutOfBoundException.class, () -> board.getPiece(8, 3));
    }

    @Test
    void getPiece_CellIsEmpty() throws Board.OutOfBoundException {
        Board board = new Board();
        assertNull(board.getPiece(2, 3));
    }

    @Test
    void move() throws Board.CellIsNotEmptyException, Board.OutOfBoundException, Board.CellIsEmptyException {
        Board board = new Board();
        Piece mockPiece = new MockPiece(Color.WHITE);
        board.addPiece(mockPiece, 2, 3);
        board.move(2, 3, 4, 5);
        assertEquals(mockPiece, board.getPiece(4, 5));
    }

    @Test
    void move_OutOfBound() throws Board.CellIsNotEmptyException, Board.OutOfBoundException {
        Board board = new Board();
        Piece mockPiece = new MockPiece(Color.BLACK);
        board.addPiece(mockPiece, 2, 3);
        assertThrows(Board.OutOfBoundException.class, () -> board.move(2, 3, 8, 5));
    }

    @Test
    void move_CellIsEmpty() {
        Board board = new Board();
        assertThrows(Board.CellIsEmptyException.class, () -> board.move(2, 3, 4, 5));
    }

    @Test
    void move_CellIsNotEmpty() throws Board.CellIsNotEmptyException, Board.OutOfBoundException {
        Board board = new Board();
        Piece mockPiece = new MockPiece(Color.BLACK);
        board.addPiece(mockPiece, 2, 3);
        board.addPiece(new MockPiece(Color.WHITE), 4, 5);
        assertThrows(Board.CellIsNotEmptyException.class, () -> board.move(2, 3, 4, 5));
    }

    @Test
    void remove() throws Board.CellIsNotEmptyException, Board.OutOfBoundException {
        Board board = new Board();
        Piece mockPiece = new MockPiece(Color.WHITE);
        board.addPiece(mockPiece, 2, 3);
        board.remove(2, 3);
        assertNull(board.getPiece(2, 3));
    }

    @Test
    void remove_OutOfBound() {
        Board board = new Board();
        assertThrows(Board.OutOfBoundException.class, () -> board.remove(8, 3));
    }

    @Test
    void OutOfBoundMsg() throws Board.CellIsNotEmptyException {
        Board board = new Board();
        Piece mockPiece = new MockPiece(Color.BLACK);
        try {
            board.addPiece(mockPiece, 2, 8);
        } catch (Board.OutOfBoundException e) {
            assertEquals("Cell (2, 8) is out of bound", e.getMessage());
        }
    }

    @Test
    void CellIsNotEmptyMsg() throws Board.OutOfBoundException {
        Board board = new Board();
        Piece mockPiece = new MockPiece(Color.BLACK);
        try {
            board.addPiece(new MockPiece(Color.WHITE), 4, 5);
            board.addPiece(mockPiece, 4, 5);
        } catch (Board.CellIsNotEmptyException e) {
            assertEquals("Cell (4, 5) is not empty", e.getMessage());
        }
    }

    @Test
    void CellIsEmptyMsg() throws Board.OutOfBoundException, Board.CellIsNotEmptyException {
        Board board = new Board();
        try {
            board.move(2, 3, 4, 5);
        } catch (Board.CellIsEmptyException e) {
            assertEquals("Cell (2, 3) is empty", e.getMessage());
        }
    }
}