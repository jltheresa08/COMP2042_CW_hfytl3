module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.game.start to javafx.fxml;
    exports com.game.start;
}