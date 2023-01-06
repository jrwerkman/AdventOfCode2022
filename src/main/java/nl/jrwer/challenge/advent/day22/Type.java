package nl.jrwer.challenge.advent.day22;

public enum Type {
	NOTHING, OPEN_TILE, SOLID_WALL;
	
	public static Type get(char in) {
		if(in == '.')
			return OPEN_TILE;
		
		if(in == '#')
			return SOLID_WALL;
		
		throw new RuntimeException("Unsupported char: " + in);
	}
}
