package ru.job4j.puzzle;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimatedCircleOnCanvas extends Application {
    public static final double W = 200; // canvas dimensions.
    public static final double H = 200;

    public static final double D = 20;  // diameter.

    @Override public void start(Stage stage) {
        DoubleProperty x  = new SimpleDoubleProperty();
        DoubleProperty y  = new SimpleDoubleProperty();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(x, 0),
                        new KeyValue(y, 0)
                ),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(x, W - D),
                        new KeyValue(y, H - D)
                )
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);

        final Canvas canvas = new Canvas(W, H);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.CORNSILK);
                gc.fillRect(0, 0, W, H);
                gc.setFill(Color.FORESTGREEN);
                gc.fillOval(
                        x.doubleValue(),
                        y.doubleValue(),
                        D,
                        D
                );
            }
        };

        stage.setScene(
                new Scene(
                        new Group(
                                canvas
                        )
                )
        );
        stage.show();

        timer.start();
        timeline.play();
    }
}
