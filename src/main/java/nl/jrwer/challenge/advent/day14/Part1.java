package nl.jrwer.challenge.advent.day14;

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
		List<String> rocks = new InputLoader("input-day-14.txt").getInput();

		long start = System.currentTimeMillis();
		RockMap map = new RockMap(rocks);

		while(map.nextRock()) {
			// do nothin
		}
		
		System.out.println("Units of sand: " + map.amount);
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
