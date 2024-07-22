package ru.job4j.tetris;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    private static final String JOB4J = "Snake";
    private final int size = 10;
    private final Snake snake = new Snake(List.of(
            new Cell(0, 0), new Cell(1, 0), new Cell(2, 0))
    );
    private final List<Rectangle> snakePosition = new ArrayList<>();
    private Rectangle applePosition;

    private Rectangle buildRectangle(int x, int y, int size) {
        Rectangle rect = new Rectangle();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    private Rectangle block(Cell cell, Color color) {
        Rectangle rect = new Rectangle();
        rect.setX(cell.x() * 40 + 5);
        rect.setY(cell.y() * 40 + 5);
        rect.setHeight(30);
        rect.setWidth(30);
        rect.setFill(color);
        return rect;
    }

    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                panel.getChildren().add(
                        this.buildRectangle(x, y, 40)
                );
            }
        }
        return panel;
    }

    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            switch (event.getCode().getName()) {
                case "Right" : snake.directionTo(Direction.RIGHT); break;
                case "Up" : snake.directionTo(Direction.UP); break;
                case "Left" : snake.directionTo(Direction.LEFT); break;
                default: snake.directionTo(Direction.DOWN); break;
            }
        });
        var grid = buildGrid();
        border.setCenter(grid);
        stage.setScene(new Scene(border, 500, 500));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        drawSnake(grid);
        drawApple(grid);
        stage.show();
        new Thread(
                () -> {
                    try {
                        while (true) {
                            var eat = snake.step();
                            Platform.runLater(() -> {
                                if (eat) {
                                    redrawApple(grid);
                                }
                                redrawSnake(grid);
                            });
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).start();
    }

    private void drawSnake(Group grid) {
        for (Cell body : snake.asCells()) {
            var el = block(body, Color.BLACK);
            snakePosition.add(el);
            grid.getChildren().add(el);
        }
    }

    private void drawApple(Group grid) {
        var cell = genApple();
        snake.apple(cell);
        applePosition = block(cell, Color.RED);
        grid.getChildren().add(applePosition);
    }

    private void redrawApple(Group group) {
        group.getChildren().removeAll(applePosition);
        drawApple(group);
    }

    private Cell genApple() {
        var rm = new Random();
        var x = rm.nextInt(9);
        var y = rm.nextInt(9);
        return new Cell(x, y);
    }

    private void redrawSnake(Group group) {
        group.getChildren().removeAll(snakePosition);
        drawSnake(group);
    }

    
    public static void main(String[] args) {
        Main.launch(args);
    }
}
