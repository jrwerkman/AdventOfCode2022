package nl.jrwer.challenge.advent.day01;

import java.util.List;

public class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
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
