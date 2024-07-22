package ru.job4j.tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Figure {
    private List<Cell> body;

    public Figure(List<Cell> cells) {
        this.body = new ArrayList<>(cells);
    }
    
    public void rotate() {
       
    }

    public void move() {
        List<Cell> newBody = new ArrayList<>();
        for (Cell cell : body) {
            newBody.add(new Cell(cell.x(), cell.y() + 1));
        }
        body = newBody;
    }


    List<Cell> asCells() {
        return body;
    }
}
