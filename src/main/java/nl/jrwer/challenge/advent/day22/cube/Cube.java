package nl.jrwer.challenge.advent.day22.cube;

import java.util.List;

import nl.jrwer.challenge.advent.day22.Coord;
import nl.jrwer.challenge.advent.day22.Direction;
import nl.jrwer.challenge.advent.day22.Type;

public class Cube {
	public final List<CubeSide> cubeSides;
	private final Coord cursor;
	private final int dimensions;
	private CubeSide currentSide;
	private CubeSideConnection[] sideConnection;

	public Cube(List<CubeSide> sides, Coord startPoint) {
		this.cubeSides = sides;
		this.cursor = startPoint;
		
		this.currentSide = get(1);
		this.dimensions = this.currentSide.dimensions;
	}

	public  void setSideConnections(CubeSideConnection[] sideConnection) {
		this.sideConnection = sideConnection;
	}
	
	private CubeSide get(int index) {
		for(CubeSide side : cubeSides)
			if(side.number == index)
				return side;
		
		throw new RuntimeException("Side not found: " + index);
	}
	
	public void walk(int steps, Direction direction) {
		switch(direction) {
		case DOWN:
			moveDown(steps);
			break;
		case LEFT:
			moveLeft(steps);
			break;
		case RIGHT:
			moveRight(steps);
			break;
		case UP:
			moveUp(steps);
			break;
		default:
			break;
		}
	}
	
	private void moveRight(int steps) {
		for(int i=0; i<steps; i++) {
			CubeCoord cc = currentSide.get(cursor.x + 1, cursor.y);

			if(cc.type == Type.OPEN_TILE)
				cursor.x++;
			else if(cc.type == Type.SOLID_WALL 
					|| (cc.type == Type.NOTHING && !switchCubeSide(Side.RIGHT, 0, cursor.y)))
				return;
		}
	}
	
	private void moveLeft(int steps) {
		for(int i=0; i<steps; i++) {
			CubeCoord cc = currentSide.get(cursor.x - 1, cursor.y);
			
			if(cc.type == Type.OPEN_TILE)
				cursor.x--;
			else if(cc.type == Type.SOLID_WALL 
					|| (cc.type == Type.NOTHING && !switchCubeSide(Side.LEFT, dimensions - 1, cursor.y)))
				return;
		}		
	}
	
	private void moveUp(int steps) {
		for(int i=0; i<steps; i++) {
			CubeCoord cc = currentSide.get(cursor.x, cursor.y - 1);
			
			if(cc.type == Type.OPEN_TILE)
				cursor.y--;
			else if(cc.type == Type.SOLID_WALL 
					|| (cc.type == Type.NOTHING && !switchCubeSide(Side.TOP, cursor.x, dimensions - 1)))
				return;
		}	
	}
	
	private void moveDown(int steps) {
		for(int i=0; i<steps; i++) {
			CubeCoord cc = currentSide.get(cursor.x, cursor.y + 1);
			
			if(cc.type == Type.OPEN_TILE)
				cursor.y++;
			else if(cc.type == Type.SOLID_WALL 
					|| (cc.type == Type.NOTHING && !switchCubeSide(Side.BOTTOM, cursor.x, 0)))
				return;
		}
	}
	
	private boolean switchCubeSide(Side side, int x, int y) {
		CubeSide nexCubeSide = getNextCube(side);
		CubeCoord nextCC = nexCubeSide.get(x, y);
		
		if(nextCC.type == Type.SOLID_WALL) {
			nexCubeSide.resetRotation();
			return false;
		} 
		
//		System.out.println("  --> switched from cube side [" + currentSide.number + "] "
//				+ "to [" + nexCubeSide.number + "]");
		executeSwitchCubeSide(nexCubeSide, x, y);
		return true;
	}
	
	private void executeSwitchCubeSide(CubeSide nexCubeSide, int x, int y) {
		currentSide.resetRotation();
		currentSide = nexCubeSide;

		cursor.x = x;
		cursor.y = y;
	}
	
	private CubeSide getNextCube(Side side) {
		// if cube side is rotated, add rotation to find the original side.
		Side realSide = side.addRotation(currentSide.rotation);
		CubeSideConnection conn = getConnection(realSide);
		CubeSidePair next = conn.getOtherPair(currentSide);
		CubeSide nextCubeSide = get(next.cube);
		
		if(needsRotation(side, next.side))
			rotate(nextCubeSide, side, next.side);
		
		return nextCubeSide;
	}

	
	public void rotate(CubeSide nextCubeSide, Side curr, Side next) {
		if(need90CWRotation(curr, next))
			nextCubeSide.rotate90CW();
		
		if(need90CCWRotation(curr, next))
			nextCubeSide.rotate90CCW();
		
		if(need180Rotation(curr, next))
			nextCubeSide.rotate180();
	}
	
	public boolean needsRotation(Side curr, Side next) {
		return !(curr == Side.RIGHT && next == Side.LEFT) 
				&& !(curr == Side.LEFT && next == Side.RIGHT)
				&& !(curr == Side.TOP && next == Side.BOTTOM)
				&& !(curr == Side.BOTTOM && next == Side.TOP);
	}
	
	public boolean need90CCWRotation(Side curr, Side next) {
		return (curr == Side.TOP && next == Side.RIGHT) 
				|| (curr == Side.BOTTOM && next == Side.LEFT)
				|| (curr == Side.LEFT && next == Side.TOP)
				|| (curr == Side.RIGHT && next == Side.BOTTOM);
	}
	
	public boolean need90CWRotation(Side curr, Side next) {
		return (curr == Side.TOP && next == Side.LEFT) 
				|| (curr == Side.BOTTOM && next == Side.RIGHT)
				|| (curr == Side.LEFT && next == Side.BOTTOM)
				|| (curr == Side.RIGHT && next == Side.TOP);
	}
	
	public boolean need180Rotation(Side curr, Side next) {
		return (curr == Side.TOP && next == Side.TOP) 
				|| (curr == Side.BOTTOM && next == Side.BOTTOM)
				|| (curr == Side.LEFT && next == Side.LEFT)
				|| (curr == Side.RIGHT && next == Side.RIGHT);
	}
	
	public CubeSideConnection getConnection(Side side) {
		for(CubeSideConnection conn : sideConnection)
			if((conn.one.cube == currentSide.number && conn.one.side == side)
					|| (conn.two.cube == currentSide.number  && conn.two.side == side))
				return conn;
		
		throw new RuntimeException("No connection object found for cube: " +
				currentSide.number + ", from side: " + side.name());
	}
	
	public int getCurrentX() {
		return currentSide.get(cursor).x;
	}
	
	public int getCurrentY() {
		return currentSide.get(cursor).y;
	}

	public CubeSideRotation getCurrentRotation() {
		return currentSide.rotation;
	}
}
