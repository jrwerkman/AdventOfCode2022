package nl.jrwer.challenge.advent.day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Map {
	char[][] map;
	
	final int width, height;
	
	Coord start;
	Coord end;
	
	int steps;
	
	int max = 1, min = 50;
	
	List<Coord> visited = new ArrayList<>();
	
	public Map(List<String> input) {
		map = new char[input.size()][];
		
		for(int y=0; y<map.length; y++) {
			int realY = map.length - y - 1; // reverse 
			map[realY] = input.get(y).toCharArray();
			
			for(int x=0; x<map[realY].length; x++) {
				if(map[realY][x] == 'S') {
					start = new Coord(x, realY);
					map[realY][x] = 'a';
				}
				
				if(map[realY][x] == 'E') {
					end = new Coord(x, realY);
					map[realY][x] = 'z';
				}
			}
		}
					
		height = map.length;
		width = map[0].length;
	}
	
	public void findFastestPathOtherStartingPoint() {
		int leastSteps = Integer.MAX_VALUE;
		
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				if(getHeight(x, y) == 'a') {
					System.out.print("Trying route from, " + x + "," + y + ": ");
					visited.clear();
					Coord c = findFastestPath(new Coord(x, y));
					
					if(c == null)
						System.out.println("No route found");
					else 
						System.out.println("Needed steps: " + c.steps);
					if(c != null && c.steps < leastSteps)
						leastSteps = c.steps;
				}
			}
		}
		
		steps = leastSteps;
	}
	
	public void findFastestPath() {
		Coord c = findFastestPath(start);
		
		if(c != null)
			steps = c.steps;
	}
	
	public Coord findFastestPath(Coord coord) {
		LinkedList<Coord> q = new LinkedList<>();
		q.add(coord);
		
		while(!q.isEmpty()) {
			Coord c = q.removeFirst();
			
			if(c.equals(end))
				return c;
			
			q.addAll(nextMoves(c));
		}
		
		return null;
	}
	
	public List<Coord> nextMoves(Coord coord) {
		List<Coord> next = new ArrayList<>();
		
		if(moveUpPossible(coord))
			add(next, coord.clone().moveUp());
		
		if(moveRightPossible(coord))
			add(next, coord.clone().moveRight());
		
		if(moveLeftPossible(coord))
			add(next, coord.clone().moveLeft());
		
		if(moveDownPossible(coord))
			add(next, coord.clone().moveDown());

		return next;
	}
		
	public void add(List<Coord> next, Coord c) {
		next.add(c);
		visited.add(c);
	}
	
	public char getHeight(Coord coord) {
		return getHeight(coord.x, coord.y);
	}
	
	public char getHeight(int x, int y) {
		return map[y][x];
	}
	
	public boolean moveRightPossible(Coord coord) {
		if(coord.x >= width - 1)
			return false;
		
		return movePossible(coord, new Coord(coord.x + 1, coord.y));
	}
	
	public boolean moveLeftPossible(Coord coord) {
		if(coord.x <= 0)
			return false;
		
		return movePossible(coord, new Coord(coord.x - 1, coord.y));
	}
	
	public boolean moveUpPossible(Coord coord) {
		if(coord.y >= height - 1)
			return false;
		
		return movePossible(coord, new Coord(coord.x, coord.y + 1));
	}
	
	public boolean moveDownPossible(Coord coord) {
		if(coord.y <= 0)
			return false;

		return movePossible(coord, new Coord(coord.x, coord.y - 1));
	}
	
	public boolean movePossible(Coord currentCoord, Coord nextCoord) {
		if(visited.contains(nextCoord))
			return false;
		
		int currentHeight = getHeight(currentCoord);
		int nextHeight = getHeight(nextCoord);
		
		return nextHeight >= currentHeight - min && nextHeight <= currentHeight + max;
	}
}