package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View {
	HBox text = new HBox();
	Label mistakes = new Label("mistakes = 0");
	Label lastTyped = new Label("last typed = ");
	ImageView keyboard = new ImageView();
	int charAmount;
	
	Scene init() {
		VBox root = new VBox(10);
		root.setStyle("-fx-font-size: 20px;");
		
		for(int i = 0; i < charAmount; i++) {
			text.getChildren().add(new Label(""));
		}
		text.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(mistakes, text, lastTyped, keyboard);
		root.setAlignment(Pos.CENTER);
		
		return new Scene(root);
	}
	
	void setText(Label label, String s) {
		label.setText(s);
	}
	
	void setImage(Image image) {
		keyboard.setImage(image);
	}
	
	void setAmount(int amount) {
		charAmount = amount;
	}
}
