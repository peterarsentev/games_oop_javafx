package ru.job4j.puzzle.firuges;

public class Checker implements Figure {
    private final Cell position;

    public Checker(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if ((Math.abs(source.getX() - dest.getX())
                + Math.abs(source.getY() - dest.getY())) == 1) {
            steps = new Cell[]{dest};
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Checker(dest);
    }
}
