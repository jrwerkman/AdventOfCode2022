package nl.jrwer.challenge.advent;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Day 14: Regolith Reservoir ---
//
//The distress signal leads you to a giant waterfall! Actually, hang on - the signal seems like it's coming from the waterfall itself, and that doesn't make any sense. However, you do notice a little path that leads behind the waterfall.
//
//Correction: the distress signal leads you behind a giant waterfall! There seems to be a large cave system here, and the signal definitely leads further inside.
//
//As you begin to make your way deeper underground, you feel the ground rumble for a moment. Sand begins pouring into the cave! If you don't quickly figure out where the sand is going, you could quickly become trapped!
//
//Fortunately, your familiarity with analyzing the path of falling material will come in handy here. You scan a two-dimensional vertical slice of the cave above you (your puzzle input) and discover that it is mostly air with structures made of rock.
//
//Your scan traces the path of each solid rock structure and reports the x,y coordinates that form the shape of the path, where x represents distance to the right and y represents distance down. Each path appears as a single line of text in your scan. After the first point of each path, each point indicates the end of a straight horizontal or vertical line to be drawn from the previous point. For example:
//
//498,4 -> 498,6 -> 496,6
//503,4 -> 502,4 -> 502,9 -> 494,9
//
//This scan means that there are two paths of rock; the first path consists of two straight lines, and the second path consists of three straight lines. (Specifically, the first path consists of a line of rock from 498,4 through 498,6 and another line of rock from 498,6 through 496,6.)
//
//The sand is pouring into the cave from point 500,0.
//
//Drawing rock as #, air as ., and the source of the sand as +, this becomes:
//
//
//  4     5  5
//  9     0  0
//  4     0  3
//0 ......+...
//1 ..........
//2 ..........
//3 ..........
//4 ....#...##
//5 ....#...#.
//6 ..###...#.
//7 ........#.
//8 ........#.
//9 #########.
//
//Sand is produced one unit at a time, and the next unit of sand is not produced until the previous unit of sand comes to rest. A unit of sand is large enough to fill one tile of air in your scan.
//
//A unit of sand always falls down one step if possible. If the tile immediately below is blocked (by rock or sand), the unit of sand attempts to instead move diagonally one step down and to the left. If that tile is blocked, the unit of sand attempts to instead move diagonally one step down and to the right. Sand keeps moving as long as it is able to do so, at each step trying to move down, then down-left, then down-right. If all three possible destinations are blocked, the unit of sand comes to rest and no longer moves, at which point the next unit of sand is created back at the source.
//
//So, drawing sand that has come to rest as o, the first unit of sand simply falls straight down and then stops:
//
//......+...
//..........
//..........
//..........
//....#...##
//....#...#.
//..###...#.
//........#.
//......o.#.
//#########.
//
//The second unit of sand then falls straight down, lands on the first one, and then comes to rest to its left:
//
//......+...
//..........
//..........
//..........
//....#...##
//....#...#.
//..###...#.
//........#.
//.....oo.#.
//#########.
//
//After a total of five units of sand have come to rest, they form this pattern:
//
//......+...
//..........
//..........
//..........
//....#...##
//....#...#.
//..###...#.
//......o.#.
//....oooo#.
//#########.
//
//After a total of 22 units of sand:
//
//......+...
//..........
//......o...
//.....ooo..
//....#ooo##
//....#ooo#.
//..###ooo#.
//....oooo#.
//...ooooo#.
//#########.
//
//Finally, only two more units of sand can possibly come to rest:
//
//......+...
//..........
//......o...
//.....ooo..
//....#ooo##
//...o#ooo#.
//..###ooo#.
//....oooo#.
//.o.ooooo#.
//#########.
//
//Once all 24 units of sand shown above have come to rest, all further sand flows out the bottom, falling into the endless void. Just for fun, the path any new sand takes before falling forever is shown here with ~:
//
//.......+...
//.......~...
//......~o...
//.....~ooo..
//....~#ooo##
//...~o#ooo#.
//..~###ooo#.
//..~..oooo#.
//.~o.ooooo#.
//~#########.
//~..........
//~..........
//~..........
//
//Using your scan, simulate the falling sand. How many units of sand come to rest before sand starts flowing into the abyss below?

