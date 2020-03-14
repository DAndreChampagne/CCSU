package cs501.project;

import java.awt.*;
import javax.swing.JFrame;

public class World extends Canvas {
	private static final long serialVersionUID = 4492601785821268444L;
	private int _Rows;
	private int _Columns;
	
	
	public World(int rows, int cols) {
		_Rows = rows;
		_Columns = cols;
	}
	
	public void paint(Graphics g) {  
//        g.drawString("Hello",40,40);  
//        setBackground(Color.WHITE);  
//        g.fillRect(130, 30,100, 80);  
//        g.drawOval(30,130,50, 60);  
//        setForeground(Color.BLACK);  
//        g.fillOval(130,130,50, 60);  
//        g.drawArc(30, 200, 40,50,90,60);  
//        g.fillArc(30, 130, 40,50,180,40);
		
		g.drawString(g.getClipBounds().toString(), 40, 40);
		
		
//		g.drawLine(x1, y1, x2, y2);
	}
	
	public void Run() {
		//ProjectMain m = new ProjectMain();  
        JFrame f = new JFrame();  
        f.add(this);  
        f.setSize(400,400);  
        //f.setLayout(null);  
        f.setVisible(true);  
	}
	
	
}
