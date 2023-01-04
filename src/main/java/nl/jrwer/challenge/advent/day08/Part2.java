package nl.jrwer.challenge.advent.day08;

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
		List<TreeLine> treeLines = new InputLoader("input-day-08.txt").getInput();

		SecondGrid grid = new SecondGrid(treeLines);
		
		System.out.println(grid.higestScenicScore());
	}
}
