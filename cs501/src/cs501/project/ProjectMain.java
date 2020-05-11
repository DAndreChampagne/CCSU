package cs501.project;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;


public class ProjectMain {
	
	/*********************************************************
	 * 
	 * Program variables
	 * 
	 * Modify these properties to change the parameters of the
	 * application.
	 *
	 *********************************************************/
	
	
	// used for image capture of completed graph/path. needs to end in a slash.
	public static String _FilePath = "/Users/dan/Desktop/graph/"; 
	
	// number of rows in the graph
	public static int _Rows = 30;
	
	// number of columns in the graph
	public static int _Columns = 30;
	
	// false = slower run
	// true = heuristic function as designed
	public static boolean _UseHeuristic = false;
	
	// turn this off to only show the shorted path
	public static boolean _RenderInRealTime = true;
	
	/*********************************************************
	 * 
	 *********************************************************/
	
	public static void main(String[] args) {
		
		String csv = "";
		
		for (int i=100; i<105; ++i) {
			World w = new World(_Rows, _Columns);
			w.SetRenderInRealTime(_RenderInRealTime);
			//System.out.println("setup results: " + w.GetSetupResults().toString());
	
			
			
			try {
				Thread.sleep(2000);
				LinkedList<WorldCell> path = w.ComputePath(PathAlgorithm.AStar, _UseHeuristic);
//				System.out.println("path.Size = " + path.Size() + ", path results: " + w.GetLastPathResults().toString());
				
				String str = Arrays.stream(new int[] { 
						w.Rows(), 
						w.Columns(), 
						(w.Rows()*w.Columns()),
						w.Edges(),
						path.Size(),
						w.GetLastPathResults().comparisons,
						w.GetLastPathResults().exchanges,
						w.GetLastPathResults().assignments,
						w.GetLastPathResults().loops
				})
						.mapToObj(String::valueOf)
						.collect(Collectors.joining(","));
				
				
				csv += str + "\n";
	
				System.out.println(str);
	
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");    
	
				Container content = w.getContentPane();
				BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = img.createGraphics();
				content.paint(g);
				
				File f = new File(_FilePath + "graph " + i + ", " + dtf.format(LocalDateTime.now()) + ".png");
				ImageIO.write(img, "png", f);
				
				g.dispose();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				w.dispose();
			}
		}
		
	}
}
