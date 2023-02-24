package Backup3D;

import java.awt.Color;
import java.awt.Graphics;

public class Door extends Shapes {

	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public boolean dir = true;

	Door(int theState) {
		state = theState;
		if(theState == 0) {
			xTL = 75; xTR = 175; xBR = 175; xBL = 75;
			yTL = 340; yTR = 340; yBR = 560; yBL = 640;
		} else if(theState == 1) {
			xTL = 450; xBL = 450; xTR = 550; xBR = 550;
			yTL = 340; yBL = 500; yTR = 340; yBR = 500;
		} else if(theState == 2) {
			xTL = 825; xTR = 925; xBR = 925; xBL = 825;
			yTL = 340; yTR = 340; yBR = 640; yBL = 560;
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
		int[] doorX = {(int) xTL, (int) xTR, (int) xBR, (int) xBL};
		int[] doorY = {(int) yTL, (int) yTR, (int) yBR, (int) yBL};
		
		g.setColor(new Color(243,243,243));
		g.fillPolygon(doorX, doorY, 4);
		g.setColor(Color.black);
		g.drawLine((int) xTL,(int) yTL,(int) xBL,(int) yBL);
		g.drawLine((int) xTR,(int) yTR,(int) xBR,(int) yBR);
		g.drawLine((int) xTL,(int) yTL,(int) xTR,(int) yTR);
		g.drawLine((int) xBL,(int) yBL,(int) xBR,(int) yBR);
	}
	
	public void update() {
		
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
	
}
