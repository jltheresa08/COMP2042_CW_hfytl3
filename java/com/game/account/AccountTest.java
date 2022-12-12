package com.game.account;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class tests methods in Account.java.
 * {@link com.game.account.Account}
 * 
 * @author Theresa Lim
 */
public class AccountTest {

	static Account testAccount = new Account("testAcc", 1234);	
	static Account checker;
	
	/**
	 * This method initialises data required to run test cases such as creating file and Account object.
	 * 
	 * @throws IOException if file not found.
	 */
	@BeforeClass
	public static void initTestCase() throws IOException {
		
		new PrintWriter("JUnitAccounts.txt").close();
		
		testAccount.setFileName("JUnitAccounts.txt");
		checker = testAccount.makeNewAccount("testAcc");
		testAccount.addUser(checker);
	}
	
	
	@Test
	public void accountHaveBeenExist_accountNotExist_returnNull(){
		
		assertEquals(null, testAccount.accountHaveBeenExist("testNullAcc"));
	}
	
	@Test
	public void accountHaveBeenExist_accountExist_returnAccount(){
		
		assertEquals(checker, testAccount.accountHaveBeenExist("testAcc"));
	}
	

	@Test
	public void compareScore_prevScoreHigher_returnFalse() {

		Account testNotUpdated = new Account("testAcc", 400);
		assertFalse(testNotUpdated.compareScore(checker));
		
	}
	
	@Test
	public void updateScore_newScoreHigher_compareScoreReturnTrueUpdate() throws IOException {
		
		Account testUpdated = new Account("testAcc", 2048);
		testUpdated.setFileName("JUnitAccounts.txt");
		testUpdated.updateScore(checker);
		assertTrue(testUpdated.compareScore(checker));
	}
	
}
