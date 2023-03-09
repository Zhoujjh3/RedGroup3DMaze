import java.awt.Color;

public class Door extends RoomDimensions {

	Door(Color color, AffineTransform3D w2l) {
		super(makeWall(color), w2l);
	}
	private static Triangle[] makeWall(Color color){
		Vertex vd1 = new Vertex(-20, 0, FRONT);
		Vertex vd2 = new Vertex(20, vd1.y(), FRONT);
		Vertex vd3 = new Vertex(vd1.x(), BOTTOM, FRONT);
		Vertex vd4 = new Vertex(vd2.x(), vd3.y(), FRONT);
		Rectangle rect =  new Rectangle(vd1,vd2,vd3,vd4,color);
		return rect.getTriangles();
	}

}
