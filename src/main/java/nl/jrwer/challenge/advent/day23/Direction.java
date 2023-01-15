package nl.jrwer.challenge.advent.day23;

public enum Direction {
	NORTH, SOUTH, WEST, EAST;

	private static final Direction[] directions = values();

	public Direction next() {
		return directions[(this.ordinal() + 1) % directions.length];
	}

}
