package application;

import java.util.*;

import application.model.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameController extends Application {
	View view = new View();
	String output = new Level(LevelData.LEVEL1).getOutput();
	HashMap<String, Image> keyboard = keyboard();
	int curChar = 0, mistakes = 0;

	@Override
    public void start(Stage primaryStage) {
    	view.setAmount(output.length());
    	Scene scene = view.init();

    	for(int i = 0; i < view.text.getChildren().size(); i++) {
    		view.setText(((Label)view.text.getChildren().get(i)), output.charAt(i)+"");
    	}
    	view.text.getChildren().get(curChar).setStyle("-fx-border-width: 1; -fx-border-color: #000000;");
    	
    	view.setText(view.mistakes, "mistakes = " + mistakes);
    	view.setImage(keyboard.get(output.charAt(curChar)+""));
    	
    	primaryStage.setScene(scene);
    	scene.setOnKeyPressed(ke -> onKeyPressed(ke.getText()));
    	primaryStage.show();
    }

    void onKeyPressed(String ke) {
    	char[] array = output.toCharArray();
    	if(ke.equals(array[curChar]+"")) {
    		view.text.getChildren().get(curChar).setStyle("-fx-background-color: #00FF00;");
    		curChar=(curChar<array.length-1)?curChar+1:0;
    		if (curChar == 0) view.text.getChildren().stream().forEach(n -> n.setStyle(null)); 
    		view.text.getChildren().get(curChar).setStyle("-fx-border-width: 1; -fx-border-color: #000000;");
    		view.setImage(keyboard.get(output.charAt(curChar)+""));
    	}
    	else {
    		view.text.getChildren().get(curChar).setStyle("-fx-background-color: #FF0000");
    		mistakes++; 
    		view.setText(view.mistakes, "mistakes = " + mistakes);
    	}
    	view.setText(view.lastTyped, "last typed = " + ke);
    }

    HashMap keyboard() {
    	HashMap keyboard = new HashMap();
    	for(int i = 97; i < 97+26; i++) {
    		keyboard.put(Character.toString((char)i), new Image(Character.toString((char)i)+".png"));
    		//System.out.println(Character.toString((char)i)+".png");
    	}
    	keyboard.put(";", new Image("semicolon.png"));
    	keyboard.put(" ", new Image("space.png"));
    	return keyboard;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
