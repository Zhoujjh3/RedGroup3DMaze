package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Shapes{
	
	public int state, index, previousWallIndex, nextWallIndex;
	public int dir = 0;
	
	Wall(int theState) {
		state = theState;
		index = theState;
		previousWallIndex = previousWallIndex(index);
		nextWallIndex = nextWallIndex(index);
		if(theState == 0) {
			xTL = 0; xTR = 250; xBR = 250; xBL = 0;
			yTL = 0; yTR = 200; yBR = 500; yBL = 700;
		} else if(theState == 1) {
			xTL = 250; xBL = 250; xTR = 750; xBR = 750;
			yTL = 200; yBL = 500; yTR = 200; yBR = 500;
		} else if(theState == 2) {
			xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
			yTL = 200; yBL = 500; yTR = 0; yBR = 700;
		} else if(theState == 3) {
			if(dir == 0) {
				xTL = 0; xTR = 0; xBR = 0; xBL = 0;
				yTL = 0; yTR = 0; yBR = 700; yBL = 700;
			} else if (dir == 1){
				xTL = 1000; xTR = 1000; xBR = 1000; xBL = 1000;
				yTL = 0; yTR = 0; yBR = 700; yBL = 700;
			}
		}
	}
	
	public void paint(Graphics g) {
		int[] wallX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] wallY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		
		g.setColor(new Color(243,243,243));
		g.fillPolygon(wallX, wallY, 4);
		g.setColor(Color.black);
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xBL),(int) Math.rint(yBL));
		g.drawLine((int) Math.rint(xTR),(int) Math.rint(yTR),(int) Math.rint(xBR),(int) Math.rint(yBR));
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xTR),(int) Math.rint(yTR));
		g.drawLine((int) Math.rint(xBL),(int) Math.rint(yBL),(int) Math.rint(xBR),(int) Math.rint(yBR));
	}

	public void update() {
		if(dir == 0) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 250; xBR = 250; xBL = 0;
					yTL = 0; yTR = 200; yBR = 500; yBL = 700;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTR += 2.5;
					xBR += 2.5;
					if(ShapesPanel.timeCounter > 99) {
						yTL += 2;
						yBL -= 2;
						xTL += 2.5;
						xBL += 2.5;
					}
				} else {
					xTL = 250; xBL = 250; xTR = 750; xBR = 750;
					yTL = 200; yBL = 500; yTR = 200; yBR = 500;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 250; xBL = 250; xTR = 750; xBR = 750;
					yTL = 200; yBL = 500; yTR = 200; yBR = 500;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL += 2.5;
					xBL += 2.5;
					if(ShapesPanel.timeCounter < 100) {
						xTR += 2.5;
						xBR += 2.5;
						yTR -= 2;
						yBR += 2;
					}
				} else {
					xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
					yTL = 200; yBL = 500; yTR = 0; yBR = 700;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
					yTL = 200; yBL = 500; yTR = 0; yBR = 700;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTL = ShapesPanel.walls[previousWallIndex].getyTR();
					yBL = ShapesPanel.walls[previousWallIndex].getyBR();
					xTL = ShapesPanel.walls[previousWallIndex].getxTR();
					xBL = ShapesPanel.walls[previousWallIndex].getxBR();
				} else {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 0; yTR = 0; yBR = 700; yBL = 700;
				}
				
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 0; yTR = 0; yBR = 700; yBL = 700;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR = ShapesPanel.walls[nextWallIndex].getyTL();
					yBR = ShapesPanel.walls[nextWallIndex].getyBL();
					xTR = ShapesPanel.walls[nextWallIndex].getxTL();
					xBR = ShapesPanel.walls[nextWallIndex].getxBL();		
				} else {
					xTL = 0; xTR = 250; xBR = 250; xBL = 0;
					yTL = 0; yTR = 200; yBR = 500; yBL = 700;
				}		
			}
		} else if (dir == 1) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 250; xBR = 250; xBL = 0;
					yTL = 0; yTR = 200; yBR = 500; yBL = 700;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR = ShapesPanel.walls[nextWallIndex].getyTL();
					yBR = ShapesPanel.walls[nextWallIndex].getyBL();
					xTR = ShapesPanel.walls[nextWallIndex].getxTL();
					xBR = ShapesPanel.walls[nextWallIndex].getxBL();		
				} else {
					xTL = 1000; xTR = 1000; xBR = 1000; xBL = 1000;
					yTL = 0; yTR = 0; yBR = 700; yBL = 700;
				}	
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 250; xBL = 250; xTR = 750; xBR = 750;
					yTL = 200; yBL = 500; yTR = 200; yBR = 500;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTR -= 2.5;
					xBR -= 2.5;
					if(ShapesPanel.timeCounter < 100) {
						xTL -= 2.5;
						xBL -= 2.5;
						yTL -= 2;
						yBL += 2;
					}	
				} else {
					xTL = 0; xTR = 250; xBR = 250; xBL = 0;
					yTL = 0; yTR = 200; yBR = 500; yBL = 700;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
					yTL = 200; yBL = 500; yTR = 0; yBR = 700;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= 2.5;
					xBL -= 2.5;
					if(ShapesPanel.timeCounter > 99) {
						xTR -= 2.5;
						xBR -= 2.5;
						yTR += 2;
						yBR -= 2;
					}	
				} else {
					xTL = 250; xBL = 250; xTR = 750; xBR = 750;
					yTL = 200; yBL = 500; yTR = 200; yBR = 500;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 1000; xTR = 1000; xBR = 1000; xBL = 1000;
					yTL = 0; yTR = 0; yBR = 700; yBL = 700;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTL = ShapesPanel.walls[previousWallIndex].getyTR();
					yBL = ShapesPanel.walls[previousWallIndex].getyBR();
					xTL = ShapesPanel.walls[previousWallIndex].getxTR();
					xBL = ShapesPanel.walls[previousWallIndex].getxBR();
				} else {
					xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
					yTL = 200; yBL = 500; yTR = 0; yBR = 700;
				}
			}
		} else if (dir == 2) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 250; xBR = 250; xBL = 0;
					yTL = 0; yTR = 200; yBR = 500; yBL = 700;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 1;
					yBR += 1;
					xTR -= 1.25;
					xBR -= 1.25;
				} else {
					xTL = 0; xTR = 250; xBR = 250; xBL = 0;
					yTL = 0; yTR = 200; yBR = 500; yBL = 700;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 250; xBL = 250; xTR = 750; xBR = 750;
					yTL = 200; yBL = 500; yTR = 200; yBR = 500;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= 1.25; 
					yTL -= 1;
					xBL -= 1.25;
					yBL += 1; 
					xTR += 1.25; 
					yTR -= 1;
					xBR += 1.25;
					yBR += 1;
				} else {
					xTL = 250; xBL = 250; xTR = 750; xBR = 750;
					yTL = 200; yBL = 500; yTR = 200; yBR = 500;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
					yTL = 200; yBL = 500; yTR = 0; yBR = 700;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTL -= 1;
					yBL += 1;
					xTL += 1.25;
					xBL += 1.25;
				} else {
					xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
					yTL = 200; yBL = 500; yTR = 0; yBR = 700;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 1000; xTR = 1000; xBR = 1000; xBL = 1000;
					yTL = 0; yTR = 0; yBR = 700; yBL = 700;
				}
			}
		}
		
	}

	public int getState() {
		return state;
	}

	public int getDir() {
		return dir;
	}
	
	public void setState(int theState) {
		state = theState;
	}

	public void setDir(int theDir) {
		dir = theDir;
	}
	
	public int previousWallIndex(int index) {
		if(index > 0) {
			return index-1;
		} else {
			return 3;
		}
	}
	public int nextWallIndex(int index) {
		if(index < 3) {
			return index+1;
		} else {
			return 0;
		}
	}
}
