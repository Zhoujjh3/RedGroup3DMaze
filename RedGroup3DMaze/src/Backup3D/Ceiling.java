package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class Ceiling extends Shapes{
	
	public int state = 0;
	public int dir = 0;
	
	Ceiling() {
		xTL = 0; xTR = 250; xBR = 250; xBL = 0;
		yTL = 0; yTR = 200; yBR = 500; yBL = 700;
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
		if (dir == 3) {
			if(ShapesPanel.timeCounter <= 200) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 1000; xBR = 750; xBL = 250;
					yTL = 0; yTR = 0; yBR = 200; yBL = 200;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5;
					yBR += 3.5;
					yTL += 3.5;
					yBL += 3.5;
				} else {
					xTL = 0; xTR = 1000; xBR = 750; xBL = 250;
					yTL = 0; yTR = 0; yBR = 200; yBL = 200;
				}
			}
		} else if (dir == 4) {
			if(ShapesPanel.timeCounter <= 200) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 1000; xBR = 750; xBL = 250;
					yTL = 0; yTR = 0; yBR = 200; yBL = 200;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5;
					yBR -= 3.5;
					yTL -= 3.5;
					yBL -= 3.5;
				} else {
					xTL = 0; xTR = 1000; xBR = 750; xBL = 250;
					yTL = 0; yTR = 0; yBR = 200; yBL = 200;
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
}