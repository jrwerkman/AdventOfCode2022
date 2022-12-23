package nl.jrwer.challenge.advent;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Day 9: Rope Bridge ---
//
//This rope bridge creaks as you walk along it. You aren't sure how old it is, or whether it can even support your weight.
//
//It seems to support the Elves just fine, though. The bridge spans a gorge which was carved out by the massive river far below you.
//
//You step carefully; as you do, the ropes stretch and twist. You decide to distract yourself by modeling rope physics; maybe you can even figure out where not to step.
//
//Consider a rope with a knot at each end; these knots mark the head and the tail of the rope. If the head moves far enough away from the tail, the tail is pulled toward the head.
//
//Due to nebulous reasoning involving Planck lengths, you should be able to model the positions of the knots on a two-dimensional grid. Then, by following a hypothetical series of motions (your puzzle input) for the head, you can determine how the tail will move.
//
//Due to the aforementioned Planck lengths, the rope must be quite short; in fact, the head (H) and tail (T) must always be touching (diagonally adjacent and even overlapping both count as touching):
//
//....
//.TH.
//....
//
//....
//.H..
//..T.
//....
//
//...
//.H. (H covers T)
//...
//
//If the head is ever two steps directly up, down, left, or right from the tail, the tail must also move one step in that direction so it remains close enough:
//
//.....    .....    .....
//.TH.. -> .T.H. -> ..TH.
//.....    .....    .....
//
//...    ...    ...
//.T.    .T.    ...
//.H. -> ... -> .T.
//...    .H.    .H.
//...    ...    ...
//
//Otherwise, if the head and tail aren't touching and aren't in the same row or column, the tail always moves one step diagonally to keep up:
//
//.....    .....    .....
//.....    ..H..    ..H..
//..H.. -> ..... -> ..T..
//.T...    .T...    .....
//.....    .....    .....
//
//.....    .....    .....
//.....    .....    .....
//..H.. -> ...H. -> ..TH.
//.T...    .T...    .....
//.....    .....    .....
//
//You just need to work out where the tail goes as the head follows a series of motions. Assume the head and the tail both start at the same position, overlapping.
//
//For example:
//
//R 4
//U 4
//L 3
//D 1
//R 4
//D 1
//L 5
//R 2
//
//This series of motions moves the head right four steps, then up four steps, then left three steps, then down one step, and so on. After each step, you'll need to update the position of the tail if the step means the head is no longer adjacent to the tail. Visually, these motions occur as follows (s marks the starting position as a reference point):
//
//== Initial State ==
//
//......
//......
//......
//......
//H.....  (H covers T, s)
//
//== R 4 ==
//
//......
//......
//......
//......
//TH....  (T covers s)
//
//......
//......
//......
//......
//sTH...
//
//......
//......
//......
//......
//s.TH..
//
//......
//......
//......
//......
//s..TH.
//
//== U 4 ==
//
//......
//......
//......
//....H.
//s..T..
//
//......
//......
//....H.
//....T.
//s.....
//
//......
//....H.
//....T.
//......
//s.....
//
//....H.
//....T.
//......
//......
//s.....
//
//== L 3 ==
//
//...H..
//....T.
//......
//......
//s.....
//
//..HT..
//......
//......
//......
//s.....
//
//.HT...
//......
//......
//......
//s.....
//
//== D 1 ==
//
//..T...
//.H....
//......
//......
//s.....
//
//== R 4 ==
//
//..T...
//..H...
//......
//......
//s.....
//
//..T...
//...H..
//......
//......
//s.....
//
//......
//...TH.
//......
//......
//s.....
//
//......
//....TH
//......
//......
//s.....
//
//== D 1 ==
//
//......
//....T.
//.....H
//......
//s.....
//
//== L 5 ==
//
//......
//....T.
//....H.
//......
//s.....
//
//......
//....T.
//...H..
//......
//s.....
//
//......
//......
//..HT..
//......
//s.....
//
//......
//......
//.HT...
//......
//s.....
//
//......
//......
//HT....
//......
//s.....
//
//== R 2 ==
//
//......
//......
//.H....  (H covers T)
//......
//s.....
//
//......
//......
//.TH...
//......
//s.....
//
//After simulating the rope, you can count up all of the positions the tail visited at least once. In this diagram, s again marks the starting position (which the tail also visited) and # marks other positions the tail visited:
//
//..##..
//...##.
//.####.
//....#.
//s###..
//
//So, there are 13 positions the tail visited at least once.
//
//Simulate your complete hypothetical series of motions. How many positions does the tail of the rope visit at least once?


