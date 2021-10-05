package ru.job4j.tictactoe.ships;

import ru.job4j.tictactoe.Cell;

import java.util.List;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public interface Ship {
    List<Cell> position();

    List<Cell> border();
}
