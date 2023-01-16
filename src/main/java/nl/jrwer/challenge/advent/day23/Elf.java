package nl.jrwer.challenge.advent.day23;

import java.util.Set;

public class Elf {
	public final int id;
	public final int x;
	public final int y;
	
	public Elf(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public Elf move(Direction dir) {
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
	
	public Elf moveNorth() {
		return new Elf(id, x, y - 1);
	}
	
	public Elf moveSouth() {
		return new Elf(id, x, y + 1);
	}
	
	public Elf moveWest() {
		return new Elf(id, x - 1, y);
	}
	
	public Elf moveEast() {
		return new Elf(id, x + 1, y);
	}
	
	public Set<Elf> getAdjacent(Direction direction) {
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
	
	public Set<Elf> adjacentNorth() {
		return Set.of(
				new Elf(id, x, y - 1), // N
				new Elf(id, x + 1, y - 1), // NE
				new Elf(id, x - 1, y - 1)); // NW				
	}
	
	public Set<Elf> adjacentSouth() {
		return Set.of(
				new Elf(id, x, y + 1), // S
				new Elf(id, x + 1, y + 1), // SE
				new Elf(id, x - 1, y + 1)); // SW
	}
	
	public Set<Elf> adjacentWest() {
		return Set.of(
				new Elf(id, x - 1, y), // W
				new Elf(id, x - 1, y - 1), // NW
				new Elf(id, x - 1, y + 1)); // SW
		
	}
	
	public Set<Elf> adjacentEast() {
		return Set.of(
				new Elf(id, x + 1, y), // E
				new Elf(id, x + 1, y - 1), // NE
				new Elf(id, x + 1, y + 1)); // SE
	}	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Elf) {
			Elf c = (Elf) obj;
			
			return x == c.x && y == c.y;
		}
		
		return false;
		
	}
	
    @Override
    public int hashCode() {
        return (x << 8) | y;
    }
}
