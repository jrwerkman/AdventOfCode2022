package nl.jrwer.challenge.advent.day07;

import java.util.List;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<String> input = new InputLoader("input-day-07.txt").getInput();

		Terminal t = new Terminal(input);
		
		int size = 0;
		
		for(Directory d : t.directories) {
			System.out.println(d.name + " - " + d.size);
			
			if(d.size <= 100000)
				size += d.size;
		}
		
		System.out.println(size);
	}
}
