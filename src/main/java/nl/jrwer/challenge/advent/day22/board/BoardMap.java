package nl.jrwer.challenge.advent.day22.board;

import nl.jrwer.challenge.advent.day22.Coord;
import nl.jrwer.challenge.advent.day22.Direction;

public class BoardMap {
	private final BoardRows boardRows;
	private final Coord cursor;
	
	public BoardMap(Coord startPoint, BoardRows boardRows) {
		this.boardRows = boardRows;
		this.cursor = startPoint;
	}
	
	public void walk(int steps, Direction direction) {
		switch(direction) {
		case DOWN:
			boardRows.moveDown(cursor, steps);
			break;
		case LEFT:
			boardRows.moveLeft(cursor, steps);
			break;
		case RIGHT:
			boardRows.moveRight(cursor, steps);
			break;
		case UP:
			boardRows.moveUp(cursor, steps);
			break;
		default:
			break;
		}
	}
	

	
	public int getCurrentX() {
		return cursor.x;
	}
	
	public int getCurrentY() {
		return cursor.y;
	}
}
