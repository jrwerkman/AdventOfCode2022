package nl.jrwer.challenge.advent.day22.board;

import java.util.Map;
import java.util.Set;

import nl.jrwer.challenge.advent.day22.Coord;
import nl.jrwer.challenge.advent.day22.Type;

public class BoardRow {
	final Map<Integer, Type> row;
	
	public BoardRow(Map<Integer, Type> row) {
		this.row = row;
	}
	
	public Type get(Integer x) {
		return row.getOrDefault(x, Type.NOTHING);
	}
	
	public Type previous(Coord cursor) {
		Type type = get(cursor.x - 1);
		int previousX = cursor.x - 1;
		
		if(type == Type.NOTHING) {
			previousX = getLast(cursor.x);
			type = get(previousX);
		}
		
		if(type == Type.OPEN_TILE)
			cursor.x = previousX;
		
		return type;
	}
	
	public Type next(Coord cursor) {
		Type type = get(cursor.x + 1);
		int nextX = cursor.x + 1;
		
		if(type == Type.NOTHING) {
			nextX = getFirst(cursor.x);
			type = get(nextX);
		}
		
		if(type == Type.OPEN_TILE)
			cursor.x = nextX;
		
		return type;
	}
	
	private int getFirst(int x) {
		Set<Integer> keys = row.keySet();
		
		int firstX = x;
		
		for(Integer key : keys)
			if(key < firstX)
				firstX = key;
		
		return firstX;
	}
	
	private int getLast(int x) {
		Set<Integer> keys = row.keySet();
		
		int lastX = x;
		
		for(Integer key : keys)
			if(key > lastX)
				lastX = key;
		
		return lastX;
	}	
}
