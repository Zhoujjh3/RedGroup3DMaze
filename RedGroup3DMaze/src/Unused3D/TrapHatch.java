package Unused3D;
import java.awt.Color;

public class TrapHatch extends RoomDimensions {

	TrapHatch(Color color, AffineTransform3D w2l) {
		super(makeWall(color), w2l);
	}
	private static Triangle[] makeWall(Color color){
		Vertex vd1 = new Vertex(-20, TOP, -20);
		Vertex vd2 = new Vertex(20, TOP,  vd1.z());
		Vertex vd3 = new Vertex(vd1.x(), TOP, 20);
		Vertex vd4 = new Vertex(vd2.x(),TOP, vd3.z());
		Rectangle rect =  new Rectangle(vd1,vd2,vd3,vd4,color);
		return rect.getTriangles();
	}
}
