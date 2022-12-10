package com.game.start;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is the controller for sample.fxml.
 * 
 * @author Theresa Lim - modified
 */
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
    private GameScene game;
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    private String[] mode = {"Default (4x4)", "5x5 Mode"};
    private Color currentColour = Color.rgb(189, 177, 92);

    
    /**
     * This method sets the stage and loads the scene.
     * 
     * @param event Runs on startBtn click.
     */
    public void startGame(ActionEvent event){
    	this.primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
    	changescene();
    	this.primaryStage.show();

    }
    /**
     * This method loads the game scene.
     */
    private void changescene(){
    	this.game = new GameScene();
    	
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, 600, 400, Color.rgb(250, 20, 100, 0.2));
        
        Group gameRoot = new Group();
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, this.currentColour);//colour of game screen
        
         
        this.game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);//method in gamescene
        this.primaryStage.setScene(gameScene);
         
    }
    
    /**
     * This method changes the colour of background of game.
     */
    public void changeTheme() {
    	this.currentColour = themeColour.getValue();
    	
    	BackgroundFill background_fill = new BackgroundFill(currentColour, CornerRadii.EMPTY, Insets.EMPTY);
    	Background background = new Background(background_fill);
    	
    	gameBackground.setBackground(background);
    	
        
    }
    
    /**
     * This method initializes the values in choice box.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		gameMode.getItems().addAll(mode);
		gameMode.setValue("Default (4x4)");
		gameMode.setOnAction(this::changeMode);
	}
    
	/**
	 * This method sets game mode based on choice box selection.
	 * 
	 * @param event On selection in choice box
	 */
	public void changeMode(ActionEvent event) {
		String mode = gameMode.getValue();
		
		if(mode=="5x5 Mode") {
			GameScene.setN(5);
		}

	}
	

}