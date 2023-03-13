import java.awt.Color;
import java.awt.Graphics;

public class Art3D extends Shapes3D {

	double xTL, xTR, xBR, xBL;
	double  yTL, yTR, yBR, yBL;
	int xCenter, yCenter, xMidLeft, yMidLeft;
	int xMidRight, yMidRight, xMidTop, yMidTop;
	int xMidBot, yMidBot;
	public int state;
	public int dir = 0;
	public int h, w;
	double xScale;
	double yScale;

	Art3D(int theState) {
		h = Run3DMaze.height;
		w = Run3DMaze.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		state = theState;
		if(theState == 0) {
			xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
			yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
		} else if(theState == 1) {
			xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
			yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
		} else if(theState == 2) {
			xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
			yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
		} else if(theState == 3) {
			if(dir == 0) {
				xTL = 0; xTR = 0; xBR = 0; xBL = 0;
				yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
			} else if (dir == 1) {
				xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
				yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
			}
		}
	}
	
	public void paint(Graphics g) {
		int[] artFrameX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] artFrameY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		xCenter = (int) Math.rint((xTL + xTR)/2.0);
		yCenter = (int) Math.rint((((yTL + yBL)/2.0) + ((yTR + yBR)/2.0))/2.0);
		xMidLeft = (int) Math.rint((xTL + xBL)/2.0);
		yMidLeft = (int) Math.rint((yTL + yBL)/2.0);
		xMidRight = (int) Math.rint((xTR + xBR)/2.0);
		yMidRight = (int) Math.rint((yTR + yBR)/2.0);
		xMidTop = (int) Math.rint((xTL + xTR)/2.0);
		yMidTop = (int) Math.rint((yTL + yTR)/2.0);
		xMidBot = (int) Math.rint((xBL + xBR)/2.0);
		yMidBot = (int) Math.rint((yBL + yBR)/2.0);
		int[] art1X = {(int) Math.rint(xTL), xMidTop, xCenter, xMidLeft};
		int[] art1Y = {(int) Math.rint(yTL), yMidTop, yCenter, yMidLeft};
		int[] art2X = {xMidTop, (int) Math.rint(xTR), xMidRight, xCenter};
		int[] art2Y = {yMidTop, (int) Math.rint(yTR), yMidRight, yCenter};
		int[] art3X = {xMidLeft, xCenter, xMidBot, (int) Math.rint(xBL)};
		int[] art3Y = {yMidLeft, yCenter, yMidBot, (int) Math.rint(yBL)};
		int[] art4X = {xCenter, xMidRight, (int) Math.rint(xBR), xMidBot};
		int[] art4Y = {yCenter, yMidRight, (int) Math.rint(yBR), yMidBot};
		double x = Run3DMaze.player.getCoordinate('X'); 
		double y = Run3DMaze.player.getCoordinate('Y'); 
		double z = Run3DMaze.player.getCoordinate('Z'); 
		g.setColor(new Color(243,243,243));
		g.fillPolygon(artFrameX, artFrameY, 4);
		g.setColor(new Color((int) (24*(x/5.0)), (int) (24*(y/5.0)), (int) (24*(z/5.0))));
		g.fillPolygon(art1X, art1Y, 4);
		g.setColor(new Color((int) (43*(x/5.0)), (int) (43*(y/5.0)), (int) (43*(z/5.0))));
		g.fillPolygon(art2X, art2Y, 4);
		g.setColor(new Color((int) (143*(x/5.0)), (int) (143*(y/5.0)), (int) (143*(z/5.0))));
		g.fillPolygon(art3X, art3Y, 4);
		g.setColor(new Color((int) (173*(x/5.0)), (int) (173*(y/5.0)), (int) (173*(z/5.0))));
		g.fillPolygon(art4X, art4Y, 4);
		g.setColor(Color.black);
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xBL),(int) Math.rint(yBL));
		g.drawLine((int) Math.rint(xTR),(int) Math.rint(yTR),(int) Math.rint(xBR),(int) Math.rint(yBR));
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xTR),(int) Math.rint(yTR));
		g.drawLine((int) Math.rint(xBL),(int) Math.rint(yBL),(int) Math.rint(xBR),(int) Math.rint(yBR));
		g.drawLine((int) Math.rint((xTL + xBL)/2.0),(int) Math.rint((yTL + yBL)/2.0),(int) 
		Math.rint((xTR + xBR)/2.0),(int) Math.rint((yTR + yBR)/2.0));
		g.drawLine((int) Math.rint((xTL + xTR)/2.0),(int) Math.rint((yTL + yTR)/2.0),(int) 
		Math.rint((xBL + xBR)/2.0),(int) Math.rint((yBL + yBR)/2.0));
	}
	
	public void update() {
		h = Run3DMaze.height;
		w = Run3DMaze.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		if(dir == 0) {
			if(GamePanel.timeCounter < 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				xTL += 1.875 * xScale;
				yTL += 0.235 * yScale;
				xTR += 1.875 * xScale;
				yTR += 0.1 * yScale;
				xBR += 1.875 * xScale;
				yBR -= 0.1 * yScale;
				xBL += 1.875 * xScale;
				yBL -= 0.235 * yScale;
			} else if (GamePanel.timeCounter < 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				xTL += 1.875 * xScale;
				yTL -= 0.1 * yScale;
				xBL += 1.875 * xScale;
				yBL += 0.1 * yScale;
				xTR += 1.875 * xScale;
				yTR -= 0.235 * yScale;
				xBR += 1.875 * xScale;
				yBR += 0.235 * yScale;
			} else if (GamePanel.timeCounter < 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				xTR += 2.3333 * xScale;
				xBR += 2.3333 * xScale;
				xTL += 2.3333 * xScale;
				xBL += 2.3333 * xScale;
				yTL -= 0.235 * yScale;
				yTR -= 0.1 * yScale;
				yBR += 0.1 * yScale;
				yBL += 0.235 * yScale;
			} else if (GamePanel.timeCounter < 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
				if(GamePanel.timeCounter > 125) {
					xTR += 2.3333 * xScale;
					xBR += 2.3333 * xScale;
				}
				if(GamePanel.timeCounter > 167) {
					xTL += 2.272727 * xScale;
					xBL += 2.272727 * xScale;
				}
				if(GamePanel.timeCounter > 99) {
					yTL += 0.1 * 2.0 * yScale;
					yTR += 0.235 * 2.0 * yScale;
					yBR -= 0.235 * 2.0 * yScale;
					yBL -= 0.1 * 2.0 * yScale;
				}
				
			}
		} else if (dir == 1) {
			if(GamePanel.timeCounter < 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				xTR -= 2.3333 * xScale;
				xBR -= 2.3333 * xScale;
				xTL -= 2.3333 * xScale;
				xBL -= 2.3333 * xScale;
				if(GamePanel.timeCounter < 100) {
					yTL -= 0.1 * 2.0 * yScale;
					yTR -= 0.235 * 2.0 * yScale;
					yBR += 0.235 * 2.0 * yScale;
					yBL += 0.1 * 2.0 * yScale;
				}
			} else if (GamePanel.timeCounter < 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				xTL -= 1.875 * xScale;
				yTL -= 0.235 * yScale;
				xTR -= 1.875 * xScale;
				yTR -= 0.1 * yScale;
				xBR -= 1.875 * xScale;
				yBR += 0.1 * yScale;
				xBL -= 1.875 * xScale;
				yBL += 0.235 * yScale;
			} else if (GamePanel.timeCounter < 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				xTL -= 1.875 * xScale;
				yTL += 0.1 * yScale;
				xBL -= 1.875 * xScale;
				yBL -= 0.1 * yScale;
				xTR -= 1.875 * xScale;
				yTR += 0.235 * yScale;
				xBR -= 1.875 * xScale;
				yBR -= 0.235 * yScale;
			} else if (GamePanel.timeCounter < 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
				if(GamePanel.timeCounter > 125) {
					xTL -= 2.3333 * xScale;
					xBL -= 2.3333 * xScale;
				}
				if(GamePanel.timeCounter > 167) {
					xTR -= 2.272727 * xScale;
					xBR -= 2.272727 * xScale;
				}
				yTL += 0.235 * yScale;
				yTR += 0.1 * yScale;
				yBR -= 0.1 * yScale;
				yBL -= 0.235 * yScale;
			}
		} else if (dir == 2) {
			if(GamePanel.timeCounter < 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTL -= 1.875 * xScale;
					xBL -= 1.875 * xScale;
					xTR -= 1.875 * xScale;
					xBR -= 1.875 * xScale;
					yTR -= (1.0/3.0) * yScale;
					yTL -= (1.0/3.0) * yScale;
					yBR += (1.0/3.0) * yScale;
					yBL += (1.0/3.0) * yScale;
				} else {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
			} else if (GamePanel.timeCounter < 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTL -= 0.2 * xScale; 
					yTL -= 0.2 * xScale;
					xBL -= 0.2 * xScale;
					yBL += 0.2 * xScale; 
					xTR += 0.2 * xScale; 
					yTR -= 0.2 * xScale;
					xBR += 0.2 * xScale;
					yBR += 0.2 * xScale;
				} else {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
			} else if (GamePanel.timeCounter < 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTL += 1.875 * xScale;
					xBL += 1.875 * xScale;
					xTR += 1.875 * xScale;
					xBR += 1.875 * xScale;
					yTR -= (1.0/3.0) * yScale;
					yTL -= (1.0/3.0) * yScale;
					yBR += (1.0/3.0) * yScale;
					yBL += (1.0/3.0) * yScale;
				} else {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
			} else if (GamePanel.timeCounter < 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
			}
		} else if (dir == 3) {
			if(GamePanel.timeCounter <= 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
			}
		} else if (dir == 4) {
			if(GamePanel.timeCounter <= 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
			yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
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
