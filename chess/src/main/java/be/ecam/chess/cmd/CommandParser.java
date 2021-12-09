package be.ecam.chess.cmd;

import be.ecam.chess.IBoard;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class CommandParser {
    private final InputStream inputStream;
    private final IBoard board;
    private final Map<String, Command> commands;

    private CommandParser(InputStream inputStream, IBoard board, Map<String, Command> commands) {
        this.inputStream = inputStream;
        this.board = board;
        this.commands = commands;
    }

    public static class Builder {
        private final InputStream inputStream;
        private final IBoard board;
        private final Map<String, Command> commands;

        public Builder(InputStream inputStream, IBoard board) {
            this.inputStream = inputStream;
            this.board = board;
            commands = new java.util.HashMap<>();
        }

        public CommandParser build() {
            return new CommandParser(inputStream, board, commands);
        }

        public Builder addCommand(String alias, Command command) {
            commands.put(alias, command);
            return this;
        }
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
            commands.getOrDefault(command, new Command(null) {
                @Override
                void execute(String... args) {
                    System.out.printf("Unknown command: %s%n", command);
                }
            }).execute(args);

        }
    }
}
