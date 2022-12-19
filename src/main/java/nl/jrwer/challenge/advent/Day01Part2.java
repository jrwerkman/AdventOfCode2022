package nl.jrwer.challenge.advent;

import java.util.List;

//--- Part Two ---
//
//By the time you calculate the answer to the Elves' question, they've already realized that the Elf carrying the most Calories of food might eventually run out of snacks.
//
//To avoid this unacceptable situation, the Elves would instead like to know the total Calories carried by the top three Elves carrying the most Calories. That way, even if one of those Elves runs out of snacks, they still have two backups.
//
//In the example above, the top three Elves are the fourth Elf (with 24000 Calories), then the third Elf (with 11000 Calories), then the fifth Elf (with 10000 Calories). The sum of the Calories carried by these three elves is 45000.
//
//Find the top three Elves carrying the most Calories. How many Calories are those Elves carrying in total?


/**
 * https://adventofcode.com/2022/day/1#part2
 * 
 * @author jan
 *
 */
public class Day01Part2 extends Day01 {
	public static void main(String[] args) {
		try {
			Day01Part2 day = new Day01Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Elf> elves = new InputLoader().getElves();

		elves.sort((elf1, elf2) -> elf2.calories - elf1.calories);
		
		int calories = 0;
		for(int i=0; i<3; i++)
			calories += elves.get(i).calories;
		
		System.out.println(String.format("The top 3 elves carry [%d] calories",
				calories));
	}
}
