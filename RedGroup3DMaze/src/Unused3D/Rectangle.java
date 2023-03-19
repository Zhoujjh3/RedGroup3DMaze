package Unused3D;
import java.awt.*;

public class Rectangle  {
	private Triangle[] t;
	Rectangle(Vertex v1,Vertex v2,Vertex v3,Vertex v4, Color color) {
		t = new Triangle[]{new Triangle(v1,v2,v3, color),
				new Triangle(v4,v2,v3, color)};
	}
	public Triangle[] getTriangles() {
		return t.clone();
	}

}