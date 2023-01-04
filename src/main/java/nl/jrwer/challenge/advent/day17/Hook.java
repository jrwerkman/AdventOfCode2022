package nl.jrwer.challenge.advent.day17;

class Hook extends Rock {
	public Hook() {
		super(2, 4, 0);
	}
	
	@Override
	protected Coord[] getCoords() {
		return new Coord[] {new Coord(4,2), 
				new Coord(4,1), 
				new Coord(2,0), new Coord(3,0), new Coord(4,0)};
	}
}