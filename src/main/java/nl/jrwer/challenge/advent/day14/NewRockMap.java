package nl.jrwer.challenge.advent.day14;

import java.util.List;

class NewRockMap extends RockMap {
	
	final int floor;

	public NewRockMap(List<String> rocks) {
		super(rocks);
		
		floor = findFloor();
	}
	
	private int findFloor() {
		int lowestY = 0;
		
		for(Coord c : coords) {
			if(c.y > lowestY)
				lowestY = c.y;
		}
		
		return lowestY + 2;
	}
	
	@Override
	protected Coord inXAxis(Coord c) {
		Coord highest = super.inXAxis(c);
		
		if(highest == null)
			highest = new Coord(c.x, floor, '#');
		
		return highest;
	}
	
	@Override
	protected boolean isOccupied(Coord c) {
		if(c.y == floor)
			return true;
		
		return coords.contains(c);
	}
	
	@Override
	protected boolean addStone(int x, int y) {
		add(x, y, 'o');
		amount++;
		
		return !fallingStart.equals(x, y);
	}
}