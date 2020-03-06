package cs501.project;

import cs501.project.Queue;

import java.awt.Color;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class ProjectMain {

	public static void main(String[] args) {
		
		// I'm so happy
		WorldCell c = new WorldCell() {{
			north = 0;
		}};
		
		System.out.println("c.north = " + c.north);
	}

}
