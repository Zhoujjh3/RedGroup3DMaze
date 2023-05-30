import java.awt.Color;
import java.awt.Graphics;

public abstract class Art3D extends Shapes3D {

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
	Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'), 
	Run3DMaze.player.getCoordinate('X'), 
	Run3DMaze.player.getCoordinate('Y'));

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
	
	public abstract void paint(Graphics g);
	
	public void update() {
		h = Run3DMaze.height;
		w = Run3DMaze.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		if(dir == 0) {
			if(GamePanel.timeCounter < 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTL += 1.875 * xScale * 5;
					yTL += 0.235 * yScale * 5;
					xTR += 1.875 * xScale * 5;
					yTR += 0.1 * yScale * 5;
					xBR += 1.875 * xScale * 5;
					yBR -= 0.1 * yScale * 5;
					xBL += 1.875 * xScale * 5;
					yBL -= 0.235 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTL += 1.875 * xScale * 5;
					yTL -= 0.1 * yScale * 5;
					xBL += 1.875 * xScale * 5;
					yBL += 0.1 * yScale * 5;
					xTR += 1.875 * xScale * 5;
					yTR -= 0.235 * yScale * 5;
					xBR += 1.875 * xScale * 5;
					yBR += 0.235 * yScale * 5;
				}
				
			} else if (GamePanel.timeCounter < 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTR += 2.3333 * xScale * 5;
					xBR += 2.3333 * xScale * 5;
					xTL += 2.3333 * xScale * 5;
					xBL += 2.3333 * xScale * 5;
					yTL -= 0.235 * yScale * 5;
					yTR -= 0.1 * yScale * 5;
					yBR += 0.1 * yScale * 5;
					yBL += 0.235 * yScale * 5;
				}
				
			} else if (GamePanel.timeCounter < 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0; xTR = 0; xBR = 0; xBL = 0;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
				if(GamePanel.timeCounter > 25 && Run3DMaze.clicked) {
					xTR += 2.3333 * xScale * 5;
					xBR += 2.3333 * xScale * 5;
				}
				if(GamePanel.timeCounter > 33 && Run3DMaze.clicked) {
					xTL += 2.272727 * xScale * 5;
					xBL += 2.272727 * xScale * 5;
				}
				if(GamePanel.timeCounter > 19 && Run3DMaze.clicked) {
					yTL += 0.1 * 2.0 * yScale * 5;
					yTR += 0.235 * 2.0 * yScale * 5;
					yBR -= 0.235 * 2.0 * yScale * 5;
					yBL -= 0.1 * 2.0 * yScale * 5;
				}
				
			}
		} else if (dir == 1) {
			if(GamePanel.timeCounter < 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTR -= 2.3333 * xScale * 5;
					xBR -= 2.3333 * xScale * 5;
					xTL -= 2.3333 * xScale * 5;
					xBL -= 2.3333 * xScale * 5;
				}
				if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
					yTL -= 0.1 * 2.0 * yScale * 5;
					yTR -= 0.235 * 2.0 * yScale * 5;
					yBR += 0.235 * 2.0 * yScale * 5;
					yBL += 0.1 * 2.0 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTL -= 1.875 * xScale * 5;
					yTL -= 0.235 * yScale * 5;
					xTR -= 1.875 * xScale * 5;
					yTR -= 0.1 * yScale * 5;
					xBR -= 1.875 * xScale * 5;
					yBR += 0.1 * yScale * 5;
					xBL -= 1.875 * xScale * 5;
					yBL += 0.235 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(Run3DMaze.clicked) {
					xTL -= 1.875 * xScale * 5;
					yTL += 0.1 * yScale * 5;
					xBL -= 1.875 * xScale * 5;
					yBL -= 0.1 * yScale * 5;
					xTR -= 1.875 * xScale * 5;
					yTR += 0.235 * yScale * 5;
					xBR -= 1.875 * xScale * 5;
					yBR -= 0.235 * yScale * 5;
				}
			} else if (GamePanel.timeCounter < 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
				if(GamePanel.timeCounter > 25 && Run3DMaze.clicked) {
					xTL -= 2.3333 * xScale * 5;
					xBL -= 2.3333 * xScale * 5;
				}
				if(GamePanel.timeCounter > 33 && Run3DMaze.clicked) {
					xTR -= 2.272727 * xScale * 5;
					xBR -= 2.272727 * xScale * 5;
				}
				if(Run3DMaze.clicked) {
					yTL += 0.235 * yScale * 5;
					yTR += 0.1 * yScale * 5;
					yBR -= 0.1 * yScale * 5;
					yBL -= 0.235 * yScale * 5;
				}
			}
		} else if (dir == 2) {
			if(GamePanel.timeCounter < 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL -= 1.875 * xScale * 5;
					xBL -= 1.875 * xScale * 5;
					xTR -= 1.875 * xScale * 5;
					xBR -= 1.875 * xScale * 5;
					yTR -= (1.0/3.0) * yScale * 5;
					yTL -= (1.0/3.0) * yScale * 5;
					yBR += (1.0/3.0) * yScale * 5;
					yBL += (1.0/3.0) * yScale * 5;
				} else if(Run3DMaze.clicked){
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
			} else if (GamePanel.timeCounter < 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL -= 0.2 * xScale * 5; 
					yTL -= 0.2 * xScale * 5;
					xBL -= 0.2 * xScale * 5;
					yBL += 0.2 * xScale * 5; 
					xTR += 0.2 * xScale * 5; 
					yTR -= 0.2 * xScale * 5;
					xBR += 0.2 * xScale * 5;
					yBR += 0.2 * xScale * 5;
				} else if(Run3DMaze.clicked){
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
			} else if (GamePanel.timeCounter < 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL += 1.875 * xScale * 5;
					xBL += 1.875 * xScale * 5;
					xTR += 1.875 * xScale * 5;
					xBR += 1.875 * xScale * 5;
					yTR -= (1.0/3.0) * yScale * 5;
					yTL -= (1.0/3.0) * yScale * 5;
					yBR += (1.0/3.0) * yScale * 5;
					yBL += (1.0/3.0) * yScale * 5;
				} else if(Run3DMaze.clicked){
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
			} else if (GamePanel.timeCounter < 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
			}
		} else if (dir == 3) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked){
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked){
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked){
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 233 * yScale; yTR = 233 * yScale; yBR = 467 * yScale; yBL = 467 * yScale;
				}
			}
		} else if (dir == 4) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 75 * xScale; xTR = 175 * xScale; xBR = 175 * xScale; xBL = 75 * xScale;
					yTL = 253 * yScale; yTR = 280 * yScale; yBR = 420 * yScale; yBL = 447 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 450 * xScale; xBL = 450 * xScale; xTR = 550 * xScale; xBR = 550 * xScale;
					yTL = 300 * yScale; yBL = 400 * yScale; yTR = 300 * yScale; yBR = 400 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
			yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else if(Run3DMaze.clicked) {
					xTL = 825 * xScale; xTR = 925 * xScale; xBR = 925 * xScale; xBL = 825 * xScale;
					yTL = 280 * yScale; yTR = 253 * yScale; yBR = 447 * yScale; yBL = 420 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
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
