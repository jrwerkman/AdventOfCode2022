package nl.jrwer.challenge.advent.day22.cube;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.day22.Coord;
import nl.jrwer.challenge.advent.day22.Rotation;
import nl.jrwer.challenge.advent.day22.Type;
import nl.jrwer.challenge.advent.day22.path.Path;
import nl.jrwer.challenge.advent.day22.path.PathElement;
import nl.jrwer.challenge.advent.day22.path.PathFinderCube;
import nl.jrwer.challenge.advent.input.SingleObjectsInputLoader;

public class InputLoaderCube extends SingleObjectsInputLoader<PathFinderCube>{
	private List<PathElement> paths = new ArrayList<>();
	private Coord startPoint = null;

	Integer index = 1;
	final int dimensions;
	List<String> cubeLines = new ArrayList<>();
	
	public InputLoaderCube(String file, int dimensions) {
		super(file);
		
		this.dimensions = dimensions;
	}

	@Override
	protected void handleLine(String line) {
		if(line.contains("."))
			addCoordinates(line);
		else
			createPaths(line);
	}

	protected void addCoordinates(String line) {
		cubeLines.add(line);
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
	protected PathFinderCube createObject() {
		return new PathFinderCube(createCube(), new Path(paths));
	}
	
	public Cube createCube() {
		List<CubeSide> sides = new ArrayList<>();

		for(int i=0; i<cubeLines.size(); i+=dimensions) {
			String line = cubeLines.get(i);
			int amount = line.trim().length()/dimensions;
			int firstX = firstXSide(i, line);
			
			for(int j=0;j<amount; j++) {
				CubeSide side = new CubeSide(index, dimensions);
				sides.add(side);
				index++;
				
				createSide(side, i, firstX + (j * dimensions));
			}
			
		}
		
		return new Cube(sides, startPoint);
	}
	
	public int firstXSide(int y, String line) {
//		System.out.println(line);
		char[] chars = line.toCharArray();
		
		for(int i=0; i<chars.length; i++)
			if(chars[i] != ' ')
				return i;

		throw new RuntimeException(line);
	}
	
	public void createSide(CubeSide side, int startY, int startX) {
		for(int y=startY; y<startY+dimensions; y++) {
			char[] row = cubeLines.get(y).toCharArray();
			
			for(int x=startX; x<startX+dimensions; x++) {
				if(startPoint == null && row[x] == '.')
					startPoint = new Coord(x - startX, y - startY);

				side.set(x - startX, y - startY, new CubeCoord(x + 1, y + 1, Type.get(row[x])));
			}
			
		}
	}	
}