package nl.jrwer.challenge.advent.day24;

import java.util.Set;

public class Coord {
	final int x, y;
	final int time;
	final Type type;
	
	boolean visited;
	int blizzards = 0;
	
	public Coord(Type type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.time = 0;
	}
	
	public Coord(int time, Type type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.time = time;
	}
	
	@Override
	public int hashCode() {
        return (x << 8) | y;
	}
	
	@Override 
	public boolean equals(Object o) {
		if(o instanceof Coord) {
			Coord c = (Coord) o;
			
			return x == c.x && y == c.y;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "time: " + time + ", " + x + "," + y;
	}
	
	public Coord nextMinute() {
		return new Coord(time + 1, type, x, y);
	}

	public Coord clone() {
		return new Coord(time, type, x, y);
	}

	public Coord clone(Type type) {
		return new Coord(time, type, x, y);
	}
	
	public Set<Coord> getMoves() {
		return Set.of(
				new Coord(time + 1, type, x, y),
				new Coord(time + 1, type, x + 1, y),
				new Coord(time + 1, type, x, y + 1),
				new Coord(time + 1, type, x - 1, y),
				new Coord(time + 1, type, x, y - 1));
	}
}
