import java.awt.*;

public class Wall extends RoomDimensions {

	Wall(Color color, AffineTransform3D w2l) {
		super(makeWall(color), w2l);
	}
	private static Triangle[] makeWall(Color color){
		Rectangle rect =  new Rectangle(LTF,RTF,LBF,RBF,color);
		return rect.getTriangles();
	}
}
