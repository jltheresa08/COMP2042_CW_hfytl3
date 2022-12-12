package com.game.start;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class creates all the texts in Game scene.
 * 
 * @author Theresa Lim - modified
 */
class TextMaker {
    private static TextMaker singleInstance = null;

    private TextMaker() {

    }

    /**
     * This method creates a new TextMaker object once.
     * 
     * @return TextMaker object.
     */
    static TextMaker getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new TextMaker();
        return singleInstance;
    }

    /**
     * This method writes the text on cell.
     * 
     * @param input Value in string to be written.
     * @param xCell position of cell on horizontal pane.
     * @param yCell position of cell on vertical pane.
     * @param root Group of components to be displayed on scene.
     * @return Value in text form.
     */
    Text madeText(String input, double xCell, double yCell, Group root) {
        double length = GameScene.getLENGTH();
        double fontSize = (3 * length) / 7.0;
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);

        return text;
    }

    /**
     * This method replace text with another.
     * 
     * @param first Text to be replaced.
     * @param second Text to replace first text.
     */
    static void changeTwoText(Text first, Text second) {
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber;
        tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);

    }

}
