package be.ecam.chess.rule;

import be.ecam.chess.Color;

public class TurnIterator {
    private int turnNumber = 1;
    private Color currentPlayer = Color.WHITE;

    public void nextTurn() {
        turnNumber++;
        currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public void reset() {
        turnNumber = 1;
        currentPlayer = Color.WHITE;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }
}
