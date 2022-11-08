package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException() {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenOccupiedCellException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.D2));
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.C1, Cell.G5);
        });
        assertThat(exception.getMessage()).isNull();
    }

    @Test
    public void whenImpossibleMoveException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C2));
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(Cell.C2, Cell.G5);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not way by diagonal from C2 to G5");
    }
}