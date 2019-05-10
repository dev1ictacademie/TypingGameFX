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
	HashMap<String, Image> keyboard = keyboard();
	long timeStamp, cpm = 0;
	boolean startLevel = true, anim = false;
	AnimationTimer at;
	String output;
	
	// TODO Implement players
	Player p = Player.create("test");
	{p.setCurLevel(5);}
	
	HashMap<Character, Integer> mistakes = p!=null?p.getMistakes():new HashMap<>();
	int curChar = 0, curLevel = p!=null?p.getCurLevel():0;

	@Override
    public void start(Stage primaryStage) {
		// FIXME Duplicate code (see update())
		view.setCurLevel("Current level = " + curLevel);
		output = new Level(LevelData.levels[curLevel]).getOutput();
    	view.setAmount(output.length());
    	Scene scene = view.init();

    	for(int i = 0; i < output.length(); i++) {
    		view.getLabel(i).setText(output.charAt(i)+"");
    	}
    	view.getLabel(curChar).setStyle("-fx-border-width: 1; -fx-border-color: #000000;");
    	
    	
    	if(!anim) view.setImage(keyboard.get(output.charAt(curChar)+""));
    	else {
    		AnimationTimer at = new AnimationTimer() {
    			int frameCount = 0;
    			int last = curChar;
    		    
	            @Override
	            public void handle(long currentNanoTime) {
	               if (frameCount % 6 == 0) {
	               		//int last = curChar;
	               		for(int i = last; i < output.indexOf(' ', last)+1; i++) {
		               		Label label = view.getLabel(i); //curChar
		               		label.setTranslateY(label.getTranslateY()>=(scene.getHeight()/2)?0:label.getTranslateY()+10);
	               		}
	               		last = output.indexOf(' ', last)+1;
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
	
	/** Processes keyboard input */
    void onKeyPressed(String ke) {
    	char[] array = output.toCharArray();
    	if(ke.equals(array[curChar]+"")) {
    		view.getLabel(curChar).setStyle("-fx-background-color: #00FF00;");
    		//if(anim) view.getLabel(curChar).setTranslateY(0);
    		
    		// To continue : move the following lines outside if-statement
    		if(anim && curChar < array.length-1 && array[curChar+1] == ' ') curChar++;
    		curChar++;
    		if(curChar >= array.length) {curChar = 0; startLevel = true; update();}
    		
    		view.getLabel(curChar).setStyle("-fx-border-width: 1; -fx-border-color: #000000;");
    		if(!anim) view.setImage(keyboard.get(output.charAt(curChar)+""));
    	}
    	
    	else {
    		view.getLabel(curChar).setStyle("-fx-background-color: #FF0000");
    		view.setMistakes(view.getMistakes()+1);
    		
    		// Remember which chars go wrong the most
    		mistakes.compute(array[curChar], (k, v) -> (v == null)?1:v+1);
    		p.setMistakes(mistakes);
    	}
    	view.setLastTyped("last typed = " + ke);
    	
    	// characters per minute
    	if (startLevel) {
    		view.setCpm((int)(output.length()/((1.0*System.nanoTime()-timeStamp))*60_000_000_000d));
    		timeStamp = System.nanoTime();
    		startLevel = false;
    	}
    }

    /** Updates the view */
	void update() {
		if ((view.getMistakes() + 0.1)/output.length() < 0.8) {
			curLevel=curLevel<LevelData.levels.length-1?curLevel+1:0;
			view.setCurLevel("Current level = " + curLevel);
			view.setMistakes(0);
		}
		Level level = new Level(LevelData.levels[curLevel]);
		//level.setNumberOfCharstoType(int);
		
		//TODO Animation, split words and remove spaces
		output = level.getOutput();
		view.setAmount(output.length());
		view.update();
		
    	for(int i = 0; i < output.length(); i++) {
    		view.getLabel(i).setText(output.charAt(i)+"");
    	}
    	
    	p.setCurLevel(curLevel);
    	p.setMistakes(mistakes);
	}

    private HashMap keyboard() {
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
