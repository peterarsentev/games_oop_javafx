package ru.job4j.chess.firuges;

import ru.job4j.chess.ImpossibleMoveException;

/**
 * Описывает поведение фигуры
 */
public interface Figure {
    /**
     * Для получения позиции фигуры на поле
     *
     * @return тип перечисления
     */
    Cell position();

    /**
     * Для движения фигуры до точки dest
     *
     * @param dest до куда идет фигура
     * @return массив с клетками,
     * которые должна пройти фигура при своем движении из текущей позиции до клетки dest
     * Массив возвращается потому, что все фируры ходят по разному
     * @throws ImpossibleMoveException ловит исключение
     */
    Cell[] way(Cell dest) throws ImpossibleMoveException;

    /**
     * Для возврата имени картинки фигуры
     *
     * @return имя картинки фигуры
     */
    default String icon() {
        return String.format(
                "%s.png", getClass().getSimpleName()
        );
    }

    /**
     * Создает объет класса с позицией dest. Все фигуры одноразовые.
     * При переходе из одной клетки в другую, мы будем создавать новый обьект с новой позицией,
     * а старый - удалять
     *
     * @param dest позиция
     * @return новый объект с новой позицией
     */
    Figure copy(Cell dest);
}
