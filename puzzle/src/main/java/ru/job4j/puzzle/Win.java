package ru.job4j.puzzle;

public class Win {
    /* Метод проверяет заполнена ли строка в двухмерном массиве числом 1 */
    public static boolean monoHorizontal(int[][] board, int row) {
        boolean result = true;
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] != 1) {
                result = false;
                break;
            }
        }
        return result;
    }

    /* Метод проверяет заполнены ли все элементы в колонке числом 1 */
    public static boolean monoVertical(int[][] board, int column) {
        boolean result = true;
        for (int i = 0; i < board[column].length; i++) {
            if (board[i][column] != 1) {
                result = false;
                break;
            }
        }
        return result;
    }

    /* Метод проверяет, находится ли на поле выйгрышная ситуация */
    public static boolean check(int[][] board) {
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            if ((board[i][i] == 1
                    && monoHorizontal(board, i)
                    || monoVertical(board, i))) {
                result = true;
                break;
            }
        }
        return result;
    }
}
