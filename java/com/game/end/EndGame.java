package com.game.end;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.game.account.accountController;


/**
 * This class loads the Game Over scene.
 * 
 * @author Theresa Lim -modified
 */
public class EndGame {
    private static EndGame singleInstance = null;

    /**
     * Constructor for EndGame class.
     */
    public EndGame(){
    }
    
    /**
     * This method creates a new EndGame object once.
     * 
     * @return EndGame object.
     */
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }
    
    /**
     * This method loads the Game Over scene.
     * 
     * @param endGameScene Container for Game Over scene.
     * @param root Group of components to be displayed on Game Over scene.
     * @param primaryStage Container for a scene.
     * @param score Current score of player.
     */
    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score){
  	
    	endgameController.currentScore=score;
    	accountController.score=score;
    
    	Parent parentRoot;
    	
		try 
		{
			
			parentRoot = FXMLLoader.load(getClass().getResource("endgame.fxml"));
	        primaryStage.setScene(new Scene(parentRoot));
	        primaryStage.setResizable(false);
	        primaryStage.show();
	    	
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
       
    }

}
