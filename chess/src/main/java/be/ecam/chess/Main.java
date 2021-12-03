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
                        int fromX = Integer.parseInt(arguments[1]);
                        int fromY = Integer.parseInt(arguments[2]);
                        int toX = Integer.parseInt(arguments[3]);
                        int toY = Integer.parseInt(arguments[4]);
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
