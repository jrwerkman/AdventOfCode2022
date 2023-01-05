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
		List<Monkey> monkeys = new InputLoader("input-day-21.txt").getInput();
		
		long start = System.currentTimeMillis();
		Calculator c = new Calculator(monkeys);
		System.out.println("Result: " + c.getValueRootMonkey());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
