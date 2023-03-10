package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class Ceiling extends Shapes{
	
	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state = 0;
	public int dir = 0;
	public int h, w;
	double xScale = w/1000;
	double yScale = h/700;
	
	Ceiling() {
		h = DrawShapes.height;
		w = DrawShapes.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		xTL = 0 * xScale; xTR = 1000 * xScale; xBR = 750 * xScale; xBL = 250 * xScale;
		yTL = 0 * yScale; yTR = 0 * yScale; yBR = 200 * yScale; yBL = 200 * yScale;
	}
	
	public void paint(Graphics g) {
		int[] ceilingX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] ceilingY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		
		g.setColor(new Color(243,243,243));
		g.fillPolygon(ceilingX, ceilingY, 4);
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
		if (dir == 3) {
			if(ShapesPanel.timeCounter <= 200) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0 * xScale; xTR = 1000 * xScale; xBR = 750 * xScale; xBL = 250 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 200 * yScale; yBL = 200 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 0 * xScale; xTR = 1000 * xScale; xBR = 750 * xScale; xBL = 250 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 200 * yScale; yBL = 200 * yScale;
				}
			}
		} else if (dir == 4) {
			if(ShapesPanel.timeCounter <= 200) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0 * xScale; xTR = 1000 * xScale; xBR = 750 * xScale; xBL = 250 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 200 * yScale; yBL = 200 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 0 * xScale; xTR = 1000 * xScale; xBR = 750 * xScale; xBL = 250 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 200 * yScale; yBL = 200 * yScale;
				}
			}
		} else {
			xTL = 0 * xScale; xTR = 1000 * xScale; xBR = 750 * xScale; xBL = 250 * xScale;
			yTL = 0 * yScale; yTR = 0 * yScale; yBR = 200 * yScale; yBL = 200 * yScale;
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