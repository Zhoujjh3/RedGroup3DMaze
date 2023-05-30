
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Wall3D extends Shapes3D{
	
	public int state, index, previousWallIndex, nextWallIndex;
	public int h, w;
	public int dir = 0;
	double xScale = w/1000;
	double yScale = h/700;
	Room room;
	
	Wall3D(int theState, Room room) {
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
			yTL = 0 * yScale; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
		} else if(theState == 1) {
			xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
			yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
		} else if(theState == 2) {
			xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
			yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0 * yScale; yBR = 700 * yScale;
		} else if(theState == 3) {
			if(dir == 0) {
				xTL = 0 * xScale; xTR = 0 * xScale; xBR = 0 * xScale; xBL = 0 * xScale;
				yTL = 0 * yScale; yTR = 0 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
			} else if (dir == 1){
				xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
				yTL = 0 * yScale; yTR = 0 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
			}
		}
		this.room = room;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		int[] wallX = {(int) Math.rint(xTL),(int) Math.rint(xTR),(int) Math.rint(xBR),(int) Math.rint(xBL)};
		int[] wallY = {(int) Math.rint(yTL),(int) Math.rint(yTR),(int) Math.rint(yBR),(int) Math.rint(yBL)};
		
		Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'), 
				Run3DMaze.player.getCoordinate('X'), 
				Run3DMaze.player.getCoordinate('Y'));
		
		Color roomColorStart = new Color(currentRoom.getRGBValues()[0], currentRoom.getRGBValues()[1], currentRoom.getRGBValues()[2]+25);
		Color roomColorEnd = new Color(currentRoom.getRGBValues()[0]-50, currentRoom.getRGBValues()[1]-50, currentRoom.getRGBValues()[2]-25);
		
		GradientPaint gradient = new GradientPaint(wallX[0], wallY[0], roomColorStart, wallX[2], wallY[2], roomColorEnd);
		//GradientPaint gradient = new GradientPaint(0, 0, roomColorStart, 500, 350, roomColorEnd);
		g2.setPaint(gradient);
		
		g2.fillPolygon(wallX, wallY, 4);
		g2.setColor(Color.black);
		g2.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xBL),(int) Math.rint(yBL));
		g2.drawLine((int) Math.rint(xTR),(int) Math.rint(yTR),(int) Math.rint(xBR),(int) Math.rint(yBR));
		g2.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xTR),(int) Math.rint(yTR));
		g2.drawLine((int) Math.rint(xBL),(int) Math.rint(yBL),(int) Math.rint(xBR),(int) Math.rint(yBR));
	}

	public void update() {
		h = Run3DMaze.height;
		w = Run3DMaze.width;
		xScale = w/1000.0;
		yScale = h/700.0;
		if(dir == 0) {
			//System.out.println(Run3DMaze.clicked);
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
					yTL = 0 * yScale; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTR += 2.5 * xScale * 5;
					xBR += 2.5 * xScale * 5;
					if(GamePanel.timeCounter > 20) {
						yTL += 2 * yScale * 5;
						yBL -= 2 * yScale * 5;
						xTL += 2.5 * xScale * 5;
						xBL += 2.5 * xScale * 5;
					}
				} else {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL += 2.5 * xScale * 5;
					xBL += 2.5 * xScale * 5;
					if(GamePanel.timeCounter < 20) {
						xTR += 2.5 * xScale * 5;
						xBR += 2.5 * xScale * 5;
						yTR -= 2 * yScale * 5;
						yBR += 2 * yScale * 5;
					}
				} else {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0 * yScale; yBR = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0 * yScale; yBR = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTL = room.walls[previousWallIndex].getyTR();
					yBL = room.walls[previousWallIndex].getyBR();
					xTL = room.walls[previousWallIndex].getxTR();
					xBL = room.walls[previousWallIndex].getxBR();
				} else {
					xTL = 0 * xScale; xTR = 0 * xScale; xBR = 0 * xScale; xBL = 0 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
				
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0 * xScale; xTR = 0 * xScale; xBR = 0 * xScale; xBL = 0 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR = room.walls[nextWallIndex].getyTL();
					yBR = room.walls[nextWallIndex].getyBL();
					xTR = room.walls[nextWallIndex].getxTL();
					xBR = room.walls[nextWallIndex].getxBL();		
				} else {
					xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
					yTL = 0 * yScale; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}		
			}
		} else if (dir == 1) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
					yTL = 0 * yScale; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR = room.walls[nextWallIndex].getyTL();
					yBR = room.walls[nextWallIndex].getyBL();
					xTR = room.walls[nextWallIndex].getxTL();
					xBR = room.walls[nextWallIndex].getxBL();		
				} else {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}	
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTR -= 2.5 * xScale * 5;
					xBR -= 2.5 * xScale * 5;
					if(GamePanel.timeCounter < 20 && Run3DMaze.clicked) {
						xTL -= 2.5 * xScale * 5;
						xBL -= 2.5 * xScale * 5;
						yTL -= 2 * yScale * 5;
						yBL += 2 * yScale * 5;
					}	
				} else {
					xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0 * yScale; yBR = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL -= 2.5 * xScale * 5;
					xBL -= 2.5 * xScale * 5;
					if(GamePanel.timeCounter > 20 && Run3DMaze.clicked) {
						xTR -= 2.5 * xScale * 5;
						xBR -= 2.5 * xScale * 5;
						yTR += 2 * yScale * 5;
						yBR -= 2 * yScale * 5;
					}	
				} else {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTL = room.walls[previousWallIndex].getyTR();
					yBL = room.walls[previousWallIndex].getyBR();
					xTL = room.walls[previousWallIndex].getxTR();
					xBL = room.walls[previousWallIndex].getxBR();
				} else {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0 * yScale; yBR = 700 * yScale;
				}
			}
		} else if (dir == 2) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
					yTL = 0 * yScale; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 1 * yScale * 5;
					yBR += 1 * yScale * 5;
					xTR -= 1.25 * xScale * 5;
					xBR -= 1.25 * xScale * 5;
				} else {
					xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
					yTL = 0 * yScale; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					xTL -= 1.25 * xScale * 5; 
					yTL -= 1 * yScale * 5;
					xBL -= 1.25 * xScale * 5;
					yBL += 1 * yScale * 5; 
					xTR += 1.25 * xScale * 5; 
					yTR -= 1 * yScale * 5;
					xBR += 1.25 * xScale * 5;
					yBR += 1 * yScale * 5;
				} else {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0 * yScale; yBR = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTL -= 1 * yScale * 5;
					yBL += 1 * yScale * 5;
					xTL += 1.25 * xScale * 5;
					xBL += 1.25 * xScale * 5;
				} else {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0 * yScale; yBR = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
			}
		} else if (dir == 3) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else {
					xTL = 0; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0 * yScale; yBR = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR += 3.5 * yScale * 5;
					yBR += 3.5 * yScale * 5;
					yTL += 3.5 * yScale * 5;
					yBL += 3.5 * yScale * 5;
				} else {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0 * yScale; yBR = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
				}
			}
		} else if (dir == 4) {
			if(GamePanel.timeCounter <= 40 && state == 0) {
				if(GamePanel.timeCounter == 0) {
					xTL = 0; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * xScale;
					yTL = 0; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
				if(GamePanel.timeCounter < 200 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else {
					xTL = 0 * xScale; xTR = 250 * xScale; xBR = 250 * xScale; xBL = 0 * yScale;
					yTL = 0 * yScale; yTR = 200 * yScale; yBR = 500 * yScale; yBL = 700 * yScale;
				}
			} else if(GamePanel.timeCounter <= 40 && state == 1){
				if(GamePanel.timeCounter == 0) {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else {
					xTL = 250 * xScale; xBL = 250 * xScale; xTR = 750 * xScale; xBR = 750 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 200 * yScale; yBR = 500 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 2){
				if(GamePanel.timeCounter == 0) {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0 * yScale; yBR = 700 * yScale;
				}
				if(GamePanel.timeCounter < 40 && Run3DMaze.clicked) {
					yTR -= 3.5 * yScale * 5;
					yBR -= 3.5 * yScale * 5;
					yTL -= 3.5 * yScale * 5;
					yBL -= 3.5 * yScale * 5;
				} else {
					xTL = 750 * xScale; xBL = 750 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale;
					yTL = 200 * yScale; yBL = 500 * yScale; yTR = 0; yBR = 700 * yScale;
				}
			} else if (GamePanel.timeCounter <= 40 && state == 3) {
				if(GamePanel.timeCounter == 0) {
					xTL = 1000 * xScale; xTR = 1000 * xScale; xBR = 1000 * xScale; xBL = 1000 * xScale;
					yTL = 0 * yScale; yTR = 0 * yScale; yBR = 700 * yScale; yBL = 700 * yScale;
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