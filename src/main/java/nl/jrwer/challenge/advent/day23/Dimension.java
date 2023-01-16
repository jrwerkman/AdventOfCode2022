package nl.jrwer.challenge.advent.day23;

import java.util.Set;

public class Dimension {
	public int minX, maxX, minY, maxY;
	
	public Dimension(Set<Coord> coord) {
		this.set(coord);
	}
	
	private void set(Set<Coord> coords) {
		if(coords.size() == 0)
			throw new RuntimeException();
		
		minX = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		minY = Integer.MAX_VALUE;
		maxY = Integer.MIN_VALUE;
		
		for(Coord coord : coords) {
			if(coord.x < minX)
				minX = coord.x;
			if(coord.x > maxX)
				maxX = coord.x;

			if(coord.y < minY)
				minY = coord.y;
			if(coord.y > maxY)
				maxY = coord.y;
		}
	}
	
	public int getSurfaceArea() {
		return ((maxX - minX) + 1) * ((maxY - minY) + 1);
	}	
}
