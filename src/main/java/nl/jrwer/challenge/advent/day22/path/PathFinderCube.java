package nl.jrwer.challenge.advent.day22.path;

import nl.jrwer.challenge.advent.day22.Direction;
import nl.jrwer.challenge.advent.day22.Type;
import nl.jrwer.challenge.advent.day22.cube.Cube;
import nl.jrwer.challenge.advent.day22.cube.CubeCoord;
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
		System.out.println(String.format("row:     1000 * %d = %d", 
				cube.getCurrentY(), cube.getCurrentY() * 1000));
		System.out.println(String.format("column:  4 * %d = %d", 
				cube.getCurrentX(), cube.getCurrentX() * 4));
		System.out.println(String.format("facing:  %s = %d", 
				currentDirection.name(), currentDirection.weight));
		
		return cube.getCurrentY() * 1000 
				+ cube.getCurrentX() * 4 
				+ currentDirection.weight;
	}
	
	public void printCube() {
		for(CubeSide s : cube.cubeSides) {
			StringBuilder sb = new StringBuilder();
			sb.append(s.number).append('\n');
			
			CubeCoord[][] grid = s.getGrid();
			for(int y=0; y<grid.length; y++) {
				for(int x=0; x<grid.length; x++)
					sb.append(grid[x][y].type == Type.OPEN_TILE ? '.' : '#');
				
				sb.append("\n");
			}
			System.out.println(sb.toString());
		}		
	}
	
}
