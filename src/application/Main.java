package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author svdpluijm
 */
public class Main extends Application {
    String border = "-fx-border-width: 1; -fx-border-color: #000000;",
            green = "-fx-background-color: #00FF00;",
            red = "-fx-background-color: #FF0000",
            blue = "-fx-background-color: #0000FF; -fx-text-fill: #FFFFFF";

    String[][] text = {{"ffffjjj", "fjfjfjfj"}, {"example text"}};
    int level = 0, part = 0, letter = 0;
    
    HBox example = new HBox();
    VBox board = new VBox();
        
    @Override
    public void start(Stage primaryStage) {
        updateExample();
        
        String[] keys = {"1234567890 ", "qwertyuiop", "asdfghjkl", "zxcvbnm "};
        board.setAlignment(Pos.CENTER);
        for(String s : keys) {
            HBox row = new HBox();
            row.setAlignment(Pos.CENTER);
            for(char c : s.toCharArray()) {
                Label label = new Label(String.valueOf(c));
                label.setPrefSize(25, 25);
                label.setAlignment(Pos.CENTER);
                label.setStyle(border);
                row.getChildren().add(label);
            }
            board.getChildren().add(row);
        }
        
        board.getChildren().stream().forEach(row -> {
            ((HBox)row).getChildren().stream().forEach(key -> {
                if(((Label)key).getText().equals(String.valueOf(text[level][part].toCharArray()[letter]))) {
                    key.setStyle(blue);
                }
            });
        });
        
        VBox root = new VBox(25);
        root.getChildren().addAll(example, board);
        Scene scene = new Scene(root, 450, 300);
        scene.setOnKeyPressed((k) -> keyPressed(k));
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    void updateExample() {
        example.getChildren().clear();
        example.setAlignment(Pos.CENTER);
        for(char c : text[level][part].toCharArray()) {
            Label label = new Label(String.valueOf(c));
            label.setPrefHeight(25);
            label.setAlignment(Pos.CENTER);
            example.getChildren().add(label);
        }
        example.getChildren().get(letter).setStyle(border);
    }
    
    void keyPressed(KeyEvent k) {
        if (k.getText().equals(String.valueOf(text[level][part].toCharArray()[letter]))) {
            example.getChildren().get(letter).setStyle(green);
            if(letter < text[level][part].length()-1) letter++;
            else {
                letter = 0;
                if(part < text[level].length-1) part++;
                else {
                    part = 0;
                    if(level < text.length-1) level++;
                    else {
                        level = 0;
                    }
                }
                updateExample();
            }
            example.getChildren().get(letter).setStyle(border);
            
            board.getChildren().stream().forEach(row -> {
                ((HBox)row).getChildren().stream().forEach(key -> {
                    if(((Label)key).getText().equals(String.valueOf(text[level][part].toCharArray()[letter]))) {
                        key.setStyle(blue);
                    } else key.setStyle(border);
                });
            });
        }
        else {
            example.getChildren().get(letter).setStyle(red);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
