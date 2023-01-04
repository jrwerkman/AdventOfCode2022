package nl.jrwer.challenge.advent.day11;

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
		List<SecondMonkey> monkeys = new SecondInputLoader("input-day-11.txt").getInput();

		for(int i=0; i<10000; i++)
			for(SecondMonkey monkey : monkeys)
				monkey.throwItems(monkeys);
		
		long highest = 0, higher = 0;
		for(SecondMonkey monkey : monkeys) {
			int inspectedItems = monkey.inspectedItems;
			System.out.println("Monkey " + monkey.number + ": " + monkey.inspectedItems);
			
			if(inspectedItems > higher) {
				if(inspectedItems > highest) {
					higher = highest;
					highest = inspectedItems;
				} else {
					higher = inspectedItems;
				}
			}
		}
		
		// TODO proper inheritance
		
		System.out.println("\nhigher: " + higher);
		System.out.println("highest: " + highest);
		System.out.println("\nOutcome: " + higher * highest);
	}
}
