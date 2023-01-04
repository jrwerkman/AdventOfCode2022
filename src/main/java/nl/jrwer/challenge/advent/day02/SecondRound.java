package nl.jrwer.challenge.advent.day02;
class SecondRound {
	public final Hand opponent;
	public final MatchStatus status;
	
	public SecondRound(char opp, char status) {
		this.opponent = Hand.getHand(opp);
		this.status = MatchStatus.getHand(status);
	}
	
	public int getPoints() {
		int points = status.points;
		Hand you = yourHand();
		points += you.points;
		
		return points;
	}
	
	private Hand yourHand() {
		switch(status) {
		case DRAW:
			return opponent;
		case LOSE:
			if(opponent == Hand.ROCK)
				return Hand.SCISSOR;
			else if(opponent == Hand.PAPER)
				return Hand.ROCK;
			else 
				return Hand.PAPER;
		case WIN:
			if(opponent == Hand.ROCK)
				return Hand.PAPER;
			else if(opponent == Hand.PAPER)
				return Hand.SCISSOR;
			else 
				return Hand.ROCK;
		default:
			return null;
		}
	}
}	