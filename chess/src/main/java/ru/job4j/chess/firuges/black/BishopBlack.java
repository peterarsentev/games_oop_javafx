package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    /**
     * Строит путь от начальной точки до конечной
     * @param dest до куда идет фигура
     * @return шаги до точки
     * @throws ImpossibleMoveException исключение, если пользователь сделал неверный ход
     */
    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not way by diagonal from %s to %s", position, dest)
            );
        }
        int positionX = position().getX();
        int positionY = position().getY();
        int destX = dest.getX();
        int destY = dest.getY();
        int size = Math.abs(positionX - destX);
        Cell[] steps = new Cell[size];
        int deltaX = positionX - destX > 0 ? -1 : 1;
        int deltaY = positionY - destY > 0 ? -1 : 1;
        for (int index = 0; index < size; index++) {
            positionX += deltaX;
            positionY += deltaY;
            steps[index] = Cell.findBy(positionX, positionY);
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
