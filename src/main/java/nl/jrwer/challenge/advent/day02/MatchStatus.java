package nl.jrwer.challenge.advent.day02;

enum MatchStatus {
	WIN(6),
	LOSE(0),
	DRAW(3);
	
	public final int points;
	
	MatchStatus(int points) {
		this.points = points;
	}
	
	public static MatchStatus getHand(char input) {
		switch(input) {
			case 'X':
				return LOSE;
			case 'Y':
				return DRAW;
			case 'Z':
				return WIN;
			default:
				return null;
		}
	}
}