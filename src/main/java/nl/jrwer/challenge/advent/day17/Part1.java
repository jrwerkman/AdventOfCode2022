package nl.jrwer.challenge.advent.day17;

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
		Sequence sequence = new InputLoader("input-day-17.txt").getInput();
		State state = new State(2_022L, sequence);
		
		long start = System.currentTimeMillis();
		Chamber c = new Chamber(state);
		System.out.println(c.calculate());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
