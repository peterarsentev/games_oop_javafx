package ru.job4j.puzzle;

public class Win {
    public static boolean check(int[][] board) {
        return checkHLine(board) && checkVLine(board);
    }

    public static boolean checkHLine(int[][] board) {
        boolean rsl = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 1) {
                for (int j = 1; j < board.length; j++) {
                    if (board[i][j] != 1) {
                        rsl = false;
                        break;
                    }
                }
            }
        }
        return rsl;
    }

    public static boolean checkVLine(int[][] board) {
        boolean rsl = true;
        for (int j = 0; j < board.length; j++) {
            if (board[0][j] == 1) {
                for (int i = 1; i < board.length; i++) {
                    if (board[i][j] != 1) {
                        rsl = false;
                        break;
                    }
                }
            }
        }
        return rsl;
    }
}
