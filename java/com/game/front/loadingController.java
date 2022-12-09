package com.game.start;


import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * This class is the controller for loading.fxml.
 * 
 * @author Theresa Lim
 */
public class loadingController implements Initializable{

	@FXML
	private ProgressBar loadingBar;
	
	@FXML
	private AnchorPane pane;
	
		/**
		 * This method runs the loading bar.
		 */
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			loadingBar.setStyle("-fx-accent: #f6d0d8;");
			
			loadingBar.setProgress(0);
			Timeline timeline = new Timeline();
	        KeyValue keyValue = new KeyValue(loadingBar.progressProperty(),1);
	        
	        EventHandler<ActionEvent> onFinished = e->{
	        	try {
					loadTitle(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        };
	        KeyFrame keyFrame = new KeyFrame(new Duration(3000), onFinished, keyValue);
	        
	        timeline.getKeyFrames().add(keyFrame);
	        timeline.play();
	        
		}
		
		/**
		 * This method loads the title screen.
		 * 
		 * @param event
		 * @throws IOException if file not found.
		 */
		public void loadTitle(ActionEvent event) throws IOException {
			
			Stage stage = (Stage) pane.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("title.fxml"));
			stage.setScene(new Scene(root));
	        stage.setResizable(false);
	        stage.show();
	        
		}
		
		
}
