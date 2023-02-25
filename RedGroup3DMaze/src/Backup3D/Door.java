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
				yTL = 400; yTR = 400; yBR = 700; yBL = 700;
			} else {
				xTL = 1000; xTR = 1000; xBR = 1000; xBL = 1000;
				yTL = 400; yTR = 400; yBR = 700; yBL = 700;
			}
		}
	}
	
	public void paint(Graphics g) {
		int[] doorX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] doorY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		g.setColor(new Color(243,243,243));
		g.fillPolygon(doorX, doorY, 4);
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
					xTL = 75; xTR = 175; xBR = 175; xBL = 75;
					yTL = 340; yTR = 340; yBR = 560; yBL = 640;
				}
				xTL += 0.75;
				xBL += 0.75;
				xTR += 1.75;
				xBR += 1.75;
				if(ShapesPanel.timeCounter > 99) {
					xTL += 2.25;
					xTR += 0.25;
					xBL += 2.25;
					yBL -= 1.4;
					xBR += 0.25;
					yBR -= 0.6;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450; xBL = 450; xTR = 550; xBR = 550;
					yTL = 340; yBL = 500; yTR = 340; yBR = 500;
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
					xTL = 825; xTR = 925; xBR = 925; xBL = 825;
					yTL = 340; yTR = 340; yBR = 640; yBL = 560;
				}
				yTL -= 1;
				yBL += 1;
				xTL += 1.25;
				xBL += 1.25;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 400; yTR = 400; yBR = 700; yBL = 700;
				}
				yTR += 1;
				yBR -= 1;
				xTR += 1.25;
				xBR += 1.25;
			}
		} else {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 75; xTR = 175; xBR = 175; xBL = 75;
					yTL = 340; yTR = 340; yBR = 560; yBL = 640;
				}
				yTR -= 1;
				yBR += 1;
				xTR -= 1.25;
				xBR -= 1.25;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450; xBL = 450; xTR = 550; xBR = 550;
					yTL = 340; yBL = 500; yTR = 340; yBR = 500;
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
					xTL = 825; xTR = 925; xBR = 925; xBL = 825;
					yTL = 340; yTR = 340; yBR = 640; yBL = 560;
				}
				xTL -= 2.5;
				xBL -= 2.5;
				if(ShapesPanel.timeCounter > 100) {
					xTR -= 2.5;
					xBR -= 2.5;
					yTR += 2;
					yBR -= 2;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 1000; xTR = 1000; xBR = 1000; xBL = 1000;
					yTL = 400; yTR = 400; yBR = 700; yBL = 700;
				}
				yTL += 1;
				yBL -= 1;
				xTL -= 1.25;
				xBL -= 1.25;
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
	
}
