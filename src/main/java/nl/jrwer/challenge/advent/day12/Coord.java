package nl.jrwer.challenge.advent.day12;

class Coord {
	int steps = 0;
	int x, y;
	
	public Coord(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public Coord moveRight() {
		x++;
		steps++;
		
		return this;
	}
	
	public Coord moveLeft() {
		x--;
		steps++;

		return this;
	}
	
	public Coord moveUp() {
		y++;
		steps++;

		return this;
	}
	
	public Coord moveDown() {
		y--;
		steps++;

		return this;
	}
	
	public Coord clone() {
		Coord clone = new Coord(x, y);
		clone.steps = steps;
		
		return clone;
	}
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Coord))
			throw new RuntimeException("");
		
		Coord c = (Coord) object;
		
		return this.x == c.x && this.y == c.y;
		
	}
}