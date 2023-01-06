package nl.jrwer.challenge.advent.day22.path;

import nl.jrwer.challenge.advent.day22.Direction;
import nl.jrwer.challenge.advent.day22.board.BoardMap;

public class PathFinderBoard {
	final BoardMap board;
	final Path path;
	Direction currentDirection = Direction.RIGHT;
	
	public PathFinderBoard(BoardMap board, Path path) {
		this.board = board;
		this.path = path;
	}
	
	public void followPath() {
		while(path.hasNext()) {
			PathElement elem = path.next();

			// walk
			board.walk(elem.steps, currentDirection);
			
			// rotate
			currentDirection = currentDirection.rotate(elem.rotation);
		}
	}
	
	public int getFinalPassword() {
		System.out.println(String.format("row:     1000 * %d = %d", 
				board.getCurrentY(), board.getCurrentY() * 1000));
		System.out.println(String.format("column:  4 * %d = %d", 
				board.getCurrentX(), board.getCurrentX() * 4));
		System.out.println(String.format("facing:  %s = %d", 
				currentDirection.name(), currentDirection.weight));
		
		return board.getCurrentY() * 1000 
				+ board.getCurrentX() * 4 
				+ currentDirection.weight;
	}
	
	
}
