package nl.jrwer.challenge.advent.day24;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Valley {
	final ValleyCalendar calendar;
	final Coord entrance, exit;
	
	public Valley(Coord entrance, Coord exit, Coord[][] grid, List<Blizzard> blizzards) {
		this.calendar = new ValleyCalendar(grid, blizzards);
		this.entrance = entrance;
		this.exit = exit;
	}
	
	public int findExit() {
		Coord start = entrance.clone(Type.EXPEDITION);
		
		Queue<Coord> q = new ArrayDeque<>();
		start.visited = true;
		q.add(start);

		while(!q.isEmpty()) {
			Coord c = q.poll();

			if(c.equals(exit))
				return c.time;
			
			Set<Coord> next = c.getMoves();
			for(Coord n : next) {
				if(calendar.isPossible(n))
					q.add(n);
			}
		}
		
		return -1;
	}
}