public class Day09 {
	public static void main(String[] args) {
		try {
			Day09 day = new Day09();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Motion> motions = new InputLoader("input-day-09.txt").getInput();

		Rope rope = new Rope(1);

		for(Motion motion : motions)
			rope.move(motion);

		rope.printResult();
		
		// 6081
		System.out.println(rope.getTail().visitedPositions());
	}
	
	class Motion {
		Direction direction;
		int steps;
		
		public Motion(String input) {
			String[] split = input.split(" ");
			
			if(split.length != 2)
				throw new RuntimeException("Wrong input: " + input);
			
			direction = Direction.getDirection(split[0].charAt(0));
			steps = Integer.parseInt(split[1]);
		}
	}
	
	class Rope {
		private Element head;		
		private Chart c = new Chart(this);
		private boolean debug = false;

		public Rope(int size) {
			this.head = new Element(size);
		}
		
		public void setDebug(boolean debug) {
			this.debug = debug;
		}
		
		public void move(Motion motion) {
			move(motion.direction, motion.steps);
		}
		
		public void move(Direction direction, int steps) {
			for(int i=0; i<steps; i++) {
				move(direction);
				
				if(debug)
					c.printState();
			}
		}
		
		private void move(Direction direction) {
			head.move(direction, null);
			head.registerPostion();
		}
		
		public Element getTail() {
			Element tail = head;
			
			while(tail.tail != null)
				tail = tail.tail;
			
			return tail;
		}
		
		public void printResult() {
			c.printResult();
		}
	}
	
	class Element extends Coord {
		Element tail = null;

		private List<Coord> visitedPositions = new ArrayList<>();
		
		public Element(int length) {
			super(0, 0);
			
			if(length != 0)
				tail = new Element(length - 1);
		}
		
		public int visitedPositions() {
			return visitedPositions.size();
		}
		
		public List<Coord> getVisitedPositions() {
			return visitedPositions;
		}
		
		protected void registerPostion() {
			if(!visitedPositions.contains(this))
				visitedPositions.add(this.clone());	
			
			if(tail != null)
				tail.registerPostion();
		}
		
		public void move(Element p) {
			this.move(null, p);
		}
		
		public void move(Direction direction, Element p) {
			if(p == null) {
				move(direction);
			} else if(!(p.x >= x - 1 && p.x <= x + 1 && p.y >= y - 1 && p.y <= y + 1)) {
				if(p.y == y + 1 || p.y == y + 2)
					y++;

				if(p.x == x + 1 || p.x == x + 2)
					x++;

				if(p.x == x - 1 || p.x == x - 2)
					x--;

				if(p.y == y - 1 || p.y == y - 2)
					y--;
			}
			
			if(tail != null)
				tail.move(this);
		}
	}
	
	public class Coord {
		protected int x, y;
		
		protected Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		protected void move(Direction direction) {
			if(direction == Direction.DOWN)	
				y--;
			if(direction == Direction.LEFT)	
				x--;
			if(direction == Direction.RIGHT)	
				x++;
			if(direction == Direction.UP)	
				y++;
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
		
		public boolean equals(int cX, int cY) {
			return x == cX && y == cY;
		}		
		
		@Override
		public String toString() {
			return x + "-" + y;
		}
		
		public Coord clone() {
			return new Coord(x, y);
		}
	}
	
	enum Direction {
		RIGHT,
		LEFT,
		UP,
		DOWN,
		UPRIGHT,
		UPLEFT,
		DOWNRIGHT,
		DOWNLEFT;
		
		public static Direction getDirection(char input) {
			switch(input) {
			case 'R':
				return RIGHT;
			case 'L':
				return LEFT;
			case 'U':
				return UP;
			case 'D':
				return DOWN;
			default:
				return null;
			}
		}
	}
	
	
	class Chart {
		private final Rope rope;
		private int xMax = 0, yMax = 0, xMin = 0, yMin = 0;
		private List<Coord> coords;
		List<Coord> elements = new ArrayList<>();
		
		public Chart(Rope rope) {
			this.rope = rope;
		}
		
		public void printResult() {
			getDimensions();
			
			StringBuilder sb = new StringBuilder();
			
			for(int y=yMax; y>=yMin; y--) {
				for(int x=xMin; x<=xMax; x++) {
					if(passed(x, y))
						sb.append('#');
					else
						sb.append('.');
				}
				
				sb.append('\n');
			}
			
			System.out.println(sb.toString());
		}
		
		public void printState() {
			getCurrentPositons();
			
			StringBuilder sb = new StringBuilder();
			
			for(int y=yMax; y>=yMin; y--) {
				for(int x=xMin; x<=xMax; x++) {
					int pos = atCurrentPosition(x, y);
					
					if(pos >= 0)
						sb.append(pos == 0 ? "H" : "" + pos);
					else
						sb.append('.');
				}
				
				sb.append('\n');
			}
			
			System.out.println(sb.toString());
		}
		
		private boolean passed(int x, int y) {
			for(Coord c : coords)
				if(c.equals(x, y))
					return true;

			return false;
		}
		
		private int atCurrentPosition(int x, int y) {
			for(int i=0; i<elements.size(); i++) {
				Coord c = elements.get(i);
				
				if(c.equals(x, y))
					return i;
			}

			return -1;
		}
		
		private void getCurrentPositons() {
			Element elem = rope.head;
			
			
			while(elem != null) {
				elements.add(elem);
				
				if(elem.x > xMax)
					xMax = elem.x;
				if(elem.x < xMin)
					xMin = elem.x;
				if(elem.y > yMax)
					yMax = elem.y;
				if(elem.y < yMin)
					yMin = elem.y;
				
				elem = elem.tail;
			}
		}
		
		private void getDimensions() {
			coords = rope.getTail().getVisitedPositions();
			
			for(Coord c : coords) {
				if(c.x > xMax)
					xMax = c.x;
				if(c.x < xMin)
					xMin = c.x;
				if(c.y > yMax)
					yMax = c.y;
				if(c.y < yMin)
					yMin = c.y;
			}
		}
	}	
	
	class InputLoader extends BasicInputLoader<Motion>{

		public InputLoader(String file) {
			super(file);
		}

		protected Motion handleLine(String line) {
			return new Motion(line);
		}
	}
}
