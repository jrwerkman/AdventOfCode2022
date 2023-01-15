package nl.jrwer.challenge.advent.day23;

import java.util.Objects;

public class Coord {
	int x, y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void moveNorth() {
		y++;
	}
	
	public void moveSouth() {
		y--;
	}
	
	public void moveWest() {
		x--;
	}
	
	public void moveEast() {
		x++;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Coord other = (Coord) obj;
		
		return x == other.x && y == other.y;
	}
	
	@Override
	public String toString() {
		return x+","+y;
	}
}
