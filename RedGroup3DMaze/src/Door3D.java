
import java.awt.Color;
import java.awt.Graphics;

public class Door3D extends Shapes3D {

	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public int dir = 0;
	public int h, w;
	double xScale = w/1000.0;
	double yScale = h/700.0;
	Color color = new Color(243, 243, 243);

	Door3D(int theState) {
		h = Run3DMaze.height;
		w = Run3DMaze.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		state = theState;
		if(theState == 0) {
			xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
			yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
		} else if(theState == 1) {
			xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
			yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
		} else if(theState == 2) {
			xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
			yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
		} else if(theState == 3) {
			if(dir == 0) {
				xTL = 0 * xScale; xTR = 0 * xScale; xBR = 0 * xScale; xBL = 0 * xScale;
				yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
			} else if (dir == 1) {
				xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
				yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
			}
		}
	}
	
	Door3D(int theState, Color color){
		this(theState);
		this.color = color;
	}
	
	public void paint(Graphics g) {
		int[] doorX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] doorY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		//color = new Color(243, 243, 243);
		g.setColor(color);
		g.fillPolygon(doorX, doorY, 4);
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
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTL += 1.25 * xScale * 5;
					xBL += 1.25 * xScale * 5;
					xTR += 1.75 * xScale * 5;
					xBR += 1.75 * xScale * 5;
					
