package Backup3D;

import java.awt.Color;
import java.awt.Graphics;

public class Door extends Shapes {

	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public int dir = 0;
	public int h, w;

	// Justin
	
	Door(int theState) {
		h = DrawShapes.height;
		w = DrawShapes.width;
		state = theState;
		if(theState == 0) {
			xTL = (w/4.0) * 0.3; xTR = (w/4.0) * 0.7; xBR = (w/4.0) * 0.7; xBL = (w/4.0) * 0.3;
			yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
			yBR = yTR + (h * (11.0/35.0)); yBL = yTL + (h * (3.0/7.0));
		} else if(theState == 1) {
			xTL = (w/2.0) - ((w/10.0)/2.0); xBL = (w/2.0) - ((w/10.0)/2.0); 
			xTR = (w/2.0) + ((w/10.0)/2.0); xBR = (w/2.0) + ((w/10.0)/2.0);
			yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBL = h * (5.0/7.0); 
			yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBR = h * (5.0/7.0);
		} else if(theState == 2) {
			xTL = w - ((w/4.0) * 0.7); xTR = w - ((w/4.0) * 0.3); 
			xBR = w - ((w/4.0) * 0.3); xBL = w - ((w/4.0) * 0.7);
			yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
			yBR = yTR + (h * (3.0/7.0)); yBL = yTL + (h * (11.0/35.0));
		} else if(theState == 3) {
			if(dir == 0) {
				xTL = 0; xTR = 0; xBR = 0; xBL = 0;
				yTL = h * (4.0/7.0); yTR = h * (4.0/7.0); yBR = h; yBL = h;
			} else if (dir == 1) {
				xTL = w; xTR = w; xBR = w; xBL = w;
				yTL = h * (4.0/7.0); yTR = h * (4.0/7.0); yBR = h; yBL = h;
			}
		}
	}
	
