package nl.jrwer.challenge.advent.day22.cube;

public class CubeSideConnection {
	final CubeSidePair one, two;
	
	public CubeSideConnection(CubeSidePair one, CubeSidePair two) {
		this.one = one;
		this.two = two;
	}
	
	public CubeSidePair getCurrentSide(CubeSide side) {
		return getCurrentSide(side.number);
	}
	
	public CubeSidePair getCurrentSide(int number) {
		return one.cube == number ? one : two;
	}

	public CubeSidePair getOtherSide(CubeSide side) {
		return getOtherSide(side.number);
	}
	
	public CubeSidePair getOtherSide(int number) {
		return one.cube == number ? two : one;
	}
}
