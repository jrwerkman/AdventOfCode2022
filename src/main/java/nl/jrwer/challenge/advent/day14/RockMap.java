package nl.jrwer.challenge.advent.day14;

import java.util.ArrayList;
import java.util.List;

class RockMap {
	final Coord fallingStart = new Coord(500, 0, 'R');

	List<Coord> coords = new ArrayList<>();

	int amount = 0;

	public RockMap(List<String> rocks) {
		for (String rock : rocks) {
			addCoords(rock.split(" -> "));
		}
	}

	public boolean nextRock() {
		return nextRock(fallingStart);
	}

	private boolean nextRock(Coord stone) {
		Coord obstacle = inXAxis(stone);

		if (obstacle == null)
			return false;

		boolean leftOccupied = isOccupied(obstacle.left());
		boolean rightOccupied = isOccupied(obstacle.right());

		if (leftOccupied && rightOccupied)
			return addStone(obstacle.x, obstacle.y - 1);

		if (!leftOccupied)
			return nextRock(new Coord(stone.x - 1, obstacle.y - 1, 'o'));
		else
			return nextRock(new Coord(stone.x + 1, obstacle.y - 1, 'o'));
	}

	protected Coord inXAxis(Coord c) {
		Coord highest = null;

		for (Coord coord : coords)
			if (coord.x == c.x && coord.y > c.y) {
				if (highest == null)
					highest = coord;
				else if (highest.y > coord.y)
					highest = coord;
			}

//			System.out.println("highest: " + higest + "  (" + c + ")");

		return highest;
	}

	protected boolean isOccupied(Coord c) {
		return coords.contains(c);
	}

	private void addCoords(String[] rockPath) {
		Coord begin = new Coord(rockPath[0], '#');
		add(begin);

		for (int i = 1; i < rockPath.length; i++) {
			Coord end = new Coord(rockPath[i], '#');

			addDelta(begin, end);
			add(end);

			begin = end;
		}
	}

	private void addDelta(Coord first, Coord second) {
		if (first.x == second.x) {
			if (first.y < second.y)
				for (int i = first.y + 1; i < second.y; i++)
					add(first.x, i, '#');
			else
				for (int i = second.y + 1; i < first.y; i++)
					add(first.x, i, '#');
		} else {
			if (first.x < second.x)
				for (int i = first.x + 1; i < second.x; i++)
					add(i, first.y, '#');
			else
				for (int i = second.x + 1; i < first.x; i++)
					add(i, first.y, '#');
		}
	}

	protected boolean addStone(int x, int y) {
		add(x, y, 'o');
		amount++;
		return true;
	}

	protected void add(int x, int y, char value) {
		add(new Coord(x, y, value));
	}

	private void add(Coord coord) {
		if (!coords.contains(coord))
			coords.add(coord);
	}

	public void print() {
		int xMin = 500, xMax = 500, yMin = 0, yMax = 0;

		for (Coord c : coords) {
			if (c.x < xMin)
				xMin = c.x;
			if (c.x > xMax)
				xMax = c.x;
			if (c.y < yMin)
				yMin = c.y;
			if (c.y > yMax)
				yMax = c.y;
		}

		xMin -= 3;
		yMin--;
		xMax += 2;
		yMax++;

		char[][] grid = new char[yMax - yMin][xMax - xMin];
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				int realX = x + xMin + 1;
				int realY = y + yMin + 1;

				int index = coords.indexOf(new Coord(realX, realY, '#'));

				if (index >= 0)
					grid[y][x] = coords.get(index).value;
				else
					grid[y][x] = '.';
			}
		}

		for (int i = 0; i < grid.length; i++)
			System.out.println(grid[i]);
	}
}