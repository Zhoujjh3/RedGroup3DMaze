import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Backup3D.Shapes;
import Backup3D.ShapesPanel;

public class Clicker implements MouseListener{

	Run3DMaze.mazeState state;
	char[] directions = {'N','E','S','W'};
    Room currentRoom;    
    public static int dir;
    
	@Override
	public void mouseClicked(MouseEvent e) {
		state = Run3DMaze.state;
		
		switch(state) {
		case WELCOMESCREEN: 
			//do nothing ig
			break;
		case CHAMBERVIEW: 
			GamePanel.timeCounter = 0;
			Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'), 
					Run3DMaze.player.getCoordinate('X'), 
					Run3DMaze.player.getCoordinate('Y'));
			System.out.println("pressed");
			//System.out.println("dir: "+ dir);
			if(e.getX() < (Run3DMaze.width * (0.4)) ) {		//left
				dir = 0;
				for(Shapes3D i : currentRoom.ceilingAndFloor) {
					i.setDir(0);
				}
				for(Shapes3D i : currentRoom.walls) {
					i.setDir(0);
				}
				for(Shapes3D i : currentRoom.doors) {
					i.setDir(0);
				}
				Run3DMaze.player.setDirection(getLeftDirection(Run3DMaze.player.getDirection()));
				currentRoom.printDoors();
				System.out.println(Run3DMaze.player.getDirection());
				
			} else if (e.getX() > (Run3DMaze.width * (0.6))) {					//right
				dir = 1;
				for(Shapes3D i : currentRoom.ceilingAndFloor) {
					i.setDir(1);
				}
				for(Shapes3D i : currentRoom.walls) {
					i.setDir(1);
				}
				for(Shapes3D i : currentRoom.doors) {
					i.setDir(1);
				}
				Run3DMaze.player.setDirection(getRightDirection(Run3DMaze.player.getDirection()));
				System.out.println(Run3DMaze.player.getDirection());
				
			} else if(e.getX() > (450) && e.getX() < (550) 
					&& e.getY() > (515)) { 
				
				for(Shapes3D i : currentRoom.ceilingAndFloor) {
					i.setDir(1);
				}
				for(Shapes3D i : currentRoom.walls) {
					i.setDir(2);
				}
				for(Shapes3D i : currentRoom.doors) {
					i.setDir(2);
				}
				Run3DMaze.nextRoom = true;
				System.out.println(Run3DMaze.player.getDirection());
				//Run3DMaze.player.movePlayer(Run3DMaze.player.getDirection());
				
			} else if(e.getX() > (Run3DMaze.width * (0.4)) && e.getX() < (Run3DMaze.width * (0.6)) 
			&& e.getY() < (Run3DMaze.height * (2.0/7.0))) {
				for(Shapes3D i : currentRoom.ceilingAndFloor) {
					i.setDir(3);
				}
				for(Shapes3D i : currentRoom.walls) {
					i.setDir(3);
				}
				for(Shapes3D i : currentRoom.doors) {
					i.setDir(3);
				}
				System.out.println(Run3DMaze.player.getDirection());
				
			} else if(e.getX() > (Run3DMaze.width * (0.4)) && e.getX() < (Run3DMaze.width * (0.6)) 
			&& e.getY() > (Run3DMaze.height * (5.0/7.0))) {
				for(Shapes3D i : currentRoom.ceilingAndFloor) {
					i.setDir(4);
				}
				for(Shapes3D i : currentRoom.walls) {
					i.setDir(4);
				}
				for(Shapes3D i : currentRoom.doors) {
					i.setDir(4);
				}
				System.out.println(Run3DMaze.player.getDirection());
			}
			
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
	
	public char getLeftDirection(char direction) {
		int index = 0;
		if(direction == 'N') {
			index = 0;
		} else if(direction == 'E') {
			index = 1;
		} else if(direction == 'S') {
			index = 2;
		} else {
			index = 3;
		}
		
		if(index > 0) {
			return directions[index-1];
		} else {
			return directions[3];
		}
	}
	
	public char getRightDirection(char direction) {
		int index = 0;
		if(direction == 'N') {
			index = 0;
		} else if(direction == 'E') {
			index = 1;
		} else if(direction == 'S') {
			index = 2;
		} else {
			index = 3;
		}
		
		if(index < 3) {
			return directions[index+1];
		} else {
			return directions[0];
		}
	}

}




