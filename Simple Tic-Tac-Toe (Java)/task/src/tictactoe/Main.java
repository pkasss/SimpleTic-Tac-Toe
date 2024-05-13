package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static boolean game = true;
    static boolean turn = true;

    public static void main(String[] args) {
        String grid = "_________";

        printTheGrid(grid);

        while (game) {
            try {
                grid = makeAMove(grid);
                printTheGrid(grid);
                analyzeTheGame(grid);
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public static void printTheGrid(String grid) {
        int charIndex = 0;

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid.charAt(charIndex) + " ");
                charIndex++;
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public static String makeAMove(String grid) {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        int x;
        int y;
        int gridIndex = 0;

        while (loop) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            x--;
            y--;

            gridIndex = (x * 3) + y;

            if ((x < 0 || x > 2) || (y < 0 || y > 2)) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (grid.charAt(gridIndex) != '_') {
                System.out.println("This cell is occupied! Choose another one!");
            } else if (grid.charAt(gridIndex) == '_') {
                loop = false;
            }
        }

        StringBuilder sb = new StringBuilder(grid);

        if (turn) {
            sb.replace(gridIndex, gridIndex + 1, "X");
        } else {
            sb.replace(gridIndex, gridIndex + 1, "O");
        }
        turn = !turn;

        return sb.toString();
    }

    public static void analyzeTheGame(String grid) {
        int countOfX = countOccurrences(grid, 'X');
        int countOfY = countOccurrences(grid, 'O');
        boolean xWins = checkWinner(grid, 'X');
        boolean oWins = checkWinner(grid, 'O');
        boolean gameNotFinished = grid.contains("_");

        if ((xWins && oWins) || Math.abs(countOfX - countOfY) > 1) {
            game = false;
            System.out.println("Impossible");
        } else if (xWins) {
            game = false;
            System.out.println("X wins");
        } else if (oWins) {
            game = false;
            System.out.println("O wins");
        } else if (!gameNotFinished) {
            game = false;
            System.out.println("Draw");
        }
    }

    public static int countOccurrences(String grid, char player) {
        int count = 0;
        for (char ch : grid.toCharArray()) {
            if (ch == player) {
                count++;
            }
        }
        return count;
    }

    public static boolean checkWinner(String grid, char player) {
        for (int i = 0; i < 3; i += 3) {
            if (grid.charAt(i) == player && grid.charAt(1 + i) == player && grid.charAt(2 + i) == player) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (grid.charAt(i) == player && grid.charAt(3 + i) == player && grid.charAt(6 + i) == player) {
                return true;
            }
        }
        return (grid.charAt(0) == player && grid.charAt(4) == player && grid.charAt(8) == player) ||
                (grid.charAt(2) == player && grid.charAt(4) == player && grid.charAt(6) == player);
    }
}
