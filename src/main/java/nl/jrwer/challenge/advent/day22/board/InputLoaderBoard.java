package nl.jrwer.challenge.advent.day22.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.jrwer.challenge.advent.day22.Coord;
import nl.jrwer.challenge.advent.day22.Rotation;
import nl.jrwer.challenge.advent.day22.Type;
import nl.jrwer.challenge.advent.day22.path.Path;
import nl.jrwer.challenge.advent.day22.path.PathElement;
import nl.jrwer.challenge.advent.day22.path.PathFinderBoard;
import nl.jrwer.challenge.advent.input.SingleObjectsInputLoader;

public class InputLoaderBoard extends SingleObjectsInputLoader<PathFinderBoard>{
	private Map<Integer, BoardRow> boardRows = new HashMap<>();
	private List<PathElement> paths = new ArrayList<>();
	private Coord startPoint = null;
	Integer index = 1;
	
	public InputLoaderBoard(String file) {
		super(file);
	}

	@Override
	protected void handleLine(String line) {
		if(line.contains("."))
			addCoordinates(line);
		else
			createPaths(line);
	}

	protected void addCoordinates(String line) {
		char[] chars = line.toCharArray();
		Map<Integer, Type> row = new HashMap<>();
		
		for(int i=0; i<chars.length; i++) {
			
			if(chars[i] == '.') {
				row.put(i+1, Type.OPEN_TILE);
				
				if(startPoint == null)
					startPoint = new Coord(i+1, index);
			} else if(chars[i] == '#') {
				row.put(i+1, Type.SOLID_WALL);
			}
			
		}
		
		boardRows.put(index, new BoardRow(row));
		index++;
	}
	
	protected void createPaths(String line) {
		char split[] = line.toCharArray();
		
		String steps = "";
		
		for(int i=0; i<split.length; i++) {
			char current = split[i];
			
			if(current == 'R' || current == 'L') {
				paths.add(new PathElement(Integer.parseInt(steps), Rotation.get(current)));
				steps = "";
			} else {
				steps += current;
			}
		}
		
		paths.add(new PathElement(Integer.parseInt(steps), Rotation.NONE));
	}

	@Override
	protected PathFinderBoard createObject() {
		return new PathFinderBoard(new BoardMap(startPoint, new BoardRows(boardRows)), new Path(paths));
	}
}