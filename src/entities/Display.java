package entities;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class Display {

	private static final Double width = 270.0;
	private static final Double heigh = 90.0;

	private Label display;
	private String valueDisplay = "0";

	public Display() {
		initializeDisplay();
	}

	public Label getDisplay() {
		return display;
	}

	public String getValueDisplay() {
		return valueDisplay;
	}

	public void setValueDisplay(String valueDisplay) {
		this.valueDisplay = valueDisplay;
		this.display.setText(valueDisplay);
	}
	
	private void initializeDisplay() {
		display = new Label(valueDisplay);
		display.setMinSize(width, heigh);
		display.setAlignment(Pos.CENTER_RIGHT);
		display.getStyleClass().add("display");
	}
}
