package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class Trapdoor extends Shapes{
	
	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public int dir = 0;
	public int h, w;
	public double xScale;
	public double yScale;
	
	Trapdoor(int theState) {
		state = theState;
		h = DrawShapes.height;
		w = DrawShapes.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		if(theState == 0) {
			xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
			yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
		} else if(theState == 1) {
			xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
			yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
		} else if(theState == 2) {
			xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
			yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
		} else if(theState == 3) {
			xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
			yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
		}
	}
	
	public void paint(Graphics g) {
		int[] trapdoorX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] trapdoorY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		
		g.setColor(new Color(243,243,243));
		g.fillPolygon(trapdoorX, trapdoorY, 4);
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
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
				xTL += 0.5 * xScale;
				xTR += 0.125 * xScale;
				yTR += 0.5 * yScale;
				xBR -= 0.75 * xScale;
				xBL += 0.125 * xScale;
				yBL -= 0.5 * yScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				xTL += 0.125 * xScale;
				yTL += 0.5 * yScale;
				xTR -= 0.75 * xScale;
				xBR += 0.125 * xScale;
				yBR -= 0.5 * yScale;
				xBL += 0.5 * xScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
				xTL -= 0.75 * xScale;
				xTR += 0.125 * xScale;
				yTR -= 0.5 * yScale;
				xBR += 0.5 * xScale;
				xBL += 0.125 * xScale;
				yBL += 0.5 * yScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				xTL += 0.125 * xScale;
				yTL -= 0.5 * yScale;
				xTR += 0.5 * xScale;
				xBR += 0.125 * xScale;
				yBR += 0.5 * yScale;
				xBL -= 0.75 * xScale;
			}
		} else if (dir == 1) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
				xTL -= 0.125 * xScale;
				yTL += 0.5 * yScale;
				xTR -= 0.5 * xScale;
				xBR -= 0.125 * xScale;
				yBR -= 0.5 * yScale;
				xBL += 0.75 * xScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				xTR -= 0.125 * xScale;
				yTR += 0.5 * yScale;
				xTL += 0.75 * xScale;
				xBL -= 0.125 * xScale;
				yBL -= 0.5 * yScale;
				xBR -= 0.5 * xScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
				xTR += 0.75 * xScale;
				xTL -= 0.125 * xScale;
				yTL -= 0.5 * yScale;
				xBL -= 0.5 * xScale;
				xBR -= 0.125 * xScale;
				yBR += 0.5 * yScale;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				xTR -= 0.125 * xScale;
				yTR -= 0.5 * yScale;
				xTL -= 0.5 * xScale;
				xBL -= 0.125 * xScale;
				yBL += 0.5 * yScale;
				xBR += 0.75 * xScale;
			}
		} else if (dir == 2) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= 0.25 * xScale;
					xBL -= 0.25 * xScale;
					xTR += 0.25 * xScale;
					xBR += 0.25 * xScale;
					yTL += 1 * yScale;
					yTR += 1 * yScale;
					yBR += 1 * yScale;
					yBL += 1 * yScale;
				} else {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL += 0.25 * xScale;
					xBL -= 0.25 * xScale;
					xTR += 0.25 * xScale;
					xBR -= 0.25 * xScale;
					yTL += 1 * yScale;
					yTR += 1 * yScale;
					yBR += 1 * yScale;
					yBL += 1 * yScale;
				} else {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL += 0.25 * xScale;
					xBL += 0.25 * xScale;
					xTR -= 0.25 * xScale;
					xBR -= 0.25 * xScale;
					yTL += 1 * yScale;
					yTR += 1 * yScale;
					yBR += 1 * yScale;
					yBL += 1 * yScale;
				} else {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= 0.25 * xScale;
					xBL += 0.25 * xScale;
					xTR -= 0.25 * xScale;
					xBR += 0.25 * xScale;
					yTL += 1 * yScale;
					yTR += 1 * yScale;
					yBR += 1 * yScale;
					yBL += 1 * yScale;
				} else {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				
			}
		} else if (dir == 3) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				
			}
		} else if (dir == 4) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
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