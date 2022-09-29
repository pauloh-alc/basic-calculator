package entities;

import javafx.scene.control.Button;

public class CustomButton {

	private static final Double MinWidth = 65.0;
	private static final Double MinHeight = 65.0;
	private Button button;
	private String label;

	public CustomButton(String label) {
		initializeCustomButtom(label, "custom-button");
	}

	public CustomButton(String label, String classSelector) {
		initializeCustomButtom(label, classSelector);
	}

	public Button getButton() {
		return button;
	}

	public String getLabel() {
		return label;
	}
	
	private void initializeCustomButtom(String label, String classSelector) {
		this.button = new Button(label);
		this.button.setMinSize(MinWidth, MinHeight);
		this.button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.button.getStyleClass().add(classSelector);
		this.label = label;
	}
}
