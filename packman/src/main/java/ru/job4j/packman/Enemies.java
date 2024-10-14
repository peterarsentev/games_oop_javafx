package ru.job4j.packman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.HashSet;
import java.util.Set;

public class Enemies {
    record Enemy(int x, int y) {
    }
    public GraphicsContext gc;
    private final int width;
    private final int height;
    private final int playerSize;
    private final int enemySize;
    private final int enemySpeed;
    private final Set<Enemy> enemies = new HashSet<>();

    public Enemies(int width, int height, int playerSize, int enemySize, int enemySpeed) {
        this.width = width;
        this.height = height;
        this.playerSize = playerSize;
        this.enemySize = enemySize;
        this.enemySpeed = enemySpeed;
    }

    public void initializeEnemies(Walls walls) {
        int enemiesAdded = 0;
        for (int i = 1; i < walls.getMaze().length - 1 && enemiesAdded < 4; i++) {
            for (int j = 1; j < walls.getMaze()[i].length - 1 && enemiesAdded < 4; j++) {
                if (!walls.getMaze()[i][j] && (i + j) % 4 == 0) {
                    enemies.add(new Enemy(i * playerSize, j * playerSize));
                    enemiesAdded++;
                }
            }
        }
    }

    public void update(Pacman pacman, Walls walls) {
        Set<Enemy> updatedEnemies = new HashSet<>();

        for (var enemy : enemies) {
            double diffX = pacman.playerX - enemy.x();
            double diffY = pacman.playerY - enemy.y();

            int newX = enemy.x();
            int newY = enemy.y();

            boolean moved = false;

            // Prioritize movement based on same position on one axis
            if (enemy.x() == pacman.playerX) {
                // Same x position, move by y coordinate
                double nextY = enemy.y() + Math.signum(diffY) * enemySpeed;
                if (!isCollision(enemy.x(), nextY, walls) && !isEnemyCollision(newX, (int) nextY)) {
                    newY = (int) nextY;
                    moved = true;
                }
            } else if (enemy.y() == pacman.playerY) {
                // Same y position, move by x coordinate
                double nextX = enemy.x() + Math.signum(diffX) * enemySpeed;
                if (!isCollision(nextX, enemy.y(), walls) && !isEnemyCollision((int) nextX, newY)) {
                    newX = (int) nextX;
                    moved = true;
                }
            } else {
                // Standard movement logic based on smaller difference between x and y
                if (Math.abs(diffX) <= Math.abs(diffY)) {
                    // Move by x coordinate
                    double nextX = enemy.x() + Math.signum(diffX) * enemySpeed;
                    if (!isCollision(nextX, enemy.y(), walls) && !isEnemyCollision((int) nextX, newY)) {
                        newX = (int) nextX;
                        moved = true;
                    }
                } else {
                    // Move by y coordinate
                    double nextY = enemy.y() + Math.signum(diffY) * enemySpeed;
                    if (!isCollision(enemy.x(), nextY, walls) && !isEnemyCollision(newX, (int) nextY)) {
                        newY = (int) nextY;
                        moved = true;
                    }
                }
            }

            // If collision occurred or collided with another enemy, move backward
            if (!moved || isEnemyCollision(newX, newY)) {
                // Move backward in x direction
                if (Math.abs(diffX) <= Math.abs(diffY)) {
                    newX = enemy.x() - (int) Math.signum(diffX) * enemySpeed;
                } else {
                    // Move backward in y direction
                    newY = enemy.y() - (int) Math.signum(diffY) * enemySpeed;
                }
            }

            updatedEnemies.add(new Enemy(newX, newY));
        }

        enemies.clear();
        enemies.addAll(updatedEnemies);
    }

    private boolean isEnemyCollision(int x, int y) {
        for (Enemy other : enemies) {
            if (other.x() == x && other.y() == y) {
                return true;
            }
        }
        return false;
    }

    private boolean isCollision(double x, double y, Walls walls) {
        boolean[][] maze = walls.getMaze();
        int gridXStart = (int) (x / playerSize);
        int gridYStart = (int) (y / playerSize);
        int gridXEnd = (int) ((x + enemySize - 1) / playerSize);
        int gridYEnd = (int) ((y + enemySize - 1) / playerSize);

        if (gridXStart < 0 || gridXEnd >= maze.length || gridYStart < 0 || gridYEnd >= maze[0].length) {
            return true;
        }
        return maze[gridXStart][gridYStart] || maze[gridXEnd][gridYStart] || maze[gridXStart][gridYEnd] || maze[gridXEnd][gridYEnd];
    }

    public void draw() {
        gc.setFill(Color.RED);
        for (var enemy : enemies) {
            gc.fillArc(enemy.x(), enemy.y(), enemySize, enemySize, 0, 180, ArcType.ROUND);
            gc.fillRect(enemy.x(), enemy.y() + enemySize / 2, enemySize, enemySize / 2);
            gc.setFill(Color.WHITE);
            gc.fillOval(enemy.x() + enemySize * 0.2, enemy.y() + enemySize * 0.2, enemySize * 0.2, enemySize * 0.2);
            gc.fillOval(enemy.x() + enemySize * 0.6, enemy.y() + enemySize * 0.2, enemySize * 0.2, enemySize * 0.2);
            gc.setFill(Color.RED);
        }
    }
}
