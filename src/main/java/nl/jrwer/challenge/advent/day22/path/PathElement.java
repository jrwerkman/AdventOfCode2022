package nl.jrwer.challenge.advent.day22.path;

import nl.jrwer.challenge.advent.day22.Rotation;

public class PathElement {
	final int steps;
	final Rotation rotation;
	
	public PathElement(int steps, Rotation rotation) {
		this.steps = steps;
		this.rotation = rotation;
	}
	
	@Override
	public String toString() {
		return steps + " - " + rotation;
	}
}
