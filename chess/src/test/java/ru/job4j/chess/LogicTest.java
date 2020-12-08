package ru.job4j.chess;


import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.RookBlack;



public class LogicTest {

    @Test(expected = FigureNotFoundException.class)
    public void figureNotFound()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.D1));
        logic.move(Cell.C1, Cell.H6);
    }

    @Test(expected = OccupiedCellException.class)
    public void cellIsOccupied()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new RookBlack(Cell.B2));
        logic.move(Cell.C1, Cell.A3);
    }

    @Test(expected = IllegalStateException.class)
    public void moveIsImpossible()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException, IllegalStateException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.move(Cell.C1, Cell.C6);
    }
}