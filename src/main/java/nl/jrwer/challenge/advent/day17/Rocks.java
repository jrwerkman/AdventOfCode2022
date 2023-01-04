package nl.jrwer.challenge.advent.day17;

class Rocks {
	final Stones[] stones;
	int index = -1;
	
	public Rocks() {
		this.stones = new Stones[] {
				Stones.MINUS,
				Stones.PLUS,
				Stones.HOOK,
				Stones.PIPE,
				Stones.SQUARE};
	}
	
	public Rock next() {
		index++;
		
		if(index == stones.length)
			index = 0;
		
		return getRock(stones[index]);
	}
	
	private Rock getRock(Stones stone) {
		switch (stone) {
		case HOOK:
			return new Hook();
		case MINUS:
			return new Minus();
		case PIPE:
			return new Pipe();
		case PLUS:
			return new Plus();
		case SQUARE:
			return new Square();
		default:
			return null;
		}
	}
}