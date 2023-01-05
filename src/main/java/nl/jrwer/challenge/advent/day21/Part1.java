package nl.jrwer.challenge.advent.day21;

import java.util.List;

class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<BaseMonkey> monkeys = new InputLoader("input-day-21.txt").getInput();
		
		long start = System.currentTimeMillis();
		Calculator c = new Calculator(monkeys);
		c.calculateValues();

		System.out.println("Result: " + c.rootMonkey.getValue());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
