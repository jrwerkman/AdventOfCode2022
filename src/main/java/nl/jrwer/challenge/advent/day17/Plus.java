package nl.jrwer.challenge.advent.day17;

class Plus extends Rock {
	public Plus() {
		super(1, 3, 0);
	}
	
	@Override
	protected Coord[] getCoords() {
		return new Coord[] {new Coord(3,2), 
				new Coord(2,1), new Coord(3,1), new Coord(4,1), 
				new Coord(3,0)};
	}
}