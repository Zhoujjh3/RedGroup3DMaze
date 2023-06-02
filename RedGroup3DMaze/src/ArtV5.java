
import java.awt.Color;
import java.awt.Graphics;

public class ArtV5 extends Art3D {

	int xQuarterLeft, yQuarterLeft, xQuarterTop, yQuarterTop, xMidTopLeft, yMidTopLeft;
	
	ArtV5(int theState) {
		super(theState);
	}
	
	public void paint(Graphics g) {
		int roomRed = currentRoom.getRGBValues()[0];
		int roomGreen = currentRoom.getRGBValues()[1];
		int roomBlue = currentRoom.getRGBValues()[2];
		xCenter = (int) Math.rint((xTL + xTR)/2.0);
		yCenter = (int) Math.rint((((yTL + yBL)/2.0) + ((yTR + yBR)/2.0))/2.0);
		int[] xBackground = {(int) Math.rint(xTL), (int) Math.rint(xTR), (int) Math.rint(xBR), (int) Math.rint(xBL)};
		int[] yBackground = {(int) Math.rint(yTL), (int) Math.rint(yTR), (int) Math.rint(yBR), (int) Math.rint(yBL)};
		g.setColor(new Color(255 -(roomRed - 50), 255 - (roomGreen - 50), 255 - (roomBlue - 50)));
		g.fillPolygon(xBackground, yBackground, 4);
		double oneThirdX = xTL + Math.rint((xTR-xTL)/3.0);
		paintStar(oneThirdX, Math.rint(yTopAtX(oneThirdX) + (yBotAtX(oneThirdX)-yTopAtX(oneThirdX))/3.0), 
		40, new Color(roomRed - 60, roomGreen - 60, roomBlue - 60), g);
		double oneEighthX = xTL + Math.rint((xTR-xTL)/8.0);
		paintStar(oneEighthX, Math.rint(yBotAtX(oneEighthX) - (yBotAtX(oneEighthX)-yTopAtX(oneEighthX))/8.0), 
		Math.rint((xTR-xTL)/8.0), new Color(roomRed - 40, roomGreen - 40, roomBlue - 40), g);
		double thirdFourthX = xTL + Math.rint(3.0 * ((xTR-xTL)/4.0));
		paintStar(thirdFourthX, Math.rint(yBotAtX(thirdFourthX) - (yBotAtX(thirdFourthX)-yTopAtX(thirdFourthX))/4.0), 
		Math.rint(9.0 * ((xTR-xTL)/40.0)), new Color(roomRed - 20, roomGreen - 20, roomBlue - 20), g);
		double fifthSixthX = xTL + Math.rint(5.0 * ((xTR-xTL)/6.0));
		paintStar(fifthSixthX, Math.rint(yTopAtX(fifthSixthX) + (yBotAtX(fifthSixthX)-yTopAtX(fifthSixthX))/6.0), 
		Math.rint((xTR-xTL)/6.0), new Color(roomRed, roomGreen, roomBlue), g);
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xBL),(int) Math.rint(yBL));
		g.drawLine((int) Math.rint(xTR),(int) Math.rint(yTR),(int) Math.rint(xBR),(int) Math.rint(yBR));
		g.drawLine((int) Math.rint(xTL),(int) Math.rint(yTL),(int) Math.rint(xTR),(int) Math.rint(yTR));
		g.drawLine((int) Math.rint(xBL),(int) Math.rint(yBL),(int) Math.rint(xBR),(int) Math.rint(yBR));
		
	}
	
	public void paintStar(double xCenter, double yCenter, double size, Color color, Graphics g) {
		
		double xSize =  (int) Math.rint(size * ((xTR-xTL)/100.0));
		double ySize =  (int) Math.rint(size * ((yBotAtX(xCenter)-yTopAtX(xCenter))/100.0));
		
		int xTopPoint, xLeftPoint, xRightPoint, xBottomPoint, 
		xTopLeftPoint, xTopRightPoint, xBottomLeftPoint, xBottomRightPoint,
		yTopPoint, yLeftPoint, yRightPoint, yBottomPoint, 
		yTopLeftPoint, yTopRightPoint, yBottomLeftPoint, yBottomRightPoint;
		
		xTopPoint = (int) Math.rint(xCenter);
		xBottomPoint = (int) Math.rint(xCenter);
		xLeftPoint = (int) Math.rint(xCenter - (xSize/2.0));
		xRightPoint = (int) Math.rint(xCenter + (xSize/2.0));
		xTopLeftPoint = (int) Math.rint(xCenter - (xSize/8.0));
		xTopRightPoint = (int) Math.rint(xCenter + (xSize/8.0));
		xBottomLeftPoint = (int) Math.rint(xCenter - (xSize/8.0));
		xBottomRightPoint = (int) Math.rint(xCenter + (xSize/8.0));
		
		yTopPoint = (int) Math.rint(yCenter - (ySize/2.0));
		yBottomPoint = (int) Math.rint(yCenter + (ySize/2.0));
		yLeftPoint = (int) Math.rint(yCenter);
		yRightPoint = (int) Math.rint(yCenter);
		yTopLeftPoint = (int) Math.rint(yCenter - (ySize/8.0));
		yTopRightPoint = (int) Math.rint(yCenter - (ySize/8.0));
		yBottomLeftPoint = (int) Math.rint(yCenter + (ySize/8.0));
		yBottomRightPoint = (int) Math.rint(yCenter + (ySize/8.0));
		
		int[] starX = {xTopPoint, xTopRightPoint, xRightPoint, xBottomRightPoint, 
		xBottomPoint, xBottomLeftPoint, xLeftPoint, xTopLeftPoint};
		
		int[] starY = {yTopPoint, yTopRightPoint, yRightPoint, yBottomRightPoint, 
		yBottomPoint, yBottomLeftPoint, yLeftPoint, yTopLeftPoint};
		
		g.setColor(color);
		g.fillPolygon(starX, starY, 8);
		g.setColor(Color.black);
		
		g.drawLine(xLeftPoint, yLeftPoint, xTopLeftPoint, yTopLeftPoint);
		g.drawLine(xTopLeftPoint, yTopLeftPoint, xTopPoint, yTopPoint);
		g.drawLine(xTopPoint, yTopPoint, xTopRightPoint, yTopRightPoint);
		g.drawLine(xTopRightPoint, yTopRightPoint, xRightPoint, yRightPoint);
		g.drawLine(xRightPoint, yRightPoint, xBottomRightPoint, yBottomRightPoint);
		g.drawLine(xBottomRightPoint, yBottomRightPoint, xBottomPoint, yBottomPoint);
		g.drawLine(xBottomPoint, yBottomPoint, xBottomLeftPoint, yBottomLeftPoint);
		g.drawLine(xBottomLeftPoint, yBottomLeftPoint, xLeftPoint, yLeftPoint);
	}
	
	public int yTopAtX(double xValue) {
		double slope = (yTR - yTL)/(xTR - xTL);
		return (int) Math.rint(slope * (xValue-xTL) + yTL);
		
	}
	
	public int yBotAtX(double xValue) {
		double slope = (yBR - yBL)/(xBR - xBL);
		return (int) Math.rint(slope * (xValue-xBL) + yBL);
	}
	
}
