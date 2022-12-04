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

public class Account implements Comparable<Account> {
	
    private  long score = 0;
	private String userName;
    private Account account;
    private File file = new File("test.txt");
    public static boolean highscoreChanged;
    private static ArrayList<Account> accounts = new ArrayList<>();

    public Account(String userName, long score) {
	    	
    	this.userName=userName;
	   	this.score=score;

   }
    
    
    public Account(){
    	
    	this(accountController.name,accountController.score);
    	
    	try
    	{
    		accounts.clear();
			getUserList();
		}
    	catch (FileNotFoundException e)
    	{
			e.printStackTrace();
		}

    	
    	//read file to get list of registered usernames

        if(accountHaveBeenExist(userName)==null)
        {
        	account = makeNewAccount(userName);
        	
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
    		accounts.clear();
			getUserList();
		}
    	catch (FileNotFoundException e)
    	{
			e.printStackTrace();
		}
        
        getHighScore();
    }
    
    

    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }
    
    @Override
    public String toString() {
    	return userName + "," +score;
    }
    
    
    
    private void addUser(Account account) throws IOException {
    	//File file = new File("test.txt");
    	FileWriter fileWriter = new FileWriter(file, true);
    	
    	fileWriter.write(account.toString());
        fileWriter.close();
    }
    
 
    private long getScore() {
        return score;
    }

    private String getUserName() {
        return userName;
    }
    
    private void getUserList() throws FileNotFoundException {
    	
    	String[] userNscore;
    	//File myObj = new File("test.txt");
		Scanner getUserList = new Scanner(file);
		
		while(getUserList.hasNextLine())
		{
			String data = getUserList.nextLine();
			userNscore = data.split(",");
			accounts.add(new Account(userNscore[0],Long.parseLong(userNscore[1])));
		}
    }
    private void updateScore(Account account) throws IOException {
    	
    	Path path = Paths.get("test.txt");
        List<String> lines = Files.readAllLines(path);
        
        if(compareScore(account))
        {
        	lines.set((accounts.indexOf(account)), (userName+","+score));
        	Files.write(path, lines);
        }
        else
        	System.out.println("No change");
    }
    
    private boolean compareScore(Account account){
    	
    	long prevScore = account.getScore();
        
        if(score>prevScore)
        {
        	highscoreChanged = true;
        	return true;
        }
        	
        else
    	{
        	highscoreChanged = false;
        	return false;
    	}
    }
    
    private void getHighScore() {
    	
    	Collections.sort(accounts);
    	
    	System.out.println(accounts);
    
    
    }
    private Account accountHaveBeenExist(String userName) {
    	
    	for(Account account : accounts)
    	{
            if(account.getUserName().equals(userName))
            {
                return account;
            }
    	}
    	
        return null;

    }

    private Account makeNewAccount(String userName){
    	
        Account account = new Account(userName,score);
        accounts.add(account);
     
        return account;
    }

}
