package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 * Управляет логикий игры
 */
public final class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    /**
     * 1. По объекту типа Cell source в массиве figures найти объект типа Figure.
     * Для этого используется метод findBy. Он возвращает индекс ячейки или выкидывает исключение.
     * 2. Если объект найден, то нужно получить его ходы до клетки Cell dest.
     * Это нужно сделать с помощью метода way объекта Figure.
     * 3. Дальше нужно проверить,
     * что массив клеток от метода way не заполнен другими объектами класса Figure.
     * Если он не заполнен, то нужно в массив figures в позицию, полученную в пункте 1,
     * записать новый объект полученный из метода figure.copy.
     *
     * @param source начальная точка
     * @param dest   конечная точка
     * @throws FigureNotFoundException если фигуры нет на клетке
     * @throws ImpossibleMoveException если пользователь двигает фигуру не по правилам шахмат
     * @throws OccupiedCellException   если ячейка занята
     */
    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        int index = findBy(source);
        Cell[] steps = figures[index].way(dest);
        free(steps);
        figures[index] = figures[index].copy(dest);
    }

    /**
     * Проходится по массику figures и проверяет, что фигура не занимает элементы из массива steps
     *
     * @param steps массив steps
     * @return если ячейки не заняты - true
     * @throws OccupiedCellException если ячейка занята
     */
    private boolean free(Cell[] steps) throws OccupiedCellException {
        for (Cell step : steps) {
            for (Figure figure : figures) {
                if (figure != null && figure.position().equals(step)) {
                    throw new OccupiedCellException();
                }
            }
        }
        return true;
    }

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException("Figure not found on the board.");
    }
}
