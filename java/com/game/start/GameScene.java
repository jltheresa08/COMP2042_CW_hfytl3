package com.game.start;

import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.game.end.EndGame;


/**
 * This class creates the Game scene.
 * 
 * @author Theresa Lim - modified
 */
class GameScene {
    private static int HEIGHT = 700;
    private static int n = 4;
    private final static int distanceBetweenCells = 10;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];
    private Group root;
    private long score = 0;

    /**
     * This method sets size of grid.
     * E.g. when number=4, gird is 4x4.
     * 
     * @param number The number of grids.
     */
    static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    /**
     * This method returns the length of scene window.
     * 
     * @return length of window.
     */
    static double getLENGTH() {
        return LENGTH;
    }

    /**
     * This method finds all empty cells in grid.
     * 
     * @param turn Which turn since starting the game.
     */
    private void randomFillNumber(int turn) {

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                if (cells[i][j].getNumber() == 0) 
                {
                    emptyCells[a][b] = cells[i][j];
                    
                    if (b < n-1) 
                    {
                        bForBound=b;
                        b++;
                    } 
                    else
                    {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }
        
        put2or4(aForBound,bForBound,emptyCells);
    }
    
    /**
     * This method randomly select 2 or 4 to be input into cell.
     * 
     * @param aForBound Used to determine random empty grid's column.
     * @param bForBound Used to determine random empty grid's row.
     * @param emptyCells Empty cells in grid.
     */
    private void put2or4(int aForBound, int bForBound, Cell[][] emptyCells) {

        Text text;
        Random random = new Random();
        boolean putTwo = true;
        int xCell, yCell;
        String number = "2";
        
        xCell = random.nextInt(aForBound+1);
        yCell = random.nextInt(bForBound+1);
        
        if (random.nextInt() % 2 == 0)
            putTwo = false;
            
        if (putTwo) 
        {
        	number = "2";
        } 
        else 
        {
        	number = "4";
        }
        
        text = textMaker.madeText(number, emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
        emptyCells[xCell][yCell].setTextClass(text);
        root.getChildren().add(text);
        emptyCells[xCell][yCell].setColorByNumber(Integer.parseInt(number));
    }

    /**
     * This method checks if there's still empty cells in grid and presence of cell 2048.
     * 
     * @return -1 if no more empty cell and 1 if still have empty cell with the exception of cell number = 2048, in which case, return 0.
     */
    private int  haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
                if(cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }
        return -1;
    }

    /**
     * This method passes the next destination of cell after move.
     * 
     * @param i x coordinate
     * @param j y coordinate
     * @param direct Which direction to move according to key pressed.
     * @return coordinate of cell to move to if arrow keys pressed, -1 otherwise.
     */
    private int passDestination(int i, int j, char direct) {
    	int coordinate;

    	if(direct == 'l'||direct =='r')
    	{
    		coordinate = j;
			return getCoordinate(direct,i,j,coordinate);
    	}
    	else if(direct == 'd'|| direct == 'u')
    	{
    		coordinate = i;
			return getCoordinate(direct,i,j,coordinate);
			
    	}
    	else
    	{
    		return -1;
    	}
   
    }
    
    /**
     * This method determines the coordinate for cell to move.
     * 
     * @param direct Which direction to move according to key pressed.
     * @param i x coordinate
     * @param j y coordinate
     * @param coordinate Determines cell moving vertically or horizontally.
     * @return coordinate of cell to move to.
     */
    private int getCoordinate(char direct, int i, int j, int coordinate) {
   
    	int k,x;
    	
    	if (direct == 'l' || direct == 'u')
    	{

    		if(direct == 'l') 
    			k = j - 1;
    		else
    			k = i - 1;
    			
    		for (; k >= 0; k--) 
    		{
    			
    			if(direct == 'l')
    				x = cells[i][k].getNumber();
    			else
    				x = cells[k][j].getNumber();
    			
                if (x != 0) 
                {
                    coordinate = k + 1;
                    break;
                } 
                else if (k == 0) 
                {
                    coordinate = 0;
                }
            }

    	}
    	else if (direct == 'r'|| direct == 'd') 
    	{
    		
    		if(direct == 'r') 
    			k = j + 1;
    		else
    			k = i + 1;

    		
            for (; k <= n - 1; k++)
            {
            	
            	if(direct == 'r')
    				x = cells[i][k].getNumber();
    			else
    				x = cells[k][j].getNumber();
            	
                if (x != 0)
                {
                    coordinate = k - 1;
                    break;
                } 
                else if (k == n - 1) 
                {
                    coordinate = n - 1;
                }
            }
    	}
    	return coordinate;
    }
    
    /**
     * This method moves cell to the leftmost available cell.
     */
    private void moveLeft() {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    /**
     * This method moves cell to rightmost available cell.
     */
    private void moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    /**
     * This method moves cell to up-most available cell.
     */
    private void moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                moveVertically(i, j, passDestination(i, j, 'u'), -1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    /**
     * This method moves cell to down-most available cell.
     */
    private void moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    /**
     * This method checks if destination for cell to move is valid on the horizontal plane.
     * 
     * @param i x coordinate
     * @param j y coordinate
     * @param des destination of cell.
     * @param sign Determine to go right by increasing x coordinate by 1, or left by decreasing by 1.
     * @return true if destination is available, false otherwise.
     */
    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }
    
	/**
	 * This method changes cells if needed after moving horizontally.
	 * 
	 * @param i x coordinate
	 * @param j y coordinate
     * @param des destination of cell.
     * @param sign Determine to go right by increasing x coordinate by 1, or left by decreasing by 1.
	 */
    private void moveHorizontally(int i, int j, int des, int sign) {

        if (isValidDesH(i, j, des, sign)) {
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des].setModify(true);
            score +=cells[i][des + sign].getNumber();

        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
        }
    }

    /**
     * This method checks if destination for cell to move is valid on the vertical plane.
     * 
	 * @param i x coordinate
	 * @param j y coordinate
     * @param des destination of cell.
     * @param sign Determine to go down by increasing y coordinate by 1, or up by decreasing by 1.
     * @return true if destination is available, false otherwise.
     */
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    /**
     * This method changes cells if needed after moving horizontally.
	 * 
	 * @param i x coordinate
	 * @param j y coordinate
     * @param des destination of cell.
     * @param sign Determine to go down by increasing y coordinate by 1, or up by decreasing by 1.
     */
    private void moveVertically(int i, int j, int des, int sign) {

        if (isValidDesV(i, j, des, sign)) {
            cells[i][j].adder(cells[des + sign][j]);
            cells[des][j].setModify(true);
            score +=cells[des + sign][j].getNumber();

        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);

        }
    }

    /**
     * This method checks if neighbouring cell have same value.
     * 
     * @param i x coordinate
     * @param j y coordinate
     * @return true if neighbouring cell have same value, false otherwise.
     */
    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    /**
     * This method checks if there are still moves which can be made in game.
     * 
     * @return true if unable to move to any cell, false otherwise.
     */
    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method creates the game scene.
     * 
     * @param gameScene Container for game scene.
     * @param root Group of components to be displayed on Game scene.
     * @param primaryStage Container for a scene.
     * @param endGameScene Container for Game Over scene.
     * @param endGameRoot Group of components to be displayed on Game Over scene.
     */
    void game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot) {
        this.root = root;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }

        Text text = new Text();
        root.getChildren().add(text);
        text.setText("SCORE :");
        text.setFont(Font.font(30));
        text.relocate(750, 100);
        
        Text scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");

        randomFillNumber(1);
        randomFillNumber(1);

        /**
         * This method runs when key is pressed.
         * If arrow keys are pressed, move cells.
         */
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
                Platform.runLater(() -> {
                    int haveEmptyCell;
           
	                if(key.getCode()==KeyCode.DOWN||key.getCode()==KeyCode.UP||key.getCode()==KeyCode.LEFT||key.getCode()==KeyCode.RIGHT)
	                {
	                    if (key.getCode() == KeyCode.DOWN) {
	                        GameScene.this.moveDown();
	                    } else if (key.getCode() == KeyCode.UP) {
	                        GameScene.this.moveUp();
	                    } else if (key.getCode() == KeyCode.LEFT) {
	                        GameScene.this.moveLeft();
	                    } else if (key.getCode() == KeyCode.RIGHT) {
	                        GameScene.this.moveRight();
	                    }

	                    scoreText.setText(score + "");
	                    haveEmptyCell = GameScene.this.haveEmptyCell();
	                    if (haveEmptyCell == -1) {
	                        if (GameScene.this.canNotMove()) {
	                            primaryStage.setScene(endGameScene);
	
	                            EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score);
	                            root.getChildren().clear();
	                            score = 0;
	                        }
	                    } else if(haveEmptyCell == 1)
	                        GameScene.this.randomFillNumber(2);
                    }
                });
            });
    }
}
