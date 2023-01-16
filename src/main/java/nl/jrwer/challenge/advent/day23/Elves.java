package nl.jrwer.challenge.advent.day23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Elves {
	private final Set<Elf> elves;
	
	private final List<Direction> direction = List.of(
			Direction.NORTH,
			Direction.SOUTH,
			Direction.WEST,
			Direction.EAST);

	private int rounds = 0;
	
	
	public Elves(Set<Elf> elves) {
		this.elves = elves;
	}
	
	public int executeRounds(int number) {
		do {
			executeRound();
		} while(rounds < number);

		int surfaceArea = new Dimension(elves).getSurfaceArea();
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
		Map<Elf, List<Elf>> proposedElves = propose();
		move(proposedElves);
		
		rounds++;
		return proposedElves.size();
	}
	
	private Map<Elf, List<Elf>> propose() {
		Map<Elf, List<Elf>> proposedElves = new HashMap<>();
		
		for(Elf elf : this.elves) {
			int n = elvesAt(elf, Direction.NORTH);
			int s = elvesAt(elf, Direction.SOUTH);
			int w = elvesAt(elf, Direction.WEST);
			int e = elvesAt(elf, Direction.EAST);
			
			
			if((n == 0 && s == 0 && w == 0 && e == 0) ||
					(n > 0 && s > 0 && w > 0 && e > 0)) {
				continue;
			} else {
				Direction dir = nextDirection(n, s, w, e);
				Elf moved = elf.move(dir);
				
				if(proposedElves.containsKey(moved)) {
					proposedElves.get(moved).add(elf);
				} else {
					List<Elf> elvings = new ArrayList<>();
					elvings.add(elf);
					proposedElves.put(moved, elvings);
				}
			}
		}
		
		return proposedElves;
	}
	
	private int elvesAt(Elf currentElf, Direction checkDirection) {
		int amount = 0;
		Set<Elf> coordsToCheck = currentElf.getAdjacent(checkDirection);
		
		for(Elf c : coordsToCheck)
			if(elves.contains(c))
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
	
	private void move(Map<Elf, List<Elf>> proposed) {
		for(Entry<Elf, List<Elf>> entry : proposed.entrySet()) {
			if(entry.getValue().size() == 1) {
				elves.remove(entry.getValue().get(0));
				elves.add(entry.getKey());
			}
		}
	}
	
	public void print() {
		StringBuilder sb = new StringBuilder();
		Dimension dim = new Dimension(elves);
		
		for(int y=dim.minY; y<=dim.maxY; y++) {
			for(int x=dim.minX; x<=dim.maxX; x++)
				sb.append(hasElf(x, y) ? '#' : '.');
			
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	private boolean hasElf(int x, int y) {
		return elves.contains(new Elf(-1, x, y));
	}
	
}
