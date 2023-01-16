package nl.jrwer.challenge.advent.day23;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Elves {
	private final Set<Elf> elves;
	private final Set<Coord> elvesCoords = new HashSet<>();
	
	private final List<Direction> direction = List.of(
			Direction.NORTH,
			Direction.SOUTH,
			Direction.WEST,
			Direction.EAST);
//	private Direction firstDirection = Direction.NORTH;
	private int rounds = 0;
	
	
	public Elves(Set<Elf> elves) {
		this.elves = elves;
		
		for(Elf elf : this.elves)
			this.elvesCoords.add(elf.getCurrentCoord());
	}
	
	public int executeRounds(int number) {
		do {
			executeRound();
		} while(rounds < number);

		int surfaceArea = new Dimension(elvesCoords).getSurfaceArea();
		System.out.println("  Surface area: " + surfaceArea);
		System.out.println("  Elves: " + this.elves.size());
		
		return surfaceArea - this.elves.size();
	}
	
	public int executeRounds() {
		do {
			// nothing to do
		} while(executeRound() > 0);

		return rounds;
	}
	
	public int executeRound() {
//		System.out.println(firstDirection);
		Set<Elf> proposedElves = propose();
		Set<Elf> movableElves = movableElves(proposedElves);
		move(movableElves);

		System.out.println(rounds + " - proposed: " + proposedElves.size() + " - move: " + movableElves.size());
		
		rounds++;
		// Update first direction
//		firstDirection = firstDirection.next();
//		print();
		
		return proposedElves.size();
	}
	
	private Set<Elf> propose() {
		Set<Elf> proposedElves = new HashSet<>();
		
		for(Elf elf : this.elves) {
			int n = elvesAt(elf, Direction.NORTH);
			int s = elvesAt(elf, Direction.SOUTH);
			int w = elvesAt(elf, Direction.WEST);
			int e = elvesAt(elf, Direction.EAST);
			
			
			if((n == 0 && s == 0 && w == 0 & e == 0) ||
					(n > 0 && s > 0 && w > 0 & e > 0)) {
				continue;
			} else {

				Direction dir = nextDirection(n, s, w, e);
				
//				if(elf.getCurrentCoord().y == 1)
//					System.out.println(elf.getCurrentCoord() +": d: " + dir + " n:" + n + " - s:" + s + " - w:" +  w + " - e:" +  e);
				
				elf.setProposedDirection(dir);
				proposedElves.add(elf);
			}
		}
		
		return proposedElves;
	}
	
	private int elvesAt(Elf currentElf, Direction checkDirection) {
		int amount = 0;
		Set<Coord> coordsToCheck = currentElf.getCurrentCoord().getAdjacent(checkDirection);
		
		for(Coord c : coordsToCheck)
			if(elvesCoords.contains(c))
				amount++;
						
		return amount;
	}
	
	private Direction nextDirection(int n, int s, int w, int e) {
		for(int i=0; i<direction.size(); i++) {
			Direction next = direction.get((i + rounds) % direction.size());
			
			if(n == 0 && next == Direction.NORTH)
				return Direction.NORTH;
			if(s == 0 && next == Direction.SOUTH)
				return Direction.SOUTH;
			if(w == 0 && next == Direction.WEST)
				return Direction.WEST;
			if(e == 0 && next == Direction.EAST)
				return Direction.EAST;
		}
		
		throw new RuntimeException();
	}
	
//	private Set<Elf> propose() {
//		Set<Elf> proposedElves = new HashSet<>();
//		
//		int free = 0;
//		
//		for(Elf elf : this.elves) {
//			if(noElfAdjacent(elf)) {
//				free++;
//				continue;
//			} else { 
//				getProposedDirection(elf, proposedElves);
//			}
//		}
//		
//		System.out.println("free: " + free);
//		
//		return proposedElves;
//	}
	
//	public boolean noElfAdjacent(Elf currentElf) {
//		Set<Coord> adjacentCoords = currentElf.getCurrentCoord().allAdjacentCoords();
//		
//		for(Coord adjCoord: adjacentCoords) 
//			if(elvesCoords.contains(adjCoord))
//				return false;
//		
//		return true;
//	}
	
//	private void getProposedDirection(Elf currentElf, Set<Elf> proposedElves) {
//		Direction checkDirection = firstDirection; 
//		
//		for(int i=0; i<Direction.values().length; i++) {
//			// if a free direction is found and not all direction are free
//			// dont continue searching.
//			
//			if(noElves(currentElf, checkDirection)) {
//				currentElf.setProposedDirection(checkDirection);
//				proposedElves.add(currentElf);
//				return;
//			}
//			
//			// go and check next
//			checkDirection = checkDirection.next(); 
//		}
//	}
	
//	private boolean noElves(Elf currentElf, Direction checkDirection) {
//		Set<Coord> coordsToCheck = currentElf.getCurrentCoord().getAdjacent(checkDirection);
//		
//		for(Coord c : coordsToCheck)
//			if(elvesCoords.contains(c))
//				return false;
//						
//		return true;
//	}
	
	private Set<Elf> movableElves(Set<Elf> proposedElves) {
		Set<Elf> movableElves = new HashSet<>();
		
		for(Elf elf : proposedElves)
			if(canMove(elf, proposedElves))
				movableElves.add(elf);
		
		return movableElves;
	}
	
	private boolean canMove(Elf currentElf, Set<Elf> proposedElves) {
		for(Elf elf : proposedElves) {
			if(elf.equals(currentElf))
				continue;
			
			if(currentElf.sameProposedCoord(elf))
				return false;
		}
		
		return true;
	}
	
	private void move(Set<Elf> movableElves) {
		for(Elf elf : movableElves)
			elf.move(elvesCoords);
	}
	
	public void print() {
		StringBuilder sb = new StringBuilder();
		Dimension dim = new Dimension(elvesCoords);
		
		for(int y=dim.minY; y<=dim.maxY; y++) {
			for(int x=dim.minX; x<=dim.maxX; x++)
				sb.append(hasElf(x, y) ? '#' : '.');
			
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	private boolean hasElf(int x, int y) {
		return elvesCoords.contains(new Coord(x, y));
	}
	
}
