package nl.jrwer.challenge.advent.day23;

public class AdjacentCoords {
	private static final int N = 0;
	private static final int NE = 1;
	private static final int NW = 2;
	private static final int S = 3;
	private static final int SE = 4;
	private static final int SW = 5;
	private static final int W = 6;
	private static final int E = 7;
	
	private Coord[] adjacentCoords;
	
	public AdjacentCoords(Coord currentCoords) {
		this.adjacentCoords = new Coord[] {
				new Coord(currentCoords.x, currentCoords.y + 1), // N
				new Coord(currentCoords.x + 1, currentCoords.y + 1), // NE
				new Coord(currentCoords.x - 1, currentCoords.y + 1), // NW
				new Coord(currentCoords.x, currentCoords.y - 1), // S
				new Coord(currentCoords.x + 1, currentCoords.y - 1), // SE
				new Coord(currentCoords.x - 1, currentCoords.y - 1), // SW
				new Coord(currentCoords.x - 1, currentCoords.y), // W
				new Coord(currentCoords.x + 1, currentCoords.y), // E
		};
	}
	
	public Coord getCoord(Direction direction) {
		switch(direction) {
		case NORTH:
			return adjacentCoords[N];
		case SOUTH:
			return adjacentCoords[S];
		case WEST:
			return adjacentCoords[W];
		case EAST:
			return adjacentCoords[E];
		default:
			throw new RuntimeException();
		}	}
	
	public boolean hasElf(Direction direction, Elf elf) {
		switch(direction) {
		case NORTH:
			return hasElfNorth(elf);
		case SOUTH:
			return hasElfSouth(elf);
		case WEST:
			return hasElfWest(elf);
		case EAST:
			return hasElfEast(elf); 
		default:
			throw new RuntimeException();
		}
	}
	
	public boolean hasElfNorth(Elf elf) {
		return elf.onCoord(adjacentCoords[N])
				|| elf.onCoord(adjacentCoords[NE])
				|| elf.onCoord(adjacentCoords[NW]);
	}
	
	public boolean hasElfSouth(Elf elf) {
		return elf.onCoord(adjacentCoords[S])
				|| elf.onCoord(adjacentCoords[SE])
				|| elf.onCoord(adjacentCoords[SW]);
	}
	
	public boolean hasElfWest(Elf elf) {
		return elf.onCoord(adjacentCoords[W])
				|| elf.onCoord(adjacentCoords[NW])
				|| elf.onCoord(adjacentCoords[SW]);
	}
	
	public boolean hasElfEast(Elf elf) {
		return elf.onCoord(adjacentCoords[E])
				|| elf.onCoord(adjacentCoords[NE])
				|| elf.onCoord(adjacentCoords[SE]);
	}
}
