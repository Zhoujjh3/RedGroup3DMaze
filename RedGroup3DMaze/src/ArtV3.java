import java.awt.Color;
import java.awt.Graphics;

public class ArtV3 extends Art3D {

	ArtV3(int theState) {
		super(theState);
	}

	public void paint(Graphics g) {
		xMidLeft = (int) Math.rint((xTL + xBL)/2.0);
		yMidLeft = (int) Math.rint((yTL + yBL)/2.0);
		xMidRight = (int) Math.rint((xTR + xBR)/2.0);
		yMidRight = (int) Math.rint((yTR + yBR)/2.0);
		xMidTop = (int) Math.rint((xTL + xTR)/2.0);
		yMidTop = (int) Math.rint((yTL + yTR)/2.0);
		xMidBot = (int) Math.rint((xBL + xBR)/2.0);
		yMidBot = (int) Math.rint((yBL + yBR)/2.0);
		int[] art1X = {(int) Math.rint(xTL), xMidTop, xMidLeft};
		int[] art1Y = {(int) Math.rint(yTL), yMidTop, yMidLeft};
		int[] art2X = {xMidTop, (int) Math.rint(xTR), xMidRight};
		int[] art2Y = {yMidTop, (int) Math.rint(yTR), yMidRight};
		int[] art3X = {xMidLeft, xMidBot, (int) Math.rint(xBL)};
		int[] art3Y = {yMidLeft, yMidBot, (int) Math.rint(yBL)};
		int[] art4X = {xMidRight, (int) Math.rint(xBR), xMidBot};
		int[] art4Y = {yMidRight, (int) Math.rint(yBR), yMidBot};
		int[] art5X = {xMidTop, xMidRight, xMidBot, xMidLeft};
		int[] art5Y = {yMidTop, yMidRight, yMidBot, yMidLeft};
		int roomRed = currentRoom.getRGBValues()[0];
		int roomGreen = currentRoom.getRGBValues()[1];
		int roomBlue = currentRoom.getRGBValues()[2];
		g.setColor(new Color(255 -(roomRed - 50), 255 - (roomGreen - 50), 255 - (roomBlue - 50)));
		g.fillPolygon(art1X, art1Y, 3);
		g.setColor(new Color(255 - (roomRed - 20), 255 - (roomGreen - 20), 255 - (roomBlue - 20)));		
		g.fillPolygon(art2X, art2Y, 3);
		g.setColor(new Color(255 - roomRed, 255 - roomGreen, 255 - roomBlue));
		g.fillPolygon(art3X, art3Y, 3);
		g.setColor(new Color(255 - (roomRed + 10), 255 - (roomGreen + 10), 255 - (roomBlue + 10)));
		g.fillPolygon(art4X, art4Y, 3);
		g.setColor(new Color(255 - (roomRed + 20), 255 - (roomGreen + 20), 255 - (roomBlue + 20)));
		g.fillPolygon(art5X, art5Y, 4);
		g.setColor(Color.black);
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xBL),(int) Math.rint(yBL));
		g.drawLine((int) Math.rint(xTR),(int) Math.rint(yTR),(int) Math.rint(xBR),(int) Math.rint(yBR));
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xTR),(int) Math.rint(yTR));
		g.drawLine((int) Math.rint(xBL),(int) Math.rint(yBL),(int) Math.rint(xBR),(int) Math.rint(yBR));
		g.drawLine(xMidLeft,yMidLeft,xMidTop,yMidTop);
		g.drawLine(xMidTop,yMidTop,xMidRight,yMidRight);
		g.drawLine(xMidLeft,yMidLeft,xMidBot,yMidBot);
		g.drawLine(xMidBot,yMidBot,xMidRight,yMidRight);
	}
	
}
