import java.awt.Dimension;
import java.awt.Point;
import java.util.Arrays;

public class Camera extends Entity{
	Vertex location;
	double screenDistance, viewAngleH,viewAngleV, canvasWidth,
			canvasHeight, imageWidth, imageHeight;
	Camera(double angle, double distance){
		location = new Vertex(0,0,0);
		setLocalToWorld(new AffineTransform3D());
		//canvasWidth = width;
		//viewAngleH = Math.atan(width/(2*distance));
		//viewAngleV = Math.atan(height/(2*distance));
		//canvasHeight = height;
		//imageWidth = imgWidth;
		//imageHeight = imgHeight;
		screenDistance = distance;
		canvasWidth= 2*Math.tan(angle/2)*screenDistance;
		canvasHeight= 2*Math.tan(angle/2)*screenDistance;
}
	public boolean isVisible(Vertex p) {
		p=toScreen(p);
		if((Math.abs(p.x())<=canvasWidth/2&&Math.abs(p.y())<=canvasHeight/2)&&p.z()>screenDistance) {
			return true;
		}
		return false;
	}
	public boolean isVisible(Triangle t) {
		boolean visibility = isVisible(t.v1);
		visibility|=isVisible(t.v2);
		visibility|=isVisible(t.v3);
		return visibility;
	}
	private Vertex toScreen(Vertex v) {
		Vertex point = new Vertex(screenDistance*v.x()/-v.z(),
				screenDistance*v.y()/-v.z(),
				-v.z());
		return point;
	}
	private Triangle toScreen(Triangle t) {
		t.v1 = toScreen(t.v1);
		t.v2 = toScreen(t.v2);
		t.v3 = toScreen(t.v3);
		return t;
	}
	private Vertex toNDC(Vertex p) {
		Vertex pNorm = new Vertex((p.x()+canvasWidth/2)/canvasWidth,
				(p.y()+canvasHeight/2)/canvasHeight,p.z());
		return pNorm;
	}
	private Triangle toNDC(Triangle t) {
		t.v1 = toNDC(t.v1);
		t.v2 = toNDC(t.v2);
		t.v3 = toNDC(t.v3);
		return t;
	}
	private static Vertex toRaster(Vertex p,int width,int height) {
		Vertex point = new Vertex(p.x()*width,
				(1-p.y())*height,p.z());
		return point;
	}
	private static Triangle toRaster(Triangle t,int width,int height) {
		t.v1 = toRaster(t.v1,width, height);
		t.v2 = toRaster(t.v2,width, height);
		t.v3 = toRaster(t.v3,width, height);
		return t;
	}
	public Triangle worldToRaster(Triangle t,int width,int height) {
		t = toScreen(t);
		t = toNDC(t);
		t = toRaster(t,width, height);
		return t;
	}
	public void transform(AffineTransform3D t) {
		location = location.transform(t);
	}
	
}
