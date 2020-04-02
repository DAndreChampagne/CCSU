package cs501.project;

import java.awt.*;

import javax.swing.JFrame;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swingViewer.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


//http://graphstream-project.org/doc/Tutorials/Graph-Visualisation/
//https://github.com/graphstream/gs-ui-swing/blob/master/src-test/org/graphstream/ui/viewer_swing/test/AllSwingTest.java



public class World extends JFrame {
	
	private static final long serialVersionUID = 4492601785821268444L;
	private int _Rows = 0;
	private int _Columns = 0;
	private Graph _Graph = new MultiGraph("Test");
	private WorldCell[][] _Data;	
	private Viewer _Viewer = null;
	private ViewPanel _ViewPanel = null;
	
	private WorldCell _Start = null;
	private WorldCell _Goal = null;
	
	public World(int rows, int cols) {
		_Rows = rows;
		_Columns = cols;

		LayoutManager layout = new BorderLayout();
		
		generateGraph();
		
		_Viewer = new Viewer(_Graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
//		_Viewer.enableAutoLayout(); // uncomment this to make the graph less boxy
		_ViewPanel = _Viewer.addDefaultView(false);
		
		this.setLayout(layout);
		World.setDefaultLookAndFeelDecorated(true);
		this.add(_ViewPanel, BorderLayout.CENTER);
		this.setTitle("CS501 Graph Project");
		this.setSize(25 * _Rows, 25 * _Columns);
		this.setLocation(0, 0);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
		
//	public WorldCell[][] Data() { return _Data; }
	
	public int Rows() { return _Rows; }
	public int Columns() { return _Columns; }
		
	public WorldCell Start() { return _Start; }
	public WorldCell Goal() { return _Goal; }
	
//	public void SetStart(int row, int column) {
//		_Data[row][column].isStart = true;
//	}	
//	public void SetGoal(int row, int column) {
//		_Data[row][column].isGoal = true;
//	}
	
	public void ChangeNodeColor(int row, int column, Color color) { ChangeNodeColor(_Data[row][column].GraphNode, color); }
	public void ChangeNodeColor(org.graphstream.graph.Node n, Color color) {
		n.changeAttribute("ui.style", "fill-color: #"+String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue())+";");
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public LinkedList<WorldCell> ComputePath(PathAlgorithm algorithm) throws Exception {
		switch (algorithm) {
			case AStar:
				return ComputePath_AStar();
			default:
				throw new Exception();
		}
		
	}
	
	// https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/	
	// https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode
	private LinkedList<WorldCell> ComputePath_AStar() throws Exception {
		Set<WorldCell>
			openSet = new HashSet<WorldCell>(),
			closedSet = new HashSet<WorldCell>();
		int[][]
			gScore = new int[_Rows][_Columns],
			fScore = new int[_Rows][_Columns];
		WorldCell[][]
			cameFrom = new WorldCell[_Rows][_Columns];
		
		for (int i=0; i<_Rows; ++i) {
			for (int j=0; j<_Columns; ++j) {
				gScore[i][j] = Integer.MAX_VALUE;
				fScore[i][j] = Integer.MAX_VALUE;
				cameFrom[i][j] = null;
			}
		}
		
		openSet.add(_Start);
		_Start.gScore = gScore[_Start.x][_Start.y] = 0;
		_Start.fScore = fScore[_Start.x][_Start.y] = distance(_Start, _Goal);

		
		while (!openSet.isEmpty()) {
			WorldCell current = openSet.stream()
				.sorted(Comparator.comparingInt(WorldCell::getfScore))
				.findFirst()
				.get();
			
			ChangeNodeColor(current.GraphNode, Color.CYAN);
		
			// goal reached! reconstruct path and return it
			if (current.equals(_Goal)) {
				LinkedList<WorldCell> path = new LinkedList<>(WorldCell.class);
				
				path.Prepend(current);
				ChangeNodeColor(current.GraphNode, Color.GREEN);
				
				for (;;) {
					current = cameFrom[current.x][current.y];
					if (current == null)
						break;
					path.Prepend(current);
					ChangeNodeColor(current.GraphNode, Color.GREEN);
				}
				
				return path;
			}
			
			closedSet.add(current);
			openSet.remove(current);
			
			
			WorldCell[] neighbors = { current.North, current.South, current.East, current.West };
			for (WorldCell neighbor : neighbors) {
				if (neighbor != null && !closedSet.contains(neighbor)) {
					
					int tentative_gScore = gScore[current.x][current.y] + distance(current, neighbor);
					if (tentative_gScore < gScore[neighbor.x][neighbor.y]) {
						neighbor.cameFrom = cameFrom[neighbor.x][neighbor.y] = current;
						neighbor.gScore = gScore[neighbor.x][neighbor.y] = tentative_gScore;
						neighbor.fScore = fScore[neighbor.x][neighbor.y] = tentative_gScore + distance(neighbor, _Goal);
//						neighbor.fScore = fScore[neighbor.x][neighbor.y] = distance(neighbor, _Goal);
						
						if (!closedSet.contains(neighbor)) {
							openSet.add(neighbor);
							ChangeNodeColor(neighbor.GraphNode, Color.YELLOW);
						}
					}	
				}
			}
			
			ChangeNodeColor(current.GraphNode, Color.RED);
//			System.out.println(this.tofScoreString());
		}
		
		throw new Exception("Could not compute path");
	}

	private int distance(WorldCell a, WorldCell b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	
		
 	private void generateGraph() {
		Random r = new Random();
		
		
		// deal with the border of the map
		_Data = new WorldCell[_Rows][_Columns];
		for (int i=0; i<_Rows; ++i) {
			for (int j=0; j<_Columns; ++j) {
				_Data[i][j] = new WorldCell();
				_Data[i][j].x = i;
				_Data[i][j].y = j;
				_Data[i][j].label = String.format("%03d%03d", i, j);
			}
		}
		
		// the rest of the stuff!
		for (int i=0; i<_Rows; ++i) {
			for (int j=0; j<_Columns; ++j) {

				if (i-1 >= 0) {
					_Data[i][j].North = _Data[i-1][j];
					_Data[i][j].North.South = _Data[i][j];
				}
				if (i+1 < _Rows) {
					_Data[i][j].South = _Data[i+1][j];
					_Data[i][j].South.North = _Data[i][j];
				}
				if (j-1 >= 0) {
					_Data[i][j].East = _Data[i][j-1];
					_Data[i][j].East.West = _Data[i][j];
				}
				if (j+1 < _Columns) {
					_Data[i][j].West = _Data[i][j+1];
					_Data[i][j].West.East = _Data[i][j];
				}
			}
		}
		
		
		// choose starting and goal points
		_Data[0][0].isStart = true;
		_Data[_Rows-1][_Columns-1].isGoal = true;
//		_Data[r.nextInt(_Rows/2)][r.nextInt(_Columns/2)].isStart = true;
//		_Data[r.nextInt(_Rows/2) + (_Rows/2)][r.nextInt(_Columns/2) + (_Columns/2)].isGoal = true;
		_Start = _Data[0][0];
		_Goal = _Data[_Rows-1][_Columns-1]; 
		
		
		
		// remove random connections to make the path more complicated
		for (int i=0; i<_Rows; ++i) {
			for (int j=0; j<_Columns; ++j) {
				WorldCell c = _Data[i][j];
				
				if (c.isStart || c.isGoal)
					continue;
				
				if (c.Connections() >= 1 && c.North != null && c.North.Connections() > 3 && r.nextDouble() > 0.5) {
					c.North.South = null;
					c.North = null;
				}
				if (c.Connections() >= 1 && c.South != null && c.South.Connections() > 3 && r.nextDouble() > 0.5) {
					c.South.North = null;
					c.South = null;
				}
				if (c.Connections() >= 1 && c.East != null && c.East.Connections() > 3 && r.nextDouble() > 0.5) {
					c.East.West = null;
					c.East = null;
				}
				if (c.Connections() >= 1 && c.West != null && c.West.Connections() > 3 && r.nextDouble() > 0.5) {
					c.West.East = null;
					c.West = null;
				}
			}
		}
		
		
		// generate the nodes on the graph
		for (int i=0; i<Rows(); ++i) {
			for (int j=0; j<Columns(); ++j) {
				WorldCell c = _Data[i][j];
				org.graphstream.graph.Node n = _Graph.addNode(c.label);
				
				c.GraphNode = n;
				n.addAttribute("ui.frozen");
				n.addAttribute("x", i);
				n.addAttribute("y", j);
				
				if (c.isStart) {		
					ChangeNodeColor(n, Color.BLUE);
					n.addAttribute("ui.label", "Start");
				}
				else if (c.isGoal) {
					ChangeNodeColor(n, Color.GREEN);
					n.addAttribute("ui.label", "Goal");
				}
			}
		}
		
		// generate the edges between nodes
		for (int i=0; i<Rows(); ++i) {
			for (int j=0; j<Columns(); ++j) {
				WorldCell c = _Data[i][j];
				String source = c.label;
				String dest = "";
				
				if (c.North != null) {
					dest = c.North.label;
					try { _Graph.addEdge(source+dest, source, dest); } catch (Exception ex) { System.out.println(ex.getMessage()); }
				}
				
				if (c.South != null) {
					dest = c.South.label;
					try { _Graph.addEdge(source+dest, source, dest); } catch (Exception ex) { System.out.println(ex.getMessage()); }
				}
				
				if (c.East != null) {
					dest = c.East.label;
					try { _Graph.addEdge(source+dest, source, dest); } catch (Exception ex) { System.out.println(ex.getMessage()); }
				}
				
				if (c.West != null) {
					dest = c.West.label;
					try { _Graph.addEdge(source+dest, source, dest); } catch (Exception ex) { System.out.println(ex.getMessage()); }
				}
			}
		}
	}

	public void Run() {
	
		
		
	}
	
	public String tofScoreString() {
		String result = "";
		
		for (int i=0; i < _Rows; ++i) {
			for (int j=0; j < _Columns; ++j) {
				WorldCell cell = _Data[i][j];
				if (cell.fScore == Integer.MAX_VALUE)
					result += String.format("[∞∞]");
				else
					result += String.format("[%02d]", cell.fScore);
			}
			result += "\n";
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		String result = "";
		
		for (int i=0; i < _Rows; ++i) {
			for (int j=0; j < _Columns; ++j) {
				WorldCell cell = _Data[i][j];
				if (cell.isStart)
					result += "[S]";
				else if (cell.isGoal)
					result += "[G]";
				else
					result += "[ ]";	
			}
			result += "\n";
		}
		
		return result;
	}

}
