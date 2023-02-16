package Learn3D;

import java.awt.*;

public class TriangleLearn {

	VertexLearn[] vertices = new VertexLearn[3];
	Color theColor;
	
	TriangleLearn(VertexLearn v1, VertexLearn v2, VertexLearn v3, Color color) {
		vertices[0] = v1;
		vertices[1] = v2;
		vertices[2] = v3;
		theColor = color;
	}
	
}

