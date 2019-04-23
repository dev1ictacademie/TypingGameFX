package application;

import javafx.beans.property.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View {
	
	private HBox text = new HBox();
	private IntegerProperty mistakes = new SimpleIntegerProperty();
	private DoubleProperty cpm = new SimpleDoubleProperty();
	private StringProperty lastTyped = new SimpleStringProperty("last typed = ");
	private ImageView keyboard = new ImageView();
	private int charAmount;
	
//	private String 	border = "-fx-border-width: 1; -fx-border-color: #000000;",
//					red = "-fx-background-color: #FF0000",
//					green = "-fx-background-color: #00FF00;";
	
	Scene init() {
		VBox root = new VBox(50);
		root.setStyle("-fx-font-size: 20px;");
		
		VBox topLeft = new VBox();
		
		Label mistakesLabel = new Label("mistakes = 0");
		mistakesLabel.textProperty().bind(mistakes.asString());
		
		Label cpmLabel = new Label("cpm = 0");
		cpmLabel.textProperty().bind(cpm.asString());
		
		Label lastTypedLabel = new Label("last typed = ");
		lastTypedLabel.textProperty().bind(lastTyped);
		
		topLeft.getChildren().addAll(mistakesLabel, cpmLabel, lastTypedLabel);
		
		for(int i = 0; i < charAmount; i++) {
			text.getChildren().add(new Label(""));
		}
		text.setAlignment(Pos.CENTER);
		
		VBox middle = new VBox(50);
		middle.getChildren().addAll(text, keyboard);
		middle.setAlignment(Pos.CENTER);
		middle.setStyle("-fx-font-size: 30px;");
		
		root.getChildren().addAll(topLeft, middle);
		
		return new Scene(root, 800, 600);
	}
	
	Label getLabel(int index) {
		return (Label) text.getChildren().get(index);
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

	public final IntegerProperty mistakesProperty() {
		return this.mistakes;
	}
	

	public final int getMistakes() {
		return this.mistakesProperty().get();
	}
	

	public final void setMistakes(final int mistakes) {
		this.mistakesProperty().set(mistakes);
	}

	public final StringProperty lastTypedProperty() {
		return this.lastTyped;
	}
	

	public final String getLastTyped() {
		return this.lastTypedProperty().get();
	}
	

	public final void setLastTyped(final String lastTyped) {
		this.lastTypedProperty().set(lastTyped);
	}

	public final DoubleProperty cpmProperty() {
		return this.cpm;
	}
	

	public final double getCpm() {
		return this.cpmProperty().get();
	}
	

	public final void setCpm(final double cpm) {
		this.cpmProperty().set(cpm);
	}
}
