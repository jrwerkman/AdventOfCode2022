package nl.jrwer.challenge.advent.day08;

import java.util.List;

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