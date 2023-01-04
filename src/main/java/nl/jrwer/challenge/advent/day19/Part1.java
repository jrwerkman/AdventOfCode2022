package nl.jrwer.challenge.advent.day19;

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
		List<Blueprint> blueprints = new InputLoader("input-day-19.txt").getInput();
		
		long start = System.currentTimeMillis();
		int result = 0;
		for(Blueprint b : blueprints) {
			Factory f = new Factory(b, 24);
			int maxGeodes = f.maxGeodes();
			
			System.out.println(String.format("Blueprint %d:\t Opened %d geodes", b.number, maxGeodes));
			
			result += b.number * maxGeodes;
		}
		
		System.out.println("");
		System.out.println("Result: " + result);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
