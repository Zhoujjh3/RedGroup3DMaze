package Backup3D;

import java.awt.Color;
import java.awt.Graphics;

public class Floor extends Shapes{

	public int state = 0;
	public int dir = 0;
	
	Floor(){
		xTL = 250; xTR = 750; xBR = 1000; xBL = 0;
		yTL = 700; yTR = 500; yBR = 700; yBL = 500;
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
		if(dir == 3) {
			if(ShapesPanel.timeCounter == 0) {
				xTL = 250; xTR = 750; xBR = 1000; xBL = 0;
				yTL = 700; yTR = 500; yBR = 700; yBL = 500;
			}
			if(ShapesPanel.timeCounter < 200) {
				yBR += 3.5;
				yTR += 3.5;
				yBL += 3.5;
				yTL += 3.5;
			} else {
				xTL = 250; xTR = 750; xBR = 1000; xBL = 0;
				yTL = 700; yTR = 500; yBR = 700; yBL = 500;
			}
		} else if(dir == 4) {
			if(ShapesPanel.timeCounter == 0) {
				xTL = 250; xTR = 750; xBR = 1000; xBL = 0;
				yTL = 700; yTR = 500; yBR = 700; yBL = 500;
			}
			if(ShapesPanel.timeCounter < 200) {
				yBR -= 3.5;
				yTR -= 3.5;
				yBL -= 3.5;
				yTL -= 3.5;
			} else {
				xTL = 250; xTR = 750; xBR = 1000; xBL = 0;
				yTL = 700; yTR = 500; yBR = 700; yBL = 500;
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
	
}
