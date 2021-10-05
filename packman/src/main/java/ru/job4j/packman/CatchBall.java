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
        Circle ball = new Circle(10, Color.RED);
        ball.setLayoutX(10);
        ball.setLayoutY(10);
        canvas.getChildren().add(ball);
        List<Circle> apples = generateApples();
        canvas.getChildren().addAll(apples);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("PackMan minimal");
    }

    private List<Circle> generateApples() {
        List<Circle> apples = new ArrayList<>();
        Random rn = new Random();
        for (int i = 0; i < 10; i++) {
            Circle apple = new Circle(10, Color.GREEN);
            apple.relocate(rn.nextInt(500), rn.nextInt(500));
            apples.add(apple);
        }
        return apples;
    }

    private void catchEnemy(Circle hero,  List<Circle> apples, Pane canvas) {
        double heroX = hero.getTranslateX() + hero.getCenterX();
        double heroY = hero.getTranslateY() + hero.getCenterY();
        for (Circle apple : apples) {
            double enemyX = apple.getLayoutX() - apple.getRadius() / 2;
            double enemyY = apple.getLayoutY() - apple.getRadius() / 2;
            if (heroX + hero.getRadius() >= enemyX
                    && heroX <= enemyX
                    && heroY + hero.getRadius() >= enemyY
                    && heroY <= enemyY) {
                canvas.getChildren().remove(apple);
                break;
            }
        }
    }
}
