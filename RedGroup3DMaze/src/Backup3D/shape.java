package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class shape extends Shapes{
	
	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public boolean dir = true;
	
	shape(int theState) {
		state = theState;
		if(theState == 0) {
			xTL = 0; xTR = 250; xBR = 250; xBL = 0;
			yTL = 0; yTR = 200; yBR = 500; yBL = 700;
		} else if(theState == 1) {
			xTL = 250; xBL = 250; xTR = 750; xBR = 750;
			yTL = 200; yBL = 500; yTR = 200; yBR = 500;
		} else if(theState == 2) {
			xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
			yTL = 200; yBL = 500; yTR = 0; yBR = 700;
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
		int[] shapeX = {(int) xTL, (int) xTR, (int) xBR, (int) xBL};
		int[] shapeY = {(int) yTL, (int) yTR, (int) yBR, (int) yBL};
		
		g.setColor(new Color(243, 243, 243));
		g.fillPolygon(shapeX, shapeY, 4);
	}

	@Override
	public void update() {
		if(dir) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 250; xBR = 250; xBL = 0;
					yTL = 0; yTR = 200; yBR = 500; yBL = 700;
				}
				xTR += 2.5;
				xBR += 2.5;
				if(ShapesPanel.timeCounter > 99) {
					yTL += 2;
					yBL -= 2;
					xTL += 2.5;
					xBL += 2.5;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 250; xBL = 250; xTR = 750; xBR = 750;
					yTL = 200; yBL = 500; yTR = 200; yBR = 500;
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
					xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
					yTL = 200; yBL = 500; yTR = 0; yBR = 700;
				}
				yTL -= 1;
				yBL += 1;
				xTL += 1.25;
				xBL += 1.25;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 0; yTR = 0; yBR = 700; yBL = 700;
				}
				yTR += 1;
				yBR -= 1;
				xTR += 1.25;
				xBR += 1.25;
			}
		} else {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 250; xBR = 250; xBL = 0;
					yTL = 0; yTR = 200; yBR = 500; yBL = 700;
				}
				yTR -= 1;
				yBR += 1;
				xTR -= 1.25;
				xBR -= 1.25;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 250; xBL = 250; xTR = 750; xBR = 750;
					yTL = 200; yBL = 500; yTR = 200; yBR = 500;
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
					xTL = 750; xBL = 750; xTR = 1000; xBR = 1000;
					yTL = 200; yBL = 500; yTR = 0; yBR = 700;
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
					yTL = 0; yTR = 0; yBR = 700; yBL = 700;
				}
				yTL += 1;
				yBL -= 1;
				xTL -= 1.25;
				xBL -= 1.25;
			}
		}
		
	}
}
