package nl.jrwer.challenge.advent;

import java.util.List;

//--- Part Two ---
//
//Content with the amount of tree cover available, the Elves just need to know the best spot to build their tree house: they would like to be able to see a lot of trees.
//
//To measure the viewing distance from a given tree, look up, down, left, and right from that tree; stop if you reach an edge or at the first tree that is the same height or taller than the tree under consideration. (If a tree is right on the edge, at least one of its viewing distances will be zero.)
//
//The Elves don't care about distant trees taller than those found by the rules above; the proposed tree house has large eaves to keep it dry, so they wouldn't be able to see higher than the tree house anyway.
//
//In the example above, consider the middle 5 in the second row:
//
//30373
//25512
//65332
//33549
//35390
//
//    Looking up, its view is not blocked; it can see 1 tree (of height 3).
//    Looking left, its view is blocked immediately; it can see only 1 tree (of height 5, right next to it).
//    Looking right, its view is not blocked; it can see 2 trees.
//    Looking down, its view is blocked eventually; it can see 2 trees (one of height 3, then the tree of height 5 that blocks its view).
//
//A tree's scenic score is found by multiplying together its viewing distance in each of the four directions. For this tree, this is 4 (found by multiplying 1 * 1 * 2 * 2).
//
//However, you can do even better: consider the tree of height 5 in the middle of the fourth row:
//
//30373
//25512
//65332
//33549
//35390
//
//    Looking up, its view is blocked at 2 trees (by another tree with a height of 5).
//    Looking left, its view is not blocked; it can see 2 trees.
//    Looking down, its view is also not blocked; it can see 1 tree.
//    Looking right, its view is blocked at 2 trees (by a massive tree of height 9).
//
//This tree's scenic score is 8 (2 * 2 * 1 * 2); this is the ideal spot for the tree house.
//
//Consider each tree on your map. What is the highest scenic score possible for any tree?

public class Day08Part2 extends Day08{
	public static void main(String[] args) {
		try {
			Day08Part2 day = new Day08Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<TreeLine> treeLines = new InputLoader("input-day-08.txt").getInput();

		GridPart2 grid = new GridPart2(treeLines);
		
		System.out.println(grid.higestScenicScore());
	}
	
	class GridPart2 extends Grid {

		public GridPart2(List<TreeLine> rowList) {
			super(rowList);
		}
		
		public int higestScenicScore() {
			int higestScenicScore = 0;
			
			for(int y=0; y<height; y++)
				for(int x=0; x<width; x++) {
					int newScenicScore = scenicScore(x, y);
					
					if(newScenicScore > higestScenicScore)
						higestScenicScore = newScenicScore;
				}
			
			return higestScenicScore;
		}
		
		private int scenicScore(int x, int y) {
			int scenicScore = 0;
			int treeHeight = getHeight(x, y);
			
			// if the tree is on the border the scenic score will be 0,
			// because multiplying by 0 will be zero.
			if(x==0 || x == (this.width-1) || y == 0  || y == (this.height -1))
				return 0;
			
			scenicScore = visibleTreesAbove(treeHeight, x, y) *
					visibleTreesBelow(treeHeight, x, y) *
					visibleTreesLeft(treeHeight, x, y) *
					visibleTreesRight(treeHeight, x, y);
			
			
			return scenicScore;
		}
		
		private int visibleTreesAbove(int treeHeight, int x, int y) {
			int visible = 0;
			
			for(int i=y-1; i>=0; i--) {
				visible++;

				if(getHeight(x, i) >= treeHeight)
					break;
			}
			
			return visible;
		}
		
		private int visibleTreesBelow(int treeHeight, int x, int y) {
			int visible = 0;
			
			for(int i=y+1; i<height; i++) {
				visible++;

				if(getHeight(x, i) >= treeHeight)
					break;
			}
			
			return visible;
		}
		
		private int visibleTreesLeft(int treeHeight, int x, int y) {
			int visible = 0;
			
			for(int i=x-1; i>=0; i--) {
				visible++;

				if(getHeight(i, y) >= treeHeight)
					break;
			}
			
			return visible;
		}
		
		private int visibleTreesRight(int treeHeight, int x, int y) {
			int visible = 0;

			for(int i=x+1; i<width; i++) {
				visible++;

				if(getHeight(i, y) >= treeHeight)
					break;
			}
			
			return visible;
		}
	}
}
