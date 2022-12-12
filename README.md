# COMP2042_CW_hfytl3
Name: Theresa Lim Jiyi
ID: 20300517

## Getting Started
### How to run
1. Install Git from [here](https://git-scm.com/downloads). Skip this step if already installed.
2. Clone the repository
```
git clone https://github.com/jltheresa08/COMP2042_CW_hfytl3.git
```

## Documentation
Javadocs generated for this project can be found at: \COMP2042_CW_hfytl3-master\COMP2042_CW_hfytl3-master\javadoc

## Functional Features
1. **Loading screen :**
A window with a loading bar starts when the code is run.

2. **Start Button :**
START button launches the game.

3. **Colour Picker :**
Changes colour of background based on what is chosen. Default is

4. **Scoreboard Button :**
Opens a screen with list of previous saved accounts and their scores.

5. **Game Mode :**
Changes grid of game from 4x4 to 5x5.

6. **Game Scoring System :**
Prevents score increase when no valid moves is made or when non-arrow keys are pressed.

7. **Score Pop-Up :**
Final score is displayed on screen after game over.

8. **Account Creation and Saving Score :**
Choice to save score with username into text file. Score in file is updated if new high score is achieved.

9. **Javadoc :**
Documentation generated are in folder named javadoc.

10. **JUnit Test :**
Tests for Account class.

## New Java Classes
### Controllers
1. accountController.java
2. scoreboardController.java
3. endgameController.java
4. loadingController.java

### JUnit
1. AccountTest.java

## Modified Java Classes
1. Account.java
2. EndGame.java
3. GameScene.java
4. Main.java

## Key Changes
### Repackaging
The classes are sorted into packages based on their functions such as loadingController.java in com.game.front, EndGame.java in com.game.end.

### Splitting Large Methods
GameScene.java has a few large methods such as randomFillNumber and passDestionation.
randomFillNumber was reduced in length by creating another method caleed put2or4 to decide whether the next cell generated should be valued 2 or 4.
passDestination was also shortened through the creation of another method called getCoordinate which handles calculating the next coordinate for the cell to move.

### Game Logic
1. Prevent non-arrow keys from triggering the code.
2. Prevent invalid moves from increasing the score.

### Others
#### Account.java 
1. Now has a variable named fileName which enables developers to easily change the name and type of file to be created by using the method setFileName.
2. Sorts the data and saves into file from highest score to lowest.
3. Updates score of player matching current username entered in file.

#### EndGame.java
1. Game over scene now designed using fxml file.

#### JUnit Test for Account.java
1. Included test cases to determine that method for account creation and score update is functioning.
