package ru.job4j.puzzle;

public class Win {
    public static boolean check(int[][] board) {
        /*return checkHLine(board) && checkVLine(board);*/
        return monoHLine(board, findHLine(board)) || monoVLine(board, findVLine(board));
    }

    /*public static boolean checkHLine(int[][] board) {
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
    }*/

    /*public static boolean checkVLine(int[][] board) {
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
    }*/

    public static int findHLine(int[][] board) {
        int rsl = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 1) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    public static int findVLine(int[][] board) {
        int rsl = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == 1) {
                rsl = i;
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
