package nl.jrwer.challenge.advent.day14;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RockMap {
	final Coord fallingStart = new Coord(500, 0);
	final int lowest;
	final int floor;

	Set<Coord> coords = new HashSet<>();
	int amount = 0;

	public RockMap(List<String> rocks) {
		for (String rock : rocks)
			addCoords(rock.split(" -> "));
		
		lowest = findLowest();
		floor = lowest + 2;
	}
	
	private int findLowest() {
		int lowestY = 0;
		
		for(Coord c : coords) {
			if(c.y > lowestY)
				lowestY = c.y;
		}
		
		return lowestY;
	}	
	
	public boolean nextRock() {
		return nextRock(fallingStart);
	}

	private boolean nextRock(Coord stone) {
		Coord obstacle;
		do {
			obstacle = stone.down();
			
			if(coords.contains(obstacle))
				break;
			else 
				stone = obstacle;
		} while(canFall(stone));

		
		boolean leftOccupied = isOccupied(obstacle.left());
		boolean rightOccupied = isOccupied(obstacle.right());

		if(!next(stone, leftOccupied, rightOccupied)) {
			return false;
		} else if (leftOccupied && rightOccupied) {
			amount++;
			coords.add(obstacle.up());
			return true;
		} else if (!leftOccupied) {
			return nextRock(stone.left());
		} else {
			return nextRock(stone.right());
		}
	}
	
	protected boolean canFall(Coord stone) {
		return stone.y < lowest;
	}
	
	protected boolean next(Coord stone, boolean leftOccupied, boolean rightOccupied) {
		return !(stone.y >= lowest);
	}

	protected boolean isOccupied(Coord stone) {
		return coords.contains(stone);
	}

	private void addCoords(String[] rockPath) {
		Coord begin = new Coord(rockPath[0]);
		coords.add(begin);

		for (int i = 1; i < rockPath.length; i++) {
			Coord end = new Coord(rockPath[i]);

			addDelta(begin, end);
			coords.add(end);

			begin = end;
		}
	}

	private void addDelta(Coord first, Coord second) {
		if (first.x == second.x) {
			if (first.y < second.y)
				for (int i = first.y + 1; i < second.y; i++)
					coords.add(new Coord(first.x, i));
			else
				for (int i = second.y + 1; i < first.y; i++)
					coords.add(new Coord(first.x, i));
		} else {
			if (first.x < second.x)
				for (int i = first.x + 1; i < second.x; i++)
					coords.add(new Coord(i, first.y));
			else
				for (int i = second.x + 1; i < first.x; i++)
					coords.add(new Coord(i, first.y));
		}
	}
}