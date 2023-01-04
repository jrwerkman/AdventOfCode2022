package nl.jrwer.challenge.advent.day13;

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
		List<Pair> pairs = new InputLoader("input-day-13.txt").getInput();

		int result = 0;
		
//		for(Pair pair : pairs) 
//			pair.print();
		
		for(Pair pair : pairs) {
			System.out.println("Comparing pair: " + pair.number);
			
			if(pair.compare()) {
				System.out.println("Pair is right order: " + pair.number);
				
				result += pair.number;
			}
		}
		
		System.out.println("Result: " + result);
	}
}
