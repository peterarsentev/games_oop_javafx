package ru.job4j.packman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Walls {
    private static final int PLAYER_SIZE = 40;
    private final GraphicsContext gc;
    private final boolean[][] maze;

    public Walls(GraphicsContext gc, int width, int height, int playerSize) {
        this.gc = gc;
        this.maze = new boolean[width / playerSize][height / playerSize];
    }

    public void initializeMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i == 0 || i == maze.length - 1 || j == 0 || j == maze[i].length - 1) {
                    maze[i][j] = true;
                } else {
                    maze[i][j] = i % 2 == 0 && j % 2 == 0
                            && i != maze.length - 2
                            && j != maze[i].length - 2;
                }
            }
        }
    }

    public boolean[][] getMaze() {
        return maze;
    }

    public void draw() {
        gc.setFill(Color.BLUE);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j]) {
                    gc.fillRect(i * PLAYER_SIZE, j * PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
                }
            }
        }
    }
}
