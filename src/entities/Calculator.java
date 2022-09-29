package entities;

import java.util.ArrayList;
import java.util.List;

import entities.enums.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Calculator {

	public static final Double width = 270.0;
	public static final Double height = 320.0;
	public static final int amountOfButtons = 17;

	private static String[] numbers = { "", "" };
	private static String op = "";
	private static boolean flag = true;
	private static int idx = 0;

	public static void buildCalculator(BorderPane borderPane, GridPane gridPane) {

		Display display = new Display();
		borderPane.setTop(display.getDisplay());

		List<CustomButton> listButton = new ArrayList<CustomButton>();

		String[] listLabels = new String[] { Label.CLEAR.getValue(), Label.DIVISION.getValue(), Label.SEVEN.getValue(),
				Label.EIGHT.getValue(), Label.NINE.getValue(), Label.MULTIPLICATION.getValue(), Label.FOUR.getValue(),
				Label.FIVE.getValue(), Label.SIX.getValue(), Label.SUBTRACTION.getValue(), Label.ONE.getValue(),
				Label.TWO.getValue(), Label.THREE.getValue(), Label.ADDITION.getValue(), Label.ZERO.getValue(),
				Label.DOT.getValue(), Label.EQUAL.getValue() };

		for (int i = 0; i < amountOfButtons; i++) {
			CustomButton btn;
			if (i == 1 || i == 5 || i == 9 || i == 13 || i == 16) {
				btn = new CustomButton(listLabels[i], "operator");
			} else {
				btn = new CustomButton(listLabels[i]);
			}
			listButton.add(btn);
		}

		gridPane.add(listButton.get(0).getButton(), 0, 0);
		gridPane.add(listButton.get(1).getButton(), 3, 0);

		gridPane.add(listButton.get(2).getButton(), 0, 1);
		gridPane.add(listButton.get(3).getButton(), 1, 1);
		gridPane.add(listButton.get(4).getButton(), 2, 1);
		gridPane.add(listButton.get(5).getButton(), 3, 1);

		gridPane.add(listButton.get(6).getButton(), 0, 2);
		gridPane.add(listButton.get(7).getButton(), 1, 2);
		gridPane.add(listButton.get(8).getButton(), 2, 2);
		gridPane.add(listButton.get(9).getButton(), 3, 2);

		gridPane.add(listButton.get(10).getButton(), 0, 3);
		gridPane.add(listButton.get(11).getButton(), 1, 3);
		gridPane.add(listButton.get(12).getButton(), 2, 3);
		gridPane.add(listButton.get(13).getButton(), 3, 3);

		gridPane.add(listButton.get(14).getButton(), 0, 4);
		gridPane.add(listButton.get(15).getButton(), 2, 4);
		gridPane.add(listButton.get(16).getButton(), 3, 4);

		GridPane.setColumnSpan(listButton.get(0).getButton(), 3);
		GridPane.setColumnSpan(listButton.get(14).getButton(), 2);
		borderPane.setCenter(gridPane);

		listButton.get(0).getButton().setOnAction((ActionEvent e) -> {
			clear(display);
		});

		for (int i = 1; i < amountOfButtons; i++) {
			CustomButton btn = listButton.get(i);
			btn.getButton().setOnAction((ActionEvent e) -> {
				addDigit(display, btn);
			});
		}

	}

	private static void clear(Display display) {
		numbers[0] = "";
		numbers[1] = "";
		flag = true;
		idx = 0;
		op = "";
		display.setValueDisplay(Label.ZERO.getValue());
	}

	public static void addDigit(Display display, CustomButton btn) {

		String[] digits = new String[] { Label.ZERO.getValue(), Label.ONE.getValue(), Label.TWO.getValue(),
				Label.THREE.getValue(), Label.FOUR.getValue(), Label.FIVE.getValue(), Label.SIX.getValue(),
				Label.SEVEN.getValue(), Label.EIGHT.getValue(), Label.NINE.getValue(), Label.DOT.getValue() };

		String[] operators = new String[] { Label.ADDITION.getValue(), Label.SUBTRACTION.getValue(),
				Label.MULTIPLICATION.getValue(), Label.DIVISION.getValue(), Label.EQUAL.getValue() };

		if (display.getValueDisplay() == Label.ZERO.getValue() && btn.getLabel() == Label.DOT.getValue())
			numbers[idx] = Label.ZERO.getValue();

		idx = (flag) ? 0 : 1;

		boolean exp = contains(digits, btn.getLabel())
				|| numbers[idx].isEmpty() && !contains(operators, btn.getLabel());

		if (exp) {
			workingWithNegativeNumbers();
			handleDotsInNumbers(btn);
		} else {
			flag = (flag) ? false : true;
			if (!numbers[0].isEmpty() && !numbers[1].isEmpty() && contains(operators, op)) {
				toDoOperation();
			}
			op = btn.getLabel();
		}

		addNewValuesToDisplay(display);

		if (numbers[0].isEmpty() && numbers[1].isEmpty())
			flag = true;

		if (contains(operators, btn.getLabel()))
			numbers[1] = "";

		if (numbers[1].isEmpty() && contains(operators, op) && op != Label.EQUAL.getValue() && op != "")
			flag = false;

		if (numbers[0].isEmpty())
			flag = true;

	}

	private static void addNewValuesToDisplay(Display display) {

		if (numbers[0] == "Infinity")
			numbers[0] = "";

		if (numbers[0].isEmpty() && numbers[1].isEmpty())
			display.setValueDisplay(op);
		else
			display.setValueDisplay(numbers[idx]);
	}

	private static void handleDotsInNumbers(CustomButton btn) {

		boolean exp1 = btn.getLabel() == Label.DOT.getValue();
		boolean exp2 = !numbers[idx].contains(Label.DOT.getValue());

		if (exp1) {
			if (exp2) {
				numbers[idx] += btn.getLabel();
			}
		} else {
			numbers[idx] += btn.getLabel();
		}
	}

	private static void workingWithNegativeNumbers() {

		boolean exp1 = op == Label.SUBTRACTION.getValue();
		boolean exp2 = !numbers[idx].contains(Label.SUBTRACTION.getValue());

		boolean exp3 = op == Label.ADDITION.getValue();
		boolean exp4 = !numbers[idx].contains(Label.ADDITION.getValue());
		boolean exp5 = !numbers[idx].contains(Label.SUBTRACTION.getValue());

		if (exp1 && flag && exp2)
			numbers[idx] = Label.SUBTRACTION.getValue() + numbers[idx];
		else if (exp3 && exp4 && exp5) {
			numbers[idx] = Label.ADDITION.getValue() + numbers[idx];
		}
		if (exp1 && !flag && exp2) {
			numbers[idx] = Label.SUBTRACTION.getValue() + numbers[idx];
			op = Label.ADDITION.getValue();
		}
	}

	private static boolean contains(String[] list, String element) {
		for (String e : list) {
			if (e.equals(element)) {
				return true;
			}
		}
		return false;
	}

	private static void toDoOperation() {

		double num1, num2, result;
		try {
			num1 = Double.parseDouble(numbers[0]);
			num2 = Double.parseDouble(numbers[1]);
		} catch (NumberFormatException e) {
			numbers[0] = Character.toString(numbers[0].charAt(8));
		} finally {
			num1 = Double.parseDouble(numbers[0]);
			num2 = Double.parseDouble(numbers[1]);
		}

		if (op == Label.ADDITION.getValue())
			result = num1 + num2;
		else if (op == Label.SUBTRACTION.getValue())
			result = num1 - num2;
		else if (op == Label.MULTIPLICATION.getValue())
			result = num1 * num2;
		else
			result = num1 / num2;

		String new_result = formatValue(Double.toString(result));

		numbers[0] = new_result;
		numbers[1] = new_result;
	}

	private static String formatValue(String value) {
		int idx = value.indexOf(Label.DOT.getValue());
		if (value.substring(idx + 1).equals(Label.ZERO.getValue())) {
			return value.substring(0, idx);
		}
		return value;
	}
}
