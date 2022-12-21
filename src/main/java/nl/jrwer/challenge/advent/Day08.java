package nl.jrwer.challenge.advent;

import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Day 8: Treetop Tree House ---
//
//The expedition comes across a peculiar patch of tall trees all planted carefully in a grid. The Elves explain that a previous expedition planted these trees as a reforestation effort. Now, they're curious if this would be a good location for a tree house.
//
//First, determine whether there is enough tree cover here to keep a tree house hidden. To do this, you need to count the number of trees that are visible from outside the grid when looking directly along a row or column.
//
//The Elves have already launched a quadcopter to generate a map with the height of each tree (your puzzle input). For example:
//
//30373
//25512
//65332
//33549
//35390
//
//Each tree is represented as a single digit whose value is its height, where 0 is the shortest and 9 is the tallest.
//
//A tree is visible if all of the other trees between it and an edge of the grid are shorter than it. Only consider trees in the same row or column; that is, only look up, down, left, or right from any given tree.
//
//All of the trees around the edge of the grid are visible - since they are already on the edge, there are no trees to block the view. In this example, that only leaves the interior nine trees to consider:
//
//    The top-left 5 is visible from the left and top. (It isn't visible from the right or bottom since other trees of height 5 are in the way.)
//    The top-middle 5 is visible from the top and right.
//    The top-right 1 is not visible from any direction; for it to be visible, there would need to only be trees of height 0 between it and an edge.
//    The left-middle 5 is visible, but only from the right.
//    The center 3 is not visible from any direction; for it to be visible, there would need to be only trees of at most height 2 between it and an edge.
//    The right-middle 3 is visible from the right.
//    In the bottom row, the middle 5 is visible, but the 3 and 4 are not.
//
//With 16 trees visible on the edge and another 5 visible in the interior, a total of 21 trees are visible in this arrangement.
//
//Consider your map; how many trees are visible from outside the grid?


public class Day08 {
	public static void main(String[] args) {
		try {
			Day08 day = new Day08();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<TreeLine> treeLines = new InputLoader("input-day-08.txt").getInput();

		Grid grid = new Grid(treeLines);
		
		System.out.println(grid.visibleTrees());
	}
	
	class Grid {
		protected final TreeLine[] rows;
		protected final int width;
		protected final int height;
		
		public Grid(List<TreeLine> rowList) {
			this.rows = rowList.toArray(new TreeLine[rowList.size()]); 
			
			this.height = rows.length;
			this.width = rows[0].trees.length;
		}
		
		public int visibleTrees() {
			int visible = 0;
			
			for(int y=0; y<height; y++)
				for(int x=0; x<width; x++)
					if(treeVisible(x, y))
						visible++;
			
			return visible;
		}
		
		public boolean treeVisible(int x, int y) {
			int treeHeight = getHeight(x, y);
			
			// if the tree is on the border it is visible
			if(x==0 || x == (this.width-1) || y == 0  || y == (this.height -1))
				return true;
			
			if(visibleVertical(treeHeight, x, y))
				return true;
			
			return visibleHorizontal(treeHeight, x, y);
		}
		
		private boolean visibleVertical(int treeHeight, int x, int y) {
			// check above and below
			for(int i=0; i<height; i++) {
				if(i < y && treeHeight <= getHeight(x, i))
					i = y + 1;

				if(i == y)
					return true;
				
				if(i > y && treeHeight <= getHeight(x, i)) 
					return false;
			}
			
			return true;			
		}
		
		private boolean visibleHorizontal(int treeHeight, int x, int y) {
			// check left and right
			for(int i=0; i<width; i++) {
				if(i < x && treeHeight <= getHeight(i, y)) 
					i = x + 1;
				
				if(i == x)
					return true;
				
				if(i > x && treeHeight <= getHeight(i, y))
					return false;
			}
			
			return true;
		}
		
		protected int getHeight(int x, int y) {
			return this.rows[y].trees[x];
		}
	}
	
	class TreeLine {
		final int[] trees;
		
		public TreeLine(String input) {
			char[] arr = input.toCharArray();
			this.trees = new int[input.length()];
			
			for(int i=0; i<arr.length; i++)
				// for part 1
				// '0' is 48 in char, so remove 48 to get the corresponding number 
				// Its not really necessary, because the differences between the elements in 
				// the array will not change. We could even keep the chars
				this.trees[i] = (arr[i] - 48); 
		}
	}

	class InputLoader extends BasicInputLoader<TreeLine>{

		public InputLoader(String file) {
			super(file);
		}

		protected TreeLine handleLine(String line) {
			return new TreeLine(line);
		}
	}
}
