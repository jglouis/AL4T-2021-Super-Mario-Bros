package be.ecam.chess.piece;

import be.ecam.chess.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceToStringTest {

    @Test
    void testPawn() {
        Piece whitePawn = new Pawn(Color.WHITE);
        assertEquals("♙", whitePawn.toString());
        Piece blackPawn = new Pawn(Color.BLACK);
        assertEquals("♟", blackPawn.toString());
    }

    @Test
    void testRook() {
        Piece whiteRook = new Rook(Color.WHITE);
        assertEquals("♖", whiteRook.toString());
        Piece blackRook = new Rook(Color.BLACK);
        assertEquals("♜", blackRook.toString());
    }

    @Test
    void testKnight() {
        Piece whiteKnight = new Knight(Color.WHITE);
        assertEquals("♘", whiteKnight.toString());
        Piece blackKnight = new Knight(Color.BLACK);
        assertEquals("♞", blackKnight.toString());
    }

    @Test
    void testBishop() {
        Piece whiteBishop = new Bishop(Color.WHITE);
        assertEquals("♗", whiteBishop.toString());
        Piece blackBishop = new Bishop(Color.BLACK);
        assertEquals("♝", blackBishop.toString());
    }

    @Test
    void testKing() {
        Piece whiteKing = new King(Color.WHITE);
        assertEquals("♔", whiteKing.toString());
        Piece blackKing = new King(Color.BLACK);
        assertEquals("♚", blackKing.toString());
    }

    @Test
    void testQueen() {
        Piece whiteQueen = new Queen(Color.WHITE);
        assertEquals("♕", whiteQueen.toString());
        Piece blackQueen = new Queen(Color.BLACK);
        assertEquals("♛", blackQueen.toString());
    }
}
