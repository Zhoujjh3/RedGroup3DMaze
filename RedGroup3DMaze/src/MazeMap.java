import java.awt.*;


public class MazeMap {
	
	private int level;
	private ManualTestMaze maze;
	private PlayerData player;
	private int size;
	private double width;
	private double scale = 1;
	
	MazeMap(ManualTestMaze maze, PlayerData player) {
		this.maze = maze;
		this.player = player;
		level = this.player.getCoordinate('Z');
		size = this.maze.getMazeSize();
		if (size==4) {
			width = 166.25;
		} else {
			width = 133;
		}
		
	}
	
	public void display(Graphics g, int newLevel, Dimension dimension) {
		if (dimension.height/dimension.width>=0.75) {
			scale = (double)dimension.width/(double)1000;
		} else {
			scale = (double)dimension.height/(double)750;
		}
		width = 166.25*scale;
		Painter painter = new Painter();
		painter.displayGrid(g);
		int length = size;
		for (int i=0; i<length; i++) {
			for (int o=0; o<length; o++) {
				Room room = maze.getRoom(newLevel, o, i);//FIX, MICHAEL CHANGED THE ORDER
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
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(15*scale)));
		Integer newlevel = newLevel+1;
		g.drawString("Displayed level: " + newlevel.toString(), (int)(625*scale), (int)(735*scale));
	}
	
	public class Painter {
		
		public void displayGrid(Graphics g) {
			
			//Header placeholder
	        g.setColor(Color.gray);
	        g.fillRect(0, 0, (int)(1000*scale), (int)(40*scale));
			
	        //Footer placeholder
	        g.setColor(Color.gray);
	        g.fillRect(0, (int)(710*scale), (int)(1000*scale), (int)(40*scale));
	        
	        //Grid outline
			g.setColor(Color.black);
			g.fillRect((int)(165*scale), (int)(40*scale), (int)(670*scale), (int)(5*scale));
			g.fillRect((int)(165*scale), (int)(705*scale), (int)(670*scale), (int)(5*scale));
			g.fillRect((int)(165*scale), (int)(40*scale), (int)(5*scale), (int)(670*scale));
			g.fillRect((int)(830*scale), (int)(40*scale), (int)(5*scale), (int)(670*scale));
			
			if (size==4) {
				//4x4 grid
				//vertical
				g.fillRect((int)(165*scale+width), (int)(40*scale), (int)(5*scale), (int)(665*scale));
				g.fillRect((int)(165*scale+width*2), (int)(40*scale), (int)(5*scale), (int)(665*scale));
				g.fillRect((int)(165*scale+width*3), (int)(40*scale), (int)(5*scale), (int)(665*scale));
				
				//horizontal
				g.fillRect((int)(165*scale), (int)(40*scale+width), (int)(670*scale), (int)(5*scale));
				g.fillRect((int)(165*scale), (int)(40*scale+width*2), (int)(670*scale), (int)(5*scale));
				g.fillRect((int)(165*scale), (int)(40*scale+width*3), (int)(670*scale), (int)(5*scale));
			} else {
				//5x5 grid
				//vertical
				g.fillRect((int)(165*scale+width), (int)(40*scale), (int)(5*scale), (int)(665*scale));
				g.fillRect((int)(165*scale+width*2), (int)(40*scale), (int)(5*scale), (int)(665*scale));
				g.fillRect((int)(165*scale+width*3), (int)(40*scale), (int)(5*scale), (int)(665*scale));
				g.fillRect((int)(165*scale+width*4), (int)(40*scale), (int)(5*scale), (int)(665*scale));
				
				//horizontal
				g.fillRect((int)(165*scale), (int)(40*scale+width), (int)(670*scale), (int)(5*scale));
				g.fillRect((int)(165*scale), (int)(40*scale+width*2), (int)(670*scale), (int)(5*scale));
				g.fillRect((int)(165*scale), (int)(40*scale+width*3), (int)(670*scale), (int)(5*scale));
				g.fillRect((int)(165*scale), (int)(40*scale+width*4), (int)(670*scale), (int)(5*scale));
			}
			
			//Outside area
			g.setColor(Color.darkGray);
			g.fillRect(0, (int)(40*scale), (int)(165*scale), (int)(670*scale));
			g.fillRect((int)(835*scale), (int)(40*scale), (int)(165*scale), (int)(670*scale));
		}
		public void displayTrap(Graphics g, int[] coord) {
			double startX = coord[0]*width;
			double startY = coord[1]*width;
			int[] trapX = {180, 250, 320, 320, 250, 180, 180};
			int[] trapY = {165, 180, 165, 180, 195, 180, 165};
			if (size==5) {
				for (int i=0; i<7; i++) {
					trapX[i] = (int)(trapX[i]*.8);
					trapY[i] = (int)(trapY[i]*.8);
				}
			}
			for (int i=0; i<7; i++) {
				trapX[i] = (int)(trapX[i]*scale + startX);
				trapY[i] = (int)(trapY[i]*scale + startY);
			}
			Color trapColor = new Color(255, 128, 0);
			g.setColor(trapColor);
			g.fillPolygon(trapX, trapY, 7);
		}
		public void displayHatch(Graphics g, int[] coord) {
			double startX = coord[0]*width;
			double startY = coord[1]*width;
			int[] hatchX = {180, 250, 320, 320, 250, 180, 180};
			int[] hatchY = {70, 55, 70, 85, 70, 85, 70};
			if (size==5) {
				for (int i=0; i<7; i++) {
					hatchX[i] = (int)(hatchX[i]*.8);
					hatchY[i] = (int)(hatchY[i]*.8);
				}
			}
			for (int i=0; i<7; i++) {
				hatchX[i] = (int)(hatchX[i]*scale + startX);
				hatchY[i] = (int)(hatchY[i]*scale + startY);
			}
			Color hatchColor = new Color(0, 0, 255);
			g.setColor(hatchColor);
			g.fillPolygon(hatchX, hatchY, 7);
		}
		
