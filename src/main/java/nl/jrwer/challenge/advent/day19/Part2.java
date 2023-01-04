package nl.jrwer.challenge.advent.day19;

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
		List<Blueprint> blueprints = new InputLoader("input-day-19.txt").getInput();
		
		long start = System.currentTimeMillis();
		int result = 1;
		int i=0;
		for(Blueprint b : blueprints) {
			i++;
			
			Factory f = new Factory(b, 32);
			int maxGeodes = f.maxGeodes();
			
			System.out.println(String.format("Blueprint %d:\t Opened %d geodes", b.number, maxGeodes));
			
			result *= maxGeodes;
			
			if(i == 3)
				break;
		}
		
		System.out.println("");
		System.out.println("Result: " + result);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
}
