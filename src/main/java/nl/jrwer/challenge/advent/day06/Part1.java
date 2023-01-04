package nl.jrwer.challenge.advent.day06;

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
		List<DataStreamBuffer> streams = new InputLoader("input-day-06.txt").getInput();
		
		for(DataStreamBuffer stream : streams) 
			System.out.println(stream.firstMarkerAtferPosition());
	}
}
