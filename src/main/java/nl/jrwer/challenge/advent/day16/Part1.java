package nl.jrwer.challenge.advent.day16;

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
	
	public static String[] input = new String[] {
			"input-day-16.txt",
			"input-day-16-example.txt",
			"input-day-16-example2.txt",
			"input-day-16-example3.txt",
			"input-day-16-example4.txt",
			"input-day-16-example5.txt",
	};
	
	public static int[] answers = new int[] {1906, 1651, 2640, 13468, 1288, 2400};
	public static int[] answers2 = new int[] {2548, 1707, 2670, 12887, 1484, 3680};
	
	public void start() {
//		execute(input[0], answers[0]);
		execute(input[1], answers[1]);

//		for(int i=1; i<input.length; i++)
//			execute(input[i], answers[i]);
	}
	
	public void execute(String input, int answer) {
		List<Valve> valves = new InputLoader(input).getInput();
		
		long start = System.currentTimeMillis();
		Routes routes = new Routes(valves);
		Settings settings = new Settings(1, 30);
		Calculator c = new Calculator(valves, routes, settings);
		System.out.println("Run: " + input + ", Number of routes: " + routes.size());
		int result = c.maxPresureRelease();
		System.out.println("Max flown: " + result + " - Correct: " + (result == answer));
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
