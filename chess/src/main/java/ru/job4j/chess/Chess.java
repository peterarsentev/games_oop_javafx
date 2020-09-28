package ru.job4j.chess;

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
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.*;
import ru.job4j.chess.firuges.white.*;

public final class Chess extends Application {
    private static final String JOB4J = "Шахматы на www.job4j.ru";
    private final int size = 8;
    private final Logic logic = new Logic();

    private Rectangle buildRectangle(int x, int y, int size, boolean white) {
        Rectangle rect = new Rectangle();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        if (white) {
            rect.setFill(Color.WHITE);
        } else {
            rect.setFill(Color.GRAY);
        }
        rect.setStroke(Color.BLACK);
        return rect;
    }

    private Rectangle buildFigure(int x, int y, int size, String image) {
        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setHeight(size);
        rect.setWidth(size);
        Image img = new Image(getClass().getClassLoader().getResource(image).toString());
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
                    try {
                        logic.move(
                                findBy(momento.getX(), momento.getY()),
                                findBy(event.getX(), event.getY()));
                        rect.setX(((int) event.getX() / 40) * 40 + 5);
                        rect.setY(((int) event.getY() / 40) * 40 + 5);
                    } catch (Exception e) {
                        Alert info = new Alert(Alert.AlertType.ERROR);
                        info.setContentText(e.getClass().getName() +  " "  + e.getMessage());
                        info.show();
                        rect.setX(((int) momento.getX() / 40) * 40 + 5);
                        rect.setY(((int) momento.getY() / 40) * 40 + 5);
                    }
                }
        );
        return rect;
    }

    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != size; y++) {
            for (int x = 0; x != size; x++) {
                panel.getChildren().add(
                        buildRectangle(x, y, 40, (x + y) % 2 == 0)
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
                event -> refresh(border)
        );
        control.getChildren().addAll(start);
        border.setBottom(control);
        border.setCenter(buildGrid());
        stage.setScene(new Scene(border, 400, 400));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        refresh(border);
    }

    private void refresh(final BorderPane border) {
        Group grid = buildGrid();
        logic.clean();
        border.setCenter(grid);
        buildWhiteTeam(grid);
        buildBlackTeam(grid);
    }

    private void buildBlackTeam(Group grid) {
        add(new PawnBlack(Cell.A7), grid);
        add(new PawnBlack(Cell.B7), grid);
        add(new PawnBlack(Cell.C7), grid);
        add(new PawnBlack(Cell.D7), grid);
        add(new PawnBlack(Cell.E7), grid);
        add(new PawnBlack(Cell.F7), grid);
        add(new PawnBlack(Cell.G7), grid);
        add(new PawnBlack(Cell.H7), grid);
        add(new RookBlack(Cell.A8), grid);
        add(new KnightBlack(Cell.B8), grid);
        add(new BishopBlack(Cell.C8), grid);
        add(new QueenBlack(Cell.D8), grid);
        add(new KingBlack(Cell.E8), grid);
        add(new BishopBlack(Cell.F8), grid);
        add(new KnightBlack(Cell.G8), grid);
        add(new RookBlack(Cell.H8), grid);
    }

    public void buildWhiteTeam(Group grid) {
        add(new PawnWhite(Cell.A2), grid);
        add(new PawnWhite(Cell.B2), grid);
        add(new PawnWhite(Cell.C2), grid);
        add(new PawnWhite(Cell.D2), grid);
        add(new PawnWhite(Cell.E2), grid);
        add(new PawnWhite(Cell.F2), grid);
        add(new PawnWhite(Cell.G2), grid);
        add(new PawnWhite(Cell.H2), grid);
        add(new RookWhite(Cell.A1), grid);
        add(new KnightWhite(Cell.B1), grid);
        add(new BishopWhite(Cell.C1), grid);
        add(new QueenWhite(Cell.D1), grid);
        add(new KingWhite(Cell.E1), grid);
        add(new BishopWhite(Cell.F1), grid);
        add(new KnightWhite(Cell.G1), grid);
        add(new RookWhite(Cell.H1), grid);
    }

    public void add(Figure figure, Group grid) {
        logic.add(figure);
        Cell position = figure.position();
        grid.getChildren().add(
                buildFigure(
                        position.getX() * 40 + 5,
                        position.getY() * 40 + 5,
                        30,
                        figure.icon()
                )
        );
    }

    private Cell findBy(double graphX, double graphY) {
        Cell rst = Cell.A1;
        int x = (int) graphX / 40;
        int y = (int) graphY / 40;
        for (Cell cell : Cell.values()) {
            if (cell.getX() == x && cell.getY() == y) {
                rst = cell;
                break;
            }
        }
        return rst;
    }
}
