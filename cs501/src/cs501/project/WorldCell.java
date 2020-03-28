package cs501.project;

public class WorldCell {
	int x;
	int y;
	String label;
	
	public WorldCell North = null;
	public WorldCell South = null;
	public WorldCell East = null;
	public WorldCell West = null;
	
//	public boolean north = true;
//	public boolean south = true;
//	public boolean east = true;
//	public boolean west = true;
	
	public boolean isBlocked = false;
	public boolean isStart = false;
	public boolean isGoal = false;
	
	
	public org.graphstream.graph.Node GraphNode = null;
	
	public boolean Connected() { return North != null || South != null || East != null || West != null; }
}
