package cs501.test2;

public class warshall {
	
	public static void printMatrix(int[][] m) {
		for (int i=0; i<m.length; ++i) {
			for (int j=0; j<m[i].length; ++j) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void warshall(int[][] m) {
		for (int y = 0; y < m.length; ++y) {
			for (int x = 0; x < m[y].length; ++x) {
				if (m[x][y] > 0) {
					for (int z = 0; z < m.length; ++z) {
						if (m[y][z] > 0)
							m[x][z] = 1;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		int[][] graph = {
				{0,1,0,0,0},
				{0,0,0,0,1},
				{0,1,0,0,0},
				{1,0,0,0,1},
				{0,0,1,0,0},
		};
		
		printMatrix(graph);
		
		System.out.println("\n**********************\n");
		
		warshall(graph);
		
		printMatrix(graph);
	}
}
