package nl.jrwer.challenge.advent.day17;

class Coord {
	long x, y;
	
	public Coord(long x, long y) {
		this.x = x;
		this.y = y; 
	}
	
	public void moveLeft() {
		x--;
	}
	
	public void moveRight() {
		x++;
	}	
	
	public void moveDown() {
		y--;
	}
	
	@Override
	public String toString() {
		return String.format("%d,%d", x,y);
	}
}