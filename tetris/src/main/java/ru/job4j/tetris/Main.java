package ru.job4j.tetris;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static final String JOB4J = "Tetris";
    private final int size = 10;
    private Figure figure = new Figure(
            List.of(new Cell(0, 0), new Cell(0, 1), new Cell(0, 2), new Cell(1, 2))
    );

    private List<Rectangle> blocks = new ArrayList<>();

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

    private void drawBlock(List<Cell> cells, Group group) {
        for (var block : blocks) {
            group.getChildren().remove(block);
        }
        blocks.clear();
        for (Cell cell : cells) {
            var block = block(cell, Color.BLACK);
            blocks.add(block);
            group.getChildren().add(block);
        }
    }

    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("Rotate");
        var grid = buildGrid();
        start.setOnMouseClicked(
                event -> {
                    figure.rotate();
                    drawBlock(figure.asCells(), grid);
                }
        );
        control.getChildren().addAll(start);
        border.setBottom(control);
        border.setCenter(grid);
        stage.setScene(new Scene(border, 500, 500));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        drawBlock(figure.asCells(), grid);
        new Thread(() -> {
            while (true) {
                figure.move();
                Platform.runLater(() -> drawBlock(figure.asCells(), grid));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }
}
