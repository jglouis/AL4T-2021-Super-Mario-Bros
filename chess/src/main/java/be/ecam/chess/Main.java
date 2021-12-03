package be.ecam.chess;

import be.ecam.chess.rule.TurnIterator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game(board, new TurnIterator());
        game.start();

        String userInput;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(board);
            userInput = scanner.nextLine();
            String[] arguments = userInput.split(" ");
            String command = arguments[0];
            switch (command) {
                case "move":
                    try {
                        int[] from = BoardUtils.humanChessCoordinatesToXY(arguments[1]);
                        int[] to = BoardUtils.humanChessCoordinatesToXY(arguments[2]);
                        int fromX = from[0];
                        int fromY = from[1];
                        int toX = to[0];
                        int toY = to[1];
                        game.move(fromX, fromY, toX, toY);
                    } catch (RuntimeException | Board.CellException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "start":
                    game.start();
                    break;
                case "quit":
                    System.exit(0);
                default:
                    System.out.printf("Unknown command: %s%n", command);
            }
        }
    }
}
