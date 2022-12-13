package com.game.account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * This class is the controller for scoreboard.fxml.
 * 
 * @author Theresa Lim
 */
public class scoreboardController {

	@FXML
	private Button titleBtn;
	
	@FXML
	private TableView<Account> tableview;

    @FXML
    private TableColumn<Account, Long> tableScore;

    @FXML
    private TableColumn<Account, String> tableUser;
    
    ArrayList<Account> savedAccounts = new ArrayList<>();
    
    /**
     * This method loads title screen.
     * 
     * @param event Runs on BACK button click.
     * @throws IOException if file not found.
     */
    public void showTitle(ActionEvent event) throws IOException {
		
		Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/com/game/start/title.fxml"));
		stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
	}
    
    /**
     * This method saves data into the table.
     * 
     * @throws FileNotFoundException
     */
    public void initialize() throws FileNotFoundException {
    	
    	tableUser.setCellValueFactory(new PropertyValueFactory<>("UserName"));
    	tableScore.setCellValueFactory(new PropertyValueFactory<>("Score"));
    	getDataFromFile();
    	tableview.getColumns().addAll(tableUser, tableScore);
    }

   
    /**
     * This method retrieves all accounts saved in file.
     * 
     * @throws FileNotFoundException
     */
    public void getDataFromFile() {
    	
    	String[] userNscore;
		Scanner getUserList;
		
		tableview.getColumns().clear();
		
		try 
		{
			File file = new File("accounts.txt");
			
			if(file.exists() && !file.isDirectory()) 
			{ 
				getUserList = new Scanner(file);
				
				
				while(getUserList.hasNextLine())
				{
					String data = getUserList.nextLine();
					userNscore = data.split(",");
					tableview.getItems().add(new Account(userNscore[0],Long.parseLong(userNscore[1])));
				}
			}

		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
    }

}
