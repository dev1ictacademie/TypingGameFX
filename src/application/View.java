package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class View {
	GameController controller;
	Label text = new Label("text to type");
	Label mistakes = new Label("mistakes = 0");
	Label lastTyped = new Label("last typed = ");
	ImageView keyboard = new ImageView();
	
	Scene init() {
		VBox root = new VBox(10);
		root.setStyle("-fx-font-size: 20px;");
		
		root.getChildren().addAll(mistakes, lastTyped, text, keyboard);
		root.setAlignment(Pos.CENTER);
		
		return new Scene(root);
	}
	
	void setText(Label label, String s) {
		label.setText(s);
	}
	
	void setImage(Image image) {
		keyboard.setImage(image);
	}
	
	void setController(GameController controller) {
		this.controller = controller;
	}
}
