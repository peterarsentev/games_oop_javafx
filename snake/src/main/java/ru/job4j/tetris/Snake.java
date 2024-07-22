package ru.job4j.tetris;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Snake {
    private final List<Cell> body = new CopyOnWriteArrayList<>();
    private volatile Cell apple;
    private volatile Direction direction = Direction.RIGHT;

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
             case RIGHT -> new Cell(tail.x() + 1, tail.y());
             case LEFT -> new Cell(tail.x() - 1, tail.y());
             case UP -> new Cell(tail.x(), tail.y() - 1);
             case DOWN -> new Cell(tail.x(), tail.y() + 1);
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
