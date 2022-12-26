package nl.jrwer.challenge.advent;

import java.util.List;

//--- Part Two ---
//
//You realize you misread the scan. There isn't an endless void at the bottom of the scan - there's floor, and you're standing on it!
//
//You don't have time to scan the floor, so assume the floor is an infinite horizontal line with a y coordinate equal to two plus the highest y coordinate of any point in your scan.
//
//In the example above, the highest y coordinate of any point is 9, and so the floor is at y=11. (This is as if your scan contained one extra rock path like -infinity,11 -> infinity,11.) With the added floor, the example above now looks like this:
//
//        ...........+........
//        ....................
//        ....................
//        ....................
//        .........#...##.....
//        .........#...#......
//        .......###...#......
//        .............#......
//        .............#......
//        .....#########......
//        ....................
//<-- etc #################### etc -->
//
//To find somewhere safe to stand, you'll need to simulate falling sand until a unit of sand comes to rest at 500,0, blocking the source entirely and stopping the flow of sand into the cave. In the example above, the situation finally looks like this after 93 units of sand come to rest:
//
//............o............
//...........ooo...........
//..........ooooo..........
//.........ooooooo.........
//........oo#ooo##o........
//.......ooo#ooo#ooo.......
//......oo###ooo#oooo......
//.....oooo.oooo#ooooo.....
//....oooooooooo#oooooo....
//...ooo#########ooooooo...
//..ooooo.......ooooooooo..
//#########################
//
//Using your scan, simulate the falling sand until the source of the sand becomes blocked. How many units of sand come to rest?

class Day14Part2 extends Day14 {
	public static void main(String[] args) {
		try {
			Day14Part2 day = new Day14Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	public void start() {
		List<String> rocks = new InputLoader("input-day-14.txt").getInput();

		NewRockMap map = new NewRockMap(rocks);
		System.out.println(map.floor);
		while(map.nextRock()) {
//			map.print();
		}
		
		map.print();
		System.out.println(map.amount);
		
		// Answer: 25055
	}
	
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
}
