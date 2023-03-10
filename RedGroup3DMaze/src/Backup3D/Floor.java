package Backup3D;

import java.awt.Color;
import java.awt.Graphics;

public class Floor extends Shapes{

	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state = 0;
	public int dir = 0;
	public int h, w;
	double xScale = w/1000;
	double yScale = h/700;
	
	Floor(){
		h = DrawShapes.height;
		w = DrawShapes.width;
		xTL = 250 * xScale; xTR = 750 * xScale; xBR = 1000 * xScale; xBL = 0 * xScale;
		yTL = 500 * yScale; yTR = 500 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
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
		h = DrawShapes.height;
		w = DrawShapes.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		if(dir == 3) {
			if(ShapesPanel.timeCounter == 0) {
				xTL = 250 * xScale; xTR = 750 * xScale; xBR = 1000 * xScale; xBL = 0 * xScale;
				yTL = 500 * yScale; yTR = 500 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
			}
			if(ShapesPanel.timeCounter < 200) {
				yBR += 3.5 * yScale;
				yTR += 3.5 * yScale;
				yBL += 3.5 * yScale;
				yTL += 3.5 * yScale;
			} else {
				xTL = 250 * xScale; xTR = 750 * xScale; xBR = 1000 * xScale; xBL = 0 * xScale;
				yTL = 500 * yScale; yTR = 500 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
			}
		} else if(dir == 4) {
			if(ShapesPanel.timeCounter == 0) {
				xTL = 250 * xScale; xTR = 750 * xScale; xBR = 1000 * xScale; xBL = 0 * xScale;
				yTL = 500 * yScale; yTR = 500 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
			}
			if(ShapesPanel.timeCounter < 200) {
				yBR -= 3.5 * yScale;
				yTR -= 3.5 * yScale;
				yBL -= 3.5 * yScale;
				yTL -= 3.5 * yScale;
			} else {
				xTL = 250 * xScale; xTR = 750 * xScale; xBR = 1000 * xScale; xBL = 0 * xScale;
				yTL = 500 * yScale; yTR = 500 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
			}
		} else {
			if(ShapesPanel.timeCounter == 0) {
				xTL = 250 * xScale; xTR = 750 * xScale; xBR = 1000 * xScale; xBL = 0 * xScale;
				yTL = 500 * yScale; yTR = 500 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
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