class Day14 {
	public static void main(String[] args) {
		try {
			Day14 day = new Day14();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	public void start() {
		List<String> rocks = new InputLoader("input-day-14.txt").getInput();

		RockMap map = new RockMap(rocks);

		while(map.nextRock()) {
//			map.print();
		}
		
		map.print();
		System.out.println(map.amount);
		
		// Answer: 1061
	}
	
	class RockMap {
		final Coord fallingStart = new Coord(500, 0, 'R');
		
		List<Coord> coords = new ArrayList<>();
		
		int amount = 0;
		
		public RockMap(List<String> rocks) {
			for(String rock : rocks) {
				addCoords(rock.split(" -> "));
			}
		}
		
		public boolean nextRock() {
			return nextRock(fallingStart);
		}
		
		private boolean nextRock(Coord stone) {
			Coord obstacle = inXAxis(stone);
			
			if(obstacle == null)
				return false;
			
			boolean leftOccupied = isOccupied(obstacle.left());
			boolean rightOccupied = isOccupied(obstacle.right());
			
			if(leftOccupied && rightOccupied)
				return addStone(obstacle.x, obstacle.y - 1);
			
			if(!leftOccupied)
				return nextRock(new Coord(stone.x - 1, obstacle.y - 1, 'o'));
			else
				return nextRock(new Coord(stone.x + 1, obstacle.y - 1, 'o'));	
		}
		
		protected Coord inXAxis(Coord c) {
			Coord highest = null;
			
			for(Coord coord : coords)
				if(coord.x == c.x && coord.y > c.y) {
					if(highest == null)
						highest = coord;
					else if(highest.y > coord.y)
						highest = coord;
				}
			
//			System.out.println("highest: " + higest + "  (" + c + ")");
			
			return highest;
		}
		
		protected boolean isOccupied(Coord c) {
			return coords.contains(c);
		}
		
		private void addCoords(String[] rockPath) {
			Coord begin = new Coord(rockPath[0], '#');
			add(begin);
			
			for(int i=1; i<rockPath.length; i++) {
				Coord end = new Coord(rockPath[i], '#');

				addDelta(begin, end);
				add(end);
				
				begin = end;
			}
		}
		
		private void addDelta(Coord first, Coord second) {
			if(first.x == second.x) {
				if(first.y < second.y)
					for(int i=first.y + 1; i<second.y; i++)
						add(first.x, i, '#');
				else	
					for(int i=second.y + 1; i<first.y; i++)
						add(first.x, i, '#');
			} else {
				if(first.x < second.x)
					for(int i=first.x + 1; i<second.x; i++)
						add(i, first.y, '#');
				else	
					for(int i=second.x + 1; i<first.x; i++)
						add(i, first.y, '#');
			}
		}
		
		protected boolean addStone(int x, int y) {
			add(x, y, 'o');
			amount++;
			return true;
		}
		
		protected void add(int x, int y, char value) {
			add(new Coord(x, y, value));
		}
		
		private void add(Coord coord) {
			if(!coords.contains(coord))
				coords.add(coord);
		}
		
		public void print() {
			int xMin = 500, xMax = 500, yMin = 0, yMax = 0;
			
			for(Coord c : coords) {
				if(c.x < xMin)
					xMin = c.x;
				if(c.x > xMax)
					xMax = c.x;
				if(c.y < yMin)
					yMin = c.y;
				if(c.y > yMax)
					yMax = c.y;
			}
			
			xMin -= 3; yMin--; xMax += 2; yMax++;
			
			char[][] grid = new char[yMax - yMin][xMax - xMin];
			for(int y=0; y<grid.length; y++) {
				for(int x=0; x<grid[y].length; x++) {
					int realX = x + xMin + 1;
					int realY = y + yMin + 1;
					
					int index = coords.indexOf(new Coord(realX,realY,'#'));
					
					if(index >= 0)
						grid[y][x] = coords.get(index).value;
					else 
						grid[y][x] = '.';
				}
			}
			
			for(int i=0; i<grid.length; i++)
				System.out.println(grid[i]);
		}
	}
	
	class Coord {
		int x, y;
		char value;
		
		public Coord(int x, int y, char value) {
			this.x = x;
			this.y = y;
			
			this.value = value;
		}
		
		public Coord(String input, char value) {
			String[] split = input.split(",");
			
			this.x = Integer.parseInt(split[0]);
			this.y = Integer.parseInt(split[1]);
			
			this.value = value;
		}
		
		public boolean equals(int x, int y) {
			return this.x == x && this.y == y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Coord) {
				Coord c = (Coord) obj;
				return x == c.x && y == c.y;
			} else {
				throw new RuntimeException("Cannot compare Coord with: " + obj.getClass().getSimpleName());
			}
		}
		
		@Override
		public String toString() {
			return x + "," + y + " - " + value;
		}
		
		public Coord left() {
			Coord c = this.clone();
			c.x--;
			
			return c;
		}
		
		public Coord down() {
			Coord c = this.clone();
			c.y++;
			
			return c;
		}
		
		public Coord up() {
			Coord c = this.clone();
			c.y--;
			
			return c;
		}
		
		public Coord right() {
			Coord c = this.clone();
			c.x++;
			
			return c;
		}
		
		public Coord clone() {
			return new Coord(x,y,value);
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
