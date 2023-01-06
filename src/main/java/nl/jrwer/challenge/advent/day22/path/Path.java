package nl.jrwer.challenge.advent.day22.path;

import java.util.Iterator;
import java.util.List;

public class Path implements Iterator<PathElement> {
	private final List<PathElement> paths;
	private int currentIndex = 0;

	public Path(List<PathElement> paths) {
		this.paths = paths;
	}

	@Override
	public boolean hasNext() {
		return currentIndex < paths.size();
	}

	@Override
	public PathElement next() {
		PathElement el = paths.get(currentIndex);
		currentIndex++;

		return el;
	}
	
	public void reset() {
		currentIndex = 0;
	}
}
