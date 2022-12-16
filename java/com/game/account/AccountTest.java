package com.game.account;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class tests methods in Account.java.
 * {@link com.game.account.Account}
 * 
 * @author Theresa Lim
 */
public class AccountTest {

	/**
	 * Test data for test units.
	 */
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
	
	/**
	 * Tests that account has not been saved before.
	 */
	@Test
	public void accountHaveBeenExist_accountNotExist_returnNull(){
		
		assertEquals(null, testAccount.accountHaveBeenExist("testNullAcc"));
	}
	
	/**
	 * Tests that account have been saved previously.
	 */
	@Test
	public void accountHaveBeenExist_accountExist_returnAccount(){
		
		assertEquals(checker, testAccount.accountHaveBeenExist("testAcc"));
	}
	
	/**
	 * Tests that previous score saved is higher than current score.
	 */
	@Test
	public void compareScore_prevScoreHigher_returnFalse() {

		Account testNotUpdated = new Account("testAcc", 400);
		assertFalse(testNotUpdated.compareScore(checker));
		
	}
	
	/**
	 * Tests that current score is higher than previously saved score.
	 * 
	 * @throws IOException if file not found
	 */
	@Test
	public void updateScore_newScoreHigher_compareScoreReturnTrueUpdate() throws IOException {
		
		Account testUpdated = new Account("testAcc", 2048);
		testUpdated.setFileName("JUnitAccounts.txt");
		testUpdated.updateScore(checker);
		assertTrue(testUpdated.compareScore(checker));
	}
	
}
