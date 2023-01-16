package nl.jrwer.challenge.advent.day23;

import java.util.Set;

public class Dimension {
	public int minX, maxX, minY, maxY;
	
	public Dimension(Set<Elf> elves) {
		this.set(elves);
	}
	
	private void set(Set<Elf> elves) {
		if(elves.size() == 0)
			throw new RuntimeException();
		
		minX = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		minY = Integer.MAX_VALUE;
		maxY = Integer.MIN_VALUE;
		
		for(Elf elf : elves) {
			if(elf.x < minX)
				minX = elf.x;
			if(elf.x > maxX)
				maxX = elf.x;

			if(elf.y < minY)
				minY = elf.y;
			if(elf.y > maxY)
				maxY = elf.y;
		}
	}
	
	public int getSurfaceArea() {
		return ((maxX - minX) + 1) * ((maxY - minY) + 1);
	}	
}
