package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;

import static org.junit.Assert.*;

public class BishopBlackTest {

    @Test
    public void whenPositionTrue() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        assertEquals(Cell.A1, bishop.position());
    }

    @Test
    public void whenCopyTrue() {
        BishopBlack bishop = new BishopBlack(Cell.A3);
        BishopBlack bishop1 = new BishopBlack(Cell.A1);
        bishop.copy(Cell.A3);
        assertEquals(bishop.position(), bishop1.copy(Cell.A3).position());
    }

    @Test
    public void whenWayTrue() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        Cell[] arr = new Cell[]{Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertArrayEquals(arr, bishop.way(Cell.G5));
    }
}