package nl.jrwer.challenge.advent.day24;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<Valley>{
	List<Blizzard> blizzards = new ArrayList<>();
	Coord entrance;
	Coord exit;

	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected Valley handleLines(List<String> lines) {
		int maxX = lines.get(0).length();
		int maxY = lines.size();
		
		Coord[][] grid = new Coord[maxX][maxY];
		
		entrance = new Coord(Type.ENTRANCE, 1, 0);
		exit = new Coord(Type.EXIT, maxX - 2, maxY - 1);
		
		for(int y=0; y<maxY; y++) {
			for(int x=0; x<maxX; x++) {
				char c = lines.get(y).charAt(x);
				
				grid[x][y] = new Coord(Type.get(c), x, y);
				
				if(c == 'v' || c == '^' || c == '<' || c == '>' ) {
					blizzards.add(new Blizzard(Direction.get(c), x, y));
					grid[x][y].blizzards++;
				}
			}
		}
		
		return new Valley(entrance, exit, grid, blizzards);
	}
}