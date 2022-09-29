package entities;

import javafx.scene.control.Button;

public class CustomButton {

	private static final Double MinWidth = 65.0;
	private static final Double MinHeight = 65.0;
	private Button button;
	private String label;

	public CustomButton(String label) {
		this.button = new Button(label);
		this.button.setMinSize(MinWidth, MinHeight);
		this.button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.button.getStyleClass().add("custom-button");
		this.label = label;
	}

	public CustomButton(String label, String classSelector) {
		this.button = new Button(label);
		this.button.setMinSize(MinWidth, MinHeight);
		this.button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.button.getStyleClass().add(classSelector);
		this.label = label;
	}

	public Button getButton() {
		return button;
	}

	public String getLabel() {
		return label;
	}
}
