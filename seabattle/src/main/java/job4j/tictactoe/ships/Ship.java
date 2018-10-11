package job4j.tictactoe.ships;

import job4j.tictactoe.Cell;

import java.util.List;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public interface Ship {
    List<Cell> position();

    List<Cell> border();
}
