package nl.jrwer.challenge.advent.day22.cube;

public class CubeRotate {
	public CubeCoord[][] rotate90CW(CubeCoord[][] matrix) {
	    if (matrix == null || matrix.length == 0)
	        return matrix;

	    int m = matrix.length;
	    int n = matrix[0].length;

	    CubeCoord[][] rotatedMatrix = new CubeCoord[n][m];

	    for (int i = 0; i < m; i++)
	        for (int j = 0; j < n; j++)
	        	rotatedMatrix[j][m-i-1] = matrix[i][j];
	    
	    return rotatedMatrix;
	}
	
	public CubeCoord[][] rotate90CCW(CubeCoord[][] matrix) {
	    if (matrix == null || matrix.length == 0)
	        return matrix;

	    int m = matrix[0].length;
	    int n = matrix.length;

	    CubeCoord[][] rotatedMatrix = new CubeCoord[n][m];

	    for (int i = 0; i < m; i++)
	        for (int j = 0; j < n; j++)
	        	rotatedMatrix[i][j] = matrix[j][m-i-1];
	    
	    return rotatedMatrix;
	}
	
	public CubeCoord[][] rotate180(CubeCoord[][] matrix) {
		int m = matrix.length;
	    int n = matrix[0].length;

	    CubeCoord[][] rotatedMatrix = new CubeCoord[m][n];
	    
		// Simply print from last
		// cell to first cell.
		for (int i = m - 1; i >= 0; i--)
			for (int j = n - 1; j >= 0; j--)
				rotatedMatrix[m-i-1][n-j-1] = matrix[i][j];
		
		return rotatedMatrix;
	}
	
	public void print(int[][] m) {
		for(int i=0; i<m.length; i++)  {
			for(int j=0; j<m[0].length; j++)
				System.out.print(m[i][j] + " ");
			System.out.println("");
		}
	}
}
