import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Backup3D.DrawShapes;
import Backup3D.ShapesPanel;

public class Clicker implements MouseListener{

	Run3DMaze.mazeState state;
	char[] directions = {'W','S','E','N'};
    Room currentRoom;    
	
	@Override
	public void mouseClicked(MouseEvent e) {
		switch(state) {
		case WELCOMESCREEN: 
			
			break;
		case CHAMBERVIEW: 
			
			//check coordinates of click
			//call move direction method of maze class
			//trigger animation by setting timer counter back to 0
			
			GamePanel.timeCounter = 0;
			Run3DMaze.clicked = true;
			break;
		case MAPVIEW: 
			
			break;
		case LEADERBOARD: 
			
			break;
		}
		
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
