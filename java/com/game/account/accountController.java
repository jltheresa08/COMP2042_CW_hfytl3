package com.game.account;

import java.util.Optional;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * This class is the controller for account.fxml.
 * 
 * @author Theresa Lim
 */
public class accountController{

    @FXML
    private Button enterBtn, quitBtn;
    
    @FXML
    private TextField userName;
    
    @FXML
    private Label userBest;
    
    /**
     * Get the current score.
     */
    public static long score=0;
    
    /**
     * Get user name of current player.
     */
    public static String name;
    
    //static because need to be accessed by EndGame.java
    
    /**
     * This method notifies when new high score achieved.
     * High Score is compared in Account.java.
     * @see Account
     * 
     */
    public void loginUser() {
    	
    	name = userName.getText();

    	Account currentUser = new Account();

    	if(Account.newHighScore)
    		userBest.setText("New personal best!");
    	else
    	{
    		if(Account.newPlayer)
    			userBest.setText("Score is saved!");
    		else
    			userBest.setText("No change in score!");
    	}
    		
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