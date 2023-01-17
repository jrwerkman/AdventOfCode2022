package nl.jrwer.challenge.advent.day24;

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
		Valley valley = new InputLoader("input-day-24.txt").getInput();

		long start = System.currentTimeMillis();
		System.out.println("fewest number of minutes to reach the goal: " + valley.findExit());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
		
		// 177 to low
	}

}
