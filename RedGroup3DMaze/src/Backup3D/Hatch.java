package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class Hatch extends Shapes{
	
	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public int dir = 0;
	public int h, w;

	
	Hatch(int theState) {
		h = DrawShapes.height;
		w = DrawShapes.width;
		state = theState;
		if(theState == 0) {
			xTL = w/2.0 - (w/(40.0/3.0)); xTR = w/2.0 + (w/(40.0/3.0)); 
			xBR = w/2.0 + (w/20.0); xBL = w/2.0 - (w/20.0);
			yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0))/4.0; 
			yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0)) * (3.0/4.0);
		} else if(theState == 1) {
			xTL = w/2.0 + (w/(40.0/3.0)); xTR = w/2.0 + (w/20.0); 
			xBR = w/2.0 - (w/20.0); xBL = w/2.0 - (w/(40.0/3.0));
			yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0)) * (3.0/4.0); 
			yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0))/4.0;
		} else if(theState == 2) {
			xTL = w/2.0 + (w/20.0); xTR = w/2.0 - (w/20.0); 
			xBR = w/2.0 - (w/(40.0/3.0)); xBL = w/2.0 + (w/(40.0/3.0));
			yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0)) * (3.0/4.0); 
			yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0))/4.0;
		} else if(theState == 3) {
			xTL = w/2.0 - (w/20.0); xTR = w/2.0 - (w/(40.0/3.0)); 
			xBR = w/2.0 + (w/(40.0/3.0)); xBL = w/2.0 + (w/20.0);
			yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0))/4.0; 
			yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0)) * (3.0/4.0);
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
		h = DrawShapes.height;
		w = DrawShapes.width;
		if (dir == 0) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 - (w/(40.0/3.0)); xTR = w/2.0 + (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/20.0); xBL = w/2.0 - (w/20.0);
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				xTL += ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yTL += ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xTR -= (w/(40.0/6.0))/200.0;
				xBR += ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yBR -= ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xBL += (w/10.0)/200.0;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 + (w/(40.0/3.0)); xTR = w/2.0 + (w/20.0); 
					xBR = w/2.0 - (w/20.0); xBL = w/2.0 - (w/(40.0/3.0));
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0))/4.0;
				}
				xTL -= (w/(40.0/6.0))/200.0;;
				xTR += ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yTR -= ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xBR += (w/10.0)/200.0;
				xBL += ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yBL += ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 + (w/20.0); xTR = w/2.0 - (w/20.0); 
					xBR = w/2.0 - (w/(40.0/3.0)); xBL = w/2.0 + (w/(40.0/3.0));
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0))/4.0;
				}
				xTL += ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yTL -= ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xTR += (w/10.0)/200.0;
				xBR += ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yBR += ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xBL -= (w/(40.0/6.0))/200.0;;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 - (w/20.0); xTR = w/2.0 - (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/(40.0/3.0)); xBL = w/2.0 + (w/20.0);
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				xTL += (w/10.0)/200.0;
				xTR += ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yTR += ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xBR -= (w/(40.0/6.0))/200.0;;
				xBL += ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yBL -= ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
			}
		} else if(dir == 1) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 - (w/(40.0/3.0)); xTR = w/2.0 + (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/20.0); xBL = w/2.0 - (w/20.0);
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				xTL += (w/(40.0/6.0))/200.0;;
				xTR -= ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yTR += ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xBR -= (w/10.0)/200.0;
				xBL -= ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yBL -= ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 + (w/(40.0/3.0)); xTR = w/2.0 + (w/20.0); 
					xBR = w/2.0 - (w/20.0); xBL = w/2.0 - (w/(40.0/3.0));
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0))/4.0;
				}
				xTL -= ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yTL += ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xTR -= (w/10.0)/200.0;
				xBR -= ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yBR -= ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xBL += (w/(40.0/6.0))/200.0;;
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 + (w/20.0); xTR = w/2.0 - (w/20.0); 
					xBR = w/2.0 - (w/(40.0/3.0)); xBL = w/2.0 + (w/(40.0/3.0));
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0))/4.0;
				}
				xTL -= (w/10.0)/200.0;
				xTR -= ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yTR -= ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xBR += (w/(40.0/6.0))/200.0;;
				xBL -= ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yBL += ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 - (w/20.0); xTR = w/2.0 - (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/(40.0/3.0)); xBL = w/2.0 + (w/20.0);
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				xTL -= ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yTL -= ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xTR += (w/(40.0/6.0))/200.0;;
				xBR -= ((w/(40.0/3.0)) - (w/20.0))/200.0;
				yBR += ((h * (2.0/7.0)) * (1.0/2.0))/200.0;
				xBL -= (w/10.0)/200.0;
			}
		} else if (dir == 2) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 - (w/(40.0/3.0)); xTR = w/2.0 + (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/20.0); xBL = w/2.0 - (w/20.0);
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= (w/20.0)/200.0; 
					xBL -= (w/20.0)/200.0;
					xTR += (w/20.0)/200.0; 
					xBR += (w/20.0)/200.0;
					yTL -= (h * (2.0/7.0))/200.0;
					yTR -= (h * (2.0/7.0))/200.0; 
					yBR -= (h * (2.0/7.0))/200.0; 
					yBL -= (h * (2.0/7.0))/200.0; 
				} else {
					xTL = w/2.0 - (w/(40.0/3.0)); xTR = w/2.0 + (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/20.0); xBL = w/2.0 - (w/20.0);
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 + (w/(40.0/3.0)); xTR = w/2.0 + (w/20.0); 
					xBR = w/2.0 - (w/20.0); xBL = w/2.0 - (w/(40.0/3.0));
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0))/4.0;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL += (w/20.0)/200.0; 
					xBL -= (w/20.0)/200.0;
					xTR += (w/20.0)/200.0; 
					xBR -= (w/20.0)/200.0;
					yTL -= (h * (2.0/7.0))/200.0; 
					yTR -= (h * (2.0/7.0))/200.0; 
					yBR -= (h * (2.0/7.0))/200.0; 
					yBL -= (h * (2.0/7.0))/200.0; 
				} else {
					xTL = w/2.0 + (w/(40.0/3.0)); xTR = w/2.0 + (w/20.0); 
					xBR = w/2.0 - (w/20.0); xBL = w/2.0 - (w/(40.0/3.0));
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0))/4.0;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 + (w/20.0); xTR = w/2.0 - (w/20.0); 
					xBR = w/2.0 - (w/(40.0/3.0)); xBL = w/2.0 + (w/(40.0/3.0));
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0))/4.0;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL += (w/20.0)/200.0; 
					xBL += (w/20.0)/200.0;
					xTR -= (w/20.0)/200.0; 
					xBR -= (w/20.0)/200.0;
					yTL -= (h * (2.0/7.0))/200.0; 
					yTR -= (h * (2.0/7.0))/200.0; 
					yBR -= (h * (2.0/7.0))/200.0; 
					yBL -= (h * (2.0/7.0))/200.0; 
				} else {
					xTL = w/2.0 + (w/20.0); xTR = w/2.0 - (w/20.0); 
					xBR = w/2.0 - (w/(40.0/3.0)); xBL = w/2.0 + (w/(40.0/3.0));
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0))/4.0;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 - (w/20.0); xTR = w/2.0 - (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/(40.0/3.0)); xBL = w/2.0 + (w/20.0);
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= (w/20.0)/200.0; 
					xBL += (w/20.0)/200.0;
					xTR -= (w/20.0)/200.0; 
					xBR += (w/20.0)/200.0;
					yTL -= (h * (2.0/7.0))/200.0; 
					yTR -= (h * (2.0/7.0))/200.0; 
					yBR -= (h * (2.0/7.0))/200.0; 
					yBL -= (h * (2.0/7.0))/200.0; 
				} else {
					xTL = w/2.0 - (w/20.0); xTR = w/2.0 - (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/(40.0/3.0)); xBL = w/2.0 + (w/20.0);
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				
			}
		} else if (dir == 3) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 - (w/(40.0/3.0)); xTR = w/2.0 + (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/20.0); xBL = w/2.0 - (w/20.0);
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += h/200.0;
					yBR += h/200.0;
					yTL += h/200.0;
					yBL += h/200.0;
				} else {
					xTL = w/2.0 - (w/(40.0/3.0)); xTR = w/2.0 + (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/20.0); xBL = w/2.0 - (w/20.0);
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 + (w/(40.0/3.0)); xTR = w/2.0 + (w/20.0); 
					xBR = w/2.0 - (w/20.0); xBL = w/2.0 - (w/(40.0/3.0));
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0))/4.0;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += h/200.0;
					yBR += h/200.0;
					yTL += h/200.0;
					yBL += h/200.0;
				} else {
					xTL = w/2.0 + (w/(40.0/3.0)); xTR = w/2.0 + (w/20.0); 
					xBR = w/2.0 - (w/20.0); xBL = w/2.0 - (w/(40.0/3.0));
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0))/4.0;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 + (w/20.0); xTR = w/2.0 - (w/20.0); 
					xBR = w/2.0 - (w/(40.0/3.0)); xBL = w/2.0 + (w/(40.0/3.0));
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0))/4.0;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += h/200.0;
					yBR += h/200.0;
					yTL += h/200.0;
					yBL += h/200.0;
				} else {
					xTL = w/2.0 + (w/20.0); xTR = w/2.0 - (w/20.0); 
					xBR = w/2.0 - (w/(40.0/3.0)); xBL = w/2.0 + (w/(40.0/3.0));
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0))/4.0;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 - (w/20.0); xTR = w/2.0 - (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/(40.0/3.0)); xBL = w/2.0 + (w/20.0);
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += h/200.0;
					yBR += h/200.0;
					yTL += h/200.0;
					yBL += h/200.0;
				} else {
					xTL = w/2.0 - (w/20.0); xTR = w/2.0 - (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/(40.0/3.0)); xBL = w/2.0 + (w/20.0);
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				
			}
		} else if (dir == 4) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 - (w/(40.0/3.0)); xTR = w/2.0 + (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/20.0); xBL = w/2.0 - (w/20.0);
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= h/200.0;
					yBR -= h/200.0;
					yTL -= h/200.0;
					yBL -= h/200.0;
				} else {
					xTL = w/2.0 - (w/(40.0/3.0)); xTR = w/2.0 + (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/20.0); xBL = w/2.0 - (w/20.0);
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 + (w/(40.0/3.0)); xTR = w/2.0 + (w/20.0); 
					xBR = w/2.0 - (w/20.0); xBL = w/2.0 - (w/(40.0/3.0));
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0))/4.0;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= h/200.0;
					yBR -= h/200.0;
					yTL -= h/200.0;
					yBL -= h/200.0;
				} else {
					xTL = w/2.0 + (w/(40.0/3.0)); xTR = w/2.0 + (w/20.0); 
					xBR = w/2.0 - (w/20.0); xBL = w/2.0 - (w/(40.0/3.0));
					yTL = (h * (2.0/7.0))/4.0; yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0)) * (3.0/4.0); yBL = (h * (2.0/7.0))/4.0;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 + (w/20.0); xTR = w/2.0 - (w/20.0); 
					xBR = w/2.0 - (w/(40.0/3.0)); xBL = w/2.0 + (w/(40.0/3.0));
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0))/4.0;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= h/200.0;
					yBR -= h/200.0;
					yTL -= h/200.0;
					yBL -= h/200.0;
				} else {
					xTL = w/2.0 + (w/20.0); xTR = w/2.0 - (w/20.0); 
					xBR = w/2.0 - (w/(40.0/3.0)); xBL = w/2.0 + (w/(40.0/3.0));
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0)) * (3.0/4.0); 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0))/4.0;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/2.0 - (w/20.0); xTR = w/2.0 - (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/(40.0/3.0)); xBL = w/2.0 + (w/20.0);
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0)) * (3.0/4.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= h/200.0;
					yBR -= h/200.0;
					yTL -= h/200.0;
					yBL -= h/200.0;
				} else {
					xTL = w/2.0 - (w/20.0); xTR = w/2.0 - (w/(40.0/3.0)); 
					xBR = w/2.0 + (w/(40.0/3.0)); xBL = w/2.0 + (w/20.0);
					yTL = (h * (2.0/7.0)) * (3.0/4.0); yTR = (h * (2.0/7.0))/4.0; 
					yBR = (h * (2.0/7.0))/4.0; yBL = (h * (2.0/7.0)) * (3.0/4.0);
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