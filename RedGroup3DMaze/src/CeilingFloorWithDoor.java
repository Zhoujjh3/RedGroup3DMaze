import java.awt.Color;
import java.util.Arrays;

public class CeilingFloorWithDoor extends RoomDimensions {

	CeilingFloorWithDoor(Color color, AffineTransform3D w2l) {
		super(makeWall(color), w2l);
	}
	private static Triangle[] makeWall(Color color){
		Vertex vd1 = new Vertex(-20, TOP, -20);
		Vertex vd2 = new Vertex(20, TOP,  vd1.z());
		Vertex vd3 = new Vertex(vd1.x(), TOP, 20);
		Vertex vd4 = new Vertex(vd2.x(),TOP, vd3.z());
		Vertex vm1 = new Vertex(vd1.x(),TOP, FRONT);
		Vertex vm2 = new Vertex(vd2.x(), TOP, FRONT);
		Vertex vm3 = new Vertex(vd3.x(),TOP, BACK);
		Vertex vm4 = new Vertex(vd4.x(), TOP, BACK);
		Triangle[] tris = new Triangle[8];
		Rectangle rect[] =  {new Rectangle(LTF,vm1,LTB,vm3,color),
				new Rectangle(vm1,vm2,vd1,vd2,color),
				new Rectangle(vm3,vm4,vd3,vd4,color),
				new Rectangle(RTF,vm2,RTB,vm4,color)};
		for (int i = 0;i<rect.length;i++) {
			for (int j = 0;j < 2;j++) {
				Triangle t = rect[i].getTriangles()[j];
				tris[2*i+j] = t;
			}
		}
		return tris;
	}

}
