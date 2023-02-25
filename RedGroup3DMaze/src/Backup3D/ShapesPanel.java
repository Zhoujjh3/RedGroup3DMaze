package Backup3D;
import java.awt.*;
import javax.swing.*;

public class ShapesPanel extends JPanel{
	
	public static Shapes[] shapesList = {
		new Wall(0),
		new Wall(1),
		new Wall(2),
		new Wall(3),
		new Door(0),
		//new Door(1),
		//new Door(2)
	};
	public static int timeCounter = 200;
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(243,243,243));
		g.fillRect(0, 0, 1000, 700);
		
		for(Shapes shape : shapesList) {
			shape.paint(g);
		}
	}

}
