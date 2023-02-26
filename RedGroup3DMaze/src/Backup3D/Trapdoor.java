package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class Trapdoor extends Shapes{
	
	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public boolean dir = true;
	
	Trapdoor(int theState) {
		state = theState;
		if(theState == 0) {
			xTL = 450; xTR = 550; xBR = 575; xBL = 425;
			yTL = 550; yTR = 550; yBR = 650; yBL = 650;
		} else if(theState == 1) {
			xTL = 550; xTR = 575; xBR = 425; xBL = 450;
			yTL = 550; yTR = 650; yBR = 650; yBL = 550;
		} else if(theState == 2) {
			xTL = 575; xTR = 425; xBR = 450; xBL = 550;
			yTL = 650; yTR = 650; yBR = 550; yBL = 550;
		} else if(theState == 3) {
			xTL = 425; xTR = 450; xBR = 550; xBL = 575;
			yTL = 650; yTR = 550; yBR = 550; yBL = 650;
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
		if(dir) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450; xTR = 550; xBR = 575; xBL = 425;
					yTL = 550; yTR = 550; yBR = 650; yBL = 650;
				}
				xTL += 0.5;
				xTR += 0.125;
				yTR += 0.5;
				xBR -= 0.75;
				xBL += 0.125;
				yBL -= 0.5;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550; xTR = 575; xBR = 425; xBL = 450;
					yTL = 550; yTR = 650; yBR = 650; yBL = 550;
				}
				xTL += 0.125;
				yTL += 0.5;
				xTR -= 0.75;
				xBR += 0.125;
				yBR -= 0.5;
				xBL += 0.5;
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575; xTR = 425; xBR = 450; xBL = 550;
					yTL = 650; yTR = 650; yBR = 550; yBL = 550;
				}
				xTL -= 0.75;
				xTR += 0.125;
				yTR -= 0.5;
				xBR += 0.5;
				xBL += 0.125;
				yBL += 0.5;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425; xTR = 450; xBR = 550; xBL = 575;
					yTL = 650; yTR = 550; yBR = 550; yBL = 650;
				}
				xTL += 0.125;
				yTL -= 0.5;
				xTR += 0.5;
				xBR += 0.125;
				yBR += 0.5;
				xBL -= 0.75;
			}
		} else {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450; xTR = 550; xBR = 575; xBL = 425;
					yTL = 550; yTR = 550; yBR = 650; yBL = 650;
				}
				xTL -= 0.125;
				yTL += 0.5;
				xTR -= 0.5;
				xBR -= 0.125;
				yBR -= 0.5;
				xBL += 0.75;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425; xTR = 450; xBR = 550; xBL = 575;
					yTL = 650; yTR = 550; yBR = 550; yBL = 650;
				}
				xTR -= 0.125;
				yTR += 0.5;
				xTL += 0.75;
				xBL -= 0.125;
				yBL -= 0.5;
				xBR -= 0.5;
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575; xTR = 425; xBR = 450; xBL = 550;
					yTL = 650; yTR = 650; yBR = 550; yBL = 550;
				}
				xTR += 0.75;
				xTL -= 0.125;
				yTL -= 0.5;
				xBL -= 0.5;
				xBR -= 0.125;
				yBR += 0.5;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550; xTR = 575; xBR = 425; xBL = 450;
					yTL = 550; yTR = 650; yBR = 650; yBL = 550;
				}
				xTR -= 0.125;
				yTR -= 0.5;
				xTL -= 0.5;
				xBL -= 0.125;
				yBL += 0.5;
				xBR += 0.75;
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
