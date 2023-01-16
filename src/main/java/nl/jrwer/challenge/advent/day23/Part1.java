package nl.jrwer.challenge.advent.day23;

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
		Elves elves = new InputLoader("input-day-23.txt").getInput();

		long start = System.currentTimeMillis();
//		elves.print();
		System.out.println("Number of empty ground tiles: " + elves.executeRounds(1));
//		elves.print();
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}

}
