import java.awt.Color;
import java.awt.Graphics;

public class ArtV2 extends Art3D {
	
	int xFirstLeft, yFirstLeft, xFirstRight, yFirstRight, xThirdLeft, 
	yThirdLeft, xThirdRight, yThirdRight;
	
	ArtV2(int theState) {
		super(theState);
	}

	public void paint(Graphics g) {
		xMidLeft = (int) Math.rint((xTL + xBL)/2.0);
		yMidLeft = (int) Math.rint((yTL + yBL)/2.0);
		xMidRight = (int) Math.rint((xTR + xBR)/2.0);
		yMidRight = (int) Math.rint((yTR + yBR)/2.0);
		xFirstLeft = (int) Math.rint((xTL + xMidLeft)/2.0);
		yFirstLeft = (int) Math.rint((yTL + yMidLeft)/2.0);
		xFirstRight = (int) Math.rint((xTR + xMidRight)/2.0);
		yFirstRight = (int) Math.rint((yTR + yMidRight)/2.0);
		xThirdLeft = (int) Math.rint((xMidLeft + xBL)/2.0);
		yThirdLeft = (int) Math.rint((yMidLeft + yBL)/2.0);
		xThirdRight = (int) Math.rint((xMidRight + xBR)/2.0);
		yThirdRight = (int) Math.rint((yMidRight + yBR)/2.0);
		int[] art1X = {(int) Math.rint(xTL), (int) Math.rint(xTR), xFirstRight, xFirstLeft};
		int[] art1Y = {(int) Math.rint(yTL), (int) Math.rint(yTR), yFirstRight, yFirstLeft};
		int[] art2X = {xFirstLeft, xFirstRight, xMidRight, xMidLeft};
		int[] art2Y = {yFirstLeft, yFirstRight, yMidRight, yMidLeft};
		int[] art3X = {xMidLeft, xMidRight, xThirdRight, xThirdLeft};
		int[] art3Y = {yMidLeft, yMidRight, yThirdRight, yThirdLeft};
		int[] art4X = {xThirdLeft, xThirdRight, (int) Math.rint(xBR), (int) Math.rint(xBL)};
		int[] art4Y = {yThirdLeft, yThirdRight, (int) Math.rint(yBR), (int) Math.rint(yBL)};
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
		g.drawLine(xFirstLeft,yFirstLeft,xFirstRight,yFirstRight);
		g.drawLine(xMidLeft,yMidLeft,xMidRight,yMidRight);
		g.drawLine(xThirdLeft,yThirdLeft,xThirdRight,yThirdRight);
	}
}
