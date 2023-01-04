package nl.jrwer.challenge.advent.day02;

enum Hand {
	ROCK(1),
	PAPER(2),
	SCISSOR(3);
	
	public final int points;
	
	Hand(int points) {
		this.points = points;
	}
	
	public static Hand getHand(char input) {
		switch(input) {
			case 'A':
			case 'X':
				return ROCK;
			case 'B':
			case 'Y':
				return PAPER;
			case 'C':
			case 'Z':
				return SCISSOR;
			default:
				return null;
		}
	}
}