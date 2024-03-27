package ru.job4j.packman;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PackMan extends Application {
    private static final String JOB4J = "Snake";
    private final int size = 10;
    private List<Rectangle> snake = new ArrayList<>();

    enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    private volatile Direction direction = Direction.RIGHT;

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

    private Rectangle block(int x, int y) {
        Rectangle rect = new Rectangle();
        rect.setX(x * 40 + 5);
        rect.setY(y * 40 + 5);
        rect.setHeight(30);
        rect.setWidth(30);
        Image img = new Image(this.getClass().getClassLoader().getResource("Block.png").toString());
        rect.setFill(new ImagePattern(img));
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
        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);
        var right = new Button("Right");
        right.setOnMouseClicked(
                event -> direction = Direction.RIGHT
        );
        var left = new Button("Left");
        left.setOnMouseClicked(
                event -> direction = Direction.LEFT
        );
        var up = new Button("Up");
        up.setOnMouseClicked(
                event -> direction = Direction.UP
        );
        var down = new Button("Down");
        down.setOnMouseClicked(
                event -> direction = Direction.DOWN
        );
        var moveBtn = new Button("Move");
        moveBtn.setOnMouseClicked(
                event -> move()
        );
        control.getChildren().addAll(right, left, up, down, moveBtn);
        border.setBottom(control);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border, 600, 600));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        this.refresh(border);
    }

    private void refresh(final BorderPane border) {
        Group grid = this.buildGrid();
        border.setCenter(grid);
        IntStream.range(0, 8).forEach(
                el -> snake.add(block(el, 0))
        );
        grid.getChildren().addAll(snake);
    }

    private void move() {
        var it = snake.listIterator();
        while (it.hasNext()) {
            var el = it.next();
            if (it.hasNext()) {
                var next = it.next();
                el.setY(next.getY());
                el.setX(next.getX());
                it.previous();
            } else {
                switch (direction) {
                    case RIGHT -> el.setX(el.getX() + 40);
                    case LEFT -> el.setX(el.getX() - 40);
                    case UP -> el.setY(el.getY() - 40);
                    case DOWN -> el.setY(el.getY() + 40);
                    default -> throw new RuntimeException("Not found direction!");
                }
            }
        }
    }
}
