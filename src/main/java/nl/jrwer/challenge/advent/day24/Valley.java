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
		return findExit(
				entrance.clone(Type.EXPEDITION), 
				exit.clone(Type.EXIT)).time;
	}
	
	public int findExitRoundTrip(int amount) {
		Coord start = entrance.clone(Type.EXPEDITION);
		Coord finish = exit.clone(Type.EXIT);
		
		for(int i=0; i<amount; i++) {
			Coord nextStart = findExit(start, finish).clone();
			
			finish = start;
			start = nextStart;
		}
		
		return start.time;
	}
	
	public Coord findExit(Coord start, Coord finish) {
		Queue<Coord> q = new ArrayDeque<>();
		q.add(start);

		while(!q.isEmpty()) {
			Coord c = q.poll();

			if(c.equals(finish))
				return c;
			
			Set<Coord> next = c.getMoves();
			for(Coord n : next) {
				if(calendar.isPossible(n))
					q.add(n);
			}
		}

		throw new RuntimeException("No escape found");
	}
}
