package Backup3D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShapesClicker implements MouseListener{

	public static boolean dir;
	public void mouseClicked(MouseEvent e) {
		
		if(e.getX() < 500) {	
			dir = true;
			for(Shapes i : ShapesPanel.shapesList) {
				i.setDir(true);
			}
		} else {
			dir = false;
			for(Shapes i : ShapesPanel.shapesList) {
				i.setDir(false);
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
