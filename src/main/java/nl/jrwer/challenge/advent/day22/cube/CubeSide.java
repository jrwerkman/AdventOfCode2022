package nl.jrwer.challenge.advent.day22.cube;

import nl.jrwer.challenge.advent.day22.Coord;
import nl.jrwer.challenge.advent.day22.Type;

public class CubeSide extends CubeRotate {
	public final int number;
	final int dimensions;
	private final CubeCoord[][] grid;
	CubeSideRotation rotation = CubeSideRotation.NO_ROTATION;

	private CubeCoord[][] rotatedGrid = null;
	
	public CubeSide(int number, int dimensions) {
		this.number = number;
		this.dimensions = dimensions;
		this.grid = new CubeCoord[dimensions][dimensions];
	}
	
	public CubeCoord get(Coord cursor) {
		return this.getGrid()[cursor.x][cursor.y];
	}
	
	public void rotate90CW() {
		this.resetRotation();
		this.rotatedGrid = rotate90CW(grid);
		this.rotation = CubeSideRotation.QUARTER;
	}
	
	public void rotate90CCW() {
		this.resetRotation();
		this.rotation = CubeSideRotation.THREE_QUARTER;
		this.rotatedGrid = rotate90CCW(grid);
	}
	
	public void rotate180() {
		this.resetRotation();
		this.rotation = CubeSideRotation.HALF;
		this.rotatedGrid = rotate180(grid);
	}
	
	public void resetRotation() {
		if(this.rotation != CubeSideRotation.NO_ROTATION) {
			this.rotation = CubeSideRotation.NO_ROTATION;
			this.rotatedGrid = null;
		}
	}
	
	public void set(int x, int y, CubeCoord cubeCoord) {
		this.grid[x][y] = cubeCoord;
	}

	public CubeCoord get(int x, int y) {
		if(x < 0 || x >= this.dimensions || y < 0 || y >= this.dimensions)
			return new CubeCoord(-1, -1, Type.NOTHING);
		
		return this.getGrid()[x][y];
	}

	public CubeCoord[][] getGrid() {
		if(this.rotation != CubeSideRotation.NO_ROTATION)
			return this.rotatedGrid;
		
		return this.grid;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		CubeCoord[][] realGrid = getGrid();
		
		for(int y=0; y<realGrid.length; y++) {
			for(int x=0; x<realGrid.length; x++)
				sb.append(realGrid[x][y].type == Type.OPEN_TILE ? '.' : '#');
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
