package ru.job4j.snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Cell> body = new ArrayList<>();
    private Cell apple;
    private Direction direction = Direction.RIGHT;

    public Snake(List<Cell> cells) {
        body.addAll(cells);
    }

    void apple(Cell cell) {
        apple = cell;
    }

    void directionTo(Direction direction) {
        this.direction = direction;
    }

    boolean step() {
         var tail = body.get(body.size() - 1);
         var next = switch (direction) {
             case RIGHT -> new Cell(tail.getX() + 1, tail.getY());
             case LEFT -> new Cell(tail.getX() - 1, tail.getY());
             case UP -> new Cell(tail.getX(), tail.getY() - 1);
             case DOWN -> new Cell(tail.getX(), tail.getY() + 1);
         };
         var eat = next.equals(apple);
         if (!eat) {
             body.remove(0);
         }
         body.add(next);
         return eat;
    }

    List<Cell> asCells() {
        return body;
    }
}