		public void displayPlayerIconN(Graphics g, int[] coord) {
			double startX = coord[0]*width;
			double startY = coord[1]*width;
			int[] playerIconX = {220, 250, 280, 265, 250, 235, 220};
			int[] playerIconY = {140, 110, 140, 140, 125, 140, 140};
			if (size==5) {
				for (int i=0; i<7; i++) {
					playerIconX[i] = (int)(playerIconX[i]*.8);
					playerIconY[i] = (int)(playerIconY[i]*.8);
				}
			}
			for (int i=0; i<7; i++) {
				playerIconX[i] = (int)(playerIconX[i]*scale + startX);
				playerIconY[i] = (int)(playerIconY[i]*scale + startY);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(playerIconX, playerIconY, 7);
			Color playerIconColor = new Color(255, 255, 0);
			g.setColor(playerIconColor);
			g.fillPolygon(playerIconX, playerIconY, 7);
		}
		public void displayPlayerIconE(Graphics g, int[] coord) {
			double startX = (coord[0]+1)*width;
			double startY = coord[1]*width;
			int[] playerIconX = {140, 110, 140, 140, 125, 140, 140};
			int[] playerIconY = {220, 250, 280, 265, 250, 235, 220};
			if (size==5) {
				for (int i=0; i<7; i++) {
					playerIconX[i] = (int)(playerIconX[i]*.8);
					playerIconY[i] = (int)(playerIconY[i]*.8);
				}
			}
			for (int i=0; i<7; i++) {
				playerIconX[i] = (int)((-playerIconX[i] + 165 + 40)*scale + startX);
				playerIconY[i] = (int)((playerIconY[i] - 165 + 40)*scale + startY);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(playerIconX, playerIconY, 7);
			Color playerIconColor = new Color(255, 255, 0);
			g.setColor(playerIconColor);
			g.fillPolygon(playerIconX, playerIconY, 7);
		}
		public void displayPlayerIconS(Graphics g, int[] coord) {
			double startX = (coord[0]+1)*width;
			double startY = (coord[1]+1)*width;
			int[] playerIconX = {220, 250, 280, 265, 250, 235, 220};
			int[] playerIconY = {140, 110, 140, 140, 125, 140, 140};
			if (size==5) {
				for (int i=0; i<7; i++) {
					playerIconX[i] = (int)(playerIconX[i]*.8);
					playerIconY[i] = (int)(playerIconY[i]*.8);
				}
			}
			for (int i=0; i<7; i++) {
				playerIconX[i] = (int)((-playerIconX[i] + 330)*scale + startX);
				playerIconY[i] = (int)((-playerIconY[i] + 80)*scale + startY);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(playerIconX, playerIconY, 7);
			Color playerIconColor = new Color(255, 255, 0);
			g.setColor(playerIconColor);
			g.fillPolygon(playerIconX, playerIconY, 7);
		}
		public void displayPlayerIconW(Graphics g, int[] coord) {
			double startX = coord[0]*width;
			double startY = coord[1]*width;
			int[] playerIconX = {140, 110, 140, 140, 125, 140, 140};
			int[] playerIconY = {220, 250, 280, 265, 250, 235, 220};
			if (size==5) {
				for (int i=0; i<7; i++) {
					playerIconX[i] = (int)(playerIconX[i]*.8);
					playerIconY[i] = (int)(playerIconY[i]*.8);
				}
			}
			for (int i=0; i<7; i++) {
				playerIconX[i] = (int)((-playerIconX[i] - 40 + 165)*scale + startX);
				playerIconY[i] = (int)((playerIconY[i] - 165 + 40)*scale + startY);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(playerIconX, playerIconY, 7);
			Color playerIconColor = new Color(255, 255, 0);
			g.setColor(playerIconColor);
			g.fillPolygon(playerIconX, playerIconY, 7);
		}
		
		public void gray(Graphics g, int[] coord) {
			g.setColor(Color.darkGray);
			if (size==5) {
				g.fillRect((int)((170*.8+coord[0]*width)*scale), (int)((45*.8+coord[1]*width)*scale), (int)((width-4)*scale), (int)((width-4)*scale));
			} else {
				g.fillRect((int)((170+coord[0]*width)*scale), (int)((45+coord[1]*width)*scale), (int)((width-5)*scale), (int)((width-5)*scale));

			}
		}
		
		public void displayDoorN(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect((int)((165*scale+(0.25+coord[0])*width)), (int)(40*scale+coord[1]*width), (int)(0.5*width), (int)(5*scale));
		}
		public void displayDoorE(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect((int)(165*scale+(1+coord[0])*width), (int)(40*scale+(0.25+coord[1])*width), (int)(5*scale), (int)(0.5*width));
		}
		public void displayDoorS(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect((int)((165)*scale+(0.25+coord[0])*width), (int)(40*scale+(1+coord[1])*width), (int)(0.5*width), (int)(5*scale));
		}
		public void displayDoorW(Graphics g, int[] coord) {
			g.setColor(Color.green);
			g.fillRect((int)(165*scale+coord[0]*width), (int)((40)*scale+(0.25+coord[1])*width), (int)(5*scale), (int)(0.5*width));
		}
	}
}