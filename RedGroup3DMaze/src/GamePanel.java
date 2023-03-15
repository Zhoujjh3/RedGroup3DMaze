import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
    Header header;
    int mapLevelIncrement = 0;
    Image up, down, left, right, forward;
    Image upMac, downMac, leftMac, rightMac, forwardMac;
    double xScale;
    double yScale;
    double buttonXScale;
    double buttonYScale;
    
    //char[] directions = {'W','S','E','N','U','D'};
   
    public static int timeCounter = 200;
    
    public GamePanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		//player = new PlayerData(4);
	}
    
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		xScale = getWidth()/1000.0;
		yScale = getHeight()/750.0;
		if(Run3DMaze.state == Run3DMaze.mazeState.WELCOMESCREEN) {
			//do nothing, welcomeScreen draws itself
		} else if(Run3DMaze.state == Run3DMaze.mazeState.CHAMBERVIEW) {
			setChamberView(g);
			setHeader(g);
		} else if(Run3DMaze.state == Run3DMaze.mazeState.MAPVIEW) {
			setMapView(g);
			setHeader(g);
		} else if(Run3DMaze.state == Run3DMaze.mazeState.LEADERBOARD) {
			Run3DMaze.changeView.setVisible(false);
		}
	}
	
	public void setChamberView(Graphics g) {
		// resetting map
		//Run3DMaze.changeView = new JButton("MAP");
	//	Run3DMaze.changeView.setBounds((int)(740*xScale), (int)(5*yScale), (int)(100*xScale), (int)(30*yScale));
		Run3DMaze.mapLevelIncrement = 0;
		Run3DMaze.changeView.setVisible(true);
        Run3DMaze.levelDown.setVisible(false);
        Run3DMaze.levelUp.setVisible(false);
        
		Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'), 
				Run3DMaze.player.getCoordinate('X'), 
				Run3DMaze.player.getCoordinate('Y'));
//		currentRoom.populateDoors();
		g.setColor(currentRoom.getColor());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for(Shapes3D shape : currentRoom.ceilingAndFloor) {
			shape.paint(g);
		}
		for(Shapes3D shape : currentRoom.walls) {
			shape.paint(g);
		}
		for(Shapes3D shape : currentRoom.doors) {
			shape.paint(g);
		}
		
		up = new ImageIcon("Images\\up arrow.png").getImage();
		down = new ImageIcon("Images\\down arrow.png").getImage();
		left = new ImageIcon("Images\\rotate left arrow.png").getImage();
		right = new ImageIcon("Images\\rotate right arrow.png").getImage();
		forward = new ImageIcon("Images\\forward arrow.png").getImage();
		upMac = new ImageIcon("Images/up arrow.png").getImage();
		downMac = new ImageIcon("Images/down arrow.png").getImage();
		leftMac = new ImageIcon("Images/rotate left arrow.png").getImage();
		rightMac = new ImageIcon("Images/rotate right arrow.png").getImage();
		forwardMac = new ImageIcon("Images/forward arrow.png").getImage();
		if(GamePanel.timeCounter > 40) {
			buttonYScale = getHeight()/700.0;
			buttonXScale = getWidth()/1000.0;
			g.drawImage(left, (int) (120*buttonXScale), (int) (450*buttonYScale), 
			(int) (150*buttonXScale), (int) (90*buttonYScale), null);
			g.drawImage(leftMac, (int) (120*buttonXScale), (int) (450*buttonYScale), 
			(int) (150*buttonXScale), (int) (90*buttonYScale), null);
			g.drawImage(right, (int) (730*buttonXScale), (int) (450*buttonYScale), 
			(int) (150*buttonXScale), (int) (90*buttonYScale), null);
			g.drawImage(rightMac, (int) (730*buttonXScale), (int) (450*buttonYScale), 
			(int) (150*buttonXScale), (int) (90*buttonYScale), null);
			char currentDirection = Run3DMaze.player.getDirection();
			if(currentRoom.getDirection(currentDirection) == true) {
				g.drawImage(forward, (int) (450*buttonXScale), (int) (430*buttonYScale), 
				(int) (100*buttonXScale), (int) (60*buttonYScale), null);
				g.drawImage(forwardMac, (int) (450*buttonXScale), (int) (430*buttonYScale), 
				(int) (100*buttonXScale), (int) (60*buttonYScale), null);
			} 
			if(currentRoom.getDirection('D')) {
				g.drawImage(down, (int) (465*buttonXScale), (int) (530*buttonYScale), 
				(int) (70*buttonXScale), (int) (100*buttonYScale), null);
				g.drawImage(downMac, (int) (465*buttonXScale), (int) (530*buttonYScale), 
				(int) (70*buttonXScale), (int) (100*buttonYScale), null);
			}
			if(currentRoom.getDirection('U')) {
				g.drawImage(up, (int) (465*buttonXScale), (int) (130*buttonYScale), 
				(int) (70*buttonXScale), (int) (100*buttonYScale), null);
				g.drawImage(upMac, (int) (465*buttonXScale),(int) (130*buttonYScale), 
				(int) (70*buttonXScale), (int) (100*buttonYScale), null);
			}
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
		xScale = getWidth()/1000.0;
		yScale = getHeight()/750.0;
		Run3DMaze.changeView.setBounds((int)(740*xScale), (int)(5*yScale), (int)(100*xScale), (int)(30*yScale));
		Run3DMaze.levelDown.setBounds((int)(300*xScale), (int)(715*yScale), (int)(100*xScale), (int)(30*yScale));
		Run3DMaze.levelUp.setBounds((int)(450*xScale), (int)(715*yScale), (int)(100*xScale), (int)(30*yScale));
		Run3DMaze.changeView.setVisible(true);
		Run3DMaze.levelDown.setVisible(true);
		Run3DMaze.levelUp.setVisible(true);
	}
	
	public void setHeader(Graphics g) {
		Run3DMaze.header.display(g, this.getSize());
		xScale = getWidth()/1000.0;
		yScale = getHeight()/750.0;
		Run3DMaze.changeView.setBounds((int)(740*xScale), (int)(5*yScale), (int)(100*xScale), (int)(30*yScale));

	}
	
	public PlayerData getPlayerData() {
		return Run3DMaze.player;
	}
}
