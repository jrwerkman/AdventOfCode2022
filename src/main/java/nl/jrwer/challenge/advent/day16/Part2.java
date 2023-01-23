package nl.jrwer.challenge.advent.day16;

import java.util.List;

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
		execute(Part1.input[0], Part1.answers2[0]);
//		execute(Part1.input[1], Part1.answers2[1]);

//		for(int i=2; i<Part1.input.length; i++)
//			execute(Part1.input[i], Part1.answers2[i]);
	}
	
	public void execute(String input, int answer) {
		List<Valve> valves = new InputLoader(input).getInput();
		
		long start = System.currentTimeMillis();
		Routes routes = new Routes(valves);
		Settings settings = new Settings(2, 26);
		SecondCalculator c = new SecondCalculator(valves, routes, settings);
		System.out.println("Run input: " + input);
		int result = c.maxPresureRelease();
		System.out.println("Max flown: " + result + " - Correct: " + (result == answer));
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
}
