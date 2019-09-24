module puzzle {
    requires javafx.fxml;
    requires javafx.controls;
    opens ru.job4j.puzzle to javafx.fxml;
    exports ru.job4j.puzzle;
}