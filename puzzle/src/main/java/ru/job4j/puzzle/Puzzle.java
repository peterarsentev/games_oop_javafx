package ru.job4j.puzzle;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.job4j.puzzle.firuges.Block;
import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Checker;
import ru.job4j.puzzle.firuges.Figure;

import java.util.Random;

public class Puzzle extends Application {
    private static final String JOB4J = "Пазлы на www.job4j.ru";
    private final int size = 5;
    private final Logic logic = new Logic(size);

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

    private Rectangle buildFigure(int x, int y, int size, String image) {
        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setHeight(size);
        rect.setWidth(size);
        Image img = new Image(this.getClass().getClassLoader().getResource(image).toString());
        rect.setFill(new ImagePattern(img));
        final Rectangle momento = new Rectangle(x, y);
        rect.setOnDragDetected(
                event -> {
                    momento.setX(event.getX());
                    momento.setY(event.getY());
                }
        );
        rect.setOnMouseDragged(
                event -> {
                    rect.setX(event.getX() - size / 2);
                    rect.setY(event.getY() - size / 2);
                }
        );
        rect.setOnMouseReleased(
                event -> {
                    if (logic.move(this.extract(momento.getX(), momento.getY()), this.extract(event.getX(), event.getY()))) {
                        rect.setX(((int) event.getX() / 40) * 40 + 5);
                        rect.setY(((int) event.getY() / 40) * 40 + 5);
                        checkWinner();
                    } else {
                        rect.setX(((int) momento.getX() / 40) * 40 + 5);
                        rect.setY(((int) momento.getY() / 40) * 40 + 5);
                    }
                }
        );
        return rect;
    }

    private void checkWinner() {
        if (this.logic.isWin()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(JOB4J);
            alert.setHeaderText(null);
            alert.setContentText("Пазл решен! Начните новую Игру!");
            alert.showAndWait();
        }
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
        Button start = new Button("Начать");
        start.setOnMouseClicked(
                event -> this.refresh(border)
        );
        control.getChildren().addAll(start);
        border.setBottom(control);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border, 400, 400));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        this.refresh(border);
    }

    private void refresh(final BorderPane border) {
        Group grid = this.buildGrid();
        this.logic.clean();
        border.setCenter(grid);
        this.generate(true, 6, grid);
        this.generate(false, 5, grid);
    }

    public void generate(boolean block, int total,  Group grid) {
        int count = total;
        final Random random = new Random();
        while (count > 0) {
            Cell position = new Cell(random.nextInt(size), random.nextInt(size));
            if (this.logic.isFree(position)) {
                if (block) {
                    this.add(new Block(position), grid);
                } else {
                    this.add(new Checker(position), grid);
                }
                count--;
            }
        }
    }

    public void add(Figure figure, Group grid) {
        this.logic.add(figure);
        Cell position = figure.position();
        grid.getChildren().add(
                this.buildFigure(
                        position.x * 40 + 5,
                        position.y * 40 + 5,
                        30,
                        figure.icon()
                )
        );
    }

    private Cell extract(double graphX, double graphY) {
        return new Cell((int) graphX / 40, (int) graphY / 40);
    }
}
