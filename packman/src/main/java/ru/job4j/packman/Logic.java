package ru.job4j.packman;

import ru.job4j.packman.firuges.Cell;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
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
        return this.board[cell.getY()][cell.getY()].tryLock();
    }

    public boolean move(Cell dest) {
        boolean result = false;
        try {
            result = this.board[dest.getY()][dest.getX()]
                    .tryLock(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void clean(Cell source) {
        this.board[source.getY()][source.getX()].unlock();
    }
}
