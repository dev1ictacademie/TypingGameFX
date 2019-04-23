package application;

import java.util.*;

import application.model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameController extends Application {
	View view = new View();
	String output = new Level(LevelData.LEVEL1).getOutput();
	HashMap<String, Image> keyboard = keyboard();
	int curChar = 0;//, mistakes = 0;
	long timeStamp, cpm = 0;
	boolean anim = false;
	AnimationTimer at;

	@Override
    public void start(Stage primaryStage) {
		// FIXME Level select, 80% accuracy
		//Level level = new Level(LevelData.LEVEL1);
		//level.setNumberOfCharstoType(int);
		//String output = level.getOutput();
    	view.setAmount(output.length());
    	Scene scene = view.init();

    	for(int i = 0; i < output.length(); i++) {
    		view.setText(view.getLabel(i), output.charAt(i)+"");
    	}
    	view.getLabel(curChar).setStyle("-fx-border-width: 1; -fx-border-color: #000000;");
    	
    	if(!anim) view.setImage(keyboard.get(output.charAt(curChar)+""));
    	else {
    		AnimationTimer at = new AnimationTimer() {
    			int frameCount = 0;
    		    
	            @Override
	            public void handle(long currentNanoTime) {
	               if (frameCount % 6 == 0) {
	            	   Label label = view.getLabel(curChar);
	            	   // FIXME 300 is an arbitrary number (scene height / 2)
	            	   label.setTranslateY(label.getTranslateY()>=300?0:label.getTranslateY()+10);
	               }         
	               frameCount++;
	            }
    		};
    		at.start();
    	}
    	
    	primaryStage.setScene(scene);
    	scene.setOnKeyPressed(ke -> onKeyPressed(ke.getText()));
    	primaryStage.show();
    }

    void onKeyPressed(String ke) {
    	// process keyboard input
    	char[] array = output.toCharArray();
    	if(ke.equals(array[curChar]+"")) {
    		view.getLabel(curChar).setStyle("-fx-background-color: #00FF00;");
    		if(anim) view.getLabel(curChar).setTranslateY(0);
    		
    		// To continue : move the following lines outside if-statement
    		curChar=(curChar<array.length-1)?curChar+1:0; 
    		//FIXME if (curChar == 0) view.text.getChildren().stream().forEach(n -> n.setStyle(null)); 
    		view.getLabel(curChar).setStyle("-fx-border-width: 1; -fx-border-color: #000000;");
    		if(!anim) view.setImage(keyboard.get(output.charAt(curChar)+""));
    	}
    	
    	else {
    		view.getLabel(curChar).setStyle("-fx-background-color: #FF0000");
    		view.setMistakes(view.getMistakes()+1);
    		// TODO Remember which chars go wrong the most
    	}
    	view.setLastTyped("last typed = " + ke);
    	
    	// characters per minute
    	if (curChar == 0) {
    		view.setCpm((output.length()/((1.0*System.nanoTime()-timeStamp))*60_000_000_000d));
    		cpm = 0;
    	}
    	if(cpm == 0) timeStamp = System.nanoTime();
    	cpm++; //counts wrong chars as well
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
