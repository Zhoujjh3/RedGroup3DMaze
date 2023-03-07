import java.awt.*;


public class MazeMap {
	
	private int level;
	private ManualTestMaze manMaze;
	private Maze maze;
	private PlayerData player;
	private int size;
	private double sWidth;
	private double sHeight;
	private double wScale;
	private double hScale;
	
	MazeMap(ManualTestMaze maze, PlayerData player) {
		this.manMaze = manMaze;
		this.player = player;
		level = this.player.getCoordinate('Z');
		size = this.manMaze.getMazeSize();
		if (size==4) {
			sWidth = 166.25;
			sHeight = 166.25;
		} else {
			sWidth = 133;
			sHeight = 133;
		}
		
	}
	
	MazeMap(Maze maze, PlayerData player) {
		this.maze = maze;
		this.player = player;
		level = this.player.getCoordinate('Z');
		size = this.maze.getMazeSize();
		if (size==4) {
			sWidth = 166.25;
			sHeight = 166.25;
		} else {
			sWidth = 133;
			sHeight = 133;
		}
		
	}
	
	public void display(Graphics g, int newLevel, Dimension dimension) {
		wScale = (double)dimension.width/(double)1000;
		hScale = (double)dimension.height/(double)750;
		if (size==4) {
			sWidth = 166.25*wScale;
			sHeight = 166.25*hScale;
		} else {
			sWidth = 133*wScale;
			sHeight = 133*hScale;
		}
		Painter painter = new Painter();
		painter.displayGrid(g);
		int length = size;
		for (int i=0; i<length; i++) {
			for (int o=0; o<length; o++) {
				Room room = maze.getRoom(newLevel, o, i);//FIX, MICHAEL CHANGED THE ORDER
				int[] coord = {o,i};
				if (room.hasVisited()) {
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
				} else {
					painter.gray(g, coord);
				}
			}
		}
		
		if (newLevel==level) {
			int[] coord = {player.getCoordinate('X'), player.getCoordinate('Y')};
			if (player.getDirection()=='N') {
				painter.displayPlayerIconN(g, coord);
			} else if (player.getDirection()=='E') {
				painter.displayPlayerIconE(g, coord);
			} else if (player.getDirection()=='S') {
				painter.displayPlayerIconS(g, coord);
			} else {
				painter.displayPlayerIconW(g, coord);
			}
		}
		g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(15*hScale)));
		Integer newlevel = newLevel+1;
		g.drawString("Displayed level: " + newlevel.toString(), (int)(625*wScale), (int)(735*hScale));
	}
	
	public class Painter {
		
		public void displayGrid(Graphics g) {
			
			//Header placeholder
	        g.setColor(Color.gray);
	        g.fillRect(0, 0, (int)(1000*wScale), (int)(40*hScale));
			
	        //Footer placeholder
	        g.setColor(Color.gray);
	        g.fillRect(0, (int)(710*hScale), (int)(1000*wScale), (int)(40*hScale));
	        
	        //Grid outline
			g.setColor(Color.black);
			g.fillRect((int)(165*wScale), (int)(40*hScale), (int)(670*wScale), (int)(5*hScale));
			g.fillRect((int)(165*wScale), (int)(705*hScale), (int)(670*wScale), (int)(5*hScale));
			g.fillRect((int)(165*wScale), (int)(40*hScale), (int)(5*wScale), (int)(670*hScale));
			g.fillRect((int)(830*wScale), (int)(40*hScale), (int)(5*wScale), (int)(670*hScale));
			
			if (size==4) {
				//4x4 grid
				//vertical
				g.fillRect((int)(165*wScale+sWidth), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale));
				g.fillRect((int)(165*wScale+sWidth*2), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale));
				g.fillRect((int)(165*wScale+sWidth*3), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale));
				
				//horizontal
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight), (int)(670*wScale), (int)(5*hScale));
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight*2), (int)(670*wScale), (int)(5*hScale));
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight*3), (int)(670*wScale), (int)(5*hScale));
			} else {
				//5x5 grid
				//vertical
				g.fillRect((int)(165*wScale+sWidth), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale));
				g.fillRect((int)(165*wScale+sWidth*2), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale));
				g.fillRect((int)(165*wScale+sWidth*3), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale));
				g.fillRect((int)(165*wScale+sWidth*4), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale));
				
				//horizontal
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight), (int)(670*wScale), (int)(5*hScale));
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight*2), (int)(670*wScale), (int)(5*hScale));
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight*3), (int)(670*wScale), (int)(5*hScale));
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight*4), (int)(670*wScale), (int)(5*hScale));
			}
			
			//Outside area
			g.setColor(Color.darkGray);
			g.fillRect(0, (int)(40*hScale), (int)(165*wScale), (int)(670*hScale));
			g.fillRect((int)(835*wScale), (int)(40*hScale), (int)(165*wScale), (int)(670*hScale));
		}
		public void displayTrap(Graphics g, int[] coord) {
			double startX = coord[0]*sWidth;
			double startY = coord[1]*sHeight;
			int[] trapX = {180, 250, 320, 320, 250, 180, 180};
			int[] trapY = {165, 180, 165, 180, 195, 180, 165};
			if (size==5) {
				for (int i=0; i<7; i++) {
					trapX[i] = (int)((trapX[i]-165)*.8+165);
					trapY[i] = (int)((trapY[i]-40)*.8+40);
				}
			}
			for (int i=0; i<7; i++) {
				trapX[i] = (int)(trapX[i]*wScale + startX);
				trapY[i] = (int)(trapY[i]*hScale + startY);
			}
			Color trapColor = new Color(255, 128, 0);
			g.setColor(trapColor);
			g.fillPolygon(trapX, trapY, 7);
		}
		public void displayHatch(Graphics g, int[] coord) {
			double startX = coord[0]*sWidth;
			double startY = coord[1]*sHeight;
			int[] hatchX = {180, 250, 320, 320, 250, 180, 180};
			int[] hatchY = {70, 55, 70, 85, 70, 85, 70};
			if (size==5) {
				for (int i=0; i<7; i++) {
					hatchX[i] = (int)((hatchX[i]-165)*.8+165);
					hatchY[i] = (int)((hatchY[i]-40)*.8+40);
				}
			}
			for (int i=0; i<7; i++) {
				hatchX[i] = (int)(hatchX[i]*wScale + startX);
				hatchY[i] = (int)(hatchY[i]*hScale + startY);
			}
			Color hatchColor = new Color(0, 0, 255);
			g.setColor(hatchColor);
			g.fillPolygon(hatchX, hatchY, 7);
		}
		
		public void displayPlayerIconN(Graphics g, int[] coord) {
			double startX = coord[0]*sWidth;
			double startY = coord[1]*sHeight;
			int[] playerIconX = {220, 250, 280, 265, 250, 235, 220};
			int[] playerIconY = {140, 110, 140, 140, 125, 140, 140};
			if (size==5) {
				for (int i=0; i<7; i++) {
					playerIconX[i] = (int)((playerIconX[i]-165)*.8+165);
					playerIconY[i] = (int)((playerIconY[i]-40)*.8+40);
				}
			}
			for (int i=0; i<7; i++) {
				playerIconX[i] = (int)(playerIconX[i]*wScale + startX);
				playerIconY[i] = (int)(playerIconY[i]*hScale + startY);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(playerIconX, playerIconY, 7);
			Color playerIconColor = new Color(255, 255, 0);
			g.setColor(playerIconColor);
			g.fillPolygon(playerIconX, playerIconY, 7);
		}
		public void displayPlayerIconE(Graphics g, int[] coord) {
			double startX = (coord[0]+1)*sWidth;
			double startY = coord[1]*sHeight;
			int[] playerIconX = {140, 110, 140, 140, 125, 140, 140};
			int[] playerIconY = {220, 250, 280, 265, 250, 235, 220};
			if (size==5) {
				for (int i=0; i<7; i++) {
					playerIconX[i] = (int)((playerIconX[i]-165)*.8+165);
					playerIconY[i] = (int)((playerIconY[i]-40)*.8+40);
				}
			}
			for (int i=0; i<7; i++) {
				playerIconX[i] = (int)((-playerIconX[i] + 165 + 40)*wScale + startX);
				playerIconY[i] = (int)((playerIconY[i] - 165 + 40)*hScale + startY);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(playerIconX, playerIconY, 7);
			Color playerIconColor = new Color(255, 255, 0);
			g.setColor(playerIconColor);
			g.fillPolygon(playerIconX, playerIconY, 7);
		}
		public void displayPlayerIconS(Graphics g, int[] coord) {
			double startX = (coord[0]+1)*sWidth;
			double startY = (coord[1]+1)*sHeight;
			int[] playerIconX = {220, 250, 280, 265, 250, 235, 220};
			int[] playerIconY = {140, 110, 140, 140, 125, 140, 140};
			if (size==5) {
				for (int i=0; i<7; i++) {
					playerIconX[i] = (int)((playerIconX[i]-165)*.8+165);
					playerIconY[i] = (int)((playerIconY[i]-40)*.8+40);
				}
			}
			for (int i=0; i<7; i++) {
				playerIconX[i] = (int)((-playerIconX[i] + 330)*wScale + startX);
				playerIconY[i] = (int)((-playerIconY[i] + 80)*hScale + startY);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(playerIconX, playerIconY, 7);
			Color playerIconColor = new Color(255, 255, 0);
			g.setColor(playerIconColor);
			g.fillPolygon(playerIconX, playerIconY, 7);
		}
		public void displayPlayerIconW(Graphics g, int[] coord) {
			double startX = coord[0]*sWidth;
			double startY = coord[1]*sHeight;
			int[] playerIconX = {140, 110, 140, 140, 125, 140, 140};
			int[] playerIconY = {220, 250, 280, 265, 250, 235, 220};
			if (size==5) {
				for (int i=0; i<7; i++) {
					playerIconX[i] = (int)((playerIconX[i]-165)*.8+165);
					playerIconY[i] = (int)((playerIconY[i]-40)*.8+40);
				}
			}
			for (int i=0; i<7; i++) {
				playerIconX[i] = (int)((-playerIconX[i] - 40 + 165)*wScale + startX);
				playerIconY[i] = (int)((playerIconY[i] - 165 + 40)*hScale + startY);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(playerIconX, playerIconY, 7);
			Color playerIconColor = new Color(255, 255, 0);
			g.setColor(playerIconColor); 
			g.fillPolygon(playerIconX, playerIconY, 7);
		}
		
		public void displayExit(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect(195, 95, 100, 50);
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString("EXIT", 210, 130);
		}
		
		public void gray(Graphics g, int[] coord) {
			g.setColor(Color.darkGray);
			if (size==5) {
				g.fillRect((int)(169*wScale+coord[0]*sWidth), (int)(44*hScale+coord[1]*sHeight), (int)(sWidth-4*wScale), (int)(sHeight-4*hScale));
			} else {
				g.fillRect((int)((170+coord[0]*sWidth)*wScale), (int)((45+coord[1]*sHeight)*hScale), (int)((sWidth-5)*wScale), (int)((sHeight-5)*hScale));

			}
		}
		
		public void displayDoorN(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect((int)((165*wScale+(0.25+coord[0])*sWidth)), (int)(40*hScale+coord[1]*sHeight), (int)(0.5*sWidth), (int)(5*hScale));
		}
		public void displayDoorE(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect((int)(165*wScale+(1+coord[0])*sWidth), (int)(40*hScale+(0.25+coord[1])*sHeight), (int)(5*wScale), (int)(0.5*sHeight));
		}
		public void displayDoorS(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect((int)((165)*wScale+(0.25+coord[0])*sWidth), (int)(40*hScale+(1+coord[1])*sHeight), (int)(0.5*sWidth), (int)(5*hScale));
		}
		public void displayDoorW(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect((int)(165*wScale+coord[0]*sWidth), (int)((40)*hScale+(0.25+coord[1])*sHeight), (int)(5*wScale), (int)(0.5*sHeight));
		}
	}
}