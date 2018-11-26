package ru.job4j.puzzle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovingBall extends Application{

    @Override
    public void start(Stage stage) {
        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 300, 300);
        buildBall(canvas, -1);
        buildBall(canvas, -10);
        stage.setTitle("Moving Ball");
        stage.setScene(scene);
        stage.show();
    }

    private static void buildBall(Pane canvas, int delta) {
        Circle ball = new Circle(10, Color.BLACK);
        ball.relocate(0, 10);
        canvas.getChildren().add(ball);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),
                event -> {

                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }
}
