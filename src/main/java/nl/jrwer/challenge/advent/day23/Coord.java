package nl.jrwer.challenge.advent.day23;

import java.util.Set;

public class Coord {
	final int x, y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coord move(Direction dir) {
		switch(dir) {
		case NORTH:
			return moveNorth();
		case SOUTH:
			return moveSouth();
		case WEST:
			return moveWest();
		case EAST:
			return moveEast();
		default:
			throw new RuntimeException();
		}
	}
	
	public Coord moveNorth() {
		return new Coord(x, y - 1);
	}
	
	public Coord moveSouth() {
		return new Coord(x, y + 1);
	}
	
	public Coord moveWest() {
		return new Coord(x - 1, y);
	}
	
	public Coord moveEast() {
		return new Coord(x + 1, y);
	}
	
	public Set<Coord> getAdjacent(Direction direction) {
		switch(direction) {
		case NORTH:
			return adjacentNorth();
		case SOUTH:
			return adjacentSouth();
		case WEST:
			return adjacentWest();
		case EAST:
			return adjacentEast(); 
		default:
			throw new RuntimeException();
		}
	}	
	
	public Set<Coord> adjacentNorth() {
		return Set.of(
				new Coord(x, y - 1), // N
				new Coord(x + 1, y - 1), // NE
				new Coord(x - 1, y - 1)); // NW				
	}
	
	public Set<Coord> adjacentSouth() {
		return Set.of(
				new Coord(x, y + 1), // S
				new Coord(x + 1, y + 1), // SE
				new Coord(x - 1, y + 1)); // SW
	}
	
	public Set<Coord> adjacentWest() {
		return Set.of(
				new Coord(x - 1, y), // W
				new Coord(x - 1, y - 1), // NW
				new Coord(x - 1, y + 1)); // SW
		
	}
	
	public Set<Coord> adjacentEast() {
		return Set.of(
				new Coord(x + 1, y), // E
				new Coord(x + 1, y - 1), // NE
				new Coord(x + 1, y + 1)); // SE
	}
	
    @Override
    public int hashCode() {
        return (x << 8) | y;
    }

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coord) {
			Coord other = (Coord) obj;
			
			return x == other.x && y == other.y;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return x+","+y;
	}
}
