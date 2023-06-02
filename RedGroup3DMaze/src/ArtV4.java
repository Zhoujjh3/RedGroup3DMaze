import java.awt.Color;
import java.awt.Graphics;

public class ArtV4 extends Art3D {

	int xQuarterLeft, yQuarterLeft, xQuarterTop, yQuarterTop, xMidTopLeft, yMidTopLeft;
	
	ArtV4(int theState) {
		super(theState);
	}
	
	public void paint(Graphics g) {
		xMidLeft = (int) Math.rint((xTL + xBL)/2.0);
		yMidLeft = (int) Math.rint((yTL + yBL)/2.0);
		xQuarterLeft = (int) Math.rint((xTL + xMidLeft)/2.0);
		yQuarterLeft = (int) Math.rint((yTL + yMidLeft)/2.0);
		xMidTop = (int) Math.rint((xTL + xTR)/2.0);
		yMidTop = (int) Math.rint((yTL + yTR)/2.0);
		xQuarterTop = (int) Math.rint((xTL + xMidTop)/2.0);
		yQuarterTop = (int) Math.rint((yTL + yMidTop)/2.0);
		xMidTopLeft = (int) Math.rint((xMidLeft + xMidTop)/2.0);
		yMidTopLeft = (int) Math.rint((yMidLeft + yMidTop)/2.0);
		int[] art1X = {(int) Math.rint(xTL), xQuarterTop, xQuarterLeft};
		int[] art1Y = {(int) Math.rint(yTL), yQuarterTop, yQuarterLeft};
		int[] art2X = {xQuarterLeft, xQuarterTop, xMidTop, xMidLeft};
		int[] art2Y = {yQuarterLeft, yQuarterTop, yMidTop, yMidLeft};
		int[] art3X = {xMidLeft, xMidTopLeft, (int) Math.rint(xBR), (int) Math.rint(xBL)};
		int[] art3Y = {yMidLeft, yMidTopLeft, (int) Math.rint(yBR), (int) Math.rint(yBL)};
		int[] art4X = {xMidTopLeft, xMidTop, (int) Math.rint(xTR), (int) Math.rint(xBR)};
		int[] art4Y = {yMidTopLeft, yMidTop, (int) Math.rint(yTR), (int) Math.rint(yBR)};
		int roomRed = currentRoom.getRGBValues()[0];
		int roomGreen = currentRoom.getRGBValues()[1];
		int roomBlue = currentRoom.getRGBValues()[2];
		g.setColor(new Color(255 -(roomRed - 50), 255 - (roomGreen - 50), 255 - (roomBlue - 50)));
		g.fillPolygon(art1X, art1Y, 3);
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
		g.drawLine(xQuarterLeft,yQuarterLeft,xQuarterTop,yQuarterTop);
		g.drawLine(xMidLeft,yMidLeft,xMidTop,yMidTop);
		g.drawLine(xMidTopLeft,yMidTopLeft,(int) Math.rint(xBR),(int) Math.rint(yBR));
	}
	
}
