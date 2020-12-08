package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopBlackTest {
    @Test
    public void positionBishop() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A3);
        Cell rsl = bishopBlack.position();
        assertThat(rsl, is(Cell.A3));
    }

    @Test
    public void copyBishop() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A3);
        Figure rsl = bishopBlack.copy(Cell.B2);
        assertThat(rsl.position(), is(Cell.B2));
    }

    @Test
    public void wayBishop() throws ImpossibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] rsl = bishopBlack.way(Cell.G5);
        assertThat(rsl, is(new Cell[]{Cell.D2, Cell.E3, Cell.F4, Cell.G5}));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void wayBishopFalse()
            throws ImpossibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        bishopBlack.way(Cell.G4);
    }
}
