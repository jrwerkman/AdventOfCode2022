package nl.jrwer.challenge.advent.day03;

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
		List<Rucksack> rucksacks = new InputLoader().getRucksacks();
		
		int total = 0;
		for(Rucksack rucksack : rucksacks) {
			total += rucksack.getPriority();
		}
		
		System.out.println(total);
	}
}
