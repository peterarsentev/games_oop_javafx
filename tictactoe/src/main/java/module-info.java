module tictactoe {
    requires javafx.fxml;
    requires javafx.controls;
    opens job4j.tictactoe to javafx.fxml;
    exports job4j.tictactoe;
}