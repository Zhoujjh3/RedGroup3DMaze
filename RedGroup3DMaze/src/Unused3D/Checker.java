package Unused3D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Checker implements MouseListener {
	Triangle[] t1=new Triangle[4];
	ArrayList<Triangle> tt = new ArrayList<Triangle>();
	Camera c;
	public void setT(int index,Triangle t,Camera c) {
		tt.add(new Triangle(t));
		this.c = c;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i=0;i<t1.length;i++) {
			Triangle t = t1[i];
		System.out.println("t"+i);
		t.toClockwise();
		c.worldToRaster(t, 600, 600);
//		System.out.println((e.getX() - t.v1.x()) * (t.v2.y() - t.v1.y()) - (e.getY() - t.v1.y()) * (t.v2.x() - t.v1.x()));
//
//		System.out.println((e.getX() - t.v2.x()) * (t.v3.y() - t.v2.y()) - (e.getY() - t.v2.y()) * (t.v3.x() - t.v2.x()));
//
//		System.out.println((e.getX() - t.v3.x()) * (t.v1.y() - t.v3.y()) - (e.getY() - t.v3.y()) * (t.v1.x() - t.v3.x()));
		double d =Engine3D.inTriangle(t.v1,t.v2,t.v3);
		Vertex p = new Vertex(e.getX(),e.getY(),0);
		double triArea = Engine3D.inTriangle(t.v1,t.v2,t.v3);
		double bary1 = Engine3D.inTriangle(t.v2,t.v3,p);
		double bary2 = Engine3D.inTriangle(t.v3,t.v1,p);
		double bary3 = Engine3D.inTriangle(t.v1,t.v2,p);
			bary1/=triArea;
			bary2/=triArea;
			bary3/=triArea;
			double depth = 1/(bary1/t.v1.z()+bary2/t.v2.z()+bary3/t.v3.z());
			System.out.println(depth);
//		System.out.println(Arrays.toString(t.v1.coordinate));
//		System.out.println(Arrays.toString(t.v2.coordinate));
//		System.out.println(Arrays.toString(t.v3.coordinate));
//		System.out.println(Engine3D.inTriangle(t.v1,t.v2, new Vertex(e.getX(),e.getY(),0))/d);
//		System.out.println(Engine3D.inTriangle(t.v2,t.v3, new Vertex(e.getX(),e.getY(),0))/d);
//		System.out.println(Engine3D.inTriangle(t.v3,t.v1, new Vertex(e.getX(),e.getY(),0))/d);
		}
		System.out.println("");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}