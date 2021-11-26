package be.ecam.chess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game(board);
        game.start();

        String userInput;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            userInput = scanner.next();
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
                    } catch (RuntimeException e) {
                        System.out.println("Invalid move arguments");
                    } catch (Board.CellException e) {
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
