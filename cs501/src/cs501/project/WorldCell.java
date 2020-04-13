package cs501.project;

public class WorldCell {
	public int x;
	public int y;
	public String label;
	
	public WorldCell North = null;
	public WorldCell South = null;
	public WorldCell East = null;
	public WorldCell West = null;
	
	public boolean isStart = false;
	public boolean isGoal = false;
	
	public int gScore = Integer.MAX_VALUE;
	public int fScore = Integer.MAX_VALUE;
	public WorldCell cameFrom = null;

	public int getgScore() { return gScore; }
	public int getfScore() { return fScore; }
	
	public org.graphstream.graph.Node GraphNode = null;
	
	public boolean Connected() { return North != null || South != null || East != null || West != null; }
	public int Connections() {
		return
				(North == null ? 0 : 1) +
				(South == null ? 0 : 1) + 
				(East == null ? 0 : 1) + 
				(West == null ? 0 : 1);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isGoal ? 1231 : 1237);
		result = prime * result + (isStart ? 1231 : 1237);
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorldCell other = (WorldCell) obj;
		if (isGoal != other.isGoal)
			return false;
		if (isStart != other.isStart)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "WorldCell [x=" + x + ", y=" + y + "]";
	}
	
	
	
}