	public void paint(Graphics g) {
		int[] doorX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] doorY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		g.setColor(new Color(243,243,243));
		g.fillPolygon(doorX, doorY, 4);
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
					xTL = (w/4.0) * 0.3; xTR = (w/4.0) * 0.7; xBR = (w/4.0) * 0.7; xBL = (w/4.0) * 0.3;
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (11.0/35.0)); yBL = yTL + (h * (3.0/7.0));
				}
				xTL += 1.25;//0.75;
				xBL += 1.25;//0.75;
				xTR += 1.75;
				xBR += 1.75;
				
				yBL -= 0.2;  //this used to not exist
				if(ShapesPanel.timeCounter > 99) {
					xTL += 1.25;//2.25;
					xTR += 0.25;
					xBL += 1.25;//2.25;
					xBR += 0.25;
				}
				if(ShapesPanel.timeCounter > 99 && ShapesPanel.timeCounter < 150) {
					yBL -= 1.05;//1.55
					yBR -= 0.5;
				}
				if(ShapesPanel.timeCounter > 149) {
					yBL -= 0.95;//1.25
					yBR -= 0.7;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = (w/2.0) - ((w/10.0)/2.0); xBL = (w/2.0) - ((w/10.0)/2.0); 
					xTR = (w/2.0) + ((w/10.0)/2.0); xBR = (w/2.0) + ((w/10.0)/2.0);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBL = h * (5.0/7.0); 
					yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBR = h * (5.0/7.0);
				}
				xTR += 1.25;//0.75;
				xBR += 1.25;//0.75;
				xTL += 1.75;
				xBL += 1.75;
				
				yBR += 0.2;	//this used to not exist
				if(ShapesPanel.timeCounter < 100) {
					xTR += 1.25;//2.25;
					xTL += 0.25;
					xBR += 1.25;//2.25;
					xBL += 0.25;
				}
				if(ShapesPanel.timeCounter < 100 && ShapesPanel.timeCounter > 49) {
					yBR += 1.05;//+= 1.55;
					yBL += 0.5;
				}
				if(ShapesPanel.timeCounter < 50) {
					yBR += 0.95;//1.25;
					yBL += 0.7;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w - ((w/4.0) * 0.7); xTR = w - ((w/4.0) * 0.3); 
					xBR = w - ((w/4.0) * 0.3); xBL = w - ((w/4.0) * 0.7);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (3.0/7.0)); yBL = yTL + (h * (11.0/35.0));
				}
				xTR += 2.3333;
				xBR += 2.3333;
				xTL += 2.3333;
				xBL += 2.3333;
				yTL += 0.8;
				yTR += 0.8;
				yBR += 1.818181;
				yBL += 1.866666667;
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = h * (4.0/7.0); yTR = h * (4.0/7.0); yBR = h; yBL = h;
				}
				if(ShapesPanel.timeCounter > 125) {
					xTR += 2.3333;
					xBR += 2.3333;
					yTR -= 0.8;
					yTL -= 0.8;
					yBR -= 1.866666667;
				}
				if(ShapesPanel.timeCounter > 167) {
					xTL += 2.272727;
					xBL += 2.272727;
					yBL -= 1.818181;
				}
			}
		} else if (dir == 1) {
			if(ShapesPanel.timeCounter < 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = (w/4.0) * 0.3; xTR = (w/4.0) * 0.7; xBR = (w/4.0) * 0.7; xBL = (w/4.0) * 0.3;
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (11.0/35.0)); yBL = yTL + (h * (3.0/7.0));
				}
				xTR -= 2.3333;
				xBR -= 2.3333;
				xTL -= 2.3333;
				xBL -= 2.3333;
				yTL += 0.8;
				yTR += 0.8;
				yBR += 1.818181;
				yBL += 1.866666667;
			} else if (ShapesPanel.timeCounter < 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = (w/2.0) - ((w/10.0)/2.0); xBL = (w/2.0) - ((w/10.0)/2.0); 
					xTR = (w/2.0) + ((w/10.0)/2.0); xBR = (w/2.0) + ((w/10.0)/2.0);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBL = h * (5.0/7.0); 
					yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBR = h * (5.0/7.0);
				}
				xTL -= 1.25;//0.75;
				xBL -= 1.25;//0.75;
				xTR -= 1.75;
				xBR -= 1.75;
				
				yBL += 0.2;  //this used to not exist
				if(ShapesPanel.timeCounter < 100) {
					xTL -= 1.25;//2.25;
					xTR -= 0.25;
					xBL -= 1.25;//2.25;
					xBR -= 0.25;
				}
				if(ShapesPanel.timeCounter < 100 && ShapesPanel.timeCounter > 49) {
					yBL += 1.05;//1.55;
					yBR += 0.5;
				}
				if(ShapesPanel.timeCounter < 50) {
					yBL += 0.95;//1.25;
					yBR += 0.7;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w - ((w/4.0) * 0.7); xTR = w - ((w/4.0) * 0.3); 
					xBR = w - ((w/4.0) * 0.3); xBL = w - ((w/4.0) * 0.7);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (3.0/7.0)); yBL = yTL + (h * (11.0/35.0));
				}
				xTR -= 1.25;//0.75;
				xBR -= 1.25;//0.75;
				xTL -= 1.75;
				xBL -= 1.75;
				
				yBR -= 0.2;  //this used to not exist
				if(ShapesPanel.timeCounter > 99) {
					xTR -= 1.25;//2.25;
					xTL -= 0.25;
					xBR -= 1.25;//2.25;
					xBL -= 0.25;
				}
				if(ShapesPanel.timeCounter > 99 && ShapesPanel.timeCounter < 150) {
					yBR -= 1.05;//1.55;
					yBL -= 0.5;
				}
				if(ShapesPanel.timeCounter > 149) {
					yBR -= 0.95;//1.25;
					yBL -= 0.7;
				}
			} else if (ShapesPanel.timeCounter < 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w; xTR = w; xBR = w; xBL = w;
					yTL = h * (4.0/7.0); yTR = h * (4.0/7.0); yBR = h; yBL = h;
				}
				if(ShapesPanel.timeCounter > 125) {
					xTL -= 2.3333;
					xBL -= 2.3333;
					yTL -= 0.8;
					yTR -= 0.8;
					yBL -= 1.866666667;
				}
				if(ShapesPanel.timeCounter > 167) {
					xTR -= 2.272727;
					xBR -= 2.272727;
					yBR -= 1.818181;
				}
			}
		} else if (dir == 2) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = (w/4.0) * 0.3; xTR = (w/4.0) * 0.7; xBR = (w/4.0) * 0.7; xBL = (w/4.0) * 0.3;
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (11.0/35.0)); yBL = yTL + (h * (3.0/7.0));
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= 1.875;
					xBL -= 1.875;
					xTR -= 1.875;
					xBR -= 1.875;
					yBR += 1.5;
					yBL += 1.5;
				} else {
					xTL = (w/4.0) * 0.3; xTR = (w/4.0) * 0.7; xBR = (w/4.0) * 0.7; xBL = (w/4.0) * 0.3;
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (11.0/35.0)); yBL = yTL + (h * (3.0/7.0));
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = (w/2.0) - ((w/10.0)/2.0); xBL = (w/2.0) - ((w/10.0)/2.0); 
					xTR = (w/2.0) + ((w/10.0)/2.0); xBR = (w/2.0) + ((w/10.0)/2.0);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBL = h * (5.0/7.0); 
					yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBR = h * (5.0/7.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= 0.25; 
					yTL -= (2.0/30.0);
					xBL -= 0.25;
					yBL += 1; 
					xTR += 0.25; 
					yTR -= (2.0/30.0);
					xBR += 0.25;
					yBR += 1;
				} else {
					xTL = (w/2.0) - ((w/10.0)/2.0); xBL = (w/2.0) - ((w/10.0)/2.0); 
					xTR = (w/2.0) + ((w/10.0)/2.0); xBR = (w/2.0) + ((w/10.0)/2.0);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBL = h * (5.0/7.0); 
					yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBR = h * (5.0/7.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w - ((w/4.0) * 0.7); xTR = w - ((w/4.0) * 0.3); 
					xBR = w - ((w/4.0) * 0.3); xBL = w - ((w/4.0) * 0.7);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (3.0/7.0)); yBL = yTL + (h * (11.0/35.0));
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL += 1.875;
					xBL += 1.875;
					xTR += 1.875;
					xBR += 1.875;
					yBR += 1.5;
					yBL += 1.5;
				} else {
					xTL = w - ((w/4.0) * 0.7); xTR = w - ((w/4.0) * 0.3); 
					xBR = w - ((w/4.0) * 0.3); xBL = w - ((w/4.0) * 0.7);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (3.0/7.0)); yBL = yTL + (h * (11.0/35.0));
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w; xTR = w; xBR = w; xBL = w;
					yTL = h * (4.0/7.0); yTR = h * (4.0/7.0); yBR = h; yBL = h;
				}
			}
		} else if (dir == 3) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = (w/4.0) * 0.3; xTR = (w/4.0) * 0.7; xBR = (w/4.0) * 0.7; xBL = (w/4.0) * 0.3;
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (11.0/35.0)); yBL = yTL + (h * (3.0/7.0));
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5;
					yBR += 3.5;
					yTL += 3.5;
					yBL += 3.5;
				} else {
					xTL = (w/4.0) * 0.3; xTR = (w/4.0) * 0.7; xBR = (w/4.0) * 0.7; xBL = (w/4.0) * 0.3;
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (11.0/35.0)); yBL = yTL + (h * (3.0/7.0));
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = (w/2.0) - ((w/10.0)/2.0); xBL = (w/2.0) - ((w/10.0)/2.0); 
					xTR = (w/2.0) + ((w/10.0)/2.0); xBR = (w/2.0) + ((w/10.0)/2.0);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBL = h * (5.0/7.0); 
					yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBR = h * (5.0/7.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5;
					yBR += 3.5;
					yTL += 3.5;
					yBL += 3.5;
				} else {
					xTL = (w/2.0) - ((w/10.0)/2.0); xBL = (w/2.0) - ((w/10.0)/2.0); 
					xTR = (w/2.0) + ((w/10.0)/2.0); xBR = (w/2.0) + ((w/10.0)/2.0);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBL = h * (5.0/7.0); 
					yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBR = h * (5.0/7.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w - ((w/4.0) * 0.7); xTR = w - ((w/4.0) * 0.3); 
					xBR = w - ((w/4.0) * 0.3); xBL = w - ((w/4.0) * 0.7);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (3.0/7.0)); yBL = yTL + (h * (11.0/35.0));
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += 3.5;
					yBR += 3.5;
					yTL += 3.5;
					yBL += 3.5;
				} else {
					xTL = w - ((w/4.0) * 0.7); xTR = w - ((w/4.0) * 0.3); 
					xBR = w - ((w/4.0) * 0.3); xBL = w - ((w/4.0) * 0.7);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (3.0/7.0)); yBL = yTL + (h * (11.0/35.0));
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w; xTR = w; xBR = w; xBL = w;
					yTL = h * (4.0/7.0); yTR = h * (4.0/7.0); yBR = h; yBL = h;
				}
			}
		} else if (dir == 4) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = (w/4.0) * 0.3; xTR = (w/4.0) * 0.7; xBR = (w/4.0) * 0.7; xBL = (w/4.0) * 0.3;
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (11.0/35.0)); yBL = yTL + (h * (3.0/7.0));
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5;
					yBR -= 3.5;
					yTL -= 3.5;
					yBL -= 3.5;
				} else {
					xTL = (w/4.0) * 0.3; xTR = (w/4.0) * 0.7; xBR = (w/4.0) * 0.7; xBL = (w/4.0) * 0.3;
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (11.0/35.0)); yBL = yTL + (h * (3.0/7.0));
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = (w/2.0) - ((w/10.0)/2.0); xBL = (w/2.0) - ((w/10.0)/2.0); 
					xTR = (w/2.0) + ((w/10.0)/2.0); xBR = (w/2.0) + ((w/10.0)/2.0);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBL = h * (5.0/7.0); 
					yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBR = h * (5.0/7.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5;
					yBR -= 3.5;
					yTL -= 3.5;
					yBL -= 3.5;
				} else {
					xTL = (w/2.0) - ((w/10.0)/2.0); xBL = (w/2.0) - ((w/10.0)/2.0); 
					xTR = (w/2.0) + ((w/10.0)/2.0); xBR = (w/2.0) + ((w/10.0)/2.0);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBL = h * (5.0/7.0); 
					yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); yBR = h * (5.0/7.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w - ((w/4.0) * 0.7); xTR = w - ((w/4.0) * 0.3); 
					xBR = w - ((w/4.0) * 0.3); xBL = w - ((w/4.0) * 0.7);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (3.0/7.0)); yBL = yTL + (h * (11.0/35.0));
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= 3.5;
					yBR -= 3.5;
					yTL -= 3.5;
					yBL -= 3.5;
				} else {
					xTL = w - ((w/4.0) * 0.7); xTR = w - ((w/4.0) * 0.3); 
					xBR = w - ((w/4.0) * 0.3); xBL = w - ((w/4.0) * 0.7);
					yTL = (h * (5.0/7.0)) - (h * (8.0/35.0)); yTR = (h * (5.0/7.0)) - (h * (8.0/35.0)); 
					yBR = yTR + (h * (3.0/7.0)); yBL = yTL + (h * (11.0/35.0));
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w; xTR = w; xBR = w; xBL = w;
					yTL = h * (4.0/7.0); yTR = h * (4.0/7.0); yBR = h; yBL = h;
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