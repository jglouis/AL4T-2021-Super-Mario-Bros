package be.ecam.chess.cmd;

import be.ecam.chess.IBoard;
import be.ecam.chess.IGame;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class CommandParser {
    private final InputStream inputStream;

    private final Start startCommand;
    private final Quit quitCommand;
    private final Move moveCommand;

    private final IBoard board;

    public CommandParser(InputStream inputStream, IGame game, IBoard board) {
        this.inputStream = inputStream;
        this.startCommand = new Start(game);
        this.board = board;
        this.quitCommand = new Quit();
        this.moveCommand = new Move(game);
    }

    public void start() {
        Scanner sc = new Scanner(inputStream);
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.println(board);
            String line = sc.nextLine();
            String[] args = line.split(" ");
            String command = args[0];
            args = Arrays.copyOfRange(args, 1, args.length);
            switch (command) {
                case "start" -> startCommand.execute(args);
                case "quit" -> quitCommand.execute(args);
                case "move" -> moveCommand.execute(args);
                default -> System.out.printf("Unknown command: %s%n", command);
            }
        }
    }
}
