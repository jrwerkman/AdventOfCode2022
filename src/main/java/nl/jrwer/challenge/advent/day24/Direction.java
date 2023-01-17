package nl.jrwer.challenge.advent.day24;

public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
	public static Direction get(char c) {
		if(c == '^')
			return UP;
		if(c == 'v')
			return DOWN;
		if(c == '<')
			return LEFT;
		if(c == '>')
			return RIGHT;
		
		throw new RuntimeException();
	}
}
