package nl.jrwer.challenge.advent.day25;

public enum SnafuDigit {
	TWO('2', 2), ONE('1', 1), ZERO('0', 0), MINUS_ONE('-', -1), MINUS_TWO('=', -2);
	
	public final char digit;
	public final int amount;
	
	private SnafuDigit(char digit, int amount) {
		this.digit = digit;
		this.amount = amount;
	}
	
	private static final SnafuDigit[] digits = values();
	
	public SnafuDigit minus() {
		return digits[(this.ordinal() + 1) % digits.length];
	}

	public SnafuDigit plus() {
		return digits[this.ordinal() > 0 ? this.ordinal() - 1 : digits.length - 1];
	}
	
	public static SnafuDigit get(int i) {
		if(i == 2)
			return TWO;
		if(i == 1)
			return ONE;
		if(i == 0)
			return ZERO;
		if(i == -1)
			return MINUS_ONE;
		if(i == -2)
			return MINUS_TWO;
		
		throw new RuntimeException("Cannot get: " + i);
	}
}
