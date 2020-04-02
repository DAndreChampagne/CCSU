package cs501.project;

public class ProjectMain {
	
	public static void main(String[] args) {
		
		World w = new World(30,30);

		try {
			LinkedList<WorldCell> path = w.ComputePath(PathAlgorithm.AStar);

			System.out.println("Path computed!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
