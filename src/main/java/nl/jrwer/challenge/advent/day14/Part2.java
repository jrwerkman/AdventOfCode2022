package nl.jrwer.challenge.advent.day14;

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
		List<String> rocks = new InputLoader("input-day-14.txt").getInput();

		long start = System.currentTimeMillis();
		NewRockMap map = new NewRockMap(rocks);
		
		while(map.nextRock()) {
			// do nothing
		}
		
		System.out.println("Units of sand: " + map.amount);
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
