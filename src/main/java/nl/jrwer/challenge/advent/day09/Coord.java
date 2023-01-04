package nl.jrwer.challenge.advent.day09;

public class Coord {
	protected int x, y;
	
	protected Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	protected void move(Direction direction) {
		if(direction == Direction.DOWN)	
			y--;
		if(direction == Direction.LEFT)	
			x--;
		if(direction == Direction.RIGHT)	
			x++;
		if(direction == Direction.UP)	
			y++;
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
	
	public boolean equals(int cX, int cY) {
		return x == cX && y == cY;
	}		
	
	@Override
	public String toString() {
		return x + "-" + y;
	}
	
	public Coord clone() {
		return new Coord(x, y);
	}
}