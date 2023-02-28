 import java.util.*;

public class Shape extends Entity {
	private Triangle[] triangles;
	Shape(Triangle[] tri, double[] fromOrigin){
		triangles=tri;
		setLocalToWorld(new AffineTransform3D());
		localToWorld().translate(fromOrigin[0], fromOrigin[1], fromOrigin[2]);
	}
	Shape(Triangle[] tri, AffineTransform3D w2l){
		triangles=tri;
		setLocalToWorld(w2l);
	}
	private Triangle[] transformTris(AffineTransform3D t) {
		Triangle[] tempTri = triangles.clone();
		for (int i=0;i<tempTri.length;i++) {
			tempTri[i]=tempTri[i].transform(t);
		}
		return tempTri;
	}
	public Triangle[] getTriangles() {
		return triangles.clone();
	}
	public Shape transform(AffineTransform3D t) {
		t = localToWorld().concatenate(t.invert());
		return new Shape(triangles.clone(),t);
	}
	public Shape changeCoords(AffineTransform3D t) {
		AffineTransform3D t2 = localToWorld().concatenate(t.invert());
		return new Shape(transformTris(t),t2);
	}
	
	public void print() {
		for(Triangle tri:triangles) {
			System.out.println(Arrays.toString(tri.v1.coordinate)+", "+
		Arrays.toString(tri.v2.coordinate)+", "+Arrays.toString(tri.v3.coordinate));
		}
		System.out.println("\n\n");
	}
}
