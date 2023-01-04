package nl.jrwer.challenge.advent.day17;

import java.util.HashMap;
import java.util.Map;

class State {
	final long fallingRocks;
	final int width = 7;
	final Sequence sequence;
	final Rocks rocks = new Rocks();
	
	long rockCount = 0;
	long sequenceCount = 0;
	long top = 0;
	
	boolean currentRockFalling = true;
	Rock currentRock;
	Direction currentDirection;
	Map<Long, Map<Long, Boolean>> chamber = new HashMap<>();

	public State(long fallingRocks, Sequence sequence) {
		this.fallingRocks = fallingRocks;
		this.sequence = sequence;
		
		for(long i=0; i<width; i++)
			chamber.put(i, new HashMap<>());			
	}
	
	public Rock nextRock() {
		currentRock = rocks.next();
		// Move rock up
		currentRock.setY(top + 4);

		rockCount++;
		currentRockFalling = true;
		
		return currentRock;
	}
	
	public boolean hasNextRock() {
		return rockCount < fallingRocks;
	}
	
	public Direction nextDirection() {
		currentDirection = sequence.next(); 
		sequenceCount++;
		return currentDirection;
	}

	public void set(long x, long y) {
		chamber.get(x).put(y, true);
	}
	
	public Boolean get(long x, long y) {
		return chamber.get(x).getOrDefault(y, false);
	}
	
	public boolean canMove() {
		Coord l = currentRock.mostLeft();
		Coord r = currentRock.mostRight();
		
		if((l.x == 0 && currentDirection == Direction.LEFT) 
				|| (r.x == width - 1 && currentDirection == Direction.RIGHT))
			return false;
		
		if(currentDirection == Direction.LEFT)
			for(Coord c : currentRock.coords)
				if(c.x > 0 && get(c.x - 1, c.y) && currentDirection == Direction.LEFT)
					return false;
		
		if(currentDirection == Direction.RIGHT)
			for(Coord c : currentRock.coords)
				if(c.x < width - 1 && get(c.x + 1, c.y) && currentDirection == Direction.RIGHT)
					return false;
		
		return true;
	}
	
	public boolean isFalling() {
		return currentRockFalling;
	}
	
	public boolean canFall() {
		for(Coord c : currentRock.coords)
			if(c.y <= 1 || get(c.x, c.y - 1))
				currentRockFalling = false;
		
		return currentRockFalling;
	}
	
	public void setTop() {
		long highestPointNewRock = currentRock.getHighest().y;
		
		if(highestPointNewRock > top)
			top = currentRock.getHighest().y;
	}
	
	public void addRock() {
		for(Coord c : currentRock.coords)
			set(c.x, c.y);
	}
}