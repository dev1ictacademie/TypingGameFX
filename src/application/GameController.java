package application;

import java.util.*;

import application.model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameController extends Application {
	View view = new View();
	HashMap<String, Image> keyboard = keyboard(), balloons = balloons();
	long timeStamp, cpm = 0;
	boolean startLevel = true, anim = true;
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
//    		if(anim) 
//    			view.getLabel(i).setGraphic(new ImageView(balloons.get(output.charAt(i))));
//    		else 
    			view.getLabel(i).setText(output.charAt(i)+"");
    	}
    	view.getLabel(curChar).setStyle("-fx-border-width: 1; -fx-border-color: #000000;");
    	
    	
    	if(!anim) view.setImage(keyboard.get(output.charAt(curChar)+""));
    	else {
    		//TODO Animation, split words and remove spaces
    		AnimationTimer at = new AnimationTimer() {
    			int frameCount = 0;
           		int last = curChar;
           		
	            @Override
	            public void handle(long currentNanoTime) {
	               if (frameCount % 6 == 0) {
	               		for(int i = last; i < (output.indexOf(' ', curChar)+1==0?output.length():output.indexOf(' ', curChar)+1); i++) {
		               		Label label = view.getLabel(i);
		               		label.setTranslateY(label.getTranslateY()>=(scene.getHeight()/2)?0:label.getTranslateY()+10);
	               		}
	               		if(curChar == output.indexOf(' ', curChar)-1) last = curChar+2;
	               		if(curChar == 0) last = curChar;
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
    		
//    		if(anim && curChar == output.indexOf(' ')) {
//    			for(int i = output.lastIndexOf(' ', curChar); 
//    					i < (output.indexOf(' ', curChar)+1==0?output.length():output.indexOf(' ', curChar)+1); i++) {
//    				view.getLabel(i).setTranslateY(0);
//    			}
//    		}
    		
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
		
		output = level.getOutput();
		view.setAmount(output.length());
		view.update();
		
		// FIXME Balloons don't show
    	for(int i = 0; i < output.length(); i++) {
    		Label label = view.getLabel(i);
//    		if(anim) {
//    			label.setGraphic(new ImageView(balloons.get(output.charAt(i))));
//    		} else 
    			label.setText(output.charAt(i)+"");
    	}
    	
    	p.setCurLevel(curLevel);
    	p.setMistakes(mistakes);
	}

    private HashMap keyboard() {
    	String k = "/keyboard/";
    	HashMap keyboard = new HashMap();
    	for(int i = 97; i < 97+26; i++) {
    		keyboard.put(Character.toString((char)i), new Image(k + Character.toString((char)i)+".png"));
    		//System.out.println(Character.toString((char)i)+".png");
    	}
    	keyboard.put(";", new Image(k + "semicolon.png"));
    	keyboard.put(" ", new Image(k + "space.png"));
    	return keyboard;
    }
    
    private HashMap balloons() {
    	String b = "/balloons/";
    	HashMap balloons = new HashMap();
    	// TODO capital letters 65 to 90
    	for(int i = 97; i < 97+26; i++) {
    		balloons.put(Character.toString((char)i), new Image(b + "balloon_" + Character.toString((char)i)+".png"));
    		//System.out.println("balloon_" + Character.toString((char)i)+".png");
    	}
    	return balloons;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
