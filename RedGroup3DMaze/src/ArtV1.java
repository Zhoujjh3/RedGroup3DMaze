import java.awt.Color;
import java.awt.Graphics;

public class ArtV1 extends Art3D {

	ArtV1(int theState) {
		super(theState);
	}

	public void paint(Graphics g) {
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
		int roomRed = currentRoom.getRGBValues()[0];
		int roomGreen = currentRoom.getRGBValues()[1];
		int roomBlue = currentRoom.getRGBValues()[2];
		g.setColor(new Color(255 -(roomRed - 50), 255 - (roomGreen - 50), 255 - (roomBlue - 50)));
		g.fillPolygon(art1X, art1Y, 4);
		g.setColor(new Color(255 - (roomRed - 20), 255 - (roomGreen - 20), 255 - (roomBlue - 20)));		
		g.fillPolygon(art2X, art2Y, 4);
		g.setColor(new Color(255 - roomRed, 255 - roomGreen, 255 - roomBlue));
		g.fillPolygon(art3X, art3Y, 4);
		g.setColor(new Color(255 - (roomRed + 20), 255 - (roomGreen + 20), 255 - (roomBlue + 20)));
		g.fillPolygon(art4X, art4Y, 4);
		g.setColor(Color.black);
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xBL),(int) Math.rint(yBL));
		g.drawLine((int) Math.rint(xTR),(int) Math.rint(yTR),(int) Math.rint(xBR),(int) Math.rint(yBR));
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xTR),(int) Math.rint(yTR));
		g.drawLine((int) Math.rint(xBL),(int) Math.rint(yBL),(int) Math.rint(xBR),(int) Math.rint(yBR));
		g.drawLine(xMidLeft,yMidLeft,xMidRight,yMidRight);
		g.drawLine(xMidTop,yMidTop,xMidBot,yMidBot);
	}
	
}
