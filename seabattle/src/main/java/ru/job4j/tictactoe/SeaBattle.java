package ru.job4j.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SeaBattle extends Application {
    private static final String JOB4J = "Морской бой www.job4j.ru";
    private final int size = 10;
    private final Logic logic = new Logic();

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

    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                panel.getChildren().add(this.buildRectangle(x, y, 25));
            }
        }
        return panel;
    }

    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40F);
        control.setSpacing(10F);
        control.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("Начать");
        HBox center = new HBox();
        center.setPadding(new Insets(15, 12, 15, 12));
        center.setSpacing(10.0);
        center.setAlignment(Pos.BASELINE_CENTER);
        border.setCenter(center);
        border.setBottom(control);
        Group board = this.buildGrid();
        center.getChildren().add(board);
        center.getChildren().add(this.buildGrid());
        control.getChildren().addAll(start);
        border.setBottom(control);
        stage.setScene(new Scene(border, 600, 300));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        this.buildShip(board, 4, 0, 0);
        this.buildShip(board, 3, 0, 50);
        this.buildShip(board, 3, 0, 100);
        this.buildShip(board, 2, 0, 150);
        this.buildShip(board, 2, 0, 200);
        this.buildShip(board, 2, 200, 0);
        this.buildShip(board, 1, 200, 50);
        this.buildShip(board, 1, 200, 100);
        this.buildShip(board, 1, 200, 150);
        this.buildShip(board, 1, 200, 200);
    }

    private void buildShip(Group board, int desk, int startX, int startY) {
        Rectangle rect = new Rectangle();
        rect.setX(startX);
        rect.setY(startY);
        rect.setHeight(25);
        rect.setWidth(desk * 25);
        rect.setFill(Color.BLACK);
        rect.setOnMouseDragged(
                event -> {
                    rect.setX(event.getX());
                    rect.setY(event.getY());
                }
        );
        rect.setOnMouseReleased(
                event -> {
                    rect.setX((((int) event.getX() / 25) * 25));
                    rect.setY(((int) event.getY() / 25) * 25);
                }
        );
        rect.setOnMouseClicked(
                event -> {
                    if (event.getButton() != MouseButton.PRIMARY) {
                        Rectangle momento = new Rectangle(rect.getX(),
                                rect.getY(), rect.getWidth(), rect.getHeight());
                        rect.setWidth(momento.getHeight());
                        rect.setHeight(momento.getWidth());
                    }
                }
        );
        board.getChildren().add(rect);
    }
}
