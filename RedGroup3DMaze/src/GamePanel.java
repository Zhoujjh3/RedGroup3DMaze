import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Backup3D.DrawShapes;
import Backup3D.Shapes;
import Backup3D.Wall;

public class GamePanel extends JPanel{
	//JFrame frame;
    //JPanel panel;
    MazeMap map;
    //ManualTestMaze manMaze = new ManualTestMaze();
    //public static Maze maze = new Maze(1);
    //PlayerData player = new PlayerData(4);
    JButton changeView, levelUp, levelDown;
    Header header;
    int mapLevelIncrement = 0;
    
    //char[] directions = {'W','S','E','N','U','D'};
   
    public static int timeCounter = 200;
    
    public GamePanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		//player = new PlayerData(4);
	}
    
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(Run3DMaze.state == Run3DMaze.mazeState.WELCOMESCREEN) {
			//do nothing, welcomeScreen draws itself
		} else if(Run3DMaze.state == Run3DMaze.mazeState.CHAMBERVIEW) {
			setChamberView(g);
			setHeader(g);
		} else if(Run3DMaze.state == Run3DMaze.mazeState.MAPVIEW) {
			setMapView(g);
		} else if(Run3DMaze.state == Run3DMaze.mazeState.LEADERBOARD) {
			setLeaderboard(g);
		}
	}
	
	public void setChamberView(Graphics g) {
		// resetting map
		Run3DMaze.mapLevelIncrement = 0;
        Run3DMaze.levelDown.setVisible(false);
        Run3DMaze.levelUp.setVisible(false);
			//when setting the doors, door[1] is the door in the direction you start out facing
			//have it so that when the animation is done, it switches to the new room
			//you might already be doing this since you're refreshing the doors constantly through paintcomponent
        
		Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'), 
				Run3DMaze.player.getCoordinate('X'), 
				Run3DMaze.player.getCoordinate('Y'));
		currentRoom.populateDoors();
		g.setColor(currentRoom.getColor());
		g.fillRect(0, 0, DrawShapes.width, DrawShapes.height);
		
//		for(Shapes3D shape : ceilingAndFloor) {
//			shape.paint(g);
//		}
		for(Shapes3D shape : currentRoom.walls) {
			shape.paint(g);
		}
		for(Shapes3D shape : currentRoom.doors) {
			shape.paint(g);
		}
	}
	
	public void setMapView(Graphics g) {
		if (Run3DMaze.player.getCoordinate('Z') + Run3DMaze.mapLevelIncrement == Run3DMaze.maze.getMazeSize() - 1) {
			Run3DMaze.levelUp.setBackground(Color.GRAY);
        } else {
        	Run3DMaze.levelUp.setBackground(Color.WHITE);
        }
        if (Run3DMaze.player.getCoordinate('Z') + Run3DMaze.mapLevelIncrement == 0) {
        	Run3DMaze.levelDown.setBackground(Color.GRAY);
        } else {
        	Run3DMaze.levelDown.setBackground(Color.WHITE);
        }
		Run3DMaze.map.display(g, Run3DMaze.player.getCoordinate('Z') + Run3DMaze.mapLevelIncrement, this.getSize());
		Run3DMaze.levelDown.setVisible(true);
		Run3DMaze.levelUp.setVisible(true);
	}
	
	public void setHeader(Graphics g) {
		Run3DMaze.header.display(g, this.getSize());
	}
	
	public void setLeaderboard(Graphics g) {
		
	}
	
	public void setSelectionScreen(Graphics g) {
		
	}
	
	public PlayerData getPlayerData() {
		return Run3DMaze.player;
	}
}
