package application;

import entities.Calculator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	private String title = "Calculator";

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			primaryStage.setTitle(title);
			primaryStage.setResizable(false);

			BorderPane borderPane = new BorderPane();
			GridPane gridPane = createGridPane();
			Calculator.buildCalculator(borderPane, gridPane);
			Scene scene = new Scene(borderPane);
			String path = "./uicontrolcss/controlStyle.css";
			scene.getStylesheets().add(path);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	public GridPane createGridPane() {

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(2, 2, 2, 2));
		grid.setVgap(2);
		grid.setHgap(2);
		grid.setMinSize(Calculator.width, Calculator.height);
		grid.getStyleClass().add("back");

		return grid;
	}

}
