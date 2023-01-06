package nl.jrwer.challenge.advent.day22;

public enum Direction {
	RIGHT(0), DOWN(1), LEFT(2), UP(3);

	private static final Direction[] directions = values();
	public final int weight;

	private Direction(int weight) {
		this.weight = weight;
	}

	public Direction rotate(Rotation rotation) {
		if (rotation == Rotation.CLOCKWISE)
			return next();
		
		if (rotation == Rotation.COUNTERCLOCKWISE)
			return previous();
		
		return directions[this.ordinal()];
	}

	public Direction next() {
		return directions[(this.ordinal() + 1) % directions.length];
	}

	public Direction previous() {
		return directions[this.ordinal() > 0 ? this.ordinal() - 1 : directions.length - 1];
	}
}
