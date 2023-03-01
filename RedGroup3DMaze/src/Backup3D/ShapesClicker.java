package Backup3D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShapesClicker implements MouseListener{

	public static int dir;
	public void mouseClicked(MouseEvent e) {
		
		if(e.getX() < (DrawShapes.width * (0.4)) ) {		//left
			dir = 0;
			for(Shapes i : ShapesPanel.ceilingAndFloor) {
				i.setDir(0);
			}
			for(Shapes i : ShapesPanel.walls) {
				i.setDir(0);
			}
			for(Shapes i : ShapesPanel.doors) {
				i.setDir(0);
			}
		} else if (e.getX() > (DrawShapes.width * (0.6))) {					//right
			dir = 1;
			for(Shapes i : ShapesPanel.ceilingAndFloor) {
				i.setDir(1);
			}
			for(Shapes i : ShapesPanel.walls) {
				i.setDir(1);
			}
			for(Shapes i : ShapesPanel.doors) {
				i.setDir(1);
			}
		} else if (e.getX() > (DrawShapes.width * (0.4)) && e.getX() < (DrawShapes.width * (0.6)) 
		&& e.getY() > (DrawShapes.height * (2.0/7.0)) && e.getY() < (DrawShapes.height * (5.0/7.0))) {
			for(Shapes i : ShapesPanel.ceilingAndFloor) {
				i.setDir(1);
			}
			for(Shapes i : ShapesPanel.walls) {
				i.setDir(2);
			}
			for(Shapes i : ShapesPanel.doors) {
				i.setDir(2);
			}
		} else if(e.getX() > (DrawShapes.width * (0.4)) && e.getX() < (DrawShapes.width * (0.6)) 
		&& e.getY() < (DrawShapes.height * (2.0/7.0))) {
			for(Shapes i : ShapesPanel.ceilingAndFloor) {
				i.setDir(3);
			}
			for(Shapes i : ShapesPanel.walls) {
				i.setDir(3);
			}
			for(Shapes i : ShapesPanel.doors) {
				i.setDir(3);
			}
		} else if(e.getX() > (DrawShapes.width * (0.4)) && e.getX() < (DrawShapes.width * (0.6)) 
		&& e.getY() > (DrawShapes.height * (5.0/7.0))) {
			for(Shapes i : ShapesPanel.ceilingAndFloor) {
				i.setDir(4);
			}
			for(Shapes i : ShapesPanel.walls) {
				i.setDir(4);
			}
			for(Shapes i : ShapesPanel.doors) {
				i.setDir(4);
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