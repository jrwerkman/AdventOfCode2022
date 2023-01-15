package nl.jrwer.challenge.advent.day23;

import java.util.List;

public class Elves {
	private final List<Elf> elves;
	
	private Direction firstDirection = Direction.NORTH;
	
	
	public Elves(List<Elf> elves) {
		this.elves = elves;
	}
	
	public int executeRounds(int number) {
		for(int i=0; i<number; i++)
			executeRound();

		int surfaceArea = getSurface();
		System.out.println("  Surface area: " + surfaceArea);
		System.out.println("  Elves: " + elves.size());
		
		return surfaceArea - elves.size();
	}
	
	public int getSurface() {
		Dimension surface = getSurfaceDimension();
		
		System.out.println("  minX: " + surface.minX);
		System.out.println("  maxX: " + surface.maxX);
		System.out.println("  minY: " + surface.minY);
		System.out.println("  maxY: " + surface.maxY);
		
		return ((surface.maxX - surface.minX) + 1) * ((surface.maxY - surface.minY) + 1);
	}
	
	public Dimension getSurfaceDimension() {
		if(elves.size() == 0)
			throw new RuntimeException();
		
		Coord coord = elves.get(0).getCurrentCoord();
		Dimension surface = new Dimension();
		
		surface.minX = coord.x;
		surface.maxX = coord.x;
		surface.minY = coord.y;
		surface.maxY = coord.y;
		
		for(int i=1; i<elves.size(); i++) {
			coord = elves.get(i).getCurrentCoord();
//			System.out.println(coord);
			
			if(coord.x < surface.minX)
				surface.minX = coord.x;
			if(coord.x > surface.maxX)
				surface.maxX = coord.x;

			if(coord.y < surface.minY)
				surface.minY = coord.y;
			if(coord.y > surface.maxY)
				surface.maxY = coord.y;
		}
		
		return surface;
	}
	
	public void executeRound() {
//		System.out.println(firstDirection);
		propose();
		setCanMove();
		move();
		
		// Update first direction
		firstDirection = firstDirection.next();
//		print();
	}
	
	private void propose() {
		for(Elf elf : elves) {
			Direction proposedDirection = getProposedDirection(elf);
			
			if(proposedDirection != null)
				elf.setProposedDirection(proposedDirection);
		}
	}
	
	private Direction getProposedDirection(Elf currentElf) {
		AdjacentCoords adjacentCoords = currentElf.getAdjacentCoords();
		
		int freeDirections = 0;
		Direction firstFreeDirection = null;
		Direction checkDirection = firstDirection; 

		for(int i=0; i<Direction.values().length; i++) {
			// if a free direction is found and not all direction are free
			// dont continue searching.
			if(freeDirections == 1 && i < freeDirections)
				break;
			
			if(!hasElf(adjacentCoords, checkDirection)) {
				// if is a free direction add
				freeDirections++;
				
				// register the first free direction
				if(firstFreeDirection == null)
					firstFreeDirection = checkDirection; 
			}

			
			// go and check next
			checkDirection = checkDirection.next(); 
		}
		
		return freeDirections == 4 ? null : firstFreeDirection;
	}
	
	private boolean hasElf(AdjacentCoords adjacentCoords, Direction checkDirection) {
		for(Elf elf : elves)
			if(adjacentCoords.hasElf(checkDirection, elf))
				return true;
		
		return false;
	}
	
	private void setCanMove() {
		for(Elf elf : elves)
			setCanMove(elf);
	}
	
	private void setCanMove(Elf currentElf) {
		if(!currentElf.hasProposedCoords())
			return;
		
		for(Elf elf : elves) {
			if(elf.equals(currentElf))
				continue;
			
			if(currentElf.sameProposedCoord(elf))
				return;
		}
		
		// default canMove is false
		currentElf.setCanMove(true);
	}
	
	private void move() {
		for(Elf elf : elves)
			elf.move();
	}
	
	public void print() {
		StringBuilder sb = new StringBuilder();
		Dimension dim = getSurfaceDimension();
		
		for(int y=dim.minY; y<=dim.maxY; y++) {
			for(int x=dim.minX; x<=dim.maxX; x++)
				sb.append(hasElf(x, y) ? '#' : '.');
			
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	private boolean hasElf(int x, int y) {
		Coord c = new Coord(x, y);
		
		for(Elf elf : elves)
			if(elf.getCurrentCoord().equals(c))
				return true;
		
		return false;
	}
	
}
