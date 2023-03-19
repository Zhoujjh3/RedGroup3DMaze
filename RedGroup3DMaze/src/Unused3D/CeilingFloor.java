package Unused3D;
import java.awt.Color;

public class CeilingFloor extends RoomDimensions{
	CeilingFloor(Color color, AffineTransform3D w2l) {
		super(makeWall(color), w2l);
	}
	private static Triangle[] makeWall(Color color){
		Rectangle rect =  new Rectangle(LTF,RTF,LTB,RTB,color);
		return rect.getTriangles();
	}
}
