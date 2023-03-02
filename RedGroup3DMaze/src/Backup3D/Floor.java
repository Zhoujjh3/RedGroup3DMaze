package Backup3D;

import java.awt.Color;
import java.awt.Graphics;

public class Floor extends Shapes{

	public int state = 0;
	public int dir = 0;
	public int h, w;
	
	Floor(){
		h = DrawShapes.height;
		w = DrawShapes.width;
		xTL = w/4.0; xTR = w * (3.0/4.0); xBR = w; xBL = 0;
		yTL = h * (5.0/7.0); yTR = h * (5.0/7.0); yBR = h; yBL = h;
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
		if(dir == 3) {
			if(ShapesPanel.timeCounter == 0) {
				xTL = w/4.0; xTR = w * (3.0/4.0); xBR = w; xBL = 0;
				yTL = h * (5.0/7.0); yTR = h * (5.0/7.0); yBR = h; yBL = h;
			}
			if(ShapesPanel.timeCounter < 200) {
				yBR += h/200.0;
				yTR += h/200.0;
				yBL += h/200.0;
				yTL += h/200.0;
			} else {
				xTL = w/4.0; xTR = w * (3.0/4.0); xBR = w; xBL = 0;
				yTL = h * (5.0/7.0); yTR = h * (5.0/7.0); yBR = h; yBL = h;
			}
		} else if(dir == 4) {
			if(ShapesPanel.timeCounter == 0) {
				xTL = w/4.0; xTR = w * (3.0/4.0); xBR = w; xBL = 0;
				yTL = h * (5.0/7.0); yTR = h * (5.0/7.0); yBR = h; yBL = h;
			}
			if(ShapesPanel.timeCounter < 200) {
				yBR -= h/200.0;
				yTR -= h/200.0;
				yBL -= h/200.0;
				yTL -= h/200.0;
			} else {
				xTL = w/4.0; xTR = w * (3.0/4.0); xBR = w; xBL = 0;
				yTL = h * (5.0/7.0); yTR = h * (5.0/7.0); yBR = h; yBL = h;
			}
		} else {
			if(ShapesPanel.timeCounter == 0) {
				xTL = 0; xTR = w; xBR = w * (3.0/4.0); xBL = w/4.0;
				yTL = 0; yTR = 0; yBR = h * (2.0/7.0); yBL = h * (2.0/7.0);
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