package nl.jrwer.challenge.advent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Day 12: Hill Climbing Algorithm ---
//
//You try contacting the Elves using your handheld device, but the river you're following must be too low to get a decent signal.
//
//You ask the device for a heightmap of the surrounding area (your puzzle input). The heightmap shows the local area from above broken into a grid; the elevation of each square of the grid is given by a single lowercase letter, where a is the lowest elevation, b is the next-lowest, and so on up to the highest elevation, z.
//
//Also included on the heightmap are marks for your current position (S) and the location that should get the best signal (E). Your current position (S) has elevation a, and the location that should get the best signal (E) has elevation z.
//
//You'd like to reach E, but to save energy, you should do it in as few steps as possible. During each step, you can move exactly one square up, down, left, or right. To avoid needing to get out your climbing gear, the elevation of the destination square can be at most one higher than the elevation of your current square; that is, if your current elevation is m, you could step to elevation n, but not to elevation o. (This also means that the elevation of the destination square can be much lower than the elevation of your current square.)
//
//For example:
//
//Sabqponm
//abcryxxl
//accszExk
//acctuvwj
//abdefghi
//
//Here, you start in the top-left corner; your goal is near the middle. You could start by moving down or right, but eventually you'll need to head toward the e at the bottom. From there, you can spiral around to the goal:
//
//v..v<<<<
//>v.vv<<^
//.>vv>E^^
//..v>>>^^
//..>>>>>^
//
//In the above diagram, the symbols indicate whether the path exits each square moving up (^), down (v), left (<), or right (>). The location that should get the best signal is still E, and . marks unvisited squares.
//
//This path reaches the goal in 31 steps, the fewest possible.
//
//What is the fewest steps required to move from your current position to the location that should get the best signal?


public class Day12 {
	public static void main(String[] args) {
		try {
			Day12 day = new Day12();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	public void start() {
		List<String> input = new InputLoader("input-day-12.txt").getInput();
		Map map = new Map(input);

		System.out.println("Start at: " + map.start.x + "," + map.start.y);
		System.out.println("End at: " + map.end.x + "," + map.end.y);
		
		map.findFastestPath();
		
		System.out.println("Step to highest point: " + map.steps);
	}
	
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
		
		public void findFastestPath() {
			findFastestPath(start);
		}
		
		public void findFastestPath(Coord coord) {
			LinkedList<Coord> q = new LinkedList<>();
			q.add(coord);
			
			while(!q.isEmpty()) {
				Coord c = q.removeFirst();
				
				if(c.equals(end)) {
					steps = c.steps;
					System.out.println("reached end");
					return;
				}
				
				q.addAll(nextMoves(c));
			}
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
	
	class Coord {
		int steps = 0;
		int x, y;
		
		public Coord(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public Coord moveRight() {
			x++;
			steps++;
			
			return this;
		}
		
		public Coord moveLeft() {
			x--;
			steps++;

			return this;
		}
		
		public Coord moveUp() {
			y++;
			steps++;

			return this;
		}
		
		public Coord moveDown() {
			y--;
			steps++;

			return this;
		}
		
		public Coord clone() {
			Coord clone = new Coord(x, y);
			clone.steps = steps;
			
			return clone;
		}
		
		@Override
		public boolean equals(Object object) {
			if(!(object instanceof Coord))
				throw new RuntimeException("");
			
			Coord c = (Coord) object;
			
			return this.x == c.x && this.y == c.y;
			
		}
	}
	
	class InputLoader extends BasicInputLoader<String>{
		public InputLoader(String file) {
			super(file);
		}

		@Override
		protected String handleLine(String line) {
			return line;
		}
	}
}
