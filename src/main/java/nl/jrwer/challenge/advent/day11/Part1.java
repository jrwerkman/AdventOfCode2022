package nl.jrwer.challenge.advent.day11;

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
		List<Monkey> monkeys = new InputLoader("input-day-11.txt").getInput();

		for(int i=0; i<20; i++)
			for(Monkey monkey : monkeys)
				monkey.throwItems(monkeys);
		
		int highest = 0, higher = 0;
		for(Monkey monkey : monkeys) {
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
		
		System.out.println("\nhigher: " + higher);
		System.out.println("highest: " + highest);
		System.out.println("\nOutcome: " + higher * highest);
	}
}
