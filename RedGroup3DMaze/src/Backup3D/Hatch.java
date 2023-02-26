package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class Hatch extends Shapes{
	
	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public int dir = 0;
	
	Hatch(int theState) {
		state = theState;
		if(theState == 0) {
			xTL = 425; xTR = 575; xBR = 550; xBL = 450;
			yTL = 50; yTR = 50; yBR = 150; yBL = 150;
		} else if(theState == 1) {
			xTL = 575; xTR = 550; xBR = 450; xBL = 425;
			yTL = 50; yTR = 150; yBR = 150; yBL = 50;
		} else if(theState == 2) {
			xTL = 550; xTR = 450; xBR = 425; xBL = 575;
			yTL = 150; yTR = 150; yBR = 50; yBL = 50;
		} else if(theState == 3) {
			xTL = 450; xTR = 425; xBR = 575; xBL = 550;
			yTL = 150; yTR = 50; yBR = 50; yBL = 150;
		}
	}
	
	public void paint(Graphics g) {
		int[] hatchX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] hatchY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		
		g.setColor(new Color(243,243,243));
		g.fillPolygon(hatchX, hatchY, 4);
		g.setColor(Color.black);
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xBL),(int) Math.rint(yBL));
		g.drawLine((int) Math.rint(xTR),(int) Math.rint(yTR),(int) Math.rint(xBR),(int) Math.rint(yBR));
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xTR),(int) Math.rint(yTR));
		g.drawLine((int) Math.rint(xBL),(int) Math.rint(yBL),(int) Math.rint(xBR),(int) Math.rint(yBR));
	}

	public void update() {
		if(dir == 1) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425; xTR = 575; xBR = 550; xBL = 450;
					yTL = 50; yTR = 50; yBR = 150; yBL = 150;
				}
				xTL += 0.75;
				xTR -= 0.125;
				yTR += 0.5;
				xBR -= 0.5;
				xBL -= 0.125;
				yBL -= 0.5;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575; xTR = 550; xBR = 450; xBL = 425;
					yTL = 50; yTR = 150; yBR = 150; yBL = 50;
				}
				xTL -= 0.125;
				yTL += 0.5;
				xTR -= 0.5;
				xBR -= 0.125;
				yBR -= 0.5;
				xBL += 0.75;
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550; xTR = 450; xBR = 425; xBL = 575;
					yTL = 150; yTR = 150; yBR = 50; yBL = 50;
				}
				xTL -= 0.5;
				xTR -= 0.125;
				yTR -= 0.5;
				xBR += 0.75;
				xBL -= 0.125;
				yBL += 0.5;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450; xTR = 425; xBR = 575; xBL = 550;
					yTL = 150; yTR = 50; yBR = 50; yBL = 150;
				}
				xTL -= 0.125;
				yTL -= 0.5;
				xTR += 0.75;
				xBR -= 0.125;
				yBR += 0.5;
				xBL -= 0.5;
			}
		} else if (dir == 0) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425; xTR = 575; xBR = 550; xBL = 450;
					yTL = 50; yTR = 50; yBR = 150; yBL = 150;
				}
				xTL += 0.125;
				yTL += 0.5;
				xTR -= 0.75;
				xBR += 0.125;
				yBR -= 0.5;
				xBL += 0.5;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575; xTR = 550; xBR = 450; xBL = 425;
					yTL = 50; yTR = 150; yBR = 150; yBL = 50;
				}
				xTL -= 0.75;
				xTR += 0.125;
				yTR -= 0.5;
				xBR += 0.5;
				xBL += 0.125;
				yBL += 0.5;
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550; xTR = 450; xBR = 425; xBL = 575;
					yTL = 150; yTR = 150; yBR = 50; yBL = 50;
				}
				xTL += 0.125;
				yTL -= 0.5;
				xTR += 0.5;
				xBR += 0.125;
				yBR += 0.5;
				xBL -= 0.75;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450; xTR = 425; xBR = 575; xBL = 550;
					yTL = 150; yTR = 50; yBR = 50; yBL = 150;
				}
				xTL += 0.5;
				xTR += 0.125;
				yTR += 0.5;
				xBR -= 0.75;
				xBL += 0.125;
				yBL -= 0.5;
			}
		} else {
			
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
