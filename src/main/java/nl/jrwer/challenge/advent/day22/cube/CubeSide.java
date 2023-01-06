package nl.jrwer.challenge.advent.day22.cube;

import java.util.List;

import nl.jrwer.challenge.advent.day22.Coord;
import nl.jrwer.challenge.advent.day22.Type;

public class CubeSide extends CubeRotate {
	public final int number;
	private final int dimensions;
	private final CubeCoord[][] grid;
	Rotation rotation = Rotation.NO_ROTATION;

	private final List<CubeSide> sides;
	private boolean rotated = false;
	private CubeCoord[][] rotatedGrid = null;
	
	public CubeSide(int number, int dimensions, List<CubeSide> sides) {
		this.number = number;
		this.dimensions = dimensions;
		this.sides = sides;
		this.grid = new CubeCoord[dimensions][dimensions];
	}
	
	public CubeCoord get(Coord cursor) {
		return grid[cursor.x][cursor.y];
	}
	
	public void resetRotation() {
		rotatedGrid = null;
		rotated = false;
	}
	
	public void set(int x, int y, CubeCoord cubeCoord) {
		grid[x][y] = cubeCoord;
	}

	public CubeCoord get(int x, int y) {
		if(x < 0 || x >= dimensions || y < 0 || y >= dimensions)
			return new CubeCoord(-1, -1, Type.NOTHING);
		
		return grid[x][y];
	}

	public CubeCoord[][] getGrid() {
		if(rotated)
			return rotatedGrid;
		
		return grid;
	}
}
