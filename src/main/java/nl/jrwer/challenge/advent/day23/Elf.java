package nl.jrwer.challenge.advent.day23;


public class Elf {
	private final int id;
	private Coord currentCoords;
	private AdjacentCoords adjacentCoords = null;
	
	private Direction proposedDirection = null;
	private Coord proposedCoordinates = null;
	private boolean canMoveToProposedDirection = false;
	
	
	public Elf(int id, int x, int y) {
		this.id = id;
		this.currentCoords = new Coord(x, y);
		this.adjacentCoords = new AdjacentCoords(currentCoords);
	}
	
	public boolean onCoord(Coord c) {
		return currentCoords.equals(c);
	}
	
	public boolean sameProposedCoord(Elf compare) {
		return proposedCoordinates.equals(compare.proposedCoordinates);
	}
	
	public void setProposedDirection(Direction proposedDirection) {
		this.proposedDirection = proposedDirection;
		this.proposedCoordinates = adjacentCoords.getCoord(this.proposedDirection);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Elf) {
			Elf c = (Elf) obj;
			
			return id == c.id;
		}
		
		return false;
		
	}
	
	public AdjacentCoords getAdjacentCoords() {
		return adjacentCoords;
	}
	
	public boolean hasProposedCoords() {
		return proposedCoordinates != null;
	}

	public boolean canMove() {
		return canMoveToProposedDirection;
	}

	public void setCanMove(boolean canMove) {
		canMoveToProposedDirection = canMove;
	}
	
	public void move() {
		if(canMoveToProposedDirection) {
			doMove();
			adjacentCoords = new AdjacentCoords(currentCoords);
		}
		
		// reset values
		proposedDirection = null;
		proposedCoordinates = null;
		canMoveToProposedDirection = false;		
	}
	
	private void doMove() {
		switch(proposedDirection) {
		case EAST:
			currentCoords.moveEast();
			break;
		case NORTH:
			currentCoords.moveNorth();
			break;
		case SOUTH:
			currentCoords.moveSouth();
			break;
		case WEST:
			currentCoords.moveWest();
			break;
		default:
			throw new RuntimeException();
		}
	}
	
	public Coord getCurrentCoord() {
		return currentCoords;
	}
}
