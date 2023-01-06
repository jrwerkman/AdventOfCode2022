package nl.jrwer.challenge.advent.day22;

import nl.jrwer.challenge.advent.day22.board.InputLoaderBoard;
import nl.jrwer.challenge.advent.day22.path.PathFinderBoard;

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
		PathFinderBoard finder = new InputLoaderBoard("input-day-22.txt").getInput();

		long start = System.currentTimeMillis();
		finder.followPath();
		System.out.println("Final password is: " + finder.getFinalPassword());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}

}
