package ru.job4j.puzzle.firuges;

public class Block implements Figure {
    private final Cell position;

    public Block(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell dest) {
        return new Cell[0];
    }

    @Override
    public Figure copy(Cell dest) {
        throw new IllegalStateException("Block could not move.");
    }

    @Override
    public boolean movable() {
        return false;
    }
}
