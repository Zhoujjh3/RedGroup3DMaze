import java.awt.*;
import java.util.*;

public class RectanglePrism extends Shape {
	RectanglePrism(Vertex[] vertices,AffineTransform3D w2l,Color[] color) {
		super(toTriangles(vertices,color), w2l);
		}
	RectanglePrism(Vertex[] vertices, double[] fromOrigin,Color[] color) {
		super(toTriangles(vertices,color), fromOrigin);
	}
	private static Triangle[] toTriangles(Vertex[] v,Color[] color) {
		Rectangle[] rects = {new Rectangle(v[1],v[2],v[3],v[4],color[0]),
				new Rectangle(v[5],v[6],v[7],v[8],color[1]),
				new Rectangle(v[1],v[2],v[5],v[6],color[2]),
				new Rectangle(v[7],v[8],v[3],v[4],color[3]),
				new Rectangle(v[1],v[4],v[5],v[8],color[4]),
				new Rectangle(v[6],v[2],v[3],v[7],color[5])};
		ArrayList<Triangle> ts = new ArrayList<Triangle>();
		for(Rectangle rect: rects) {
			for (Triangle t: rect.getTriangles()) {
				ts.add(t);
			}
		}
		return (Triangle[])ts.toArray();
	}
	
}