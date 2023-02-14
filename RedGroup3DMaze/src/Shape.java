import java.util.*;

public class Shape extends Entity {
	Triangle[] triangles;
	Shape(Triangle[] tri, double[] fromOrigin){
		triangles=tri;
//		print();
		w2l = new AffineTransform3D();
		w2l.translate(fromOrigin[0], fromOrigin[1], fromOrigin[2]);
		triangles = transformTris(w2l.invert());
	}
	Shape(Triangle[] tri, AffineTransform3D w2l){
		triangles=tri;
		this.w2l = w2l;
		triangles = transformTris(w2l.invert());
	}
	private Triangle[] transformTris(AffineTransform3D t) {
		Triangle[] tempTri = triangles.clone();
		for (int i=0;i<tempTri.length;i++) {
			tempTri[i]=tempTri[i].transform(w2l);
		}
		return tempTri;
	}
	public Shape transform(AffineTransform3D t) {
		t = w2l.concatenate(t);
		return new Shape(transformTris(w2l),t);
	}
	public void print() {
		for(Triangle tri:triangles) {
			System.out.println(Arrays.toString(tri.v1.coordinate)+", "+
		Arrays.toString(tri.v2.coordinate)+", "+Arrays.toString(tri.v3.coordinate));
		}
		System.out.println("\n\n");
	}
}
