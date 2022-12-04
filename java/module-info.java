module com.example.demo {
	exports com.game.end;
	exports com.game.start;

	requires javafx.base;
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	


 opens com.game.start to javafx.fxml;


}