
import java.awt.Color;
import java.awt.Graphics;

public class Door3D extends Shapes3D {

	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	public int state;
	public int dir = 0;
	public int h, w;
	double xScale = w/1000;
	double yScale = h/700;

	// Justin
	

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
				xTL = 0; xTR = 0; xBR = 0; xBL = 0;
				yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
			} else if (dir == 1) {
				xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
				yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
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
		System.out.println(state);
		h = Run3DMaze.height;
		w = Run3DMaze.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		if(dir == 0) {
			if(GamePanel.timeCounter < 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
				xTL += 1.25 * xScale;//0.75;
				xBL += 1.25 * xScale;//0.75;
				xTR += 1.75 * xScale;
				xBR += 1.75 * xScale;
				
				yBL -= 0.2  * yScale;  //this used to not exist
				if(GamePanel.timeCounter > 99) {
					xTL += 1.25 * xScale;//2.25;
					xTR += 0.25 * xScale;
					xBL += 1.25 * xScale;//2.25;
					xBR += 0.25 * xScale;
				}
				if(GamePanel.timeCounter > 99 && GamePanel.timeCounter < 150) {
					yBL -= 1.05 * yScale;//1.55
					yBR -= 0.5 * yScale;
				}
				if(GamePanel.timeCounter > 149) {
					yBL -= 0.95 * yScale;//1.25
					yBR -= 0.7 * yScale;
				}
			} else if (GamePanel.timeCounter < 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
				xTR += 1.25 * xScale;//0.75;
				xBR += 1.25 * xScale;//0.75;
				xTL += 1.75 * xScale;
				xBL += 1.75 * xScale;
				
				yBR += 0.2 * yScale;	//this used to not exist
				if(GamePanel.timeCounter < 100) {
					xTR += 1.25 * xScale;//2.25;
					xTL += 0.25 * xScale;
					xBR += 1.25 * xScale;//2.25;
					xBL += 0.25 * xScale;
				}
				if(GamePanel.timeCounter < 100 && GamePanel.timeCounter > 49) {
					yBR += 1.05 * yScale;//+= 1.55;
					yBL += 0.5 * yScale;
				}
				if(GamePanel.timeCounter < 50) {
					yBR += 0.95 * yScale;//1.25;
					yBL += 0.7 * yScale;
				}
			} else if (GamePanel.timeCounter < 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
				xTR += 2.3333 * xScale;
				xBR += 2.3333 * xScale;
				xTL += 2.3333 * xScale;
				xBL += 2.3333 * xScale;
				yTL += 0.8 * yScale;
				yTR += 0.8 * yScale;
				yBR += 1.818181 * yScale;
				yBL += 1.866666667 * yScale;
			} else if (GamePanel.timeCounter < 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0 * xScale; xTR = 0 * xScale; xBR = 0 * xScale; xBL = 0 * xScale;
					yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter > 125) {
					xTR += 2.3333 * xScale;
					xBR += 2.3333 * xScale;
					yTR -= 0.8 * yScale;
					yTL -= 0.8 * yScale;
					yBR -= 1.866666667 * yScale;
				}
				if(GamePanel.timeCounter > 167) {
					xTL += 2.272727 * xScale;
					xBL += 2.272727 * xScale;
					yBL -= 1.818181 * yScale;
				}
			}
		} else if (dir == 1) {
			if(GamePanel.timeCounter < 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
				xTR -= 2.3333 * xScale;
				xBR -= 2.3333 * xScale;
				xTL -= 2.3333 * xScale;
				xBL -= 2.3333 * xScale;
				yTL += 0.8 * yScale;
				yTR += 0.8 * yScale;
				yBR += 1.818181 * yScale;
				yBL += 1.866666667 * yScale;
			} else if (GamePanel.timeCounter < 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
				xTL -= 1.25 * xScale;//0.75;
				xBL -= 1.25 * xScale;//0.75;
				xTR -= 1.75 * xScale;
				xBR -= 1.75 * xScale;
				
				yBL += 0.2 * yScale;  //this used to not exist
				if(GamePanel.timeCounter < 100) {
					xTL -= 1.25 * xScale;//2.25;
					xTR -= 0.25 * xScale;
					xBL -= 1.25 * xScale;//2.25;
					xBR -= 0.25 * xScale;
				}
				if(GamePanel.timeCounter < 100 && GamePanel.timeCounter > 49) {
					yBL += 1.05 * yScale;//1.55;
					yBR += 0.5 * yScale;
				}
				if(GamePanel.timeCounter < 50) {
					yBL += 0.95 * yScale;//1.25;
					yBR += 0.7 * yScale;
				}
			} else if (GamePanel.timeCounter < 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
				xTR -= 1.25 * xScale;//0.75;
				xBR -= 1.25 * xScale;//0.75;
				xTL -= 1.75 * xScale;
				xBL -= 1.75 * xScale;
				
				yBR -= 0.2 * yScale;  //this used to not exist
				if(GamePanel.timeCounter > 99) {
					xTR -= 1.25 * xScale;//2.25;
					xTL -= 0.25 * xScale;
					xBR -= 1.25 * xScale;//2.25;
					xBL -= 0.25 * xScale;
				}
				if(GamePanel.timeCounter > 99 && GamePanel.timeCounter < 150) {
					yBR -= 1.05 * yScale;//1.55;
					yBL -= 0.5 * yScale;
				}
				if(GamePanel.timeCounter > 149) {
					yBR -= 0.95 * yScale;//1.25;
					yBL -= 0.7 * yScale;
				}
			} else if (GamePanel.timeCounter < 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter > 125) {
					xTL -= 2.3333 * xScale;
					xBL -= 2.3333 * xScale;
					yTL -= 0.8 * yScale;
					yTR -= 0.8 * yScale;
					yBL -= 1.866666667 * yScale;
				}
				if(GamePanel.timeCounter > 167) {
					xTR -= 2.272727 * xScale;
					xBR -= 2.272727 * xScale;
					yBR -= 1.818181 * yScale;
				}
			}
		} else if (dir == 2) {
			if(GamePanel.timeCounter <= 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTL -= 1.875 * xScale;
					xBL -= 1.875 * xScale;
					xTR -= 1.875 * xScale;
					xBR -= 1.875 * xScale;
					yBR += 1.5 * yScale;
					yBL += 1.5 * yScale;
				} 
//				else {
//					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
//					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
//				}
			} else if (GamePanel.timeCounter <= 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTL -= 0.25 * xScale; 
					yTL -= (2.0/30.0) * yScale;
					xBL -= 0.25 * xScale;
					yBL += 1 * yScale; 
					xTR += 0.25 * xScale; 
					yTR -= (2.0/30.0) * yScale;
					xBR += 0.25 * xScale;
					yBR += 1 * yScale;
				} 
//				else {
//					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
//					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
//				}
			} else if (GamePanel.timeCounter <= 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTL += 1.875 * xScale;
					xBL += 1.875 * xScale;
					xTR += 1.875 * xScale;
					xBR += 1.875 * xScale;
					yBR += 1.5 * yScale;
					yBL += 1.5 * yScale;
				} 
//				else {
//					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
//					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
//				}
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 400 * yScale; yTR = 400 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
			}
		} else if (dir == 3) {
			if(GamePanel.timeCounter <= 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
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
				if(GamePanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 560 * yScale; yBL = 640 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 340 * yScale; yBL = 500 * yScale; yTR = 340 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 340 * yScale; yTR = 340 * yScale; yBR = 640 * yScale; yBL = 560 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
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