					yBL -= 0.18 * yScale * 5;
				}
				
				if(GamePanel.timeCounter > 19 && Run3DMaze.clicked) {
					xTL += 1.25 * xScale * 5;
					xTR += 0.25 * xScale * 5;
					xBL += 1.25 * xScale * 5;
					xBR += 0.25 * xScale * 5;
				}
				if(GamePanel.timeCounter > 19 && GamePanel.timeCounter < 30 && Run3DMaze.clicked) {
					yBL -= 0.9 * yScale * 5;
					yBR -= 0.4 * yScale * 5;
				}
				if(GamePanel.timeCounter > 29 && Run3DMaze.clicked) {
					yBL -= 1.18 * yScale * 5;
					yBR -= 0.8 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTR += 1.25 * xScale * 5;
					xBR += 1.25 * xScale * 5;
					xTL += 1.75 * xScale * 5;
					xBL += 1.75 * xScale * 5;
					
					yBR += 0.2 * yScale * 5;
				}
				
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					xTR += 1.25 * xScale * 5;
					xTL += 0.25 * xScale * 5;
					xBR += 1.25 * xScale * 5;
					xBL += 0.25 * xScale * 5;
				}
				if(GamePanel.timeCounter < 20 && GamePanel.timeCounter > 9 && Run3DMaze.clicked) {
					yBR += 1.05 * yScale * 5;
					yBL += 0.5 * yScale * 5;
				}
				if(GamePanel.timeCounter < 10 && Run3DMaze.clicked) {
					yBR += 0.95 * yScale * 5;
					yBL += 0.7 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTR += 2.3333 * xScale * 5;
					xBR += 2.3333 * xScale * 5;
					xTL += 2.3333 * xScale * 5;
					xBL += 2.3333 * xScale * 5;
					yTL += 0.8 * yScale * 5;
					yTR += 0.8 * yScale * 5;
					yBR += 1.818181 * yScale * 5;
					yBL += 1.866666667 * yScale * 5;
				}
				
			} else if (GamePanel.timeCounter < 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0 * xScale; xTR = 0 * xScale; xBR = 0 * xScale; xBL = 0 * xScale;
					yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter > 25 && Run3DMaze.clicked) {
					xTR += 2.3333 * xScale * 5;
					xBR += 2.3333 * xScale * 5;
					yTR -= 0.8 * yScale * 5;
					yTL -= 0.8 * yScale * 5;
					yBR -= 1.866666667 * yScale * 5;
				}
				if(GamePanel.timeCounter > 33 && Run3DMaze.clicked) {
					xTL += 2.272727 * xScale * 5;
					xBL += 2.272727 * xScale * 5;
					yBL -= 1.818181 * yScale * 5;
				}
			}
		} else if (dir == 1) {
			if(GamePanel.timeCounter < 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTR -= 2.3333 * xScale * 5;
					xBR -= 2.3333 * xScale * 5;
					xTL -= 2.3333 * xScale * 5;
					xBL -= 2.3333 * xScale * 5;
					yTL += 0.8 * yScale * 5;
					yTR += 0.8 * yScale * 5;
					yBR += 1.818181 * yScale * 5;
					yBL += 1.866666667 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTL -= 1.25 * xScale * 5;
					xBL -= 1.25 * xScale * 5;
					xTR -= 1.75 * xScale * 5;
					xBR -= 1.75 * xScale * 5;
					
					yBL += 0.18 * yScale * 5; 
				}
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					xTL -= 1.25 * xScale * 5;
					xTR -= 0.25 * xScale * 5;
					xBL -= 1.25 * xScale * 5;
					xBR -= 0.25 * xScale * 5;
				}
				if(GamePanel.timeCounter < 20 && GamePanel.timeCounter > 9 && Run3DMaze.clicked) {
					yBL += 0.9 * yScale * 5;
					yBR += 0.4 * yScale * 5;
				}
				if(GamePanel.timeCounter < 10 && Run3DMaze.clicked) {
					yBL += 1.18 * yScale * 5;
					yBR += 0.8 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTR -= 1.25 * xScale * 5;
					xBR -= 1.25 * xScale * 5;
					xTL -= 1.75 * xScale * 5;
					xBL -= 1.75 * xScale * 5;
					
					yBR -= 0.18 * yScale * 5;
				}
				if(GamePanel.timeCounter > 19 && Run3DMaze.clicked) {
					xTR -= 1.25 * xScale * 5;
					xTL -= 0.25 * xScale * 5;
					xBR -= 1.25 * xScale * 5;
					xBL -= 0.25 * xScale * 5;
				}
				if(GamePanel.timeCounter > 19 && GamePanel.timeCounter < 30 && Run3DMaze.clicked) {
					yBR -= 0.9 * yScale * 5;
					yBL -= 0.4 * yScale * 5;
				}
				if(GamePanel.timeCounter > 29 && Run3DMaze.clicked) {
					yBR -= 1.18 * yScale * 5;
					yBL -= 0.8 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter > 25 && Run3DMaze.clicked) {
					xTL -= 2.3333 * xScale * 5;
					xBL -= 2.3333 * xScale * 5;
					yTL -= 0.8 * yScale * 5;
					yTR -= 0.8 * yScale * 5;
					yBL -= 1.866666667 * yScale * 5;
				}
				if(GamePanel.timeCounter > 33 && Run3DMaze.clicked) {
					xTR -= 2.272727 * xScale * 5;
					xBR -= 2.272727 * xScale * 5;
					yBR -= 1.818181 * yScale * 5;
				}
			}
		} else if (dir == 2) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL -= 1.875 * xScale * 5;
					xBL -= 1.875 * xScale * 5;
					xTR -= 1.875 * xScale * 5;
					xBR -= 1.875 * xScale * 5;
					yBR += 1.5 * yScale * 5;
					yBL += 1.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL -= 0.25 * xScale * 5; 
					yTL -= (2.0/30.0) * yScale * 5;
					xBL -= 0.25 * xScale * 5;
					yBL += 1 * yScale * 5; 
					xTR += 0.25 * xScale * 5; 
					yTR -= (2.0/30.0) * yScale * 5;
					xBR += 0.25 * xScale * 5;
					yBR += 1 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL += 1.875 * xScale * 5;
					xBL += 1.875 * xScale * 5;
					xTR += 1.875 * xScale * 5;
					xBR += 1.875 * xScale * 5;
					yBR += 1.5 * yScale * 5;
					yBL += 1.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
			}
		} else if (dir == 3) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
			}
		} else if (dir == 4) {
			if(GamePanel.timeCounter <= 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
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