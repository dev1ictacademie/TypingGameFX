package application;

import application.model.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameController extends Application {
	View view = new View();
	String output = new Level(LevelData.LEVEL1).getOutput();
	int curChar = 0, mistakes = 0;
	
    @Override
    public void start(Stage primaryStage) {
    	view.setController(this);
    	Scene scene = view.init();
    	
    	view.setText(view.text, output);
    	view.setText(view.mistakes, "mistakes = " + mistakes);
    	view.setImage(new Image("a.png"));
    	primaryStage.setScene(scene);
    	scene.setOnKeyPressed(ke -> onKeyPressed(ke.getText()));
    	primaryStage.show();
    }
    
    void onKeyPressed(String ke) {
    	char[] array = output.toCharArray();
    	System.out.println(array[curChar] + ", " + ke);
    	if(ke.equals(array[curChar])) {
    		System.out.println(" :D");
    		curChar=curChar<array.length?curChar+1:0;
    	}
    	else {
    		mistakes++; 
    		view.setText(view.mistakes, "mistakes = " + mistakes);
    	}
    	view.setText(view.lastTyped, "last typed = " + ke);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
