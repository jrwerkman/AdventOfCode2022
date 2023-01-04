package nl.jrwer.challenge.advent.day02;

class Round {
	public final Hand opponent;
	public final Hand you;
	
	public Round(char opp, char you) {
		this.opponent = Hand.getHand(opp);
		this.you = Hand.getHand(you);
	}
	
	public int getPoints() {
		int points = matchPoints() + you.points;
		
		return points;
	}
	
	private int matchPoints() {
		if((opponent == Hand.ROCK && you == Hand.PAPER) ||
				(opponent == Hand.PAPER && you == Hand.SCISSOR) ||
				(opponent == Hand.SCISSOR && you == Hand.ROCK) ) {
			return 6;
		} else if((you == Hand.ROCK && opponent == Hand.PAPER) ||
				(you == Hand.PAPER && opponent == Hand.SCISSOR) ||
				(you == Hand.SCISSOR && opponent == Hand.ROCK) ) {
			return 0;
		} else {
			// draw
			return 3;
		}
	}
}