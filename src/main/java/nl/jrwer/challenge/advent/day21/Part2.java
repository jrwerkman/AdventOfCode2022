package nl.jrwer.challenge.advent.day21;

import java.util.List;

class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Monkey> monkeys = new InputLoader("input-day-21-example.txt").getInput();
		
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
