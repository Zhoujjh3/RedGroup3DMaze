package Backup3D;

import java.awt.Color;
import java.awt.Graphics;

public class Art extends Shapes {

	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public int dir = 0;
	public int h, w;
	double xScale = w/1000;
	double yScale = h/700;

	Art(int theState) {
		h = DrawShapes.height;
		w = DrawShapes.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		state = theState;
		if(theState == 0) {
			xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
			yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
		} else if(theState == 1) {
			xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
			yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
		} else if(theState == 2) {
			xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
			yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
		} else if(theState == 3) {
			if(dir == 0) {
				xTL = 0; xTR = 0; xBR = 0; xBL = 0;
				yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
			} else if (dir == 1) {
				xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
				yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
			}
		}
	}
	
	public void paint(Graphics g) {
		int[] artFrameX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] artFrameY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		g.setColor(new Color(243,243,243));
		g.fillPolygon(artFrameX, artFrameY, 4);
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
		if(dir == 0) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				xTL += 1.875 * xScale;
				yTL += 0.235 * yScale;
				xTR += 1.875 * xScale;
				yTR += 0.1 * yScale;
				xBR += 1.875 * xScale;
				yBR -= 0.1 * yScale;
				xBL += 1.875 * xScale;
				yBL -= 0.235 * yScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				xTL += 1.875 * xScale;
				yTL -= 0.1 * yScale;
				xBL += 1.875 * xScale;
				yBL += 0.1 * yScale;
				xTR += 1.875 * xScale;
				yTR -= 0.235 * yScale;
				xBR += 1.875 * xScale;
				yBR += 0.235 * yScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				xTR += 2.3333 * xScale;
				xBR += 2.3333 * xScale;
				xTL += 2.3333 * xScale;
				xBL += 2.3333 * xScale;
				yTL -= 0.235 * yScale;
				yTR -= 0.1 * yScale;
				yBR += 0.1 * yScale;
				yBL += 0.235 * yScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
				if(ShapesPanel.timeCounter > 125) {
					xTR += 2.3333 * xScale;
					xBR += 2.3333 * xScale;
				}
				if(ShapesPanel.timeCounter > 167) {
					xTL += 2.272727 * xScale;
					xBL += 2.272727 * xScale;
				}
				if(ShapesPanel.timeCounter > 99) {
					yTL += 0.1 * 2.0 * yScale;
					yTR += 0.235 * 2.0 * yScale;
					yBR -= 0.235 * 2.0 * yScale;
					yBL -= 0.1 * 2.0 * yScale;
				}
				
			}
		} else if (dir == 1) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				xTR -= 2.3333 * xScale;
				xBR -= 2.3333 * xScale;
				xTL -= 2.3333 * xScale;
				xBL -= 2.3333 * xScale;
				if(ShapesPanel.timeCounter < 100) {
					yTL -= 0.1 * 2.0 * yScale;
					yTR -= 0.235 * 2.0 * yScale;
					yBR += 0.235 * 2.0 * yScale;
					yBL += 0.1 * 2.0 * yScale;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				xTL -= 1.875 * xScale;
				yTL -= 0.235 * yScale;
				xTR -= 1.875 * xScale;
				yTR -= 0.1 * yScale;
				xBR -= 1.875 * xScale;
				yBR += 0.1 * yScale;
				xBL -= 1.875 * xScale;
				yBL += 0.235 * yScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				xTL -= 1.875 * xScale;
				yTL += 0.1 * yScale;
				xBL -= 1.875 * xScale;
				yBL -= 0.1 * yScale;
				xTR -= 1.875 * xScale;
				yTR += 0.235 * yScale;
				xBR -= 1.875 * xScale;
				yBR -= 0.235 * yScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
				if(ShapesPanel.timeCounter > 125) {
					xTL -= 2.3333 * xScale;
					xBL -= 2.3333 * xScale;
				}
				if(ShapesPanel.timeCounter > 167) {
					xTR -= 2.272727 * xScale;
					xBR -= 2.272727 * xScale;
				}
				yTL += 0.235 * yScale;
				yTR += 0.1 * yScale;
				yBR -= 0.1 * yScale;
				yBL -= 0.235 * yScale;
			}
		} else if (dir == 2) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= 1.875 * xScale;
					xBL -= 1.875 * xScale;
					xTR -= 1.875 * xScale;
					xBR -= 1.875 * xScale;
					yTR -= 1.5 * yScale;
					yTL -= 1.5 * yScale;
					yBR += 1.5 * yScale;
					yBL += 1.5 * yScale;
				}
				if(ShapesPanel.timeCounter == 199) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= 0.25 * xScale; 
					yTL -= (2.0/30.0) * yScale;
					xBL -= 0.25 * xScale;
					yBL += 1 * yScale; 
					xTR += 0.25 * xScale; 
					yTR -= (2.0/30.0) * yScale;
					xBR += 0.25 * xScale;
					yBR += 1 * yScale;
				} 
				if(ShapesPanel.timeCounter == 199) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL += 1.875 * xScale;
					xBL += 1.875 * xScale;
					xTR += 1.875 * xScale;
					xBR += 1.875 * xScale;
					yBR += 1.5 * yScale;
					yBL += 1.5 * yScale;
				} 
				if(ShapesPanel.timeCounter == 199) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
			}
		} else if (dir == 3) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
			}
		} else if (dir == 4) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
			yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
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
