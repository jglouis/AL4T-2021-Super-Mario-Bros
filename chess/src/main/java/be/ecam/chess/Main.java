package be.ecam.chess;

import be.ecam.chess.cmd.CommandParser;
import be.ecam.chess.rule.TurnIterator;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game(board, new TurnIterator());
        game.start();

        CommandParser parser = new CommandParser(System.in, game, board);
        parser.start();
    }
}
