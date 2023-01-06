package nl.jrwer.challenge.advent.day22.cube;

import java.util.List;

import nl.jrwer.challenge.advent.day22.Coord;
import nl.jrwer.challenge.advent.day22.Direction;
import nl.jrwer.challenge.advent.day22.Type;

public class Cube {
	public final List<CubeSide> cubeSides;
	private final Coord cursor;
	private CubeSide currentSide;
	private CubeSideConnection[] sideConnection;

	public Cube(List<CubeSide> sides, Coord startPoint) {
		this.cubeSides = sides;
		this.cursor = startPoint;
		
		this.currentSide = get(1);
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
			int newX = cursor.x + 1;
			CubeCoord cc = currentSide.get(newX, cursor.y);
			
			if(cc.type == Type.NOTHING) {
				// TODO switch cube
			}
			
			if(cc.type == Type.OPEN_TILE)
				cursor.x++;
			
			if(cc.type == Type.SOLID_WALL)
				return;
		}
	}
	
	private void moveLeft(int steps) {
		for(int i=0; i<steps; i++) {
			int newX = cursor.x - 1;
			CubeCoord cc = currentSide.get(newX, cursor.y);
			
			if(cc.type == Type.NOTHING) {
				// TODO switch cube
			}
			
			if(cc.type == Type.OPEN_TILE)
				cursor.x--;
			
			if(cc.type == Type.SOLID_WALL)
				return;
		}		
	}
	
	private void moveUp(int steps) {
		for(int i=0; i<steps; i++) {
			int newY = cursor.y - 1;
			CubeCoord cc = currentSide.get(cursor.x, newY);
			
			if(cc.type == Type.NOTHING) {
				// TODO switch cube
			}
			
			if(cc.type == Type.OPEN_TILE)
				cursor.y--;
			
			if(cc.type == Type.SOLID_WALL)
				return;
		}	}
	
	private void moveDown(int steps) {
		for(int i=0; i<steps; i++) {
			int newY = cursor.y + 1;
			CubeCoord cc = currentSide.get(cursor.y, newY);
			
			if(cc.type == Type.NOTHING) {
				// TODO switch cube
			}
			
			if(cc.type == Type.OPEN_TILE)
				cursor.y++;
			
			if(cc.type == Type.SOLID_WALL)
				return;
		}
	}
	
	private CubeSide getNextCube(Side side) {
		CubeSideConnection conn = getConnection(side);
		CubeSidePair curr = conn.getCurrentSide(currentSide);
		CubeSidePair next = conn.getOtherSide(currentSide);
		CubeSide nextCubeSide = get(next.cube);
		
		if(currentSide.rotation == Rotation.NO_ROTATION) {
			if(needsRotation(curr, next))
				rotate(nextCubeSide, curr, next);
		} else {
			// TODO calculate rotation cube
		}
		
		return nextCubeSide;
	}
	
	public void rotate(CubeSide nextCubeSide, CubeSidePair curr, CubeSidePair next) {
		// TODO rotate cube
		
	}
	
	public boolean need90CWRotation(CubeSidePair curr, CubeSidePair next) {
		// TODO
	}
	
	public boolean need90CCWRotation(CubeSidePair curr, CubeSidePair next) {
		return (curr.side == Side.TOP && next.side == Side.LEFT) 
				|| (curr.side == Side.BOTTOM && next.side == Side.RIGHT)
				|| (curr.side == Side.LEFT && next.side == Side.BOTTOM)
				|| (curr.side == Side.RIGHT && next.side == Side.TOP);
	}
	
	public boolean need180WRotation(CubeSidePair curr, CubeSidePair next) {
		// TODO
	}
	
	public boolean needsRotation(CubeSidePair curr, CubeSidePair next) {
		return (curr.side == Side.RIGHT && next.side == Side.LEFT) 
				|| (curr.side == Side.LEFT && next.side == Side.RIGHT)
				|| (curr.side == Side.TOP && next.side == Side.BOTTOM)
				|| (curr.side == Side.BOTTOM && next.side == Side.TOP);
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
}
