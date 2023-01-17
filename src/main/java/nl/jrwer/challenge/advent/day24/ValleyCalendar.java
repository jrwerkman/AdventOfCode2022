package nl.jrwer.challenge.advent.day24;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValleyCalendar {
	final List<Blizzard> blizzards;
	final Map<Integer, Coord[][]> logbook = new HashMap<>();
	final int maxX, maxY;
	
	public ValleyCalendar(Coord[][] beginning, List<Blizzard> blizzards) {
		this.logbook.put(0, beginning);
		this.blizzards = blizzards;
		this.maxX = beginning.length;
		this.maxY = beginning[0].length;
		
	
	}
	
	private Coord[][] createEmptyMap() {
		Coord[][] beginning = this.logbook.get(0);
		Coord[][] empty = new Coord[maxX][maxY];
		
		for(int x=0; x<maxX; x++)
			for(int y=0; y<maxY; y++)
				empty[x][y] = beginning[x][y].nextMinute();
		
		return empty;
	}
	
	public Coord[][] get(Integer minute) {
		if(logbook.containsKey(minute))
			return logbook.get(minute);
		
		return createNewMap(minute);
	}
	
	private Coord[][] createNewMap(Integer minute) {
		Coord[][] newMap = createEmptyMap();
		
		for(Blizzard originalState : blizzards) {
			Blizzard b = originalState.atTime(minute, maxX, maxY);
			newMap[b.x][b.y].blizzards++;
		}
		
		logbook.put(minute, newMap);
		return newMap;
	}
	
	public void printMap(Integer minute) {
		Coord[][] map = get(minute);
		
		StringBuilder sb = new StringBuilder();
		
		for(int y=0; y<maxY; y++) {
			for(int x=0; x<maxX; x++) {
				Coord c = map[x][y];
				
				if(c.type == Type.WALL)
					sb.append('#');
				else if(c.blizzards > 0)
					sb.append(c.blizzards);
				else
					sb.append('.');
			}
			
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	public boolean isPossible(Coord c) {
		if(c.x < 0 || c.x >= maxX || c.y < 0 || c.y >= maxY)
			return false;
		
		Coord check = get(c.time)[c.x][c.y]; 
		if(check.visited)
			return false;
		
		check.visited = true;
			
		return check.blizzards == 0 && check.type == Type.OPEN;
	}
}
