import java.awt.*;
import java.awt.*;
import java.util.*;

public class Camera extends Entity{
	Vertex location;
	double rotateX,rotateY;
	public double screenDistance, viewAngleH,viewAngleV, canvasWidth,
			canvasHeight, imageWidth, imageHeight;
	Camera(double angle, double distance){
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
	public double getDistance() {
		return screenDistance;
	}
	public double getWidth() {
		return canvasWidth;
	}
	public double getHeight() {
		return canvasHeight;
	}
	public boolean isVisible(Vertex p, double nearClip) {
//		System.out.println("nearClip: "+nearClip);
//		System.out.println("z: "+p.z());
//		System.out.println(p.z()<0);
		if(p.z()<0)
			return true;
		else 
		return false;
	}
	public boolean isVisible(Triangle t, double nearClip) {
		boolean visibility = isVisible(t.v1,nearClip);
		visibility&=isVisible(t.v2,nearClip);
		visibility&=isVisible(t.v3,nearClip);
//		System.out.println("visible: "+visibility+"\n\n\n");
		return visibility;
	}
	public boolean isInvisible(Triangle t, double nearClip) {
		boolean visibility = isVisible(t.v1,nearClip);
		//System.out.println("v"+visibility);
		visibility|=isVisible(t.v2,nearClip);
		visibility|=isVisible(t.v3,nearClip);
//		System.out.println("invisible: "+!visibility+"\n\n\n");
		return !visibility;
	}
	public void rotateinPlace(double xRad, double yRad, double zRad) {
		AffineTransform3D m = new AffineTransform3D();
		m.rotate(xRad, yRad, zRad);
		
	}
	public Vertex toScreen(Vertex v) {
		double z;
			z=v.z();
		Vertex point = new Vertex(screenDistance*v.x()/-z,
				screenDistance*v.y()/-z,
				-z);
		return point;
	}
	public Triangle toScreen(Triangle t) {
		t.v1 = toScreen(t.v1);
		t.v2 = toScreen(t.v2);
		t.v3 = toScreen(t.v3);
		return t;
	}
	public Vertex toNDC(Vertex p) {
		Vertex pNorm = new Vertex((p.x()+canvasWidth/2)/canvasWidth,
				(p.y()+canvasHeight/2)/canvasHeight,p.z());
		return pNorm;
	}
	public Triangle toNDC(Triangle t) {
		t.v1 = toNDC(t.v1);
		t.v2 = toNDC(t.v2);
		t.v3 = toNDC(t.v3);
		return t;
	}
	public static Vertex toRaster(Vertex p,int width,int height) {
		Vertex point = new Vertex(p.x()*width,
				(1-p.y())*height,p.z());
		return point;
	}
	public static Triangle toRaster(Triangle t,int width,int height) {
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
	
}
