import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Backup3D.Shapes;
import Backup3D.ShapesPanel;

public class Clicker implements MouseListener, KeyListener{

	Run3DMaze.mazeState state;
	char[] directions = {'N','E','S','W'};
    Room currentRoom;    
    public static int dir;
    
	@Override
	public void mouseClicked(MouseEvent e) {
		if(GamePanel.timeCounter > 200) {
		state = Run3DMaze.state;
		
		switch(state) {
		case WELCOMESCREEN: 
			//do nothing ig
			break;
		case CHAMBERVIEW: 
			Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'), 
					Run3DMaze.player.getCoordinate('X'), 
					Run3DMaze.player.getCoordinate('Y'));
			//System.out.println("pressed");
			//System.out.println("dir: "+ dir);
			System.out.println("x" + e.getX());
			System.out.println("y" + e.getY());
			//120, 450, 150, 90
			if(e.getX() >= 120 && e.getX() <= 270 && e.getY() >= 450 && e.getY() <= 540) { //left
				GamePanel.timeCounter = 0;
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
				GamePanel.timeCounter = 0;
				Run3DMaze.clicked = true;
				//730, 450, 150, 90,
			} else if (e.getX() >= 730 && e.getX() <= 880 && e.getY() >= 450 && e.getY() <= 540) {		//right			//right
				GamePanel.timeCounter = 0;
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
				GamePanel.timeCounter = 0;
				Run3DMaze.clicked = true;
				//450, 515, 100, 60
			} else if(e.getX() >= 450 && e.getX() <= 550 && e.getY() >= 430 && e.getY() <= 490	//forward
					&& currentRoom.getDirection(Run3DMaze.player.getDirection())) { 
				GamePanel.timeCounter = 0;
				for(Shapes3D i : currentRoom.ceilingAndFloor) {
					i.setDir(2);
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
				GamePanel.timeCounter = 0;
				Run3DMaze.clicked = true;
				
			} else if(e.getX() >= 465 && e.getX() <= 535 	//up
			&& e.getY() >= 130 && e.getY() <= 230 && currentRoom.getDirection('U')) {
				GamePanel.timeCounter = 0;
				for(Shapes3D i : currentRoom.ceilingAndFloor) {
					i.setDir(3);
				}
				for(Shapes3D i : currentRoom.walls) {
					i.setDir(3);
				}
				for(Shapes3D i : currentRoom.doors) {
					i.setDir(3);
				}
				Run3DMaze.nextRoom = true;
				Run3DMaze.up = true;
				System.out.println(Run3DMaze.player.getDirection());
				GamePanel.timeCounter = 0;
				Run3DMaze.clicked = true;
				
			} else if(e.getX() >= 465 && e.getX() < 535 	//down
					&& e.getY() > 530 && e.getY() < 630 && currentRoom.getDirection('D')) {
				GamePanel.timeCounter = 0;
				for(Shapes3D i : currentRoom.ceilingAndFloor) {
					i.setDir(4);
				}
				for(Shapes3D i : currentRoom.walls) {
					i.setDir(4);
				}
				for(Shapes3D i : currentRoom.doors) {
					i.setDir(4);
				}
				Run3DMaze.nextRoom = true;
				Run3DMaze.down = true;
				System.out.println(Run3DMaze.player.getDirection());
				GamePanel.timeCounter = 0;
				Run3DMaze.clicked = true;
			}
			
			//check coordinates of click
			//call move direction method of maze class
			//trigger animation by setting timer counter back to 0
			break;
		case MAPVIEW: 
			
			break;
		case LEADERBOARD: 
			
			break;
		}
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

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("here");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("here");
		// TODO Auto-generated method stub
        final char aChar = e.getKeyChar();
        System.out.println(aChar);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("here");
		// TODO Auto-generated method stub
		
	}

}



