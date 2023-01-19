package nl.jrwer.challenge.advent.day14;

import java.util.List;

class NewRockMap extends RockMap {

	public NewRockMap(List<String> rocks) {
		super(rocks);
	}
	
	@Override
	protected boolean isOccupied(Coord stone) {
		if(stone.y == floor)
			return true;
		
		return coords.contains(stone);
	}

	@Override
	protected boolean next(Coord stone, boolean leftOccupied, boolean rightOccupied) {
		if(fallingStart.equals(stone) && leftOccupied  && rightOccupied) {
			// add last stone
			amount++;
			return false;
		}
		
		return true;

	}
	
	@Override
	protected boolean canFall(Coord stone) {
		return stone.y < floor;
	}
		
}