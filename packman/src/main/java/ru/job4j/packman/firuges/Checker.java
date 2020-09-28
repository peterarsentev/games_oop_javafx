package ru.job4j.packman.firuges;

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
    public Cell[] way(Cell dest) {
        Cell[] steps = new Cell[0];
        if ((Math.abs(position.getX() - dest.getX())
                + Math.abs(position.getY() - dest.getY())) == 1) {
            steps = new Cell[]{dest};
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Checker(dest);
    }
}
