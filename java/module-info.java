module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.game.start to javafx.fxml;
    exports com.game.start;
}