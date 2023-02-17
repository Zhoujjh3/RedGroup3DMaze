import java.util.*;

public class Shape extends Entity {
	Triangle[] triangles;
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
			tempTri[i]=tempTri[i].transform(localToWorld());
		}
		return tempTri;
	}
	public Shape transform(AffineTransform3D t) {
		t = localToWorld().concatenate(t);
		return new Shape(triangles.clone(),t);
	}
	public void print() {
		for(Triangle tri:triangles) {
			System.out.println(Arrays.toString(tri.v1.coordinate)+", "+
		Arrays.toString(tri.v2.coordinate)+", "+Arrays.toString(tri.v3.coordinate));
		}
		System.out.println("\n\n");
	}
}
