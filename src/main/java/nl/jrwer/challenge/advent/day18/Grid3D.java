package nl.jrwer.challenge.advent.day18;

import java.util.List;

class Grid3D {
	final List<Cube> cubes;
	final int[] dim;
	final boolean[][][] grid;

	public Grid3D(List<Cube> cubes) {
		this.cubes = cubes;
		this.dim = getDimensionsGrid();
		this.grid = new boolean[this.dim[0]][this.dim[1]][this.dim[2]];
		this.populateGrid();
	}

	private int[] getDimensionsGrid() {
		int x = 0, y = 0, z = 0;

		for (Cube cube : cubes) {
			x = x > cube.x ? x : cube.x;
			y = y > cube.y ? y : cube.y;
			z = z > cube.z ? z : cube.z;
		}

//			System.out.println(String.format("x: %d, y: %d, z: %d", x, y, z));

		return new int[] { x + 1, y + 1, z + 1 };
	}

	private void populateGrid() {
		for (Cube cube : cubes)
			set(cube);
	}

	public int exposedSurfaceArea() {
		int exposed = 0;

		for (Cube cube : cubes)
			exposed += exposedSurfaceArea(cube);

		return exposed;
	}

	private void set(Cube cube) {
		set(cube.x, cube.y, cube.z);
	}

	private void set(int x, int y, int z) {
//			System.out.println(String.format("x: %d, y: %d, z: %d", x, y, z));

		grid[x][y][z] = true;
	}

	protected boolean get(int x, int y, int z) {
		// coordinates are outside grid, so there cannot be a cube
		if (isBorder(x, y, z))
			return false;

//			System.out.println(String.format("x: %d, y: %d, z: %d", x, y, z));

		return grid[x][y][z];
	}

	protected boolean isBorder(Cube cube) {
		return isBorder(cube.x, cube.y, cube.z);
	}

	protected boolean isBorder(int x, int y, int z) {
		return x < 0 || x >= dim[0] || y < 0 || y >= dim[1] || z < 0 || z >= dim[2];
	}

	protected int exposedSurfaceArea(Cube cube) {
		return exposedSurfaceArea(cube.x, cube.y, cube.z);
	}

	protected int exposedSurfaceArea(int x, int y, int z) {
		int exposed = 0;

		if (!get(x + 1, y, z))
			exposed++;
		if (!get(x - 1, y, z))
			exposed++;
		if (!get(x, y + 1, z))
			exposed++;
		if (!get(x, y - 1, z))
			exposed++;
		if (!get(x, y, z + 1))
			exposed++;
		if (!get(x, y, z - 1))
			exposed++;

		return exposed;
	}
}