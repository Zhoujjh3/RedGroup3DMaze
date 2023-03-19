package Unused3D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class WallWithDoor extends RoomDimensions{

	WallWithDoor(Color color, AffineTransform3D w2l) {
		super(makeWall(color), w2l);
	}
	private static Triangle[] makeWall(Color color){
		Vertex vd1 = new Vertex(-20, 0, FRONT);
		Vertex vd2 = new Vertex(20, vd1.y(), FRONT);
		Vertex vd3 = new Vertex(vd1.x(), BOTTOM, FRONT);
		Vertex vd4 = new Vertex(vd2.x(), vd3.y(), FRONT);
		Vertex vm1 = new Vertex(vd1.x(), TOP, FRONT);
		Vertex vm2 = new Vertex(vd2.x(), TOP, FRONT);
		Triangle[] tris = new Triangle[6];
		Rectangle rect[] =  {new Rectangle(LTF,vm1,LBF,vd3,color),
				new Rectangle(vm1,vm2,vd1,vd2,color),
				new Rectangle(vm2,RTF,vd4,RBF,color)};
		for (int i = 0;i<rect.length;i++) {
			for (int j = 0;j < 2;j++) {
				Triangle t = rect[i].getTriangles()[j];
				tris[2*i+j] = t;
			}
		}
		return tris;
	}

}
