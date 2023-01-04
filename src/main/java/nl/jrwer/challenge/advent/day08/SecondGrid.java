package nl.jrwer.challenge.advent.day08;

import java.util.List;

class SecondGrid extends Grid {

	public SecondGrid(List<TreeLine> rowList) {
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