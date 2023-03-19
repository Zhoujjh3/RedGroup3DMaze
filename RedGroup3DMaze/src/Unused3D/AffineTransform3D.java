package Unused3D;
import java.util.Arrays;

public class AffineTransform3D {
	double[][] matrix;
	AffineTransform3D(double[] values) {
		this.matrix = new double[][]{
				{values[0],values[1],values[2],values[3]},
				{values[4],values[5],values[6],values[7]},
				{values[8],values[9],values[10],values[11]},
				{0,0,0,1}};;
	}
	AffineTransform3D(double[][] matrix) {
		this.matrix = matrix;
	}
	AffineTransform3D(){
		this.matrix = new double[][]{
			{1,0,0,0},
			{0,1,0,0},
			{0,0,1,0},
			{0,0,0,1}};
	}
	public AffineTransform3D concatenate(AffineTransform3D other) {
		double[][] result = new double[4][4];
		for (int row=0;row<4;row++) {
			for (int col=0;col<4;col++) {
				for (int i=0;i<4;i++) {
					result[row][col] +=
							matrix[row][i] * other.matrix[i][col];
				}
			}
		}
		return new AffineTransform3D(result);
	}
	public void translate(double x,double y,double z) {
		matrix[0][3] += x;
		matrix[1][3] += y;
		matrix[2][3] += z;
	}
	public void relativeTranslate(double x,double y,double z) {
		AffineTransform3D m = new AffineTransform3D();
		m.translate(x, y, z);
		this.matrix=this.concatenate(m).matrix;
	}
	public AffineTransform3D getRotation() {
		return new AffineTransform3D(new double[][] {{matrix[0][0],matrix[0][1],matrix[0][2],0},
			{matrix[1][0],matrix[1][1],matrix[1][2],0},
			{matrix[2][0],matrix[2][1],matrix[2][2],0},
			{0,0,0,1}});
	}
	public double[] getTranslation() {
		return new double[] {matrix[0][3],matrix[1][3],matrix[2][3]};
	}
	public void rotate(double xRad,double yRad, double zRad) {
		rotateX(xRad);
		rotateY(yRad);
		rotateY(zRad);
	}
	public AffineTransform3D rotateX(double xRad) {
		double[][] matrix = {{1,0,0,0},
				{1,Math.cos(xRad),Math.sin(xRad),0},
				{0,-Math.sin(xRad),Math.cos(xRad),0},
				{0,0,0,1}};
		return new AffineTransform3D(this.concatenate(new AffineTransform3D(matrix)).matrix);
	}
	public AffineTransform3D rotateY(double yRad) {
		double[][] matrix = {
				{Math.cos(yRad),0,-Math.sin(yRad),0},
				{0,1,0,0},
				{Math.sin(yRad),0,Math.cos(yRad),0},
				{0,0,0,1}};
		return new AffineTransform3D(this.concatenate(new AffineTransform3D(matrix)).matrix);
	}
	public AffineTransform3D rotateZ(double zRad) {
		double[][] matrix = {
				{Math.cos(zRad),-Math.sin(zRad),0,0},
				{Math.sin(zRad),Math.cos(zRad),0,0},
				{0,0,1,0},{0,0,0,1}};
		return new AffineTransform3D(this.concatenate(new AffineTransform3D(matrix)).matrix);
		
	}
	public AffineTransform3D invert() {
		double[][] result = new double[4][4];
		for (int row=0;row<3;row++) {
			for (int col=0;col<3;col++) {
				result[row][col] =
						matrix[col][row];
			}
		result[row][3] = -1*(matrix[0][3]*result[row][0]+matrix[1][3]*result[row][1]+matrix[2][3]*result[row][2]);
		}
		result[3][3] = 1;
		return new AffineTransform3D(result);
	}
	public void print() {
			System.out.println(Arrays.deepToString(matrix));
	}
}