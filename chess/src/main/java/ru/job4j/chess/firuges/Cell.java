package ru.job4j.chess.firuges;

public enum Cell {
    A1(0, 7), A2(0, 6), A3(0, 5), A4(0, 4), A5(0, 3), A6(0, 2), A7(0, 1), A8(0, 0),
    B1(1, 7), B2(1, 6), B3(1, 5), B4(1, 4), B5(1, 3), B6(1, 2), B7(1, 1), B8(1, 0),
    C1(2, 7), C2(2, 6), C3(2, 5), C4(2, 4), C5(2, 3), C6(2, 2), C7(2, 1), C8(2, 0),
    D1(3, 7), D2(3, 6), D3(3, 5), D4(3, 4), D5(3, 3), D6(3, 2), D7(3, 1), D8(3, 0),
    E1(4, 7), E2(4, 6), E3(4, 5), E4(4, 4), E5(4, 3), E6(4, 2), E7(4, 1), E8(4, 0),
    F1(5, 7), F2(5, 6), F3(5, 5), F4(5, 4), F5(5, 3), F6(5, 2), F7(5, 1), F8(5, 0),
    G1(6, 7), G2(6, 6), G3(6, 5), G4(6, 4), G5(6, 3), G6(6, 2), G7(6, 1), G8(6, 0),
    H1(7, 7), H2(7, 6), H3(7, 5), H4(7, 4), H5(7, 3), H6(7, 2), H7(7, 1), H8(7, 0);

    private final int x;
    private final int y;

    Cell(int cx, int cy) {
        x = cx;
        y = cy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Cell findBy(int x, int y) {
        Cell rsl = null;
        for (Cell cell : values()) {
            if (cell.x == x && cell.y == y) {
                rsl = cell;
                break;
            }
        }
        return rsl;
    }
}
