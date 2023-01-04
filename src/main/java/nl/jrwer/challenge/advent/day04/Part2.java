package nl.jrwer.challenge.advent.day04;

import java.util.List;

public class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Pair> pairs = new InputLoader("input-day-04.txt").getInput();
		
		int total = 0;
		for(Pair pair : pairs) {
			if(pair.partialOverlap())
				total++;
		}
		
		System.out.println(total);
	}
}
