// Base code for student Memory Card Game assessment
// Students do not need to use this code in their assessment, fine to junk it and do something different!
//
// The cards are A B C D E F G H.
// A player can pick card’s by entering row and column. For example, ‘First pick (row col): 0 2‘.

import java.util.*;

public class MemoryGame {
    private static final int SIZE = 4; // 4x4 board
    private static final String HIDDEN = " * ";
    private static String[][] board = new String[SIZE][SIZE];
    private static boolean[][] revealed = new boolean[SIZE][SIZE];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initBoard();
        long startTime = System.currentTimeMillis(); // start timer
        int moves = 0;

        while (true) {
            printBoard();
            try {
                // First pick
                System.out.print("First pick (row col): ");
                int r1 = sc.nextInt();
                int c1 = sc.nextInt();
                if (!isValid(r1, c1)) {
                    System.out.println("⚠️ Invalid position. Try again.\n");
                    continue;
                }
                revealed[r1][c1] = true;
                printBoard();

                // Second pick
                System.out.print("Second pick (row col): ");
                int r2 = sc.nextInt();
                int c2 = sc.nextInt();
                if (!isValid(r2, c2) || (r1 == r2 && c1 == c2)) {
                    System.out.println("⚠️ Invalid second pick. Try again.\n");
                    revealed[r1][c1] = false;
                    continue;
                }
                revealed[r2][c2] = true;
                printBoard();

                moves++;

                // Check if the two cards match
                if (board[r1][c1].equals(board[r2][c2])) {
                    System.out.println("✅ Match found!\n");
                } else {
                    System.out.println("❌ Not a match!\n");
                    pause(1500); // short delay before hiding again
                    revealed[r1][c1] = false;
                    revealed[r2][c2] = false;
                }

                // Check for win
                if (allMatched()) {
                    long endTime = System.currentTimeMillis();
                    double timeUsed = (endTime - startTime) / 1000.0;
                    printBoard();
                    System.out.println("🎉 YOU WIN! 🎉");
                    System.out.printf("Moves: %d | Time: %.1f seconds\n", moves, timeUsed);
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠️ Please enter numbers only (row and column).\n");
                sc.nextLine(); // clear invalid input
            }
        }
        sc.close();
    }

    // ===== Game setup =====
    private static void initBoard() {
        List<String> cards = new ArrayList<>();
        for (char c = 'A'; c < 'A' + (SIZE * SIZE) / 2; c++) {
            cards.add(" " + c + " ");
            cards.add(" " + c + " ");
        }
        Collections.shuffle(cards);
        Iterator<String> it = cards.iterator();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = it.next();
                revealed[r][c] = false;
            }
        }
    }

    // ===== Display board =====
    private static void printBoard() {
        System.out.println("\n   Memory (4x4) — find the pairs\n");
        System.out.print("    ");
        for (int c = 0; c < SIZE; c++) System.out.printf(" %d ", c);
        System.out.println();
        System.out.print("    ");
        for (int c = 0; c < SIZE; c++) System.out.print("---");
        System.out.println();

        for (int r = 0; r < SIZE; r++) {
            System.out.printf(" %d |", r);
            for (int c = 0; c < SIZE; c++) {
                System.out.print(revealed[r][c] ? board[r][c] : HIDDEN);
            }
            System.out.println();
        }
        System.out.println();
    }

    // ===== Helper methods =====
    private static boolean isValid(int r, int c) {
        return r >= 0 && r < SIZE && c >= 0 && c < SIZE && !revealed[r][c];
    }

    private static boolean allMatched() {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (!revealed[r][c]) return false;
        return true;
    }

    private static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
