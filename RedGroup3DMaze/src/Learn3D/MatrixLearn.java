package Learn3D;


public class MatrixLearn {

	double[] theValues;
	
	MatrixLearn(double[] values) {
		theValues = values;
	}
	
	MatrixLearn multiply(MatrixLearn other) {
		double[] result = new double[9];
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				for(int i = 0; i < 3; i++) {
					result[row * 3 + col] += 
						theValues[row * 3 + i] * other.theValues[i * 3 + col];
				}
			}
		}
		return new MatrixLearn(result);
	}
	
	VertexLearn transform(VertexLearn in) {
		return new VertexLearn(
		in.coordinates[0] * theValues[0] + 
		in.coordinates[1] * theValues[3] + 
		in.coordinates[2] * theValues[6],
		in.coordinates[0] * theValues[1] + 
		in.coordinates[1] * theValues[4] + 
		in.coordinates[2] * theValues[7],
		in.coordinates[0] * theValues[2] + 
		in.coordinates[1] * theValues[5] + 
		in.coordinates[2] * theValues[8]
		);
	}
	
}