package com.game.start;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Main class to start the application.
 * 
 * @author Theresa Lim - modified
 */
public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    
	/**
	 * This method loads the title scene of the game.
	 * 
	 * @param primaryStage An object of class Stage constructed by the platform, as the container of title scene.
	 * @throws Exception When file not found
	 */
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	Parent parentRoot = FXMLLoader.load(getClass().getResource("title.fxml"));
        primaryStage.setTitle("2048");
        primaryStage.setScene(new Scene(parentRoot));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    
    
    /**
     * This method launches the application.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}