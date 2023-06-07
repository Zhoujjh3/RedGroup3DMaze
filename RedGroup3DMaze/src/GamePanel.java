import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
    MazeMap map;
    Header header;
    int mapLevelIncrement = 0;
    Image up, down, left, right, forward;
    //Image upMac, downMac, leftMac, rightMac, forwardMac;
    double xScale;
    double yScale;
    double buttonXScale;
    double buttonYScale;
    
    //char[] directions = {'W','S','E','N','U','D'};
   
    public static int timeCounter = 200;
    
    public GamePanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
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
		Graphics2D g2 = (Graphics2D) g;
		
		Run3DMaze.mapLevelIncrement = 0;
		Run3DMaze.changeView.setVisible(true);
        Run3DMaze.levelDown.setVisible(false);
        Run3DMaze.levelUp.setVisible(false);
        
		Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'), 
				Run3DMaze.player.getCoordinate('X'), 
				Run3DMaze.player.getCoordinate('Y'));
		g2.setPaint(currentRoom.getGradient(100, 450, 100, 750));
		g2.fillRect(0, getHeight()/2, getWidth(), getHeight());
		g2.setPaint(currentRoom.getGradient(100, 150, 100, 0));
		g2.fillRect(0, 0, getWidth(), getHeight()/2);
		
		for(Shapes3D shape : currentRoom.ceilingAndFloor) {
			shape.paint(g2);
		}
		for(Shapes3D shape : currentRoom.walls) {
			shape.paint(g2);
		}
		for(Shapes3D shape : currentRoom.doors) {
			shape.paint(g2);
		}
		
		up = new ImageIcon(getClass().getClassLoader().getResource("up arrow.png")).getImage();
		down = new ImageIcon(getClass().getClassLoader().getResource("down arrow.png")).getImage();
		left = new ImageIcon(getClass().getClassLoader().getResource("rotate left arrow.png")).getImage();
		right = new ImageIcon(getClass().getClassLoader().getResource("rotate right arrow.png")).getImage();
		forward = new ImageIcon(getClass().getClassLoader().getResource("forward arrow.png")).getImage();
		
		if(GamePanel.timeCounter > 40) {
			buttonYScale = getHeight()/700.0;
			buttonXScale = getWidth()/1000.0;
			g2.drawImage(left, (int) (120*buttonXScale), (int) (450*buttonYScale), 
			(int) (150*buttonXScale), (int) (90*buttonYScale), null);
			g2.drawImage(right, (int) (730*buttonXScale), (int) (450*buttonYScale), 
			(int) (150*buttonXScale), (int) (90*buttonYScale), null);
			char currentDirection = Run3DMaze.player.getDirection();
			if(currentRoom.getDirection(currentDirection) == true) {
				g2.drawImage(forward, (int) (450*buttonXScale), (int) (430*buttonYScale), 
				(int) (100*buttonXScale), (int) (60*buttonYScale), null);
			} 
			if(currentRoom.getDirection('D')) {
				g2.drawImage(down, (int) (465*buttonXScale), (int) (530*buttonYScale), 
				(int) (70*buttonXScale), (int) (100*buttonYScale), null);
			}
			if(currentRoom.getDirection('U')) {
				g2.drawImage(up, (int) (465*buttonXScale), (int) (130*buttonYScale), 
				(int) (70*buttonXScale), (int) (100*buttonYScale), null);
			}
		}		
	}
	
	public void setMapView(Graphics g) {
		if (Run3DMaze.player.getCoordinate('Z') + Run3DMaze.mapLevelIncrement == Run3DMaze.maze.getMazeSize() - 1) {
			Run3DMaze.levelUp.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
    				getResource("leveldowndark.png")).getImage().
    				getScaledInstance((int)(100 * Run3DMaze.screen.getSize().width/1000), (int)(30* Run3DMaze.screen.getSize().height/750), 
    						java.awt.Image.SCALE_SMOOTH)));
        } else {
        	Run3DMaze.levelUp.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
    				getResource("leveldown.png")).getImage().
    				getScaledInstance((int)(100 * Run3DMaze.screen.getSize().width/1000), (int)(30* Run3DMaze.screen.getSize().height/750), 
    						java.awt.Image.SCALE_SMOOTH)));
        }
        if (Run3DMaze.player.getCoordinate('Z') + Run3DMaze.mapLevelIncrement == 0) {
        	Run3DMaze.levelDown.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
    				getResource("levelupdark.png")).getImage().
    				getScaledInstance((int)(100 * Run3DMaze.screen.getSize().width/1000), (int)(30* Run3DMaze.screen.getSize().height/750), 
    						java.awt.Image.SCALE_SMOOTH)));
        } else {
        	Run3DMaze.levelDown.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
    				getResource("levelup.png")).getImage().
    				getScaledInstance((int)(100 * Run3DMaze.screen.getSize().width/1000), (int)(30* Run3DMaze.screen.getSize().height/750), 
    						java.awt.Image.SCALE_SMOOTH)));
        }
		Run3DMaze.map.display(g, Run3DMaze.player.getCoordinate('Z') + Run3DMaze.mapLevelIncrement, this.getSize());
		xScale = getWidth()/1000.0;
		yScale = getHeight()/750.0;
		Run3DMaze.changeView.setBounds((int)(820*xScale), (int)(5*yScale), (int)(100*xScale), (int)(30*yScale));
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
		Run3DMaze.changeView.setBounds((int)(820*xScale), (int)(5*yScale), (int)(100*xScale), (int)(30*yScale));
	}
	
	public PlayerData getPlayerData() {
		return Run3DMaze.player;
	}
}
