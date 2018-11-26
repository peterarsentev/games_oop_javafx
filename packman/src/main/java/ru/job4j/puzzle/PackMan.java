package ru.job4j.puzzle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;
import ru.job4j.puzzle.firuges.Block;
import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Checker;
import ru.job4j.puzzle.firuges.Figure;

import java.util.Random;

public class PackMan extends Application {
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
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {
                    final Random rm = new Random();
                    int deltaX = 1;
                    int deltaY = 0;
                    double positionX  = rect.getLayoutX();
                    double positionY = rect.getLayoutY();

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (rect.getLayoutX() >= positionX + 40 || rect.getLayoutY() >= positionY + 40) {
                            int direction = rm.nextInt(4);
                            if (direction == 0) {
                                deltaX = 0;
                                deltaY = -1;
                            } else if (direction == 1) {
                                deltaX = 1;
                                deltaY = 0;
                            } else if (direction == 2) {
                                deltaX = 0;
                                deltaY = 1;
                            } else if (direction == 3) {
                                deltaX = -1;
                                deltaY = 0;
                            }
                        }
                        if (rect.getX() + deltaX * 50 > 0 && rect.getX() + 50 + deltaX * 50 < 250 &&
                                rect.getY() + deltaY * 50 > 0 && rect.getY() + 50 + deltaY * 50 < 250) {
                            if (deltaX != 0) {
                                rect.setLayoutX(rect.getLayoutX() + deltaX);
                            }
                            if (deltaY != 0) {
                                rect.setLayoutY(rect.getLayoutY() + deltaY);
                            }
                        }
                    }
                }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
//        new Thread(
//                () -> {
//                    this.logic.fill(extract((double) x, (double) y));
//                    final Random rm = new Random();
//                    while (!Thread.currentThread().isInterrupted()) {
//                        int direction = rm.nextInt(4);
//                        int deltaX = 0;
//                        int deltaY = 0;
//                        if (direction == 0) {
//                            deltaX = 0;
//                            deltaY = -1;
//                        } else if (direction == 1) {
//                            deltaX = 1;
//                            deltaY = 0;
//                        } else if (direction == 2) {
//                            deltaX = 0;
//                            deltaY = 1;
//                        } else if (direction == 3) {
//                            deltaX = -1;
//                            deltaY = 0;
//                        }
//                        try {
//                            Cell source = extract(rect.getX(), rect.getY());
//                            if (rect.getX() + deltaX * 50 > 0 && rect.getX() + 50 + deltaX * 50 < 250 &&
//                                    rect.getY() + deltaY * 50 > 0 && rect.getY() + 50 + deltaY * 50 < 250 &&
//                                    this.logic.move(extract(rect.getX() + deltaX * 40, rect.getY() + deltaY * 40))){
//                                for (int step = 0; step != 40; step++) {
//                                    if (deltaX != 0) {
//                                        rect.setX(rect.getX() + deltaX);
//                                    }
//                                    if (deltaY != 0) {
//                                        rect.setY(rect.getY() + deltaY);
//                                    }
//                                    Thread.sleep(20);
//                                }
//                                this.logic.clean(source);
//                            }
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
//                    }
//                }
//        ).start();
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
        border.setCenter(grid);
        this.add(new Block(new Cell(0, 0)), grid);
//        this.add(new Block(new Cell(4, 4)), grid);
    }


    public void add(Figure figure, Group grid) {
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
