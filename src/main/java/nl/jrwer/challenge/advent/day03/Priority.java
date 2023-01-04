package nl.jrwer.challenge.advent.day03;

abstract class Priority {
	protected char item = 0;
	protected int priority = 0;
	
	public int getPriority() {
		if(priority != 0)
			return priority;
		
		char item = getItem();
		
		// A-Z
		if(item >= 65 && item <= 90)
			priority = item - 38;
		// a-z
		else if(item >= 97 && item <= 122)
			priority = item - 96;
	
		return priority;
	}
	
	abstract char getItem();
}