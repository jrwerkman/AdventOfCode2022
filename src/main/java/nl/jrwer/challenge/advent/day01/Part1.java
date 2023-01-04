package nl.jrwer.challenge.advent.day01;

import java.util.List;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Elf> elves = new InputLoader().getElves();
		Elf highestCalorieElf = new Elf(-1);
		
		for(Elf elf : elves) {
			if(elf.calories > highestCalorieElf.calories)
				highestCalorieElf = elf;
		}
		
		System.out.println(String.format("Elf [%d] has the highest calories: [%d]",
				highestCalorieElf.number,
				highestCalorieElf.calories));
	}
}
