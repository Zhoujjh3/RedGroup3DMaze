
public class Vertex {
    double[] coordinate;
    Vertex(double x, double y, double z) {
       this(new double[]{x,y,z,1});
    }
    Vertex(double[] coordinate) {
        this.coordinate = coordinate;
    }
    Vertex(Vertex copy) {
        this.coordinate = new double[]
        		{copy.x(),copy.y(),copy.z()};
    }
    double x() {
    	return coordinate[0];
    }
    double y() {
    	return coordinate[1];
    }
    double z() {
    	return coordinate[2];
    }
	public Vertex transform(AffineTransform3D t) {
		double[] coord = new double[] {0,0,0,1};
		for (int row=0;row<3;row++) {
			for (int col=0;col<4;col++) {
				coord[row] +=t.matrix[row][col]*coordinate[col];
			}
		}
		return new Vertex(coord);
	}
	public void print() {
		System.out.println("("+x()+", "+y()+", "+z()+")");
	}
}
