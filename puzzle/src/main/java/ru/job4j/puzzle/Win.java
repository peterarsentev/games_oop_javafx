package ru.job4j.puzzle;

public class Win {
    public static boolean check(int[][] board) {
        boolean rsl = true;
        rsl = (Win.checkVert(board) || Win.checkHoriz(board));
        return rsl;
    }


    public static boolean checkHoriz(int[][] board) {
        boolean rsl = true;
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    count++;
                }
                if (count == 5) {
                    return true;
                }
            }
            count = 0;
        }
        rsl = count == 5;
        return rsl;
    }


    public static boolean checkVert(int[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[0][j] == board[i][j]) {
                    count++;
                }
            }
        }
        return  count == 25;
    }
}
