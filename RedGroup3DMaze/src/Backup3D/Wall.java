package Backup3D;
import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Shapes{
	
	public int state, index, previousWallIndex, nextWallIndex;
	public int h, w;
	public int dir = 0;
	
	Wall(int theState) {
		h = DrawShapes.height;
		w = DrawShapes.width;
		state = theState;
		index = theState;
		previousWallIndex = previousWallIndex(index);
		nextWallIndex = nextWallIndex(index);
		if(theState == 0) {
			xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
			yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
		} else if(theState == 1) {
			xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
			yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
		} else if(theState == 2) {
			xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
			yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
		} else if(theState == 3) {
			if(dir == 0) {
				xTL = 0; xTR = 0; xBR = 0; xBL = 0;
				yTL = 0; yTR = 0; yBR = h; yBL = h;
			} else if (dir == 1){
				xTL = w; xTR = w; xBR = w; xBL = w;
				yTL = 0; yTR = 0; yBR = h; yBL = h;
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
		h = DrawShapes.height;
		w = DrawShapes.width;
		if(dir == 0) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
					yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTR += (w/4.0)/100.0; //2.5;
					xBR += (w/4.0)/100.0; //2.5;
					if(ShapesPanel.timeCounter > 99) {
						yTL += h * (2.0/7.0)/100.0; //2;
						yBL -= h * (2.0/7.0)/100.0; //2;
						xTL += (w/4.0)/100.0; //2.5;
						xBL += (w/4.0)/100.0; //2.5;
					}
				} else {
					xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL += (w/4.0)/100.0;
					xBL += (w/4.0)/100.0;
					if(ShapesPanel.timeCounter < 100) {
						xTR += (w/4.0)/100.0; // 2.5
						xBR += (w/4.0)/100.0; // 2.5
						yTR -= h * (2.0/7.0)/100.0; // 2
						yBR += h * (2.0/7.0)/100.0; // 2
					}
				} else {
					xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTL = ShapesPanel.walls[previousWallIndex].getyTR();
					yBL = ShapesPanel.walls[previousWallIndex].getyBR();
					xTL = ShapesPanel.walls[previousWallIndex].getxTR();
					xBL = ShapesPanel.walls[previousWallIndex].getxBR();
				} else {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 0; yTR = 0; yBR = h; yBL = h;
				}
				
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 0; yTR = 0; yBR = h; yBL = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR = ShapesPanel.walls[nextWallIndex].getyTL();
					yBR = ShapesPanel.walls[nextWallIndex].getyBL();
					xTR = ShapesPanel.walls[nextWallIndex].getxTL();
					xBR = ShapesPanel.walls[nextWallIndex].getxBL();		
				} else {
					xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
					yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
				}		
			}
		} else if (dir == 1) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
					yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR = ShapesPanel.walls[nextWallIndex].getyTL();
					yBR = ShapesPanel.walls[nextWallIndex].getyBL();
					xTR = ShapesPanel.walls[nextWallIndex].getxTL();
					xBR = ShapesPanel.walls[nextWallIndex].getxBL();		
				} else {
					xTL = w; xTR = w; xBR = w; xBL = w;
					yTL = 0; yTR = 0; yBR = h; yBL = h;
				}	
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					xTR -= (w/4.0)/100.0; //2.5;
					xBR -= (w/4.0)/100.0; //2.5;
					if(ShapesPanel.timeCounter < 100) {
						xTL -= (w/4.0)/100.0; //2.5;
						xBL -= (w/4.0)/100.0; //2.5;
						yTL -= h * (2.0/7.0)/100.0; //2;
						yBL += h * (2.0/7.0)/100.0; //2;
					}	
				} else {
					xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
					yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= (w/4.0)/100.0; //2.5;
					xBL -= (w/4.0)/100.0; //2.5;
					if(ShapesPanel.timeCounter > 99) {
						xTR -= (w/4.0)/100.0; //2.5;
						xBR -= (w/4.0)/100.0; //2.5;
						yTR += h * (2.0/7.0)/100.0; //2;
						yBR -= h * (2.0/7.0)/100.0; //2;
					}	
				} else {
					xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w; xTR = w; xBR = w; xBL = w;
					yTL = 0; yTR = 0; yBR = h; yBL = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTL = ShapesPanel.walls[previousWallIndex].getyTR();
					yBL = ShapesPanel.walls[previousWallIndex].getyBR();
					xTL = ShapesPanel.walls[previousWallIndex].getxTR();
					xBL = ShapesPanel.walls[previousWallIndex].getxBR();
				} else {
					xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
				}
			}
		} else if (dir == 2) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
					yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= (h * (2.0/7.0))/200.0; //1;
					yBR += (h * (2.0/7.0))/200.0; //1;
					xTR -= (w/4.0)/200.0; //1.25;
					xBR -= (w/4.0)/200.0; //1.25;
				} else {
					xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
					yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					xTL -= (w/4.0)/200.0; //1.25; 
					yTL -= (h * (2.0/7.0))/200.0; //1;
					xBL -= (w/4.0)/200.0; //1.25;
					yBL += (h * (2.0/7.0))/200.0; //1; 
					xTR += (w/4.0)/200.0; //1.25; 
					yTR -= (h * (2.0/7.0))/200.0; //1;
					xBR += (w/4.0)/200.0; //1.25;
					yBR += (h * (2.0/7.0))/200.0; //1;
				} else {
					xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTL -= (h * (2.0/7.0))/200.0; //1;
					yBL += (h * (2.0/7.0))/200.0; //1;
					xTL += (w/4.0)/200.0; //1.25;
					xBL += (w/4.0)/200.0; //1.25;
				} else {
					xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w; xTR = w; xBR = w; xBL = w;
					yTL = 0; yTR = 0; yBR = h; yBL = h;
				}
			}
		} else if (dir == 3) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
					yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += (h/200.0); //3.5;
					yBR += (h/200.0); //3.5;
					yTL += (h/200.0); //3.5;
					yBL += (h/200.0); //3.5;
				} else {
					xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
					yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += (h/200.0); //3.5;
					yBR += (h/200.0); //3.5;
					yTL += (h/200.0); //3.5;
					yBL += (h/200.0); //3.5;
				} else {
					xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR += (h/200.0); //3.5;
					yBR += (h/200.0); //3.5;
					yTL += (h/200.0); //3.5;
					yBL += (h/200.0); //3.5;
				} else {
					xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w; xTR = w; xBR = w; xBL = w;
					yTL = 0; yTR = 0; yBR = h; yBL = h;
				}
			}
		} else if (dir == 4) {
			if(ShapesPanel.timeCounter <= 200 && state == 0) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
					yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= (h/200.0); //3.5;
					yBR -= (h/200.0); //3.5;
					yTL -= (h/200.0); //3.5;
					yBL -= (h/200.0); //3.5;
				} else {
					xTL = 0; xTR = w/4.0; xBR = w/4.0; xBL = 0;
					yTL = 0; yTR = h * (2.0/7.0); yBR = h * (5.0/7.0); yBL = h;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 1){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= (h/200.0); //3.5;
					yBR -= (h/200.0); //3.5;
					yTL -= (h/200.0); //3.5;
					yBL -= (h/200.0); //3.5;
				} else {
					xTL = w/4.0; xBL = w/4.0; xTR = w * (3.0/4.0); xBR = w * (3.0/4.0);
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = h * (2.0/7.0); yBR = h * (5.0/7.0);
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 2){
				if(ShapesPanel.timeCounter == 0) {
					xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
				}
				if(ShapesPanel.timeCounter < 200) {
					yTR -= (h/200.0); //3.5;
					yBR -= (h/200.0); //3.5;
					yTL -= (h/200.0); //3.5;
					yBL -= (h/200.0); //3.5;
				} else {
					xTL = w * (3.0/4.0); xBL = w * (3.0/4.0); xTR = w; xBR = w;
					yTL = h * (2.0/7.0); yBL = h * (5.0/7.0); yTR = 0; yBR = h;
				}
			} else if (ShapesPanel.timeCounter <= 200 && state == 3) {
				if(ShapesPanel.timeCounter == 0) {
					xTL = w; xTR = w; xBR = w; xBL = w;
					yTL = 0; yTR = 0; yBR = h; yBL = h;
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