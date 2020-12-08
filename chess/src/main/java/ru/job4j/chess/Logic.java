package ru.job4j.chess;


import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 * Класс  управляет логикой игры.
 *
 * @author alekseibulatov
 * @version 0.1
 */

public final class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    /**
     * Метод добавления фигуры
     *
     * @param figure - обьект
     */

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    /**
     * Метод описывающий логику передвижения
     *
     * @param source - начальная точка
     * @param dest   -конечная точка
     * @throws FigureNotFoundException - фигуры нет на клетке
     * @throws ImpossibleMoveException -пользователь двигает фигуру не по правилам шахмат.
     * @throws OccupiedCellException   -ячейка занята
     * @throws IllegalStateException   - движение не по диагониали
     */
    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException, IllegalStateException {
        int index = findBy(source);
        Cell[] steps = figures[index].way(dest);
        free(steps);
        figures[index] = figures[index].copy(dest);
    }

    /**
     * Метод проверки свободной ячейки
     *
     * @param steps -массив ячеек
     * @return -
     * @throws OccupiedCellException -исключение если занято
     */

    private boolean free(Cell[] steps) throws OccupiedCellException {
        for (Figure s : figures) {
            for (Cell c : steps) {
                if (s.position().equals(c)) {
                    throw new OccupiedCellException();
                }
            }
        }
        return true;
    }

    /**
     * Метод удаления старых объектов с доски
     */

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    /**
     * Метод возвращает индекс ячейки объекта Figure или выкидывает исключение
     * при поиске объекта типа Cell в массиве figures
     *
     * @param cell -
     * @return - индекс элемента
     * @throws FigureNotFoundException - исключение
     */
    private int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException();
    }
}
