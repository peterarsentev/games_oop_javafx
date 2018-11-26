package ru.job4j.puzzle;

import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Figure;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final ReentrantLock[][] board;

    public Logic(int size) {
        this.board = new ReentrantLock[size][size];
        for (int row = 0; row != size; row++) {
            for (int cell = 0; cell != size; cell++) {
                this.board[row][cell] = new ReentrantLock();
            }
        }
    }

    public boolean fill(Cell cell) {
        return this.board[cell.y][cell.x].tryLock();
    }

    public boolean move(Cell dest) {
        boolean result = false;
        try {
            result = this.board[dest.y][dest.x].tryLock(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void clean(Cell source) {
        this.board[source.y][source.x].unlock();
    }
}
