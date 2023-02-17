import java.awt.*;

public class Rectangle  {
	private Triangle t1;
	private Triangle t2;
	Rectangle(Vertex v1,Vertex v2,Vertex v3,Vertex v4, Color color) {
		t1 = new Triangle(v1,v2,v3, color);
		t2 = new Triangle(v4,v2,v3, color);
	}

}
