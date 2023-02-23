import javax.swing.*;
import java.awt.*;


public class MazeMap {
	
	private int level;
	private ManualTestMaze maze;
	private PlayerData player;
	
	MazeMap(ManualTestMaze maze, PlayerData player) {
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
			
			int[] trapX = {180, 250, 320, 320, 250, 180, 180};
			int[] trapY = {165, 180, 165, 180, 195, 180, 165};
			
			int[] hatchX = {180, 250, 320, 320, 250, 180, 180};
			int[] hatchY = {70, 55, 70, 85, 70, 85, 70};
			
			//Trapdoor
			g.setColor(Color.orange);
			g.fillPolygon(trapX, trapY, 7);
			//Hatch
			g.setColor(Color.blue);
			g.fillPolygon(hatchX, hatchY, 7);
		}
		
	}
	
	
	
}