import java.awt.Color;
import java.util.*;

public class RoomShapes {
	int[] roomCenter;
	ArrayList<Shape> walls;
	RoomShapes(int[] coord, boolean[] dir){
		walls = new ArrayList<Shape>();
		roomCenter = new int[] {coord[1]*(int)(RoomDimensions.RIGHT-RoomDimensions.LEFT),
				coord[2]*(int)(RoomDimensions.TOP-RoomDimensions.BOTTOM),
				coord[0]*(int)(RoomDimensions.FRONT-RoomDimensions.BACK)};
		for(int i=0;i<4;i++) {
			AffineTransform3D orient = new AffineTransform3D();
			orient = orient.rotateY(i*Math.PI/2);
			orient.translate(roomCenter[0], roomCenter[1], roomCenter[2]);
			if (dir[i]) {
				walls.add(new WallWithDoor(Color.BLUE,orient));
				walls.add(new Door(Color.ORANGE,orient));
			}else {
				walls.add(new Wall(Color.BLUE, orient));
			}
		}
		for(int i=4;i<6;i++) {
			AffineTransform3D orient = new AffineTransform3D();
			orient = orient.rotateZ(i*Math.PI);
			orient.translate(roomCenter[0], roomCenter[1], roomCenter[2]);
			if (dir[i]) {
				walls.add(new CeilingFloorWithDoor(Color.ORANGE,orient));
				walls.add(new TrapHatch(Color.BLUE,orient));
			}else {
				walls.add(new CeilingFloor(Color.ORANGE, orient));
			}
		}
		
	}
}
