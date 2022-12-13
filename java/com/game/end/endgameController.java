package com.game.end;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This class is the controller for endgame.fxml.
 * 
 * @author Theresa Lim
 */
public class endgameController {

	@FXML
	private Button saveBtn, quitBtn;
	
	@FXML
	private Label score;
	
	/**
	 *  Get the current score.
	 */
	public static long currentScore = 0;
	
	/**
	 * Display current score.
	 */
	public void initialize() {
		score.setText(Long.toString(currentScore));
	}
		
	/**
	 * This method opens account page.
	 * 
	 * @param event Runs on SAVE button click.
	 * @throws IOException if file not found.
	 */
	public void saveScore(ActionEvent event) throws IOException {
		
		Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/com/game/account/account.fxml"));
		stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
	}
	
	/**
	 * This method closes the game window on button click.
	 */
	public void closeGame() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Dialog");
        alert.setHeaderText("Quit from this page");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }
	}

}
