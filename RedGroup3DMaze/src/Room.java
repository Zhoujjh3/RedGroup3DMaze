public class Room {
	private boolean[] directions = new boolean[6];
	private int[] coordinates = new int[3];
	private boolean visited;
	
	/*
	 * Coordinates are stored: {X, Y, Z}
	 * Directions are stored: {N, E, S, W, U, D}
	 */
	public Room(int[] coordinates, boolean[] directions) {
		for (int i=0; i<coordinates.length; i++) {
			this.coordinates[i] = coordinates[i];
		}
		for (int i=0; i<directions.length; i++) {
			this.directions[i] = directions[i];
		}
		visited = false;
	}	
	public boolean getDirection(char direction) {
		switch(direction) {
		case 'N':
			return directions[0];
		case 'E':
			return directions[1];
		case 'S':
			return directions[2];
		case 'W':
			return directions[3];
		case 'U':
			return directions[4];
		case 'D':
			return directions[5];
		default:
			return directions[0];
		}
	}
	
	public int getCoordinate(char value) {
		if (value == 'X') {
			return coordinates[0];
		} else if (value == 'Y') {
			return coordinates[1];
		} else if (value == 'Z') {
			return coordinates[2];
		}
		return -1;
	}
	public boolean hasVisited() {
		boolean x = visited;
		return x;
	}
	public void setVisited(boolean newVal) {
		visited = newVal;
	}
}