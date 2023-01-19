package nl.jrwer.challenge.advent.day14;

class Coord {
	final int x, y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coord(String input) {
		String[] split = input.split(",");
		
		this.x = Integer.parseInt(split[0]);
		this.y = Integer.parseInt(split[1]);
	}
	
	public boolean equals(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	@Override
	public int hashCode() {
		return x << 8 | y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coord) {
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
	
	public Coord left() {
		return new Coord(x - 1, y);
	}
	
	public Coord down() {
		return new Coord(x, y + 1);
	}
	
	public Coord up() {
		return new Coord(x, y - 1);
	}
	
	public Coord right() {
		return new Coord(x + 1, y);
	}
	
	public Coord clone() {
		return new Coord(x,y);
	}
}