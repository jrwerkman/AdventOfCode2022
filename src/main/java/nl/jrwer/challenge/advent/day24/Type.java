package nl.jrwer.challenge.advent.day24;

public enum Type {
	WALL, OPEN, ENTRANCE, EXIT, EXPEDITION;
	
	public static Type get(char c) {
		if(c == '#')
			return WALL;
		
		return OPEN;
	}
}
