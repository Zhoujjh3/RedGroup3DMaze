import java.awt.*;
import javax.swing.*;


public class MazeMap {
	
	private int level;
	private Maze maze;
	private PlayerData player;
	
	MazeMap(Maze maze, PlayerData player) {
		this.maze = maze;
		this.player = player;
		level = player.getCoordinate('z');
	}
	
	public void display(Graphics g) {
		//testing
	}
	
	public void display(Graphics g, int newLevel) {
		
	}
	
	public class Painter extends JPanel {
		
		public void displayGrid(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.black);
			
			g.fillRect(0, 50, 1000, 10);
			g.fillRect(0, 50, 10, 650);
			g.fillRect(0, 690, 1000, 10);
			g.fillRect(990, 50, 10, 650);
			
			g.fillRect(245, 50, 10, 650);
			g.fillRect(510, 50, 10, 650);
			g.fillRect(765, 50, 10, 650);
			
			g.fillRect(0, 217, 1000, 10);
			g.fillRect(0, 375, 1000, 10);
			g.fillRect(0, 532, 1000, 10);
		}
		
		public void mapIcons(Graphics g) {
			super.paintComponent(g);
			
			int[] x1 = {100, 300, 500, 500, 300, 100, 100};
			int[] y1 = {250, 300, 250, 200, 250, 200, 250};
			
			g.drawPolygon(x1, y1, 7);
			g.fillPolygon(x1, y1, 7);
		}
		
	}
	
	
	
}