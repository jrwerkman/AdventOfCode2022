package nl.jrwer.challenge.advent.day09;

enum Direction {
	RIGHT,
	LEFT,
	UP,
	DOWN,
	UPRIGHT,
	UPLEFT,
	DOWNRIGHT,
	DOWNLEFT;
	
	public static Direction getDirection(char input) {
		switch(input) {
		case 'R':
			return RIGHT;
		case 'L':
			return LEFT;
		case 'U':
			return UP;
		case 'D':
			return DOWN;
		default:
			return null;
		}
	}
}