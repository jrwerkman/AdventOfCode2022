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
		List<BaseMonkey> monkeys = new InputLoader("input-day-21.txt", true).getInput();
		
		long start = System.currentTimeMillis();
		SecondCalculator c = new SecondCalculator(monkeys);
		c.findYouMonkeyValue();

		RootMonkey rootMonkey = c.getRootMonkey();
		System.out.println(String.format("\nValue of rootmonkey is: %d (%d==%d)", 
				rootMonkey.getValue(),
				rootMonkey.getLeftValue(),
				rootMonkey.getRightValue()));

		YouMonkey youMonkey = c.getYouMonkey();

		System.out.println("Value of youmonkey is: " + youMonkey.getValue() + "\n");

		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
