package nl.jrwer.challenge.advent.day24;

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
		Valley valley = new InputLoader("input-day-24.txt").getInput();

		long start = System.currentTimeMillis();
		System.out.println("fewest number of minutes to reach the goal: " + valley.findExitRoundTrip(3));
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
