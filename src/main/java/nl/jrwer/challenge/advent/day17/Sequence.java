package nl.jrwer.challenge.advent.day17;

class Sequence {
	final Direction[] sequence;
	int index = 0;
	
	public Sequence(String input) {
		this.sequence = new Direction[input.length()];
		
		for(int i=0; i<input.length(); i++)
			this.sequence[i] = input.charAt(i) == '<' ? 
					Direction.LEFT : Direction.RIGHT;
	}
	
	public boolean hasNext() {
		return index < sequence.length;
	}
	
	public Direction next() {
		if(index == sequence.length)
			index = 0;

		Direction dir = sequence[index];
		index++;
		
		return dir;
	}
}