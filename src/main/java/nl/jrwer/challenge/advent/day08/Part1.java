package nl.jrwer.challenge.advent.day08;

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
		List<TreeLine> treeLines = new InputLoader("input-day-08.txt").getInput();

		Grid grid = new Grid(treeLines);
		
		System.out.println(grid.visibleTrees());
	}
}
