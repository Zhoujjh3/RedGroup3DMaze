
import java.awt.Color;
import java.awt.Graphics;

public class Wall3D extends Shapes3D{
	
	public int state, index, previousWallIndex, nextWallIndex;
	public int h, w;
	public int dir = 0;
	double xScale = w/1000;
	double yScale = h/700;
	
	Wall3D(int theState) {
		h = Run3DMaze.height;
		w = Run3DMaze.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		state = theState;
		index = theState;
		previousWallIndex = previousWallIndex(index);
		nextWallIndex = nextWallIndex(index);
		if(theState == 0) {
			xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
			yTL = 0; yTR = 200; yBR = 500; yBL = 700;
		} else if(theState == 1) {
			xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
			yTL = 200; yBL = 500; yTR = 200; yBR = 500;
		} else if(theState == 2) {
			xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
			yTL = 200; yBL = 500; yTR = 0; yBR = 700;
		} else if(theState == 3) {
			if(dir == 0) {
				xTL = 0; xTR = 0; xBR = 0; xBL = 0;
				yTL = 0; yTR = 0; yBR = 700; yBL = 700;
			} else if (dir == 1){
				xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
				yTL = 0; yTR = 0; yBR = 700; yBL = 700;
			}
		}
	}
	
	public void paint(Graphics g) {
		int[] wallX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] wallY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		
		g.setColor(new Color(243,243,243));
		g.fillPolygon(wallX, wallY, 4);
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
			if(GamePanel.timeCounter <= 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0;
					yTL = 0 * yScale; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTR += 2.5 * xScale;
					xBR += 2.5 * xScale;
					if(GamePanel.timeCounter > 99) {
						yTL += 2 * yScale;
						yBL -= 2 * yScale;
						xTL += 2.5 * xScale;
						xBL += 2.5 * xScale;
					}
				} else {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTL += 2.5 * xScale;
					xBL += 2.5 * xScale;
					if(GamePanel.timeCounter < 100) {
						xTR += 2.5 * xScale;
						xBR += 2.5 * xScale;
						yTR -= 2 * yScale;
						yBR += 2 * yScale;
					}
				} else {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
//					yTL = Room.walls[previousWallIndex].getyTR();
//					yBL = Room.walls[previousWallIndex].getyBR();
//					xTL = Room.walls[previousWallIndex].getxTR();
//					xBL = Room.walls[previousWallIndex].getxBR();
				} else {
					xTL = 0 * xScale; xTR = 0 * xScale; xBR = 0 * xScale; xBL = 0 * xScale;
					yTL = 0; yTR = 0; yBR = 700 * yScale; yBL = 700 * yScale;
				}
				
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 0; yTR = 0; yBR = 700 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
//					yTR = Room.walls[nextWallIndex].getyTL();
//					yBR = Room.walls[nextWallIndex].getyBL();
//					xTR = Room.walls[nextWallIndex].getxTL();
//					xBR = Room.walls[nextWallIndex].getxBL();		
				} else {
					xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}		
			}
		} else if (dir == 1) {
			if(GamePanel.timeCounter <= 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
//					yTR = Room.walls[nextWallIndex].getyTL();
//					yBR = Room.walls[nextWallIndex].getyBL();
//					xTR = Room.walls[nextWallIndex].getxTL();
//					xBR = Room.walls[nextWallIndex].getxBL();		
				} else {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 0; yTR = 0; yBR = 700 * yScale; yBL = 700 * yScale;
				}	
			} else if (GamePanel.timeCounter <= 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTR -= 2.5 * xScale;
					xBR -= 2.5 * xScale;
					if(GamePanel.timeCounter < 100) {
						xTL -= 2.5 * xScale;
						xBL -= 2.5 * xScale;
						yTL -= 2 * yScale;
						yBL += 2 * yScale;
					}	
				} else {
					xTL = 0; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTL -= 2.5 * xScale;
					xBL -= 2.5 * xScale;
					if(GamePanel.timeCounter > 99) {
						xTR -= 2.5 * xScale;
						xBR -= 2.5 * xScale;
						yTR += 2 * yScale;
						yBR -= 2 * yScale;
					}	
				} else {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 0; yTR = 0; yBR = 700 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
//					yTL = Room.walls[previousWallIndex].getyTR();
//					yBL = Room.walls[previousWallIndex].getyBR();
//					xTL = Room.walls[previousWallIndex].getxTR();
//					xBL = Room.walls[previousWallIndex].getxBR();
				} else {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
			}
		} else if (dir == 2) {
			if(GamePanel.timeCounter <= 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR -= 1 * yScale;
					yBR += 1 * yScale;
					xTR -= 1.25 * xScale;
					xBR -= 1.25 * xScale;
				} else {
					xTL = 0; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					xTL -= 1.25 * xScale; 
					yTL -= 1 * yScale;
					xBL -= 1.25 * xScale;
					yBL += 1 * yScale; 
					xTR += 1.25 * xScale; 
					yTR -= 1 * yScale;
					xBR += 1.25 * xScale;
					yBR += 1 * yScale;
				} else {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTL -= 1 * yScale;
					yBL += 1 * yScale;
					xTL += 1.25 * xScale;
					xBL += 1.25 * xScale;
				} else {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 0; yTR = 0; yBR = 700 * yScale; yBL = 700 * yScale;
				}
			}
		} else if (dir == 3) {
			if(GamePanel.timeCounter <= 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 0; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR += 3.5 * yScale;
					yBR += 3.5 * yScale;
					yTL += 3.5 * yScale;
					yBL += 3.5 * yScale;
				} else {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 0; yTR = 0; yBR = 700 * yScale; yBL = 700 * yScale;
				}
			}
		} else if (dir == 4) {
			if(GamePanel.timeCounter <= 200 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 0; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200) {
					yTR -= 3.5 * yScale;
					yBR -= 3.5 * yScale;
					yTL -= 3.5 * yScale;
					yBL -= 3.5 * yScale;
				} else {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 200 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 0; yTR = 0; yBR = 700 * yScale; yBL = 700 * yScale;
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
	
	public int previousWallIndex(int index) {
		if(index > 0) {
			return index-1;
		} else {
			return 3;
		}
	}
	public int nextWallIndex(int index) {
		if(index < 3) {
			return index+1;
		} else {
			return 0;
		}
	}
}