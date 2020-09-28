package ru.job4j.packman.firuges;

public interface Figure {
    default boolean movable() {
        return true;
    }

    Cell position();

    Cell[] way(Cell dest);

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    Figure copy(Cell dest);

}
