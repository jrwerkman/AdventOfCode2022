package nl.jrwer.challenge.advent.day17;

class Minus extends Rock {
	public Minus() {
		super(0, 3, 0);
	}
	
	@Override
	protected Coord[] getCoords() {
		return new Coord[] {new Coord(2,0), new Coord(3,0), new Coord(4,0), new Coord(5,0)};
	}
}