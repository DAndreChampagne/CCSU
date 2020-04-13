package cs501.project;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

public class ProjectMain {
	
	public static void main(String[] args) {
		
		for (int i=20; i<25; ++i) {
			World w = new World(30,30);
	
			try {
				Thread.sleep(2000);
				LinkedList<WorldCell> path = w.ComputePath(PathAlgorithm.AStar, false);
	
				System.out.println("Path computed!");
	
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");    
	
				Container content = w.getContentPane();
				BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = img.createGraphics();
				content.paint(g);
				
				File f = new File("/Users/dan/Desktop/graph/graph " + i + ", " + dtf.format(LocalDateTime.now()) + ".png");
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
