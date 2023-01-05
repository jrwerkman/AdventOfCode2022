package nl.jrwer.challenge.advent.day21;

enum Operator {
	DIVIDE, MULTIPLY, ADD, SUBTRACT;
	
	public static Operator getOperator(char in) {
		switch(in) {
		case '+':
			return Operator.ADD;
		case '-':
			return Operator.SUBTRACT;
		case '*':
			return Operator.MULTIPLY;
		case '/':
			return Operator.DIVIDE;
		default:
			throw new RuntimeException("Unsupported char: " + in);
		}
	}
}