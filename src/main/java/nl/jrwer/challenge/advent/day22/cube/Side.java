package nl.jrwer.challenge.advent.day22.cube;

public enum Side {
	LEFT, TOP, RIGHT, BOTTOM;
	
	private static final Side[] sides = values();

	/**
	 * Calculate the side plus rotation
	 * 
	 * @param rotation
	 * @return
	 */
	public Side addRotation(CubeSideRotation rotation) {
		if(rotation == CubeSideRotation.QUARTER)
			return this.next();
		if(rotation == CubeSideRotation.HALF)
			return this.next(2);
		if(rotation == CubeSideRotation.THREE_QUARTER)
			return this.next(3);
		
		return this;
	}
	
	public Side next(int i) {
		return sides[(this.ordinal() + i) % sides.length];
	}

	public Side next() {
		return sides[(this.ordinal() + 1) % sides.length];
	}

	public Side previous() {
		return sides[this.ordinal() > 0 ? this.ordinal() - 1 : sides.length - 1];
	}
}
