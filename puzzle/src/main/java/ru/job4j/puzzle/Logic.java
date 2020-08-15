package ru.job4j.puzzle;

import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Figure;
import java.util.Arrays;

public class Logic {
    private final int size;
    private final Figure[] figures;
    private int index = 0;

    public Logic(int sz) {
        size = sz;
        figures = new Figure[size * size];
    }

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = findBy(source);
        if (index != -1) {
            Cell[] steps = figures[index].way(dest);
            if (isFree(steps)) {
                rst = true;
                figures[index] = figures[index].copy(dest);
            }
        }
        return rst;
    }

    public boolean isFree(Cell... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (findBy(cell) != -1) {
               result = false;
               break;
            }
        }
        return result;
    }

    public void clean() {
        for (int position = 0; position != figures.length; position++) {
            figures[position] = null;
        }
        index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != figures.length; index++) {
            if (figures[index] != null && figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public boolean isWin() {
        return Win.check(convert());
    }

    public int[][] convert() {
        int[][] table = new int[size][size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = findBy(new Cell(row, cell));
                if (position != -1 && figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }

    @Override
    public String toString() {
        return Arrays.toString(convert());
    }
}
