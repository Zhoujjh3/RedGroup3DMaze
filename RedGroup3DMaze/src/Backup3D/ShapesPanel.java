package Backup3D;
import java.awt.*;
import javax.swing.*;

public class ShapesPanel extends JPanel{
	
	public static shape[] shapesList = {
		new shape(0),
		new shape(1),
		new shape(2),
		new shape(3)
		//new leftTrapezoid(),  
		//new square(),
		//new rightTrapezoid(),
		//new backwall()
		};
	public static int timeCounter = 200;
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(50, 50, 50));
		g.fillRect(0, 0, 1000, 700);
		
		for(Shapes shape : shapesList) {
			shape.paint(g);
		}
	}

}
