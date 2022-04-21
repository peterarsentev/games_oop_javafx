package ru.job4j.puzzle;

public class Win {
    public static boolean check(int[][] board) {
        boolean rsl = false;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == 1 && monoHLine(board, i) || monoVLine(board, i)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    public static boolean monoHLine(int[][] board, int hLine) {
        boolean rsl = true;
        for (int i = 0; i < board.length; i++) {
            if (board[hLine][i] != 1) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    public static boolean monoVLine(int[][] board, int vLine) {
        boolean rsl = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][vLine] != 1) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }
}
