package nl.jrwer.challenge.advent.day17;

class Square extends Rock {
	public Square() {
		super(2, 3, 0);
	}

	@Override
	protected Coord[] getCoords() {
		return new Coord[] {new Coord(2,1), new Coord(3,1), 
				new Coord(2,0), new Coord(3,0)};
	}
}
