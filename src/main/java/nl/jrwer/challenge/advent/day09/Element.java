package nl.jrwer.challenge.advent.day09;

import java.util.ArrayList;
import java.util.List;

class Element extends Coord {
	Element tail = null;

	private List<Coord> visitedPositions = new ArrayList<>();
	
	public Element(int length) {
		super(0, 0);
		
		if(length != 0)
			tail = new Element(length - 1);
	}
	
	public int visitedPositions() {
		return visitedPositions.size();
	}
	
	public List<Coord> getVisitedPositions() {
		return visitedPositions;
	}
	
	protected void registerPostion() {
		if(!visitedPositions.contains(this))
			visitedPositions.add(this.clone());	
		
		if(tail != null)
			tail.registerPostion();
	}
	
	public void move(Element p) {
		this.move(null, p);
	}
	
	public void move(Direction direction, Element p) {
		if(p == null) {
			move(direction);
		} else if(!(p.x >= x - 1 && p.x <= x + 1 && p.y >= y - 1 && p.y <= y + 1)) {
			if(p.y == y + 1 || p.y == y + 2)
				y++;

			if(p.x == x + 1 || p.x == x + 2)
				x++;

			if(p.x == x - 1 || p.x == x - 2)
				x--;

			if(p.y == y - 1 || p.y == y - 2)
				y--;
		}
		
		if(tail != null)
			tail.move(this);
	}
}