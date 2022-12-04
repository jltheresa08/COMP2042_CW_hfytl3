package com.game.start;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller implements Initializable {


    @FXML
    private Button startBtn;
    
    @FXML
    private ColorPicker themeColour;
    
    @FXML
    private ChoiceBox<String> gameMode;
    
    @FXML
    private Pane gameBackground;
    
    
    private Stage primaryStage;
    private Stage stage;
    private GameScene game;
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    private String[] mode = {"Default (4x4)", "5x5 Mode"};
    private Color currentColour = Color.rgb(189, 177, 92);
    private boolean loggedin = false;
    
    //@FXML
    public void startGame(ActionEvent event){
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
        Scene endGameScene = new Scene(endgameRoot, 600, 400, Color.rgb(250, 20, 100, 0.2));
        
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
    
  
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		gameMode.getItems().addAll(mode);
		gameMode.setValue("Default (4x4)");
		gameMode.setOnAction(this::changeMode);
	}
    
	public void changeMode(ActionEvent event) {
		String mode = gameMode.getValue();
		
		if(mode=="5x5 Mode") {
			GameScene.setN(5);
		}

	}
	

}