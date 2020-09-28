package ru.job4j.tictactoe;

import ru.job4j.tictactoe.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    private final List<Ship> ships = new ArrayList<>();

    public void add(Ship ship) {
        this.ships.add(ship);
    }
}
