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
		Painter painter = new Painter();
		painter.displayGrid(g);
	}
	
	public void display(Graphics g, int newLevel) {
		
	}
	
	public class Painter extends JPanel {
		
		public void displayGrid(Graphics g) {
			super.paintComponent(g);
			
			/*
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
			*/
			
			//Header placeholder
	        g.setColor(Color.gray);
	        g.fillRect(0,0,1000,40);
			
	        //Footer placeholder
	        g.setColor(Color.gray);
	        g.fillRect(0, 710, 1000, 40);
	        
	        //Grid outline
			g.setColor(Color.black);
			g.fillRect(165, 40, 670, 5);
			g.fillRect(165, 705, 670, 5);
			g.fillRect(165, 40, 5, 670);
			g.fillRect(830, 40, 5, 670);
			
			//4x4 grid
			//vertical
			g.fillRect(331, 40, 5, 665);
			g.fillRect(498, 40, 5, 665);
			g.fillRect(664, 40, 5, 665);
			
			//horizontal
			g.fillRect(165, 206, 670, 5);
			g.fillRect(165, 373, 670, 5);
			g.fillRect(165, 539, 670, 5);
			
			//Outside area
			g.setColor(Color.darkGray);
			g.fillRect(0, 40, 165, 670);
			g.fillRect(835, 40, 165, 670);
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