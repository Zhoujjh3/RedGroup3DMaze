import java.awt.*;


public class MazeMap {
	
	private int level;
	private ManualTestMaze maze;
	private PlayerData player;
	private int size;
	private int width;
	
	MazeMap(ManualTestMaze maze, PlayerData player) {
		this.maze = maze;
		this.player = player;
		level = this.player.getCoordinate('Z');
		size = this.maze.getMazeSize();
		if (size==4) {
			width = 166;
		} else {
			width = 133;
		}
	}
	
	public void display(Graphics g) {
		Painter painter = new Painter();
		painter.displayGrid(g);
		int length = size;
		for (int i=0; i<length; i++) {
			for (int o=0; o<length; o++) {
				Room room = maze.getRoom(1, o, i);
				int[] coord = {i,o};
				if (room.getDirection('N')) {
					painter.displayDoorN(g, coord);
				}
				if (room.getDirection('E')) {
					painter.displayDoorE(g, coord);
				}
				if (room.getDirection('S')) {
					painter.displayDoorS(g, coord);
				}
				if (room.getDirection('W')) {
					painter.displayDoorW(g, coord);
				}
				if (room.getDirection('U')) {
					painter.displayHatch(g, coord);
				}
				if (room.getDirection('D')) {
					painter.displayTrap(g, coord);
				}
				/*if (room.hasVisited()) {
					
				} else {
					painter.gray(g);
				}*/
				//painter.gray(g, coord);
			}
		}
		
	}
	
	public void display(Graphics g, int newLevel) {
		
	}
	
	public class Painter {
		
		public void displayGrid(Graphics g) {
			
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
			
			if (size==4) {
				//4x4 grid
				//vertical
				g.fillRect(165+width, 40, 5, 665);
				g.fillRect(165+width*2, 40, 5, 665);
				g.fillRect(165+width*3, 40, 5, 665);
				
				//horizontal
				g.fillRect(165, 40+width, 670, 5);
				g.fillRect(165, 40+width*2, 670, 5);
				g.fillRect(165, 40+width*3, 670, 5);
			} else {
				//5x5 grid
				//vertical
				g.fillRect(165+width, 40, 5, 665);
				g.fillRect(165+width*2, 40, 5, 665);
				g.fillRect(165+width*3, 40, 5, 665);
				g.fillRect(165+width*4, 40, 5, 665);
				
				//horizontal
				g.fillRect(165, 40+width, 670, 5);
				g.fillRect(165, 40+width*2, 670, 5);
				g.fillRect(165, 40+width*3, 670, 5);
				g.fillRect(165, 40+width*4, 670, 5);
			}
			
			//Outside area
			g.setColor(Color.darkGray);
			g.fillRect(0, 40, 165, 670);
			g.fillRect(835, 40, 165, 670);
		}
		public void displayTrap(Graphics g, int[] coord) {
			int startX = coord[0]*width;
			int startY = coord[1]*width;
			int[] trapX = {180, 250, 320, 320, 250, 180, 180};
			int[] trapY = {165, 180, 165, 180, 195, 180, 165};
			/*if (size==5) {
				trapX = {};
				trapY = {};
			}*/
			for (int i=0; i<7; i++) {
				trapX[i] = trapX[i] + startX;
				trapY[i] = trapY[i] + startY;
			}
			Color trapColor = new Color(255, 128, 0);
			g.setColor(trapColor);
			g.fillPolygon(trapX, trapY, 7);
		}
		public void displayHatch(Graphics g, int[] coord) {
			int startX = coord[0]*width;
			int startY = coord[1]*width;
			int[] hatchX = {180, 250, 320, 320, 250, 180, 180};
			int[] hatchY = {70, 55, 70, 85, 70, 85, 70};
			/*if (size==5) {
				trapX = {};
				trapY = {};
			}*/
			for (int i=0; i<7; i++) {
				hatchX[i] = hatchX[i] + startX;
				hatchY[i] = hatchY[i] + startY;
			}
			Color hatchColor = new Color(0, 0, 255);
			g.setColor(hatchColor);
			g.fillPolygon(hatchX, hatchY, 7);
		}
		
		public void displayPlayerIcon(Graphics g, int[] coord) {
			int startX = coord[0]*width;
			int startY = coord[1]*width;
			int[] playerIconX = {220, 250, 280, 265, 250, 235, 220};
			int[] playerIconY = {140, 110, 140, 140, 125, 140, 140};
			/*if (size==5) {
				trapX = {};
				trapY = {};
			}*/
			for (int i=0; i<7; i++) {
				playerIconX[i] = playerIconX[i] + startX;
				playerIconY[i] = playerIconY[i] + startY;
			}
			
			g.setColor(Color.black);
			g.drawPolygon(playerIconX, playerIconY, 7);
			Color playerIconColor = new Color(255, 255, 0);
			g.setColor(playerIconColor);
			g.fillPolygon(playerIconX, playerIconY, 7);
		}
		
		public void gray(Graphics g, int[] coord) {
			g.setColor(Color.darkGray);
			g.fillRect(170+coord[0]*width, 45+coord[1]*width, width-5, width-5);
		}
		
		public void displayDoorN(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect(165+(int)(0.25*width)+coord[0]*width, 40+coord[1]*width, (int)(0.5*width), 5);
		}
		public void displayDoorW(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect(165+coord[0]*width, 40+(int)(0.25*width)+coord[1]*width, 5, (int)(0.5*width));
		}
		public void displayDoorS(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect(165+(int)(0.25*width)+coord[0]*width, 40+width+coord[1]*width, (int)(0.5*width), 5);
		}
		public void displayDoorE(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect(165+width+coord[0]*width, 40+(int)(0.25*width)+coord[1]*width, 5, (int)(0.5*width));
		}
	}
}