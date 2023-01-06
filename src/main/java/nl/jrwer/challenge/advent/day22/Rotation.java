package nl.jrwer.challenge.advent.day22;

public enum Rotation {
	CLOCKWISE, COUNTERCLOCKWISE, NONE;
	
	public static Rotation get(char in) {
		if(in == 'R')
			return CLOCKWISE;
		
		if(in == 'L')
			return COUNTERCLOCKWISE;
		
		throw new RuntimeException("Unsupported char: " + in);
	}
}
