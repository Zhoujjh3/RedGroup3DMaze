
import java.awt.Color;
import java.awt.Graphics;

public class Trapdoor3D extends Shapes3D{
	
	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public int dir = 0;
	public int h, w;
	public double xScale;
	public double yScale;
	
	Trapdoor3D(int theState) {
		h = Run3DMaze.height;
		w = Run3DMaze.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		state = theState;
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
		h = Run3DMaze.height;
		w = Run3DMaze.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		if(dir == 0) {
			if(GamePanel.timeCounter < 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					xTL += 0.5 * xScale * 5;
					yTL += 0.75 * yScale * 5;
					xTR += 0.5 * xScale * 5;
					yTR += 1.5 * yScale * 5;
					xBR -= 0.75 * xScale * 5;
					yBR += 1.25 * yScale * 5;
					xBL -= 0.25 * xScale * 5;
					yBL += 0.5 * yScale * 5;
				} else if(GamePanel.timeCounter > 19 && Run3DMaze.clicked){
					xTL += 0.5 * xScale * 5;
					yTL -= 0.75 * yScale * 5;
					xTR -= 0.25 * xScale * 5;
					yTR -= 0.5 * yScale * 5;
					xBR -= 0.75 * xScale * 5;
					yBR -= 1.25 * yScale * 5;
					xBL += 0.5 * xScale * 5;
					yBL -= 1.5 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					xBL += 0.5 * xScale * 5;
					yBL += 0.75 * yScale * 5;
					xTL += 0.5 * xScale * 5;
					yTL += 1.5 * yScale * 5;
					xTR -= 0.75 * xScale * 5;
					yTR += 1.25 * yScale * 5;
					xBR -= 0.25 * xScale * 5;
					yBR += 0.5 * yScale * 5;
				} else if(GamePanel.timeCounter > 19 && Run3DMaze.clicked) {
					xBL += 0.5 * xScale * 5;
					yBL -= 0.75 * yScale * 5;
					xTL -= 0.25 * xScale * 5;
					yTL -= 0.5 * yScale * 5;
					xTR -= 0.75 * xScale * 5;
					yTR -= 1.25 * yScale * 5;
					xBR += 0.5 * xScale * 5;
					yBR -= 1.5 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					xBR += 0.5 * xScale * 5;
					yBR += 0.75 * yScale * 5;
					xBL += 0.5 * xScale * 5;
					yBL += 1.5 * yScale * 5;
					xTL -= 0.75 * xScale * 5;
					yTL += 1.25 * yScale * 5;
					xTR -= 0.25 * xScale * 5;
					yTR += 0.5 * yScale * 5;
				} else  if(GamePanel.timeCounter > 19 && Run3DMaze.clicked){
					xBR += 0.5 * xScale * 5;
					yBR -= 0.75 * yScale * 5;
					xBL -= 0.25 * xScale * 5;
					yBL -= 0.5 * yScale * 5;
					xTL -= 0.75 * xScale * 5;
					yTL -= 1.25 * yScale * 5;
					xTR += 0.5 * xScale * 5;
					yTR -= 1.5 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					xTR += 0.5 * xScale * 5;
					yTR += 0.75 * yScale * 5;
					xBR += 0.5 * xScale * 5;
					yBR += 1.5 * yScale * 5;
					xBL -= 0.75 * xScale * 5;
					yBL += 1.25 * yScale * 5;
					xTL -= 0.25 * xScale * 5;
					yTL += 0.5 * yScale * 5;
				} else  if(GamePanel.timeCounter > 19 && Run3DMaze.clicked){
					xTR += 0.5 * xScale * 5;
					yTR -= 0.75 * yScale * 5;
					xBR -= 0.25 * xScale * 5;
					yBR -= 0.5 * yScale * 5;
					xBL -= 0.75 * xScale * 5;
					yBL -= 1.25 * yScale * 5;
					xTL += 0.5 * xScale * 5;
					yTL -= 1.5 * yScale * 5;
				}
			}
		} else if (dir == 1) {
			if(GamePanel.timeCounter < 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					xTR -= 0.5 * xScale * 5;
					yTR += 0.75 * yScale * 5;
					xBR += 0.25 * xScale * 5;
					yBR += 0.5 * yScale * 5;
					xBL += 0.75 * xScale * 5;
					yBL += 1.25 * yScale * 5;
					xTL -= 0.5 * xScale * 5;
					yTL += 1.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTR -= 0.5 * xScale * 5;
					yTR -= 0.75 * yScale * 5;
					xBR -= 0.5 * xScale * 5;
					yBR -= 1.5 * yScale * 5;
					xBL += 0.75 * xScale * 5;
					yBL -= 1.25 * yScale * 5;
					xTL += 0.25 * xScale * 5;
					yTL -= 0.5 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					xTL -= 0.5 * xScale * 5;
					yTL += 0.75 * yScale * 5;
					xTR += 0.25 * xScale * 5;
					yTR += 0.5 * yScale * 5;
					xBR += 0.75 * xScale * 5;
					yBR += 1.25 * yScale * 5;
					xBL -= 0.5 * xScale * 5;
					yBL += 1.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL -= 0.5 * xScale * 5;
					yTL -= 0.75 * yScale * 5;
					xTR -= 0.5 * xScale * 5;
					yTR -= 1.5 * yScale * 5;
					xBR += 0.75 * xScale * 5;
					yBR -= 1.25 * yScale * 5;
					xBL += 0.25 * xScale * 5;
					yBL -= 0.5 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					xBL -= 0.5 * xScale * 5;
					yBL += 0.75 * yScale * 5;
					xTL += 0.25 * xScale * 5;
					yTL += 0.5 * yScale * 5;
					xTR += 0.75 * xScale * 5;
					yTR += 1.25 * yScale * 5;
					xBR -= 0.5 * xScale * 5;
					yBR += 1.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xBL -= 0.5 * xScale * 5;
					yBL -= 0.75 * yScale * 5;
					xTL -= 0.5 * xScale * 5;
					yTL -= 1.5 * yScale * 5;
					xTR += 0.75 * xScale * 5;
					yTR -= 1.25 * yScale * 5;
					xBR += 0.25 * xScale * 5;
					yBR -= 0.5 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					xTL -= 0.5 * xScale * 5;
					yTL += 0.75 * yScale * 5;
					xTR += 0.25 * xScale * 5;
					yTR += 0.5 * yScale * 5;
					xBR += 0.75 * xScale * 5;
					yBR += 1.25 * yScale * 5;
					xBL -= 0.5 * xScale * 5;
					yBL += 1.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL -= 0.5 * xScale * 5;
					yTL -= 0.75 * yScale * 5;
					xTR -= 0.5 * xScale * 5;
					yTR -= 1.5 * yScale * 5;
					xBR += 0.75 * xScale * 5;
					yBR -= 1.25 * yScale * 5;
					xBL += 0.25 * xScale * 5;
					yBL -= 0.5 * yScale * 5;
				}
			}
		} else if (dir == 2) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL -= 0.25 * xScale * 5; 
					xBL -= 0.25 * xScale * 5;
					xTR += 0.25 * xScale * 5;
					xBR += 0.25 * xScale * 5;
					yTL += 1 * yScale * 5;
					yTR += 1 * yScale * 5;
					yBR += 1 * yScale * 5;
					yBL += 1 * yScale * 5;
				} else {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL += 0.25 * xScale * 5;
					xBL -= 0.25 * xScale * 5;
					xTR += 0.25 * xScale * 5;
					xBR -= 0.25 * xScale * 5;
					yTL += 1 * yScale * 5;
					yTR += 1 * yScale * 5;
					yBR += 1 * yScale * 5;
					yBL += 1 * yScale * 5;
				} else {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL += 0.25 * xScale * 5;
					xBL += 0.25 * xScale * 5;
					xTR -= 0.25 * xScale * 5; 
					xBR -= 0.25 * xScale * 5;
					yTL += 1 * yScale * 5;
					yTR += 1 * yScale * 5;
					yBR += 1 * yScale * 5;
					yBL += 1 * yScale * 5;
				} else {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL -= 0.25 * xScale * 5;
					xBL += 0.25 * xScale * 5;
					xTR -= 0.25 * xScale * 5;
					xBR += 0.25 * xScale * 5;
					yTL += 1 * yScale * 5;
					yTR += 1 * yScale * 5;
					yBR += 1 * yScale * 5;
					yBL += 1 * yScale * 5;
				} else {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				
			}
		} else if (dir == 3) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				
			}
		} else if (dir == 4) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else {
					xTL = 450 * xScale; xTR = 550 * xScale; xBR = 575 * xScale; xBL = 425 * xScale;
					yTL = 550 * yScale; yTR = 550 * yScale; yBR = 650 * yScale; yBL = 650 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else {
					xTL = 550 * xScale; xTR = 575 * xScale; xBR = 425 * xScale; xBL = 450 * xScale;
					yTL = 550 * yScale; yTR = 650 * yScale; yBR = 650 * yScale; yBL = 550 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else {
					xTL = 575 * xScale; xTR = 425 * xScale; xBR = 450 * xScale; xBL = 550 * xScale;
					yTL = 650 * yScale; yTR = 650 * yScale; yBR = 550 * yScale; yBL = 550 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 425 * xScale; xTR = 450 * xScale; xBR = 550 * xScale; xBL = 575 * xScale;
					yTL = 650 * yScale; yTR = 550 * yScale; yBR = 550 * yScale; yBL = 650 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
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