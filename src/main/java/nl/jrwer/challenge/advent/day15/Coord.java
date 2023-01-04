package nl.jrwer.challenge.advent.day15;

class Coord {
	final int x, y;
	final Type type;
	
	public Coord(int x, int y) {
		this(x, y, null);
	}
	
	protected Coord(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Sensor) {
			Sensor s = (Sensor) obj;
			return (x == s.x && y == s.y) || (x == s.beacon.x && y == s.beacon.y);
		} else if(obj instanceof Coord) {
			Coord c = (Coord) obj;
			return x == c.x && y == c.y;
		} else {
			throw new RuntimeException("Cannot compare Coord with: " + obj.getClass().getSimpleName());
		}
	}
	
	@Override
	public String toString() {
		return x + "," + y;
	}
}