package nl.jrwer.challenge.advent.day17;

abstract class Rock {
	Coord[] coords;
	final int mostLeft, mostRight;
	int highest;
	
	public Rock(int mostLeft, int mostRight, int highest) {
		this.coords = getCoords();
		
		this.mostLeft = mostLeft;
		this.mostRight = mostRight;
		this.highest = highest;
	}
	
	abstract Coord[] getCoords();
	
	public void move(Direction direction) {
		for(Coord c: coords)
			if(direction == Direction.LEFT) 
				c.moveLeft();
			else if(direction == Direction.RIGHT) 
				c.moveRight();
			else
				c.moveDown();
	}
	
	public Coord mostLeft() {
		return coords[mostLeft];
	}
	
	public Coord mostRight() {
		return coords[mostRight];
	}
	
	public void setY(long height) {
		for(Coord c : coords)
			c.y += height;
	}
	
	public Coord getHighest() {
		return coords[highest];
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Coord c : coords)
			sb.append(c).append(';');
		
		return sb.toString();
	}
}