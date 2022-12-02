package com.game.start;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button instructionBtn;

    @FXML
    private Button scoreBtn;

    @FXML
    private Button startBtn;
    
    @FXML
    private ColorPicker themeColour;
    
    @FXML
    private Pane gameBackground;
    
    private Stage primaryStage;
    private GameScene game;
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    private Color currentColour = Color.rgb(189, 177, 92);
    
    @FXML
    public void startGame(ActionEvent event){
    	System.out.println("help");
    	this.primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
    	//startBtn.getScene().getWindow().hide();    	
    	//FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.java"));  
        //Stage stage = new Stage();
        //stage.setScene(new Scene((Parent) loader.load()));
        //stage.show();
    	//Stage stage = new Stage();
    	changescene();
    	this.primaryStage.show();

    }
    
    private void changescene(){
    	this.game = new GameScene();
    	
    	 //Changes colour of game over scene
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));
        
        Group gameRoot = new Group();
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, this.currentColour);//colour of game screen
        
         
        this.game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);//method in gamescene
        this.primaryStage.setScene(gameScene);
         
    }
    
    public void changeTheme(ActionEvent event) {
    	this.currentColour = themeColour.getValue();
    	
    	BackgroundFill background_fill = new BackgroundFill(currentColour, CornerRadii.EMPTY, Insets.EMPTY);
    	Background background = new Background(background_fill);
    	
    	gameBackground.setBackground(background);
    	
    }
}