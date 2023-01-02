package nl.jrwer.challenge.advent;

import java.util.HashMap;
import java.util.Map;

import nl.jrwer.challenge.advent.input.SingleLineInputLoader;

//--- Day 17: Pyroclastic Flow ---
//
//Your handheld device has located an alternative exit from the cave for you and the elephants. The ground is rumbling almost continuously now, but the strange valves bought you some time. It's definitely getting warmer in here, though.
//
//The tunnels eventually open into a very tall, narrow chamber. Large, oddly-shaped rocks are falling into the chamber from above, presumably due to all the rumbling. If you can't work out where the rocks will fall next, you might be crushed!
//
//The five types of rocks have the following peculiar shapes, where # is rock and . is empty space:
//
//####
//
//.#.
//###
//.#.
//
//..#
//..#
//###
//
//#
//#
//#
//#
//
//##
//##
//
//The rocks fall in the order shown above: first the - shape, then the + shape, and so on. Once the end of the list is reached, the same order repeats: the - shape falls first, sixth, 11th, 16th, etc.
//
//The rocks don't spin, but they do get pushed around by jets of hot gas coming out of the walls themselves. A quick scan reveals the effect the jets of hot gas will have on the rocks as they fall (your puzzle input).
//
//For example, suppose this was the jet pattern in your cave:
//
//>>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
//
//In jet patterns, < means a push to the left, while > means a push to the right. The pattern above means that the jets will push a falling rock right, then right, then right, then left, then left, then right, and so on. If the end of the list is reached, it repeats.
//
//The tall, vertical chamber is exactly seven units wide. Each rock appears so that its left edge is two units away from the left wall and its bottom edge is three units above the highest rock in the room (or the floor, if there isn't one).
//
//After a rock appears, it alternates between being pushed by a jet of hot gas one unit (in the direction indicated by the next symbol in the jet pattern) and then falling one unit down. If any movement would cause any part of the rock to move into the walls, floor, or a stopped rock, the movement instead does not occur. If a downward movement would have caused a falling rock to move into the floor or an already-fallen rock, the falling rock stops where it is (having landed on something) and a new rock immediately begins falling.
//
//Drawing falling rocks with @ and stopped rocks with #, the jet pattern in the example above manifests as follows:
//
//The first rock begins falling:
//|..@@@@.|
//|.......|
//|.......|
//|.......|
//+-------+
//
//Jet of gas pushes rock right:
//|...@@@@|
//|.......|
//|.......|
//|.......|
//+-------+
//
//Rock falls 1 unit:
//|...@@@@|
//|.......|
//|.......|
//+-------+
//
//Jet of gas pushes rock right, but nothing happens:
//|...@@@@|
//|.......|
//|.......|
//+-------+
//
//Rock falls 1 unit:
//|...@@@@|
//|.......|
//+-------+
//
//Jet of gas pushes rock right, but nothing happens:
//|...@@@@|
//|.......|
//+-------+
//
//Rock falls 1 unit:
//|...@@@@|
//+-------+
//
//Jet of gas pushes rock left:
//|..@@@@.|
//+-------+
//
//Rock falls 1 unit, causing it to come to rest:
//|..####.|
//+-------+
//
//A new rock begins falling:
//|...@...|
//|..@@@..|
//|...@...|
//|.......|
//|.......|
//|.......|
//|..####.|
//+-------+
//
//Jet of gas pushes rock left:
//|..@....|
//|.@@@...|
//|..@....|
//|.......|
//|.......|
//|.......|
//|..####.|
//+-------+
//
//Rock falls 1 unit:
//|..@....|
//|.@@@...|
//|..@....|
//|.......|
//|.......|
//|..####.|
//+-------+
//
//Jet of gas pushes rock right:
//|...@...|
//|..@@@..|
//|...@...|
//|.......|
//|.......|
//|..####.|
//+-------+
//
//Rock falls 1 unit:
//|...@...|
//|..@@@..|
//|...@...|
//|.......|
//|..####.|
//+-------+
//
//Jet of gas pushes rock left:
//|..@....|
//|.@@@...|
//|..@....|
//|.......|
//|..####.|
//+-------+
//
//Rock falls 1 unit:
//|..@....|
//|.@@@...|
//|..@....|
//|..####.|
//+-------+
//
//Jet of gas pushes rock right:
//|...@...|
//|..@@@..|
//|...@...|
//|..####.|
//+-------+
//
//Rock falls 1 unit, causing it to come to rest:
//|...#...|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//A new rock begins falling:
//|....@..|
//|....@..|
//|..@@@..|
//|.......|
//|.......|
//|.......|
//|...#...|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//The moment each of the next few rocks begins falling, you would see this:
//
//|..@....|
//|..@....|
//|..@....|
//|..@....|
//|.......|
//|.......|
//|.......|
//|..#....|
//|..#....|
//|####...|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|..@@...|
//|..@@...|
//|.......|
//|.......|
//|.......|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|..@@@@.|
//|.......|
//|.......|
//|.......|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|...@...|
//|..@@@..|
//|...@...|
//|.......|
//|.......|
//|.......|
//|.####..|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|....@..|
//|....@..|
//|..@@@..|
//|.......|
//|.......|
//|.......|
//|..#....|
//|.###...|
//|..#....|
//|.####..|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|..@....|
//|..@....|
//|..@....|
//|..@....|
//|.......|
//|.......|
//|.......|
//|.....#.|
//|.....#.|
//|..####.|
//|.###...|
//|..#....|
//|.####..|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|..@@...|
//|..@@...|
//|.......|
//|.......|
//|.......|
//|....#..|
//|....#..|
//|....##.|
//|....##.|
//|..####.|
//|.###...|
//|..#....|
//|.####..|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|..@@@@.|
//|.......|
//|.......|
//|.......|
//|....#..|
//|....#..|
//|....##.|
//|##..##.|
//|######.|
//|.###...|
//|..#....|
//|.####..|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//To prove to the elephants your simulation is accurate, they want to know how tall the tower will get after 2022 rocks have stopped (but before the 2023rd rock begins falling). In this example, the tower of rocks will be 3068 units tall.
//
//How many units tall will the tower of rocks be after 2022 rocks have stopped falling?


