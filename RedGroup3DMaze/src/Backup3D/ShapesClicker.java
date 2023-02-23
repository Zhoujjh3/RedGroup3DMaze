package Backup3D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShapesClicker implements MouseListener{

	int clicks = 0;
	public void mouseClicked(MouseEvent e) {
		
		
		if(e.getX() < 500) {	
			for(shape i : ShapesPanel.shapesList) {
				i.dir = true;
				if (clicks > 0) {
					i.state += 1;
					i.state = i.state % 4;
				}
			}
		} else {
			for(shape i : ShapesPanel.shapesList) {
				i.dir = false;
				if (clicks > 0) {
					i.state -= 1;
					if(i.state == -1) {
						i.state = 3;
					}
				}
			}
		}
			
			//leftTrapezoid.state += 1;
			//rightTrapezoid.state += 1;
			//square.state += 1;
			//backwall.state += 1;
		//leftTrapezoid.state = leftTrapezoid.state % 4;
		//rightTrapezoid.state = rightTrapezoid.state % 4;
		//square.state = square.state % 4;
		//backwall.state = backwall.state % 4;
		//shape.state += 1;
		ShapesPanel.timeCounter = 0;
		clicks++;
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
