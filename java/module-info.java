/**
 * Defines the implementation of 2048 game in Java starting with 
 * {@link com.game.start.Main}
 * 
 * @author Theresa Lim - modified
 */
module com.example.demo {
	exports com.game.end;
	exports com.game.start;
	exports com.game.front;
	exports com.game.account;

	requires javafx.base;
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires junit;

	opens com.game.start to javafx.fxml;
	opens com.game.front to javafx.fxml;
	opens com.game.account to javafx.fxml;
	opens com.game.end to javafx.fxml;

}