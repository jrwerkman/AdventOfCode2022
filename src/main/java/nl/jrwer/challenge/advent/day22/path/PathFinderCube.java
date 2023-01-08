package nl.jrwer.challenge.advent.day22.path;

import nl.jrwer.challenge.advent.day22.Direction;
import nl.jrwer.challenge.advent.day22.cube.Cube;
import nl.jrwer.challenge.advent.day22.cube.CubeSide;
import nl.jrwer.challenge.advent.day22.cube.CubeSideConnection;

public class PathFinderCube {
	private final Cube cube;
	private final Path path;
	private Direction currentDirection = Direction.RIGHT;

	
	public PathFinderCube(Cube cube, Path path) {
		this.cube = cube;
		this.path = path;
	}
	
	public  void setSideConnections(CubeSideConnection[] sideConnection) {
		cube.setSideConnections(sideConnection);
	}
	
	public void followPath() {
		while(path.hasNext()) {
			PathElement elem = path.next();
			// walk
			cube.walk(elem.steps, currentDirection);
			
			// rotate
			currentDirection = currentDirection.rotate(elem.rotation);
		}
	}
	
	public int getFinalPassword() {
		System.out.println("cubeside rotation: " + cube.getCurrentRotation());
		Direction realDirection = currentDirection.addRotation(cube.getCurrentRotation());
		
		System.out.println(String.format("row:     1000 * %d = %d", 
				cube.getCurrentY(), cube.getCurrentY() * 1000));
		System.out.println(String.format("column:  4 * %d = %d", 
				cube.getCurrentX(), cube.getCurrentX() * 4));
		System.out.println(String.format("facing:  %s = %d", 
				realDirection.name(), realDirection.weight));
		
		return cube.getCurrentY() * 1000 
				+ cube.getCurrentX() * 4 
				+ realDirection.weight;
	}
	
	public void printCube() {
		for(CubeSide s : cube.cubeSides) {
			StringBuilder sb = new StringBuilder();
			sb.append(s.number).append('\n');
			sb.append(s);

			System.out.println(sb.toString());
		}		
	}
	
}
