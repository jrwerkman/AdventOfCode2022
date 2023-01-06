package nl.jrwer.challenge.advent.day22.board;

import java.util.Map;
import java.util.Set;

import nl.jrwer.challenge.advent.day22.Coord;
import nl.jrwer.challenge.advent.day22.Type;

public class BoardRows {
	private final Map<Integer, BoardRow> boardRows;
	
	public BoardRows(Map<Integer, BoardRow> boardRows) {
		this.boardRows = boardRows;
	}
	
	public BoardRow get(int y) {
		if(!boardRows.containsKey(y))
			return null;
		
		return boardRows.get(y);
	}
	
	public void moveRight(Coord cursor, int steps) {
		BoardRow row = boardRows.get(cursor.y);

		for(int i=0; i<steps; i++)
			if(row.next(cursor) != Type.OPEN_TILE)
				return;
	}
	
	public void moveLeft(Coord cursor, int steps) {
		BoardRow row = boardRows.get(cursor.y);
		
		for(int i=0; i<steps; i++)
			if(row.previous(cursor) != Type.OPEN_TILE)
				return;
	}
	
	public void moveUp(Coord cursor, int steps) {
		for(int i=0; i<steps; i++)
			if(up(cursor) != Type.OPEN_TILE)
				return;
	}
	
	public void moveDown(Coord cursor, int steps) {
		for(int i=0; i<steps; i++)
			if(down(cursor) != Type.OPEN_TILE)
				return;
	}
	
	private Type up(Coord cursor) {
		Type type = get(cursor.x, cursor.y - 1);
		int upY = cursor.y - 1;
		
		if(type == Type.NOTHING) {
			upY = getBottom(cursor.x, cursor.y);
			type = get(cursor.x, upY);
		}		
		
		if(type == Type.OPEN_TILE)
			cursor.y = upY;
		
		return type;
	}

	private Type down(Coord cursor) {
		Type type = get(cursor.x, cursor.y + 1);
		int downY = cursor.y + 1;
		
		if(type == Type.NOTHING) {
			downY = getTop(cursor.x, cursor.y);
			type = get(cursor.x, downY);
		}		
		
		if(type == Type.OPEN_TILE)
			cursor.y = downY;
		
		return type;
	}
	
	private int getTop(int x, int y) {
		Set<Integer> keys = boardRows.keySet();
		
		int topY = y;
		
		for(Integer key : keys)
			if(key < topY && get(x, key) != Type.NOTHING)
				topY = key;
		
		return topY;
	}
	
	private int getBottom(int x, int y) {
		Set<Integer> keys = boardRows.keySet();
		
		int bottomY = y;
		
		for(Integer key : keys)
			if(key > bottomY && get(x, key) != Type.NOTHING)
				bottomY = key;
		
		return bottomY;
	}		
	
	private Type get(int x, int y) {
		if(!boardRows.containsKey(y))
			return Type.NOTHING;
		
		return boardRows.get(y).get(x);
	}
}
