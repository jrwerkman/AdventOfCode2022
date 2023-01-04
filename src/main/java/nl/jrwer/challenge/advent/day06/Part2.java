package nl.jrwer.challenge.advent.day06;

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
		List<DataStreamBuffer> streams = new InputLoader("input-day-06.txt").getInput();
		
		for(DataStreamBuffer stream : streams) {
			System.out.println(stream.firstMarkerAtferPosition(14));
		}
	}
}