class Day17 {
	public static void main(String[] args) {
		try {
			Day17 day = new Day17();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		Sequence sequence = new InputLoader("input-day-17.txt").getInput();
		State state = new State(2_022L, sequence);
		
		long start = System.currentTimeMillis();
		Chamber c = new Chamber(state);
		System.out.println(c.calculate());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
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
	
	class Chamber {
		final State state;
		
		public Chamber(State state) {
			this.state = state;
		}
		
		public long calculate() {
			while(state.hasNextRock())
				fall(state.nextRock());
			
			return state.top;
		}
		
		private void fall(Rock rock) {
			while(state.isFalling()) {
				Direction dir = state.nextDirection();
				
				if(state.canMove())
					rock.move(dir);
				
				if(state.canFall()) 
					rock.move(Direction.DOWN);
			}
			
			state.addRock();
			state.setTop();
		}
		
		public void printHeight() {
			StringBuilder sb = new StringBuilder();
			
			for(long y=state.top; y>0; y--) {
				for(long x=0; x<state.width; x++)
					sb.append(state.get(x, y));
				
				sb.append('\n');
			}
			
			System.out.println(sb.toString());
		}
	}
	
	class Rocks {
		final Stones[] stones;
		int index = -1;
		
		public Rocks() {
			this.stones = new Stones[] {
					Stones.MINUS,
					Stones.PLUS,
					Stones.HOOK,
					Stones.PIPE,
					Stones.SQUARE};
		}
		
		public Rock next() {
			index++;
			
			if(index == stones.length)
				index = 0;
			
			return getRock(stones[index]);
		}
		
		private Rock getRock(Stones stone) {
			switch (stone) {
			case HOOK:
				return new Hook();
			case MINUS:
				return new Minus();
			case PIPE:
				return new Pipe();
			case PLUS:
				return new Plus();
			case SQUARE:
				return new Square();
			default:
				return null;
			}
		}
	}
	
	class Minus extends Rock {
		public Minus() {
			super(0, 3, 0);
		}
		
		@Override
		protected Coord[] getCoords() {
			return new Coord[] {new Coord(2,0), new Coord(3,0), new Coord(4,0), new Coord(5,0)};
		}
	}
	
	class Plus extends Rock {
		public Plus() {
			super(1, 3, 0);
		}
		
		@Override
		protected Coord[] getCoords() {
			return new Coord[] {new Coord(3,2), 
					new Coord(2,1), new Coord(3,1), new Coord(4,1), 
					new Coord(3,0)};
		}
	}
	
	class Hook extends Rock {
		public Hook() {
			super(2, 4, 0);
		}
		
		@Override
		protected Coord[] getCoords() {
			return new Coord[] {new Coord(4,2), 
					new Coord(4,1), 
					new Coord(2,0), new Coord(3,0), new Coord(4,0)};
		}
	}
	
	class Pipe extends Rock {
		public Pipe() {
			super(3, 3, 0);
		}
		
		@Override
		protected Coord[] getCoords() {
			return new Coord[] {new Coord(2,3), new Coord(2,2), new Coord(2,1), new Coord(2,0)};
		}
	}
	
	class Square extends Rock {
		public Square() {
			super(2, 3, 0);
		}

		@Override
		protected Coord[] getCoords() {
			return new Coord[] {new Coord(2,1), new Coord(3,1), 
					new Coord(2,0), new Coord(3,0)};
		}
	}

	abstract class Rock {
		Coord[] coords;
		final int mostLeft, mostRight;
		int highest;
		
		public Rock(int mostLeft, int mostRight, int highest) {
			this.coords = getCoords();
			
			this.mostLeft = mostLeft;
			this.mostRight = mostRight;
			this.highest = highest;
		}
		
		abstract Coord[] getCoords();
		
		public void move(Direction direction) {
			for(Coord c: coords)
				if(direction == Direction.LEFT) 
					c.moveLeft();
				else if(direction == Direction.RIGHT) 
					c.moveRight();
				else
					c.moveDown();
		}
		
		public Coord mostLeft() {
			return coords[mostLeft];
		}
		
		public Coord mostRight() {
			return coords[mostRight];
		}
		
		public void setY(long height) {
			for(Coord c : coords)
				c.y += height;
		}
		
//		public boolean directBelow(int x, int y) {
//			for(Coord c : coords)
//				if(c.x == x && c.y <= y + 1)
//					return true;
//
//			return false;
//		}
		
		public Coord getHighest() {
			return coords[highest];
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			for(Coord c : coords)
				sb.append(c).append(';');
			
			return sb.toString();
		}
	}
	
	enum Stones {
		MINUS,
		PLUS,
		HOOK,
		PIPE,
		SQUARE;
	}
	
	class Coord {
		long x, y;
		
		public Coord(long x, long y) {
			this.x = x;
			this.y = y; 
		}
		
		public void moveLeft() {
			x--;
		}
		
		public void moveRight() {
			x++;
		}	
		
		public void moveDown() {
			y--;
		}
		
		@Override
		public String toString() {
			return String.format("%d,%d", x,y);
		}
	}
	
	class Sequence {
		final Direction[] sequence;
		int index = 0;
		
		public Sequence(String input) {
			this.sequence = new Direction[input.length()];
			
			for(int i=0; i<input.length(); i++)
				this.sequence[i] = input.charAt(i) == '<' ? 
						Direction.LEFT : Direction.RIGHT;
		}
		
		public boolean hasNext() {
			return index < sequence.length;
		}
		
		public Direction next() {
			if(index == sequence.length)
				index = 0;

			Direction dir = sequence[index];
			index++;
			
			return dir;
		}
	}
	
	enum Direction {
		LEFT,RIGHT,DOWN;
	}
	
	class InputLoader extends SingleLineInputLoader<Sequence>{
				
		public InputLoader(String file) {
			super(file);
		}

		@Override
		protected Sequence handleLine(String line) {
			return new Sequence(line);
		}
	}
}
