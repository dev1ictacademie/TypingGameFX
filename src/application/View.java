package application;

import javafx.beans.property.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class View {
	
	private FlowPane text = new FlowPane();
	private IntegerProperty mistakes = new SimpleIntegerProperty();
	private IntegerProperty cpm = new SimpleIntegerProperty();
	private StringProperty lastTyped = new SimpleStringProperty("last typed = ");
	private StringProperty curLevel = new SimpleStringProperty();
	private ImageView keyboard = new ImageView();
	private int charAmount;
	
//	private String 	border = "-fx-border-width: 1; -fx-border-color: #000000;",
//					red = "-fx-background-color: #FF0000",
//					green = "-fx-background-color: #00FF00;";
	
	Scene init() {
		VBox root = new VBox(50);
		root.setStyle("-fx-font-size: 20px;");
		
		HBox top = new HBox();
		VBox topLeft = new VBox();
		
		Label mistakesLabel = new Label("mistakes = 0");
		mistakesLabel.textProperty().bind(mistakes.asString());
		
		Label cpmLabel = new Label("cpm = 0");
		cpmLabel.textProperty().bind(cpm.asString());
		
		Label lastTypedLabel = new Label("last typed = ");
		lastTypedLabel.textProperty().bind(lastTyped);
		
		Label curLevelLabel = new Label("Current level = ");
		curLevelLabel.textProperty().bind(curLevel);
		
		topLeft.getChildren().addAll(mistakesLabel, cpmLabel, lastTypedLabel, curLevelLabel);
		
		VBox topRight = new VBox();
		
		Label user = new Label("User?");
		
		topRight.getChildren().addAll(user);
		topRight.setAlignment(Pos.TOP_RIGHT);
		topRight.setStyle("-fx-font-size: 20px;");
		
		top.getChildren().addAll(topLeft, topRight);
		
		// Actually moves topRight to the top right.
		top.getChildren().stream().forEach(n -> HBox.setHgrow(n, Priority.ALWAYS));
		
		update();
		
		VBox middle = new VBox(50);
		middle.getChildren().addAll(text, keyboard);
		middle.setAlignment(Pos.CENTER);
		middle.setStyle("-fx-font-size: 30px;");
		
		root.getChildren().addAll(top, middle);
		return new Scene(root, 800, 600);
	}
	
	void update() {
		text.getChildren().clear();
		for(int i = 0; i < charAmount; i++) {
			Label label = new Label("");
			label.setStyle("-fx-background-color: #778899; -fx-text-fill: #FFFFFF;");
			text.getChildren().add(label);
		}
		text.setAlignment(Pos.CENTER);
	}
	
	Label getLabel(int index) {
		return (Label) text.getChildren().get(index);
	}
	
//	void setText(Label label, String s) {
//		label.setText(s);
//	}
	
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

	public final IntegerProperty cpmProperty() {
		return this.cpm;
	}
	

	public final int getCpm() {
		return this.cpmProperty().get();
	}
	

	public final void setCpm(final int cpm) {
		this.cpmProperty().set(cpm);
	}

	public final StringProperty curLevelProperty() {
		return this.curLevel;
	}
	

	public final String getCurLevel() {
		return this.curLevelProperty().get();
	}
	

	public final void setCurLevel(final String curLevel) {
		this.curLevelProperty().set(curLevel);
	}
	
}
