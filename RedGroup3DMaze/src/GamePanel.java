import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	JFrame frame;
    JPanel panel;
    MazeMap map;
    ManualTestMaze manMaze = new ManualTestMaze();
    Maze maze = new Maze(1);
    PlayerData player = new PlayerData(4);
    JButton changeView, levelUp, levelDown;
    Header header;
    int mapLevelIncrement = 0;
    
	public void paintComponent(Graphics g) {
		
	}
	
	public void setChamberView(Graphics g) {
		
	}
	
	public void setMapView(Graphics g) {
		if (player.getCoordinate('Z') + mapLevelIncrement == maze.getMazeSize() - 1) {
            levelUp.setBackground(Color.GRAY);
        } else {
            levelUp.setBackground(Color.WHITE);
        }
        if (player.getCoordinate('Z') + mapLevelIncrement == 0) {
            levelDown.setBackground(Color.GRAY);
        } else {
            levelDown.setBackground(Color.WHITE);
        }
        map.display(g, player.getCoordinate('Z') + mapLevelIncrement, this.getSize());
        levelDown.setVisible(true);
        levelUp.setVisible(true);
        /*if (header.getView().equals("CHAMBER")){
            map.display(g, player.getCoordinate('Z') + mapLevelIncrement, this.getSize());
            levelDown.setVisible(true);
            levelUp.setVisible(true);
        } else {
            mapLevelIncrement = 0;
            levelDown.setVisible(false);
            levelUp.setVisible(false);
        }*/
	}
	
	public void setHeader(Graphics g) {
		header.display(g, this.getSize());
	}
	
	public void setLeaderboard(Graphics g) {
		
	}
	
	public void setSelectionScreen(Graphics g) {
		
	}
}
