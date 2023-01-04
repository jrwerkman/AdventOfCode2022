package nl.jrwer.challenge.advent.day09;

import java.util.ArrayList;
import java.util.List;

class Chart {
	private final Rope rope;
	private int xMax = 0, yMax = 0, xMin = 0, yMin = 0;
	private List<Coord> coords;
	List<Coord> elements = new ArrayList<>();
	
	public Chart(Rope rope) {
		this.rope = rope;
	}
	
	public void printResult() {
		getDimensions();
		
		StringBuilder sb = new StringBuilder();
		
		for(int y=yMax; y>=yMin; y--) {
			for(int x=xMin; x<=xMax; x++) {
				if(passed(x, y))
					sb.append('#');
				else
					sb.append('.');
			}
			
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	public void printState() {
		getCurrentPositons();
		
		StringBuilder sb = new StringBuilder();
		
		for(int y=yMax; y>=yMin; y--) {
			for(int x=xMin; x<=xMax; x++) {
				int pos = atCurrentPosition(x, y);
				
				if(pos >= 0)
					sb.append(pos == 0 ? "H" : "" + pos);
				else
					sb.append('.');
			}
			
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	private boolean passed(int x, int y) {
		for(Coord c : coords)
			if(c.equals(x, y))
				return true;

		return false;
	}
	
	private int atCurrentPosition(int x, int y) {
		for(int i=0; i<elements.size(); i++) {
			Coord c = elements.get(i);
			
			if(c.equals(x, y))
				return i;
		}

		return -1;
	}
	
	private void getCurrentPositons() {
		Element elem = rope.getHead();
		
		
		while(elem != null) {
			elements.add(elem);
			
			if(elem.x > xMax)
				xMax = elem.x;
			if(elem.x < xMin)
				xMin = elem.x;
			if(elem.y > yMax)
				yMax = elem.y;
			if(elem.y < yMin)
				yMin = elem.y;
			
			elem = elem.tail;
		}
	}
	
	private void getDimensions() {
		coords = rope.getTail().getVisitedPositions();
		
		for(Coord c : coords) {
			if(c.x > xMax)
				xMax = c.x;
			if(c.x < xMin)
				xMin = c.x;
			if(c.y > yMax)
				yMax = c.y;
			if(c.y < yMin)
				yMin = c.y;
		}
	}
}