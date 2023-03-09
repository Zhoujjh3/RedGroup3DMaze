
public abstract class RoomDimensions extends Shape {
	public static final double LEFT = -100;
	public static final double RIGHT = 100;
	public static final double BOTTOM = -75;
	public static final double TOP = 75;
	public static final double FRONT = -100;
	public static final double BACK = 100;
	public static final Vertex LTF = new Vertex(LEFT, TOP, FRONT);
	public static final Vertex LBF = new Vertex(LEFT, BOTTOM, FRONT);
	public static final Vertex RTF = new Vertex(RIGHT, TOP, FRONT);
	public static final Vertex RBF = new Vertex(RIGHT, BOTTOM, FRONT);
	public static final Vertex LTB = new Vertex(LEFT, TOP, BACK);
	public static final Vertex LBB = new Vertex(LEFT, BOTTOM, BACK);
	public static final Vertex RTB = new Vertex(RIGHT, TOP, BACK);
	public static final Vertex RBB = new Vertex(RIGHT, BOTTOM, BACK);

	RoomDimensions(Triangle[] tri, AffineTransform3D w2l) {
		super(tri, w2l);
	}

}
