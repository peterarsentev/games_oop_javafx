package ru.job4j.packman;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CatchBall extends Application {

    @Override
    public void start(Stage stage) {
        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 500, 500);
        Circle ball = new Circle(10, Color.BLACK);
        ball.setLayoutX(10);
        ball.setLayoutY(10);
        canvas.getChildren().add(ball);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("CatchBall");
        new Thread(() -> {
            var direction = true;
            while (true) {
                if (direction) {
                    ball.setCenterX(ball.getCenterX() + 1);
                } else {
                    ball.setCenterX(ball.getCenterX() - 1);
                }
                if (ball.getCenterX() + 5 > 500) {
                    direction = false;
                }
                if (ball.getCenterX() - 5 < 0) {
                    direction = true;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
