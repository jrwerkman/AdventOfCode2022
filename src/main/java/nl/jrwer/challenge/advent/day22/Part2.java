package nl.jrwer.challenge.advent.day22;

import nl.jrwer.challenge.advent.day22.cube.CubeSideConnections;
import nl.jrwer.challenge.advent.day22.cube.InputLoaderCube;
import nl.jrwer.challenge.advent.day22.path.PathFinderCube;

class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		PathFinderCube finder = new InputLoaderCube("input-day-22.txt", 50).getInput();
		finder.setSideConnections(CubeSideConnections.CHALLENGE);
		
//		PathFinderCube finder = new InputLoaderCube("input-day-22-example.txt", 4).getInput();
//		finder.setSideConnections(CubeSideConnections.EXAMPLE);

		long start = System.currentTimeMillis();
//		finder.printCube();
		finder.followPath();
		System.out.println("Final password is: " + finder.getFinalPassword());		
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
