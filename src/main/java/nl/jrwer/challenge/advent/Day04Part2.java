package nl.jrwer.challenge.advent;

import java.util.List;

//--- Part Two ---
//
//It seems like there is still quite a bit of duplicate work planned. Instead, the Elves would like to know the number of pairs that overlap at all.
//
//In the above example, the first two pairs (2-4,6-8 and 2-3,4-5) don't overlap, while the remaining four pairs (5-7,7-9, 2-8,3-7, 6-6,4-6, and 2-6,4-8) do overlap:
//
//    5-7,7-9 overlaps in a single section, 7.
//    2-8,3-7 overlaps all of the sections 3 through 7.
//    6-6,4-6 overlaps in a single section, 6.
//    2-6,4-8 overlaps in sections 4, 5, and 6.
//
//So, in this example, the number of overlapping assignment pairs is 4.
//
//In how many assignment pairs do the ranges overlap?

public class Day04Part2 extends Day04 {
	public static void main(String[] args) {
		try {
			Day04Part2 day = new Day04Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Pair> pairs = new InputLoader("input-day-04.txt").getInput();
		
		int total = 0;
		for(Pair pair : pairs) {
			if(pair.partialOverlap())
				total++;
		}
		
		System.out.println(total);
	}
}
