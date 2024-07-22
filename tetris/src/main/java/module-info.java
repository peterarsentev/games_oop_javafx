module tictactoe {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens ru.job4j.tetris to javafx.fxml;
    exports ru.job4j.tetris;
}