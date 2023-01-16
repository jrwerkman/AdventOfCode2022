package nl.jrwer.challenge.advent.day23;

import java.util.Set;

public class Elf {
	private final int id;
	private Coord currentCoords;
	private Coord proposedCoordinates = null;
	
	public Elf(int id, int x, int y) {
		this.id = id;
		this.currentCoords = new Coord(x, y);
	}
	
	public boolean onCoord(Coord c) {
		return currentCoords.equals(c);
	}
	
	public boolean sameProposedCoord(Elf compare) {
		return proposedCoordinates.equals(compare.proposedCoordinates);
	}
	
	public void setProposedDirection(Direction proposedDirection) {
		this.proposedCoordinates = currentCoords.move(proposedDirection);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Elf) {
			Elf c = (Elf) obj;
			
			return id == c.id;
		}
		
		return false;
		
	}
	
	public void move(Set<Coord> elfCoords) {
		elfCoords.remove(currentCoords);
		elfCoords.add(proposedCoordinates);

		currentCoords = proposedCoordinates;
		proposedCoordinates = null;
	}
	
	public Coord getCurrentCoord() {
		return currentCoords;
	}
	
	public Coord getProposedCoord() {
		return proposedCoordinates;
	}
	
	@Override
	public int hashCode() {
		return currentCoords.hashCode();
	}
}
