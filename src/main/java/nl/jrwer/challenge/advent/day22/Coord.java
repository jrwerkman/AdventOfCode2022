package nl.jrwer.challenge.advent.day22;

public class Coord {
	public int x, y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return String.format("%d,%d", x, y);
	}
}
