import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Triangle {
	Vertex v1;
	Vertex v2;
	Vertex v3;
	Vertex[] v;
	Color color;
	Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		v = new Vertex[3];
		v[0] = v1;
		v[1] = v2;
		v[2] = v3;
//		System.out.print(Arrays.toString(this.v3.coordinate));
		this.color = color;
	}
	Triangle(Triangle tri) {
		this.v1 = tri.v1;
		this.v2 = tri.v2;
		this.v3 = tri.v3;
		v = new Vertex[3];
		v[0] = v1;
		v[1] = v2;
		v[2] = v3;
		this.color = tri.color;
	}
	public Triangle transform(AffineTransform3D t) {
		return new Triangle(v1.transform(t),v2.transform(t),
				v3.transform(t), color);
	}
	public Triangle[] clipTriangle(double nearClip) {
		ArrayList<Vertex> outVerts = new ArrayList<Vertex>();
		ArrayList<Vertex> inVerts = new ArrayList<Vertex>();
		ArrayList<Vertex> newVerts = new ArrayList<Vertex>();
		for(Vertex vv: v) {
			if (vv.z()<0) {
				inVerts.add(vv);
				newVerts.add(vv);
			}else outVerts.add(vv);
		}
//		System.out.println(newVerts.size());
		for(Vertex inV: inVerts) {
			for(Vertex outV: outVerts) {
				Vertex vector = new Vertex(outV.x()-inV.x(), outV.y()-inV.y(),outV.z()-inV.z());
				double t = (nearClip-inV.z())/(outV.z()-inV.z());
				double x = inV.x()+((outV.x()-inV.x())*t);
				double y = inV.y()+((outV.y()-inV.y())*t);
//				System.out.println(t+": "+x+", "+y+", "+nearClip);
//				System.out.println(t+": "+vector.x()+", "+vector.y()+", "+vector.z());
				newVerts.add(new Vertex(x,y,-nearClip));
			}
		}
//		System.out.println(newVerts.size());
//		newVerts.get(0).print();
//		newVerts.get(1).print();
//		newVerts.get(2).print();
//		newVerts.get(3).print();
		
		if (newVerts.size()==3) {
			return new Triangle[] {new Triangle
					(newVerts.get(0),newVerts.get(1),newVerts.get(2),color)};
		}else {
			Triangle[] t = {new Triangle
					(newVerts.get(0),newVerts.get(1),newVerts.get(2),color),null};
				t[1] = new Triangle
				(t[0].v2, t[0].v3, newVerts.get(3),color);
			return t;
		}
		
	}
	public void toClockwise() {
		Vertex n1 = new Vertex
				(v2.x()-v1.x(),v2.y()-v1.y(),v2.z()-v1.z());
		Vertex n2 = new Vertex
				(v3.x()-v1.x(),v3.y()-v1.y(),v3.z()-v1.z());
		Vertex normal = new Vertex(n1.y()*n2.z()-n1.z()*n2.y(),
				n1.z()*n2.x()-n1.x()*n2.z(),
				n1.x()*n2.y()-n1.y()*n2.x());
		if (normal.z()>0) {
			n2=v2;
			v2=v3;
			v3=n2;
		}
	}
	
	public Vertex getByX(int leftToRight) {
		Vertex[] v = new Vertex[3];
		if (v1.x()>v2.x()) {
			v[0]=v2;
			v[1]=v1;
		} else {
			v[0]=v1;
			v[1]=v2;
		}
		if (v[1].x()>v3.x()) {
			v[2]=v[1];
			v[1]=v3;
		} else {
			v[2]=v3;
		}
		if (v[0].x()>v[1].x()) {
			Vertex vt = v[0];
			v[0]=v[1];
			v[1]=vt;
		}
		return new Vertex(v[leftToRight]);
	}
	public Vertex getByY(int lowToHigh) {
		Vertex[] v = new Vertex[3];
		if (v1.y()>v2.y()) {
			v[0]=v2;
			v[1]=v1;
		} else {
			v[0]=v1;
			v[1]=v2;
		}
		if (v[1].y()>v3.y()) {
			v[2]=v[1];
			v[1]=v3;
		} else {
			v[2]=v3;
		}
		if (v[0].y()>v[1].y()) {
			Vertex vt = v[0];
			v[0]=v[1];
			v[1]=vt;
		}
		return new Vertex(v[lowToHigh]);
	}
	public Vertex getByZ(int nearToFar) {
		Vertex[] v = new Vertex[3];
		if (v1.z()>v2.z()) {
			v[0]=v2;
			v[1]=v1;
		} else {
			v[0]=v1;
			v[1]=v2;
		}
		if (v[1].z()>v3.z()) {
			v[2]=v[1];
			v[1]=v3;
		} else {
			v[2]=v3;
		}
		if (v[0].z()>v[1].z()) {
			Vertex vt = v[0];
			v[0]=v[1];
			v[1]=vt;
		}
		return new Vertex(v[nearToFar]);
	}
}
