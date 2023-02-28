import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Checker implements MouseListener {
	Triangle[] t1=new Triangle[4];
	ArrayList<Triangle> tt = new ArrayList<Triangle>();
	Camera c;
	int w,h;
	
	public void setT(int index,Triangle t,Camera c,int width, int height) {
		if(tt.size()<=index)
			tt.add(new Triangle(t));
		else
			tt.set(index, t);
		this.c = c;
		w=width;
		h=height;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i=0;i<tt.size();i++) {
			Triangle t = tt.get(i);
			System.out.println(c.isVisible(t.v1,c.screenDistance));
			System.out.println(t.v1.z());
			System.out.println(c.isVisible(t.v2,c.screenDistance));
			System.out.println(t.v2.z());
			System.out.println(c.isVisible(t.v3,c.screenDistance));
			System.out.println(t.v3.z());
//		System.out.println("t"+i);
//		System.out.println("Z's: "+t.getByZ(0).z());
//		System.out.println("Z's: "+t.getByZ(1).z());
//		System.out.println("Z's: "+t.getByZ(2).z());
//		Triangle ts = c.toScreen(t);
//		ts = c.toNDC(ts);
//		ts = Camera.toRaster(t, w, h);
//		ts.toClockwise();
		//c.worldToRaster(t, 600, 600);
//		System.out.println((e.getX() - t.v1.x()) * (t.v2.y() - t.v1.y()) - (e.getY() - t.v1.y()) * (t.v2.x() - t.v1.x()));
//
//		System.out.println((e.getX() - t.v2.x()) * (t.v3.y() - t.v2.y()) - (e.getY() - t.v2.y()) * (t.v3.x() - t.v2.x()));
//
////		System.out.println((e.getX() - t.v3.x()) * (t.v1.y() - t.v3.y()) - (e.getY() - t.v3.y()) * (t.v1.x() - t.v3.x()));
//		double d =Engine3D.inTriangle(ts.v1,ts.v2,ts.v3);
//		Vertex p = new Vertex(e.getX(),e.getY(),0);
//		System.out.println(e.getX()+", "+e.getY());
//		System.out.println(e.getX()+", "+e.getY());
//		double triArea = Engine3D.inTriangle(ts.v1,ts.v2,ts.v3);
//		double bary1 = Engine3D.inTriangle(ts.v2,ts.v3,p);
//		double bary2 = Engine3D.inTriangle(ts.v3,ts.v1,p);
//		double bary3 = Engine3D.inTriangle(ts.v1,ts.v2,p);
//			System.out.println("bary 1: "+bary1);
//			System.out.println("bary 2: "+bary2);
//			System.out.println("bary 3: "+bary3);
//			System.out.println(bary1>=0&&bary2>= 0&&bary3>= 0);
//			bary1/=triArea;
//			bary2/=triArea;
//			bary3/=triArea;
//			double depth = 1/(bary1/ts.v1.z()+bary2/ts.v2.z()+bary3/ts.v3.z());
//			System.out.println(depth);
//			System.out.println(t.color.getRed()+", "+t.color.getGreen()+", "+t.color.getBlue());
//		System.out.println("minX: "+ts.getByX(0).x());
//		System.out.println("maxX: "+ts.getByX(2).x());
//		System.out.println("minY: "+ts.getByY(0).y());
//		System.out.println("maxY: "+ts.getByY(2).y());
		}
		System.out.println("\n\n\n\n\n\n\n\n\n");
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
