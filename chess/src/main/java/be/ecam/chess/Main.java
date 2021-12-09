package be.ecam.chess;

import be.ecam.chess.cmd.CommandParser;
import be.ecam.chess.cmd.Move;
import be.ecam.chess.cmd.Quit;
import be.ecam.chess.cmd.Start;
import be.ecam.chess.rule.TurnIterator;

public class Main {
    public static boolean isUtf8 = false;

    public static void main(String[] args) {
        isUtf8 = args.length >= 1 && args[0].equals("-utf8");
        System.out.println("isUtf8: " + isUtf8);

        Board board = new Board();
        Game game = new Game(board, new TurnIterator());
        game.start();

        CommandParser parser = new CommandParser.Builder(System.in, board)
                .addCommand("start", new Start(game))
                .addCommand("quit", new Quit())
                .addCommand("move", new Move(game))
                .build();

        parser.start();
    }
}
