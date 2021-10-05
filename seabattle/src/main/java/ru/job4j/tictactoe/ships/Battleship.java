package ru.job4j.tictactoe.ships;

import ru.job4j.tictactoe.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Battleship implements Ship {
    private final List<Cell> positions = new ArrayList<>();

    public Battleship(Cell... cells) {
        this.positions.addAll(Arrays.asList(cells));
    }

    @Override
    public List<Cell> position() {
        return null;
    }

    @Override
    public List<Cell> border() {
        return null;
    }
}
