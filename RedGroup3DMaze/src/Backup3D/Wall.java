package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Shapes{
	
	public int state, index, previousWallIndex, nextWallIndex;
	public boolean dir = true;
	
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
			if(dir) {
				xTL = 0; xTR = 0; xBR = 0; xBL = 0;
				yTL = 0; yTR = 0; yBR = 700; yBL = 700;
			} else {
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
		if(dir) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 250; xBR = 250; xBL = 0;
					yTL = 0; yTR = 200; yBR = 500; yBL = 700;
				}
				xTR += 2.5;
				xBR += 2.5;
				if(ShapesPanel.timeCounter > 99) {
					yTL += 2;
					yBL -= 2;
					xTL += 2.5;
					xBL += 2.5;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 250; xBL = 250; xTR = 750; xBR = 750;
					yTL = 200; yBL = 500; yTR = 200; yBR = 500;
				}
				xTL += 2.5;
				xBL += 2.5;
				if(ShapesPanel.timeCounter < 100) {
					xTR += 2.5;
					xBR += 2.5;
					yTR -= 2;
					yBR += 2;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
					yTL = 200; yBL = 500; yTR = 0; yBR = 700;
				}
				yTL = ShapesPanel.walls[previousWallIndex].getyTR();
				yBL = ShapesPanel.walls[previousWallIndex].getyBR();
				xTL = ShapesPanel.walls[previousWallIndex].getxTR();//+= 2.5;
				xBL = ShapesPanel.walls[previousWallIndex].getxBR();//+= 2.5;
				
				
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 0; yTR = 0; yBR = 700; yBL = 700;
				}
				yTR = ShapesPanel.walls[nextWallIndex].getyTL();//+= 1;
				yBR = ShapesPanel.walls[nextWallIndex].getyBL();//-= 1;
				xTR = ShapesPanel.walls[nextWallIndex].getxTL();//+= 1.25;
				xBR = ShapesPanel.walls[nextWallIndex].getxBL();//+= 1.25;				
			}
		} else {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 250; xBR = 250; xBL = 0;
					yTL = 0; yTR = 200; yBR = 500; yBL = 700;
				}
				yTR = ShapesPanel.walls[nextWallIndex].getyTL();//-= 1;
				yBR = ShapesPanel.walls[nextWallIndex].getyBL();//+= 1;
				xTR = ShapesPanel.walls[nextWallIndex].getxTL();//-= 1.25;
				xBR = ShapesPanel.walls[nextWallIndex].getxBL();//-= 1.25;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 250; xBL = 250; xTR = 750; xBR = 750;
					yTL = 200; yBL = 500; yTR = 200; yBR = 500;
				}
				xTR -= 2.5;
				xBR -= 2.5;
				if(ShapesPanel.timeCounter < 100) {
					xTL -= 2.5;
					xBL -= 2.5;
					yTL -= 2;
					yBL += 2;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
					yTL = 200; yBL = 500; yTR = 0; yBR = 700;
				}
				xTL -= 2.5;
				xBL -= 2.5;
				if(ShapesPanel.timeCounter > 99) {
					xTR -= 2.5;
					xBR -= 2.5;
					yTR += 2;
					yBR -= 2;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 1000; xTR = 1000; xBR = 1000; xBL = 1000;
					yTL = 0; yTR = 0; yBR = 700; yBL = 700;
				}
				yTL = ShapesPanel.walls[previousWallIndex].getyTR();//+= 1;
				yBL = ShapesPanel.walls[previousWallIndex].getyBR();//-= 1;
				xTL = ShapesPanel.walls[previousWallIndex].getxTR();//-= 1.25;
				xBL = ShapesPanel.walls[previousWallIndex].getxBR();//-= 1.25;
			}
		}
		
	}

	public int getState() {
		return state;
	}

	public boolean getDir() {
		return dir;
	}
	
	public void setState(int theState) {
		state = theState;
	}

	public void setDir(boolean theDir) {
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
