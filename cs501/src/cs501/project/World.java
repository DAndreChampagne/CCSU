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
	private int _Edges = 0;
	
	private WorldCell[][] _Data;
	private WorldCell _Start = null;
	private WorldCell _Goal = null;

	
	private Graph _Graph = null;
	private Viewer _Viewer = null;
	private ViewPanel _ViewPanel = null;
	
	private int _GraphThreadDelay = 75;
	private boolean _RenderInRealTime = true;
	
	private AlgorithmResults _SetupResults = null, _PathResults = null;
	
	public World(int rows, int cols) {
		_Rows = rows;
		_Columns = cols;

		LayoutManager layout = new BorderLayout();
		_Graph = new MultiGraph("Test");
		_Graph.setStrict(false);
		_Graph.setAutoCreate(true);
		
		generateGraph();
		
		_Viewer = new Viewer(_Graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
//		_Viewer.enableAutoLayout(); // uncomment this to make the graph less boxy
		_ViewPanel = _Viewer.addDefaultView(false);
		
		this.setLayout(layout);
		World.setDefaultLookAndFeelDecorated(true);
		this.add(_ViewPanel, BorderLayout.CENTER);
		this.setTitle("CS501 Graph Project");
		this.setSize(30 * _Rows, 30 * _Columns);
		this.setLocation(0, 0);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	

	public AlgorithmResults GetSetupResults() { return _SetupResults; }
	public AlgorithmResults GetLastPathResults() { return _PathResults; }
	
	public int Rows() { return _Rows; }
	public int Columns() { return _Columns; }
	public int Edges() { return _Edges; }
		
	public WorldCell Start() { return _Start; }
	public WorldCell Goal() { return _Goal; }
	
	public void SetRenderInRealTime(boolean x) { _RenderInRealTime = x; }
	public boolean GetRenderInRealTime() { return _RenderInRealTime; }
	
	public void SetGraphDelay(int x) { _GraphThreadDelay = x; }
	public int GetGraphDelay() { return _GraphThreadDelay; }
	
	public void ChangeNodeColor(int row, int column, Color color) { ChangeNodeColor(_Data[row][column].GraphNode, color); }
	public void ChangeNodeColor(org.graphstream.graph.Node n, Color color) {
		try {
			n.changeAttribute("ui.style", "fill-color: #"+String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue())+";");
			Thread.sleep(_GraphThreadDelay); // without sleeping the thread, the graph library throws exceptions left and right
		} catch (InterruptedException e) {
			e.printStackTrace();
			
			// Do nothing else. The color should be picked up on the next repaint.
		}
	}
	
	
	public LinkedList<WorldCell> ComputePath(PathAlgorithm algorithm, boolean useHeuristic) throws Exception {
		switch (algorithm) {
			case AStar:
				return ComputePath_AStar(useHeuristic);
			default:
				throw new Exception("Undefined path algorithm: " + algorithm.toString());
		}
		
	}
	
	// https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/	
	// https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode
	
	private LinkedList<WorldCell> ComputePath_AStar(boolean useHeuristic) throws Exception {
		Set<WorldCell>
			openSet = new HashSet<WorldCell>(),
			closedSet = new HashSet<WorldCell>();
		WorldCell[][]
			cameFrom = new WorldCell[_Rows][_Columns];
		
		openSet.add(_Start);
		_Start.gScore = 0;
		_Start.fScore = distanceGeometric(_Start, _Goal);

		_PathResults = new AlgorithmResults();
		
		while (!openSet.isEmpty()) {
			_PathResults.loops++;
			
			WorldCell current = openSet.stream()
				.sorted(Comparator.comparingInt(WorldCell::getfScore))
				.findFirst()
				.get();
			
			if (_RenderInRealTime)
				ChangeNodeColor(current.GraphNode, Color.CYAN); // current node
		
			// goal reached! reconstruct path and return it
			_PathResults.comparisons++;
			if (current.equals(_Goal)) {
				LinkedList<WorldCell> path = new LinkedList<>(WorldCell.class);
				
				for (; current != null; current = cameFrom[current.x][current.y]) {
					_PathResults.loops++;
					_PathResults.assignments++;
					
					path.Prepend(current);
					ChangeNodeColor(current.GraphNode, Color.GREEN);
				}
				
				return path;				
			}
			
			closedSet.add(current);
			openSet.remove(current);
			
			WorldCell[] neighbors = { current.North, current.South, current.East, current.West };
			for (WorldCell neighbor : neighbors) {
				_PathResults.loops++;
				
				_PathResults.comparisons += 2;
				if (neighbor != null && !closedSet.contains(neighbor)) {

					int tentative_gScore = current.gScore + distanceGeometric(current, neighbor);
					if (tentative_gScore < neighbor.gScore) {
						neighbor.cameFrom = cameFrom[neighbor.x][neighbor.y] = current;
						neighbor.gScore = tentative_gScore;
						_PathResults.assignments += 2;
												
						if (useHeuristic) {
							neighbor.fScore = distanceGeometric(neighbor, _Goal); // faster method, not always fastest possible path
						}
						else {
							// So this is the fScore method given by the A* algorithm. However, during
							// my testing, I found that it produced a more meandering search. Calculating
							// the fScore based solely on the distance was more direct, but I decided to
							// leave it so that the algorithm will act as more of a starting point.
							neighbor.fScore = tentative_gScore + distanceGeometric(neighbor, _Goal); // much slower, fastest path
						}
						
						_PathResults.comparisons++;
						if (!openSet.contains(neighbor)) {
							_PathResults.assignments++;
							
							openSet.add(neighbor);
							
							if (_RenderInRealTime)
								ChangeNodeColor(neighbor.GraphNode, Color.YELLOW); // node added to open list
						}
					}
				}
			}
			
			if (_RenderInRealTime)
				ChangeNodeColor(current.GraphNode, Color.RED); // done processing node
		}
		
		throw new Exception("Could not compute path");
	}

	// doesn't perform as expected
	private int distanceEuclidian(WorldCell a, WorldCell b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	private int distanceGeometric(WorldCell a, WorldCell b) {
		return (int)Math.sqrt(Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
	}
	
		
 	private void generateGraph() {
		Random r = new Random();
		
		_SetupResults = new AlgorithmResults();
		
		// deal with the border of the map
		_Data = new WorldCell[_Rows][_Columns];
		for (int i=0; i<_Rows; ++i) {
			for (int j=0; j<_Columns; ++j) {
				_SetupResults.loops++;
				
				_Data[i][j] = new WorldCell();
				_Data[i][j].x = i;
				_Data[i][j].y = j;
				_Data[i][j].label = String.format("%03d%03d", i, j);
				
				_SetupResults.assignments += 4;
			}
		}
		
		// the rest of the stuff!
		for (int i=0; i<_Rows; ++i) {
			for (int j=0; j<_Columns; ++j) {
				_SetupResults.loops++;

				
				if (i-1 >= 0) {
					_SetupResults.assignments += 2;
					
					_Data[i][j].North = _Data[i-1][j];
					_Data[i][j].North.South = _Data[i][j];
				}
				if (i+1 < _Rows) {
					_SetupResults.assignments += 2;
					
					_Data[i][j].South = _Data[i+1][j];
					_Data[i][j].South.North = _Data[i][j];
				}
				if (j-1 >= 0) {
					_SetupResults.assignments += 2;
					
					_Data[i][j].East = _Data[i][j-1];
					_Data[i][j].East.West = _Data[i][j];
				}
				if (j+1 < _Columns) {
					_SetupResults.assignments += 2;
					
					_Data[i][j].West = _Data[i][j+1];
					_Data[i][j].West.East = _Data[i][j];
				}
				
				_SetupResults.comparisons += 4;
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
				_SetupResults.loops++;
				
				WorldCell c = _Data[i][j];
				
				if (c.isStart || c.isGoal)
					continue;
				
				_SetupResults.comparisons += 16;
				
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
				_SetupResults.loops++;
				
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
				_SetupResults.loops++;
				
				WorldCell c = _Data[i][j];
				String source = c.label;
				String dest = "";
				
				if (c.North != null) {
					dest = c.North.label;
					try { _Graph.addEdge(source+dest, source, dest); _Edges++; } catch (Exception ex) { System.out.println(ex.getMessage()); }
				}
				
				if (c.South != null) {
					dest = c.South.label;
					try { _Graph.addEdge(source+dest, source, dest); _Edges++; } catch (Exception ex) { System.out.println(ex.getMessage()); }
				}
				
				if (c.East != null) {
					dest = c.East.label;
					try { _Graph.addEdge(source+dest, source, dest); _Edges++; } catch (Exception ex) { System.out.println(ex.getMessage()); }
				}
				
				if (c.West != null) {
					dest = c.West.label;
					try { _Graph.addEdge(source+dest, source, dest); _Edges++; } catch (Exception ex) { System.out.println(ex.getMessage()); }
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
