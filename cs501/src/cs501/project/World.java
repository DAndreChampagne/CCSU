package cs501.project;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.graphstream.algorithm.generator.DorogovtsevMendesGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swingViewer.*;


import java.util.Collection;
import java.util.Random;

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
	
	
	public World(int rows, int cols) {
		_Rows = rows;
		_Columns = cols;

		LayoutManager layout = new BorderLayout();
		
		generateGraph();
		
		_Viewer = new Viewer(_Graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		_Viewer.enableAutoLayout();
		_ViewPanel = _Viewer.addDefaultView(false);
		
		this.setLayout(layout);
		World.setDefaultLookAndFeelDecorated(true);
		this.add(_ViewPanel, BorderLayout.CENTER);
		this.setTitle("CS501 Graph Project");
		this.setSize(1200, 800);
		this.setLocation(200, 200);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
//	public Graph GetGraph() { return _Graph; }
//	public WorldCell[][] getData() { return _Data; }
	
	public int Rows() { return _Rows; }
	public int Columns() { return _Columns; }
		
//	public void SetStart(int row, int column) {
//		_Data[row][column].isStart = true;
//	}	
//	public void SetGoal(int row, int column) {
//		_Data[row][column].isGoal = true;
//	}
	
	public void ChangeNodeColor(int row, int column, Color color) { ChangeNodeColor(_Data[row][column].GraphNode, color); }
	public void ChangeNodeColor(org.graphstream.graph.Node n, Color color) {
//		n.changeAttribute("ui.style", "fill-color: #"+String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue())+";");
		n.changeAttribute("ui.style", "fill-color: #"+String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue())+";");
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
		for (int i=0; i<_Rows; ++i) {
			for (int j=0; j<_Columns; ++j) {
				if (i-1 >= 0)
					_Data[i][j].North = _Data[i-1][j];
				if (i+1 < _Rows)
					_Data[i][j].South = _Data[i+1][j];
				if (j-1 >= 0)
					_Data[i][j].East = _Data[i][j-1];
				if (j+1 < _Columns)
					_Data[i][j].West = _Data[i][j+1];
			}
		}
		
		
		// choose starting and goal points
		_Data[0][0].isStart = true;
		_Data[_Rows-1][_Columns-1].isGoal = true;
//		_Data[r.nextInt(_Rows/2)][r.nextInt(_Columns/2)].isStart = true;
//		_Data[r.nextInt(_Rows/2) + (_Rows/2)][r.nextInt(_Columns/2) + (_Columns/2)].isGoal = true;
//		
		
		// remove random connections to make the path more complicated
		for (int i=0; i<_Rows; ++i) {
			for (int j=0; j<_Columns; ++j) {
				WorldCell c = _Data[i][j];
				
				if (c.isStart || c.isGoal)
					continue;
				
//				if (c.Connected() && c.North != null && c.North.Connected() && r.nextDouble() < 0.5) { c.North = null; }
//				if (c.Connected() && c.South != null && c.South.Connected() && r.nextDouble() < 0.5) { c.South = null; }
//				if (c.Connected() && c.East != null && c.East.Connected() && r.nextDouble() < 0.5) { c.East = null; }
//				if (c.Connected() && c.West != null && c.West.Connected() && r.nextDouble() < 0.5) { c.West = null; }
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
