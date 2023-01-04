package nl.jrwer.challenge.advent.day17;

class Part2  {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		Sequence sequence = new InputLoader("input-day-17.txt").getInput();
		StateOptimized state = new StateOptimized(1000000000000L, sequence);

		long start = System.currentTimeMillis();
		ChamberOptimized c = new ChamberOptimized(state);
		System.out.println(c.calculate());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
