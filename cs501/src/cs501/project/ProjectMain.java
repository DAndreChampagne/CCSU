package cs501.project;

import java.awt.Color;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.awt.*;
import javax.swing.JFrame;

public class ProjectMain {

	public static void main(String[] args) {
		
		// I'm so happy
		WorldCell c = new WorldCell() {{
			north = 0;
		}};
		
		System.out.println("c.north = " + c.north);
		

		World w = new World(20, 20);
		
		w.Run();
		
	}

}
