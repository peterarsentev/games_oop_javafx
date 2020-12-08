package ru.job4j.chess.firuges.black;


import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * Класс фигуры Черная ладья
 *
 * @author alekseibulatov
 * @version 1.0
 */


public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    /**
     * метод используется для получения позиции фигуры на поле
     *
     * @return -возвращает тип перечисления
     */
    @Override
    public Cell position() {
        return position;
    }

    /**
     * метод возвращает массив с клетками, которые должна пройти фигура от
     * клетки position до клетки dest
     *
     * @param dest - ячейка на которую переходит фигура
     * @return - возвращается массив
     */
    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException();
        }
        int size = Math.abs(dest.getX() - position.getX());
        Cell[] steps = new Cell[size];
        int deltaX = (dest.getX() - position.getX()) > 0 ? 1 : -1;
        int deltaY = (dest.getY() - position.getY()) > 0 ? 1 : -1;
        int x = position.getX() + deltaX;
        int y = position.getY() + deltaY;
        for (int index = 0; index < size; index++) {
            steps[index] = Cell.findBy(x, y);
            x = x + deltaX;
            y = y + deltaY;
        }
        return steps;
    }

    /**
     * Метод проверяет, что фигура ходит по диагонали
     *
     * @param source - начальная точка
     * @param dest   - конечная точка
     * @return - true или false
     */

    public boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(source.getX() - dest.getX()) == Math.abs(source.getY() - dest.getY());
    }

    /**
     * метод копирует фигуру в новую ячейку доски
     *
     * @param dest - итоговоая позиция
     * @return - объект
     */

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
