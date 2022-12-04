package com.game.account;

import java.io.IOException;
import java.util.ArrayList;

import com.game.end.EndGame;
import com.game.start.Controller;
import com.game.start.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class accountController{

    @FXML
    private Button enterBtn;
    
    @FXML
    private TextField userName;
    
    @FXML
    private Label userBest;
    
    
    public static long score=0;
    public static String name;
    
    //static because need to be accessed by EndGame.java
    
    public void loginUser(ActionEvent event) throws IOException {
    	
    	name = userName.getText();
   
    	new Account();
    	
    	if(Account.highscoreChanged)
    	{
    		userBest.setText("New personal best!");
    	}
    	
    	
    }

    
}