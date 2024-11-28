package ru.job4j.packman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class PacmanGame extends Application {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 40;
    private static final int PELLET_SIZE = 10;
    private static final int ENEMY_SIZE = 40;
    private static final int ENEMY_SPEED = 2;

    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private Walls walls;
    private Pellets pellets;
    private Enemies enemies;
    private Pacman pacman;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pac-Man Game");
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(new StackPane(canvas));
        primaryStage.setScene(scene);
        primaryStage.show();

        walls = new Walls(gc, WIDTH, HEIGHT, PLAYER_SIZE);
        walls.initializeMaze();

        pellets = new Pellets(gc, WIDTH, HEIGHT, PLAYER_SIZE, PELLET_SIZE);
        pellets.initializePellets(walls);

        enemies = new Enemies(gc, PLAYER_SIZE, ENEMY_SIZE, ENEMY_SPEED);
        enemies.initializeEnemies(walls);

        pacman = initializePlayerPosition(gc);

        scene.setOnKeyPressed(event -> pressedKeys.add(event.getCode()));
        scene.setOnKeyReleased(event -> pressedKeys.remove(event.getCode()));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw(gc);
            }
        };
        timer.start();
    }

    private Pacman initializePlayerPosition(GraphicsContext gc) {
        for (int i = 0; i < walls.getMaze().length; i++) {
            for (int j = 0; j < walls.getMaze()[i].length; j++) {
                if (!walls.getMaze()[i][j]) {
                    return new Pacman(gc,
                            i * PLAYER_SIZE,
                            j * PLAYER_SIZE
                    );
                }
            }
        }
        throw new IllegalStateException("Cannot initialize position for Pacman.");
    }

    private void update() {
        pacman.update(pressedKeys, walls);
        enemies.update(pacman, walls);
        pellets.checkCollision(pacman);
    }

    private void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        walls.draw();
        pellets.draw();
        enemies.draw();
        pacman.draw();
    }
}
