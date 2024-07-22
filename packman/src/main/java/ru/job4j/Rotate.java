package ru.job4j;

public class Rotate {
    public static void rotate(int[][] array) {
        int n = array.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = array[i][j];
                array[i][j] = array[j][n - i - 1];
                array[j][n - i - 1] = array[n - i - 1][n - j - 1];
                array[n - i - 1][n - j - 1] = array[n - j - 1][i];
                array[n - j - 1][i] = temp;
            }
        }
    }   

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Original Array:");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        rotate(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
