package Backup3D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShapesClicker implements MouseListener{

	public static int dir;
	public void mouseClicked(MouseEvent e) {
		
		if(e.getX() < 400) {		//left
			dir = 0;
			for(Shapes i : ShapesPanel.walls) {
				i.setDir(0);
			}
			for(Shapes i : ShapesPanel.doors) {
				i.setDir(0);
			}
		} else if (e.getX() > 600) {					//right
			dir = 1;
			for(Shapes i : ShapesPanel.walls) {
				i.setDir(1);
			}
			for(Shapes i : ShapesPanel.doors) {
				i.setDir(1);
			}
		} else {
			for(Shapes i : ShapesPanel.walls) {
				i.setDir(2);
			}
			for(Shapes i : ShapesPanel.doors) {
				i.setDir(2);
			}
		}
		ShapesPanel.timeCounter = 0;
		DrawShapes.clicked = true;
	}

	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {	
	}
	public void mouseExited(MouseEvent e) {
	}

}
