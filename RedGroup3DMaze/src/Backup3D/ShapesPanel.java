package Backup3D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ShapesPanel extends JPanel{
	
	ShapesPanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
	public static Shapes[] walls = {
		new Wall(0),
		new Wall(1),
		new Wall(2),
		new Wall(3)
	};
	
	public static Shapes[] doors = {
			//new Door(0),
			//new Door(1),
			//new Door(2),
			new Door(3),
			new Art(0),
			new Art(1),
			new Art(2),
			new Trapdoor(0),
			new Hatch(0)
	};
	
	public static Shapes[] ceilingAndFloor = {
			new Ceiling(),
			new Floor()
	};
	
	
	public static int timeCounter = 200;
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(243,243,243));
		g.fillRect(0, 0, DrawShapes.width, DrawShapes.height);
		
		for(Shapes shape : ceilingAndFloor) {
			shape.paint(g);
		}
		for(Shapes shape : walls) {
			shape.paint(g);
		}
		for(Shapes shape : doors) {
			shape.paint(g);
		}
	}

}