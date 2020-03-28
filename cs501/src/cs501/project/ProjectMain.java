package cs501.project;

import java.awt.Color;
import java.util.Collection;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.awt.*;
import javax.swing.JFrame;

public class ProjectMain {
	
	private static int _direction = 1;
	private static int _color = 0;
	public static String Color() {
		_color += _direction;
		
		if (_color > 16777215 || _color == 0)
			_direction *= -1;
		
		return String.format("%06X", _color);
	}

	public static void main(String[] args) {
//		WorldCell c = new WorldCell() {{
//			north = 0;
//		}};
		
		World w = new World(20, 20);
//		System.out.println(w.toString());
//		w.Run();
		

		
		


	}

}
