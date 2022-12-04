package com.game.end;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import com.game.account.Account;
import com.game.account.accountController;


public class EndGame {
    private static EndGame singleInstance = null;

    
    public EndGame(){
    	
    	
    }
    
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score){
        Text text = new Text("GAME OVER");
        text.relocate(137,80);
        text.setFont(Font.font(61));
        root.getChildren().add(text);

        //SCORE EDITING
        accountController.score=score;
        Text scoreText = new Text(score+"");
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(250,200);
        scoreText.setFont(Font.font(61));
        root.getChildren().add(scoreText);
        
        Button saveButton = new Button("CHECK SCORE");
        saveButton.setPrefSize(100,30);
        root.getChildren().add(saveButton);
        saveButton.relocate(250,250);
        
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/com/game/account/account.fxml"));
					Stage window = (Stage)saveButton.getScene().getWindow();
	            	window.setScene(new Scene(root));
				} catch (IOException e) {
					e.printStackTrace();
				}
            	
				
            	/*Account newUser = new Account(name.getText());
            	newUser.addToScore(score);
            	Text notify = new Text("Saved!");
            	notify.relocate(137,100);
                notify.setFont(Font.font(61));
                root.getChildren().add(notify);*/
				
            	//System.out.println("HELP");
            }
        });
        
        
        
        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(100,30);
        quitButton.setTextFill(Color.BLUE);
        root.getChildren().add(quitButton);
        //quitButton.setLayoutX(100);
        //quitButton.setLayoutY(80);
        quitButton.relocate(250,300);
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Quit Dialog");
                alert.setHeaderText("Quit from this page");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    root.getChildren().clear();
                    Platform.exit();
                }
            }
        });



    }


}
