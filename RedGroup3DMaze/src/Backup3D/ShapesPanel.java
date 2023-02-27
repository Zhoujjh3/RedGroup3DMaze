package Backup3D;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ShapesPanel extends JPanel{
	
	public static Shapes[] walls = {
		new Wall(0),
		new Wall(1),
		new Wall(2),
		new Wall(3)
	};
	
	public static Shapes[] doors = {
			new Door(0),
			new Door(1),
			new Door(2),
			new Door(3),
			new Trapdoor(0),
			new Hatch(0)
	};
	
	public static Shapes[] ceilingAndFloor = {
			
	};
	
	
	public static int timeCounter = 200;
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(243,243,243));
		g.fillRect(0, 0, 1000, 700);
		
		for(Shapes shape : walls) {
			shape.paint(g);
		}
		for(Shapes shape : doors) {
			shape.paint(g);
		}
	}

}
