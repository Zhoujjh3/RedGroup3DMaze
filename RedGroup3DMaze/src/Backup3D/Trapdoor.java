package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class Trapdoor extends Shapes{
	
	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public int dir = 0;
	public int h, w;
	
	Trapdoor(int theState) {
		h = DrawShapes.height;
		w = DrawShapes.width;
		state = theState;
		if(theState == 0) {
			xTL = 450 * (w/1000.0); xTR = 550 * (w/1000.0); xBR = 575 * (w/1000.0); xBL = 425 * (w/1000.0);
			yTL = 550 * (h/700.0); yTR = 550 * (h/700.0); yBR = 650 * (h/700.0); yBL = 650 * (h/700.0);
		} else if(theState == 1) {
			xTL = 550 * (w/1000.0); xTR = 575 * (w/1000.0); xBR = 425 * (w/1000.0); xBL = 450 * (w/1000.0);
			yTL = 550 * (h/700.0); yTR = 650 * (h/700.0); yBR = 650 * (h/700.0); yBL = 550 * (h/700.0);
		} else if(theState == 2) {
			xTL = 575 * (w/1000.0); xTR = 425 * (w/1000.0); xBR = 450 * (w/1000.0); xBL = 550 * (w/1000.0);
			yTL = 650 * (h/700.0); yTR = 650 * (h/700.0); yBR = 550 * (h/700.0); yBL = 550 * (h/700.0);
		} else if(theState == 3) {
			xTL = 425 * (w/1000.0); xTR = 450 * (w/1000.0); xBR = 550 * (w/1000.0); xBL = 575 * (w/1000.0);
			yTL = 650 * (h/700.0); yTR = 550 * (h/700.0); yBR = 550 * (h/700.0); yBL = 650 * (h/700.0);
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
		if(dir == 0) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * (w/1000.0); xTR = 550 * (w/1000.0); xBR = 575 * (w/1000.0); xBL = 425 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 550 * (h/700.0); yBR = 650 * (h/700.0); yBL = 650 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 100) {
					xTL += 0.5 * (w/1000.0);
					yTL += 0.75 * (h/700.0);
					xTR += 0.5 * (w/1000.0);
					yTR += 1.5 * (h/700.0);
					xBR -= 0.75 * (w/1000.0);
					yBR += 1.25 * (h/700.0);
					xBL -= 0.25 * (w/1000.0);
					yBL += 0.5 * (h/700.0);
				} else {
					xTL += 0.5 * (w/1000.0);
					yTL -= 0.75 * (h/700.0);
					xTR -= 0.25 * (w/1000.0);
					yTR -= 0.5 * (h/700.0);
					xBR -= 0.75 * (w/1000.0);
					yBR -= 1.25 * (h/700.0);
					xBL += 0.5 * (w/1000.0);
					yBL -= 1.5 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * (w/1000.0); xTR = 575 * (w/1000.0); xBR = 425 * (w/1000.0); xBL = 450 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 650 * (h/700.0); yBR = 650 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 100) {
					xBL += 0.5 * (w/1000.0);
					yBL += 0.75 * (h/700.0);
					xTL += 0.5 * (w/1000.0);
					yTL += 1.5 * (h/700.0);
					xTR -= 0.75 * (w/1000.0);
					yTR += 1.25 * (h/700.0);
					xBR -= 0.25 * (w/1000.0);
					yBR += 0.5 * (h/700.0);
				} else {
					xBL += 0.5 * (w/1000.0);
					yBL -= 0.75 * (h/700.0);
					xTL -= 0.25 * (w/1000.0);
					yTL -= 0.5 * (h/700.0);
					xTR -= 0.75 * (w/1000.0);
					yTR -= 1.25 * (h/700.0);
					xBR += 0.5 * (w/1000.0);
					yBR -= 1.5 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575 * (w/1000.0); xTR = 425 * (w/1000.0); xBR = 450 * (w/1000.0); xBL = 550 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 650 * (h/700.0); yBR = 550 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 100) {
					xBR += 0.5 * (w/1000.0);
					yBR += 0.75 * (h/700.0);
					xBL += 0.5 * (w/1000.0);
					yBL += 1.5 * (h/700.0);
					xTL -= 0.75 * (w/1000.0);
					yTL += 1.25 * (h/700.0);
					xTR -= 0.25 * (w/1000.0);
					yTR += 0.5 * (h/700.0);
				} else {
					xBR += 0.5 * (w/1000.0);
					yBR -= 0.75 * (h/700.0);
					xBL -= 0.25 * (w/1000.0);
					yBL -= 0.5 * (h/700.0);
					xTL -= 0.75 * (w/1000.0);
					yTL -= 1.25 * (h/700.0);
					xTR += 0.5 * (w/1000.0);
					yTR -= 1.5 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425 * (w/1000.0); xTR = 450 * (w/1000.0); xBR = 550 * (w/1000.0); xBL = 575 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 550 * (h/700.0); yBR = 550 * (h/700.0); yBL = 650 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 100) {
					xTR += 0.5 * (w/1000.0);
					yTR += 0.75 * (h/700.0);
					xBR += 0.5 * (w/1000.0);
					yBR += 1.5 * (h/700.0);
					xBL -= 0.75 * (w/1000.0);
					yBL += 1.25 * (h/700.0);
					xTL -= 0.25 * (w/1000.0);
					yTL += 0.5 * (h/700.0);
				} else {
					xTR += 0.5 * (w/1000.0);
					yTR -= 0.75 * (h/700.0);
					xBR -= 0.25 * (w/1000.0);
					yBR -= 0.5 * (h/700.0);
					xBL -= 0.75 * (w/1000.0);
					yBL -= 1.25 * (h/700.0);
					xTL += 0.5 * (w/1000.0);
					yTL -= 1.5 * (h/700.0);
				}
			}
		} else if (dir == 1) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * (w/1000.0); xTR = 550 * (w/1000.0); xBR = 575 * (w/1000.0); xBL = 425 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 550 * (h/700.0); yBR = 650 * (h/700.0); yBL = 650 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 100) {
					xTR -= 0.5 * (w/1000.0);
					yTR += 0.75 * (h/700.0);
					xBR += 0.25 * (w/1000.0);
					yBR += 0.5 * (h/700.0);
					xBL += 0.75 * (w/1000.0);
					yBL += 1.25 * (h/700.0);
					xTL -= 0.5 * (w/1000.0);
					yTL += 1.5 * (h/700.0);
				} else {
					xTR -= 0.5 * (w/1000.0);
					yTR -= 0.75 * (h/700.0);
					xBR -= 0.5 * (w/1000.0);
					yBR -= 1.5 * (h/700.0);
					xBL += 0.75 * (w/1000.0);
					yBL -= 1.25 * (h/700.0);
					xTL += 0.25 * (w/1000.0);
					yTL -= 0.5 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * (w/1000.0); xTR = 575 * (w/1000.0); xBR = 425 * (w/1000.0); xBL = 450 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 650 * (h/700.0); yBR = 650 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 100) {
					xBR -= 0.5 * (w/1000.0);
					yBR += 0.75 * (h/700.0);
					xBL += 0.25 * (w/1000.0);
					yBL += 0.5 * (h/700.0);
					xTL += 0.75 * (w/1000.0);
					yTL += 1.25 * (h/700.0);
					xTR -= 0.5 * (w/1000.0);
					yTR += 1.5 * (h/700.0);
				} else {
					xBR -= 0.5 * (w/1000.0);
					yBR -= 0.75 * (h/700.0);
					xBL -= 0.5 * (w/1000.0);
					yBL -= 1.5 * (h/700.0);
					xTL += 0.75 * (w/1000.0);
					yTL -= 1.25 * (h/700.0);
					xTR += 0.25 * (w/1000.0);
					yTR -= 0.5 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575 * (w/1000.0); xTR = 425 * (w/1000.0); xBR = 450 * (w/1000.0); xBL = 550 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 650 * (h/700.0); yBR = 550 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 100) {
					xBL -= 0.5 * (w/1000.0);
					yBL += 0.75 * (h/700.0);
					xTL += 0.25 * (w/1000.0);
					yTL += 0.5 * (h/700.0);
					xTR += 0.75 * (w/1000.0);
					yTR += 1.25 * (h/700.0);
					xBR -= 0.5 * (w/1000.0);
					yBR += 1.5 * (h/700.0);
				} else {
					xBL -= 0.5 * (w/1000.0);
					yBL -= 0.75 * (h/700.0);
					xTL -= 0.5 * (w/1000.0);
					yTL -= 1.5 * (h/700.0);
					xTR += 0.75 * (w/1000.0);
					yTR -= 1.25 * (h/700.0);
					xBR += 0.25 * (w/1000.0);
					yBR -= 0.5 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * (w/1000.0); xTR = 575 * (w/1000.0); xBR = 425 * (w/1000.0); xBL = 450 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 650 * (h/700.0); yBR = 650 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 100) {
					xTL -= 0.5 * (w/1000.0);
					yTL += 0.75 * (h/700.0);
					xTR += 0.25 * (w/1000.0);
					yTR += 0.5 * (h/700.0);
					xBR += 0.75 * (w/1000.0);
					yBR += 1.25 * (h/700.0);
					xBL -= 0.5 * (w/1000.0);
					yBL += 1.5 * (h/700.0);
				} else {
					xTL -= 0.5 * (w/1000.0);
					yTL -= 0.75 * (h/700.0);
					xTR -= 0.5 * (w/1000.0);
					yTR -= 1.5 * (h/700.0);
					xBR += 0.75 * (w/1000.0);
					yBR -= 1.25 * (h/700.0);
					xBL += 0.25 * (w/1000.0);
					yBL -= 0.5 * (h/700.0);
				}
			}
		} else if (dir == 2) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * (w/1000.0); xTR = 550 * (w/1000.0); xBR = 575 * (w/1000.0); xBL = 425 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 550 * (h/700.0); yBR = 650 * (h/700.0); yBL = 650 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= 0.25 * (w/1000.0); 
					xBL -= 0.25 * (w/1000.0);
					xTR += 0.25 * (w/1000.0);
					xBR += 0.25 * (w/1000.0);
					yTL += 1 * (h/700.0);
					yTR += 1 * (h/700.0);
					yBR += 1 * (h/700.0);
					yBL += 1 * (h/700.0);
				} else {
					xTL = 450 * (w/1000.0); xTR = 550 * (w/1000.0); xBR = 575 * (w/1000.0); xBL = 425 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 550 * (h/700.0); yBR = 650 * (h/700.0); yBL = 650 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * (w/1000.0); xTR = 575 * (w/1000.0); xBR = 425 * (w/1000.0); xBL = 450 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 650 * (h/700.0); yBR = 650 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL += 0.25 * (w/1000.0);
					xBL -= 0.25 * (w/1000.0);
					xTR += 0.25 * (w/1000.0);
					xBR -= 0.25 * (w/1000.0);
					yTL += 1 * (h/700.0);
					yTR += 1 * (h/700.0);
					yBR += 1 * (h/700.0);
					yBL += 1 * (h/700.0);
				} else {
					xTL = 550 * (w/1000.0); xTR = 575 * (w/1000.0); xBR = 425 * (w/1000.0); xBL = 450 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 650 * (h/700.0); yBR = 650 * (h/700.0); yBL = 550 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575 * (w/1000.0); xTR = 425 * (w/1000.0); xBR = 450 * (w/1000.0); xBL = 550 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 650 * (h/700.0); yBR = 550 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL += 0.25 * (w/1000.0);
					xBL += 0.25 * (w/1000.0);
					xTR -= 0.25 * (w/1000.0); 
					xBR -= 0.25 * (w/1000.0);
					yTL += 1 * (h/700.0);
					yTR += 1 * (h/700.0);
					yBR += 1 * (h/700.0);
					yBL += 1 * (h/700.0);
				} else {
					xTL = 575 * (w/1000.0); xTR = 425 * (w/1000.0); xBR = 450 * (w/1000.0); xBL = 550 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 650 * (h/700.0); yBR = 550 * (h/700.0); yBL = 550 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425 * (w/1000.0); xTR = 450 * (w/1000.0); xBR = 550 * (w/1000.0); xBL = 575 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 550 * (h/700.0); yBR = 550 * (h/700.0); yBL = 650 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= 0.25 * (w/1000.0);
					xBL += 0.25 * (w/1000.0);
					xTR -= 0.25 * (w/1000.0);
					xBR += 0.25 * (w/1000.0);
					yTL += 1 * (h/700.0);
					yTR += 1 * (h/700.0);
					yBR += 1 * (h/700.0);
					yBL += 1 * (h/700.0);
				} else {
					xTL = 425 * (w/1000.0); xTR = 450 * (w/1000.0); xBR = 550 * (w/1000.0); xBL = 575 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 550 * (h/700.0); yBR = 550 * (h/700.0); yBL = 650 * (h/700.0);
				}
				
			}
		} else if (dir == 3) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * (w/1000.0); xTR = 550 * (w/1000.0); xBR = 575 * (w/1000.0); xBL = 425 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 550 * (h/700.0); yBR = 650 * (h/700.0); yBL = 650 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += h/200.0;
					yBR += h/200.0;
					yTL += h/200.0;
					yBL += h/200.0;
				} else {
					xTL = 450 * (w/1000.0); xTR = 550 * (w/1000.0); xBR = 575 * (w/1000.0); xBL = 425 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 550 * (h/700.0); yBR = 650 * (h/700.0); yBL = 650 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * (w/1000.0); xTR = 575 * (w/1000.0); xBR = 425 * (w/1000.0); xBL = 450 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 650 * (h/700.0); yBR = 650 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += h/200.0;
					yBR += h/200.0;
					yTL += h/200.0;
					yBL += h/200.0;
				} else {
					xTL = 550 * (w/1000.0); xTR = 575 * (w/1000.0); xBR = 425 * (w/1000.0); xBL = 450 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 650 * (h/700.0); yBR = 650 * (h/700.0); yBL = 550 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575 * (w/1000.0); xTR = 425 * (w/1000.0); xBR = 450 * (w/1000.0); xBL = 550 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 650 * (h/700.0); yBR = 550 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += h/200.0;
					yBR += h/200.0;
					yTL += h/200.0;
					yBL += h/200.0;
				} else {
					xTL = 575 * (w/1000.0); xTR = 425 * (w/1000.0); xBR = 450 * (w/1000.0); xBL = 550 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 650 * (h/700.0); yBR = 550 * (h/700.0); yBL = 550 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425 * (w/1000.0); xTR = 450 * (w/1000.0); xBR = 550 * (w/1000.0); xBL = 575 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 550 * (h/700.0); yBR = 550 * (h/700.0); yBL = 650 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += h/200.0;
					yBR += h/200.0;
					yTL += h/200.0;
					yBL += h/200.0;
				} else {
					xTL = 425 * (w/1000.0); xTR = 450 * (w/1000.0); xBR = 550 * (w/1000.0); xBL = 575 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 550 * (h/700.0); yBR = 550 * (h/700.0); yBL = 650 * (h/700.0);
				}
				
			}
		} else if (dir == 4) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 450 * (w/1000.0); xTR = 550 * (w/1000.0); xBR = 575 * (w/1000.0); xBL = 425 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 550 * (h/700.0); yBR = 650 * (h/700.0); yBL = 650 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= h/200.0;
					yBR -= h/200.0;
					yTL -= h/200.0;
					yBL -= h/200.0;
				} else {
					xTL = 450 * (w/1000.0); xTR = 550 * (w/1000.0); xBR = 575 * (w/1000.0); xBL = 425 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 550 * (h/700.0); yBR = 650 * (h/700.0); yBL = 650 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 550 * (w/1000.0); xTR = 575 * (w/1000.0); xBR = 425 * (w/1000.0); xBL = 450 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 650 * (h/700.0); yBR = 650 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= h/200.0;
					yBR -= h/200.0;
					yTL -= h/200.0;
					yBL -= h/200.0;
				} else {
					xTL = 550 * (w/1000.0); xTR = 575 * (w/1000.0); xBR = 425 * (w/1000.0); xBL = 450 * (w/1000.0);
					yTL = 550 * (h/700.0); yTR = 650 * (h/700.0); yBR = 650 * (h/700.0); yBL = 550 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = 575 * (w/1000.0); xTR = 425 * (w/1000.0); xBR = 450 * (w/1000.0); xBL = 550 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 650 * (h/700.0); yBR = 550 * (h/700.0); yBL = 550 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= h/200.0;
					yBR -= h/200.0;
					yTL -= h/200.0;
					yBL -= h/200.0;
				} else {
					xTL = 575 * (w/1000.0); xTR = 425 * (w/1000.0); xBR = 450 * (w/1000.0); xBL = 550 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 650 * (h/700.0); yBR = 550 * (h/700.0); yBL = 550 * (h/700.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 425 * (w/1000.0); xTR = 450 * (w/1000.0); xBR = 550 * (w/1000.0); xBL = 575 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 550 * (h/700.0); yBR = 550 * (h/700.0); yBL = 650 * (h/700.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= h/200.0;
					yBR -= h/200.0;
					yTL -= h/200.0;
					yBL -= h/200.0;
				} else {
					xTL = 425 * (w/1000.0); xTR = 450 * (w/1000.0); xBR = 550 * (w/1000.0); xBL = 575 * (w/1000.0);
					yTL = 650 * (h/700.0); yTR = 550 * (h/700.0); yBR = 550 * (h/700.0); yBL = 650 * (h/700.0);
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