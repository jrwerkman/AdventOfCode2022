package nl.jrwer.challenge.advent;

import java.util.List;

//--- Part Two ---
//
//As you walk up the hill, you suspect that the Elves will want to turn this into a hiking trail. The beginning isn't very scenic, though; perhaps you can find a better starting point.
//
//To maximize exercise while hiking, the trail should start as low as possible: elevation a. The goal is still the square marked E. However, the trail should still be direct, taking the fewest steps to reach its goal. So, you'll need to find the shortest path from any square at elevation a to the square marked E.
//
//Again consider the example from above:
//
//Sabqponm
//abcryxxl
//accszExk
//acctuvwj
//abdefghi
//
//Now, there are six choices for starting position (five marked a, plus the square marked S that counts as being at elevation a). If you start at the bottom-left square, you can reach the goal most quickly:
//
//...v<<<<
//...vv<<^
//...v>E^^
//.>v>>>^^
//>^>>>>>^
//
//This path reaches the goal in only 29 steps, the fewest possible.
//
//What is the fewest steps required to move starting from any square with elevation a to the location that should get the best signal?


public class Day12Part2 extends Day12 {
	public static void main(String[] args) {
		try {
			Day12Part2 day = new Day12Part2();
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
		
		map.findFastestPathOtherStartingPoint();
		
		System.out.println("Step to highest point: " + map.steps);
		
		// Answer: 492
	}
}
