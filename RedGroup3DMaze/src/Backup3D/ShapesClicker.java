package Backup3D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShapesClicker implements MouseListener{

	int clicks = 0;
	public void mouseClicked(MouseEvent e) {
		
		
		if(e.getX() < 500) {	
			for(Shapes i : ShapesPanel.shapesList) {
				i.setDir(true);
				//i.dir = true
				if (clicks > 0) {
					i.setState(i.getState() + 1);
					//i.state += 1;
					i.setState(i.getState() % 4);
					//i.state = i.state % 4;
				}
			}
		} else {
			for(Shapes i : ShapesPanel.shapesList) {
				i.setDir(false);
				//i.dir = false;
				if (clicks > 0) {
					i.setState(i.getState() - 1);
					//i.state -= 1;
					if(i.getState() == -1) {
						i.setState(3);
					}
					/*if(i.state == -1) {
						i.state = 3;
					}*/
				}
			}
		}
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
