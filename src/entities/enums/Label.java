package entities.enums;

public enum Label {

	ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"),

	DIVISION("÷"), MULTIPLICATION("x"), ADDITION("+"), SUBTRACTION("-"),

	EQUAL("="), DOT("."), CLEAR("AC");

	private String value;

	Label(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
