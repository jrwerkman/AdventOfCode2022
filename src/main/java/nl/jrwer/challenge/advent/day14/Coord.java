package nl.jrwer.challenge.advent.day14;

class Coord {
	int x, y;
	char value;
	
	public Coord(int x, int y, char value) {
		this.x = x;
		this.y = y;
		
		this.value = value;
	}
	
	public Coord(String input, char value) {
		String[] split = input.split(",");
		
		this.x = Integer.parseInt(split[0]);
		this.y = Integer.parseInt(split[1]);
		
		this.value = value;
	}
	
	public boolean equals(int x, int y) {
		return this.x == x && this.y == y;
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
		return x + "," + y + " - " + value;
	}
	
	public Coord left() {
		Coord c = this.clone();
		c.x--;
		
		return c;
	}
	
	public Coord down() {
		Coord c = this.clone();
		c.y++;
		
		return c;
	}
	
	public Coord up() {
		Coord c = this.clone();
		c.y--;
		
		return c;
	}
	
	public Coord right() {
		Coord c = this.clone();
		c.x++;
		
		return c;
	}
	
	public Coord clone() {
		return new Coord(x,y,value);
	}
}