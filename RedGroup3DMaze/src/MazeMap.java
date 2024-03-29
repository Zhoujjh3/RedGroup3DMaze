import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;


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
	private Image displayedLevel;
	private Image[] digits;
	
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
		try {
			displayedLevel = ImageIO.read(getClass().getClassLoader().getResource("displayedlevel.png"));
			digits = new Image[10];
			for (int i=0; i<10; i++) {
				digits[i] = ImageIO.read(getClass().getClassLoader().getResource(String.valueOf(i)+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void display(Graphics g, int newLevel, Dimension dimension) {
		level = this.player.getCoordinate('Z');
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
				Room room = maze.getRoom(newLevel, o, i);
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
			if (newLevel==(size-1)) {
				painter.displayExit(g);
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
		g.drawImage(displayedLevel.getScaledInstance((int)(160 * Run3DMaze.screen.getSize().width/1000), (int)(30* Run3DMaze.screen.getSize().height/750), java.awt.Image.SCALE_SMOOTH), (int)(625*wScale), (int)(721*hScale), null);
		g.drawImage(digits[newlevel].getScaledInstance((int)(13*Run3DMaze.screen.getSize().width/1000), (int)(13*Run3DMaze.screen.getSize().height/750), java.awt.Image.SCALE_SMOOTH), (int)(790*wScale), (int)(722*hScale), null);
	}
	
	public class Painter {
		
		public void displayGrid(Graphics g) {
			 //Footer placeholder
	        g.setColor(Color.black);
	        g.fillRect(0, (int)(710*hScale), (int)(1000*wScale), (int)(40*hScale));
	        
	        //Grid outline
			g.fillRect((int)(165*wScale), (int)(40*hScale), (int)(670*wScale), (int)(5*hScale));
			g.fillRect((int)(165*wScale), (int)(705*hScale), (int)(670*wScale), (int)(5*hScale));
			g.fillRect((int)(165*wScale), (int)(40*hScale), (int)(5*wScale), (int)(670*hScale));
			g.fillRect((int)(830*wScale), (int)(40*hScale), (int)(5*wScale), (int)(670*hScale));
			
			if (size==4) {
				//4x4 grid
				//vertical
				g.fillRect((int)(165*wScale+sWidth), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale)+1);
				g.fillRect((int)(165*wScale+sWidth*2), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale)+1);
				g.fillRect((int)(165*wScale+sWidth*3), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale)+1);
				
				//horizontal
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight), (int)(670*wScale)+1, (int)(5*hScale));
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight*2), (int)(670*wScale)+1, (int)(5*hScale));
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight*3), (int)(670*wScale)+1, (int)(5*hScale));
			} else {
				//5x5 grid
				//vertical
				g.fillRect((int)(165*wScale+sWidth), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale)+1);
				g.fillRect((int)(165*wScale+sWidth*2), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale)+1);
				g.fillRect((int)(165*wScale+sWidth*3), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale)+1);
				g.fillRect((int)(165*wScale+sWidth*4), (int)(40*hScale), (int)(5*wScale), (int)(665*hScale)+1);
				
				//horizontal
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight), (int)(670*wScale)+1, (int)(5*hScale));
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight*2), (int)(670*wScale)+1, (int)(5*hScale));
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight*3), (int)(670*wScale)+1, (int)(5*hScale));
				g.fillRect((int)(165*wScale), (int)(40*hScale+sHeight*4), (int)(670*wScale)+1, (int)(5*hScale));
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
					playerIconX[i] = (int)((playerIconX[i]-40)*.8+40);
					playerIconY[i] = (int)((playerIconY[i]-165)*.8+165);
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
					playerIconX[i] = (int)((playerIconX[i]-40)*.8+40);
					playerIconY[i] = (int)((playerIconY[i]-165)*.8+165);
				}
			}
			for (int i=0; i<7; i++) {
				playerIconX[i] = (int)((playerIconX[i] - 40 + 165)*wScale + startX);
				playerIconY[i] = (int)((playerIconY[i] - 165 + 40)*hScale + startY);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(playerIconX, playerIconY, 7);
			Color playerIconColor = new Color(255, 255, 0);
			g.setColor(playerIconColor); 
			g.fillPolygon(playerIconX, playerIconY, 7);
		}
		
		public void displayExit(Graphics g) {
			g.setColor(Color.green);
			if (size==5) {
				g.fillRect((int)((193*wScale)+(sWidth*(size-1))), (int)((90*hScale)+(sHeight*(size-1))), (int)(80*wScale), (int)(40*hScale));
				g.setColor(Color.white);
				g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(22.5*hScale)));
				g.drawString("EXIT", (int)((206*wScale)+(sWidth*(size-1))), (int)((118*hScale)+(sHeight*(size-1))));
			} else {
				g.fillRect((int)((201*wScale)+(sWidth*(size-1))), (int)((101*hScale)+(sHeight*(size-1))), (int)(100*wScale), (int)(50*hScale));
				g.setColor(Color.white);
				g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(30*hScale)));
				g.drawString("EXIT", (int)((216*wScale)+(sWidth*(size-1))), (int)((136*hScale)+(sHeight*(size-1))));
			}
		}
		
		public void gray(Graphics g, int[] coord) {
			g.setColor(Color.darkGray);
			g.fillRect((int)(169*wScale+coord[0]*sWidth), (int)(44*hScale+coord[1]*sHeight), (int)(sWidth-4*wScale)+1, (int)(sHeight-4*hScale)+1);
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