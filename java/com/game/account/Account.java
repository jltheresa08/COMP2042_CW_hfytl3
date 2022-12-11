package com.game.account;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * Object of this class represents player's account
 * 
 * @author Theresa Lim - modified
 */
public class Account implements Comparable<Account> {
    private long score = 0;
	private String userName;
    private Account account;
    private String fileName = "accounts.txt";
    private File file;
    private static ArrayList<Account> accounts = new ArrayList<>();
    
	/**
	 * Check if player has saved score before.
	 */  
    public static boolean newPlayer;

    /**
     * This constructor saves player's user name and score.
     * 
     * @param userName Player's user name.
     * @param score Player's score of current round.
     */
    public Account(String userName, long score) {
	    	
    	this.userName=userName;
	   	this.score=score;

    }
    
    /**
     * This constructor runs through process of checking and saving player's user name and score.
     * Accounts previously saved are added into empty ArrayList.
     * ArrayList is cleared and re-added with new list after saving new details.
     */
    public Account(){
    	
    	this(accountController.name,accountController.score);
    	
    	setFileName(fileName);
    	
    	try 
    	{
			createFile(file);
			//accounts.clear();
			getUserList();
		} 
    	catch (IOException e) 
    	{
			
			e.printStackTrace();
		}
    	
        if(accountHaveBeenExist(userName)==null)
        {
        	account = makeNewAccount(userName);
        	newPlayer = true;
        	
        	try 
        	{
				addUser(account);
			}
        	catch (IOException e)
        	{
				e.printStackTrace();
			}

        }
        else 
        {
        	account = accountHaveBeenExist(userName);
        	newPlayer = false;
        	
        	try
        	{
				updateScore(account);
			}
        	catch (IOException e)
        	{
				e.printStackTrace();
			}
 
        }
        
        try
    	{
        	//accounts.clear();
			getUserList();
		}
    	catch (FileNotFoundException e)
    	{
			e.printStackTrace();
		}
        
        sortByScore();
    }
    
    
    /**
     * This method compares high scores of all player's saved.
     * 
     * @param user Current Player's account.
     * @return the higher score.
     */
    @Override
    public int compareTo(Account user) {
        return Long.compare(user.getScore(), score);
    }
    
    /**
     * This method represents Account objects in String.
     * @return Player's user name and score in string.
     */
    @Override
    public String toString() {
    	return userName + "," +score;
    }
    
    /**
     * This method creates a file to store accounts.
     * 
     * @param file Name and type of file.
     * @throws IOException if creation of file has error
     */
    public void createFile(File file) throws IOException {

	    if (file.createNewFile())
	    {
	    	System.out.println("File created: " + file.getName());
	    } 
	    else
	    {
	    	System.out.println("File already exists.");
	    }
    }
    
    /**
     * This method saves an account into a text file.
     * @param account Current player's account.
     * @throws IOException If file not found.
     */
    public void addUser(Account account) throws IOException {
    	
    	FileWriter fileWriter = new FileWriter(file, true);
    	
    	fileWriter.write(account.toString()+"\n");
        fileWriter.close();
    }
    
    public long getScore() {
        return score;
    }
    
    public String getUserName() {
        return userName;
    }
    
    /**
     * This method sets file to be created.
     * 
     * @param fileName Name of file to be created.
     */
    public void setFileName(String fileName) {
    	file = new File(fileName);
    	this.fileName = fileName;
    }
    
    /**
     * This method reads all previous account from file and adds into accounts ArrayList.
     * 
     * @throws FileNotFoundException If file to open isn't found.
     */
    public void getUserList() throws FileNotFoundException {
    	
    	accounts.clear();
    	String[] userNscore;
		Scanner getUserList = new Scanner(file);
		
		while(getUserList.hasNextLine())
		{
			String data = getUserList.nextLine();
			userNscore = data.split(",");
			accounts.add(new Account(userNscore[0],Long.parseLong(userNscore[1])));
		}
    }
    
    /**
     * This method overwrites current score of account in file if a new high score is achieved.
     * 
     * @param account Current player's account.
     * @throws IOException If file not found.
     */
    public void updateScore(Account account) throws IOException {
    	
    	Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path);

        if(compareScore(account))
        {
        	lines.set((accounts.indexOf(account)), (userName+","+score));
        	Files.write(path, lines);
        }
        else
        	System.out.println("No change");
    }
    
    /**
     * This method checks if current score is higher than previous highest score.
     * 
     * @param account Current player's account.
     * @return True current score is higher, false if otherwise.
     */
    public boolean compareScore(Account account){
    	
    	long prevScore = account.getScore();
        
        if(score>prevScore)
        	return true;
        else
        	return false;
    }

    /**
     * This method sorts all saved accounts from highest score to lowest.
     */
    public void sortByScore() {
    	
    	Collections.sort(accounts);
    }
    
    /**
     * This method checks if the user name entered has been saved before.
     * 
     * @param userName Player's account name.
     * @return account if user name was found, null otherwise.
     */
    public Account accountHaveBeenExist(String userName) {
    	
    	for(Account account : accounts)
    	{
            if(account.getUserName().equals(userName))
            {
                return account;
            }
    	}
    	
        return null;

    }

    /**
     * This method adds current user name into accounts ArrayList.
     * 
     * @param userName Player's account name.
     * @return account added into ArrayList.
     */
    public Account makeNewAccount(String userName){
    	
        Account account = new Account(userName,score);
        accounts.add(account);
     
        return account;
    }

}
