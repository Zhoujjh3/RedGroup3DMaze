public class Room {
	private boolean[] directions = new boolean[6];
	private int[] coordinates = new int[3];
	private boolean visited;
	private char artDirection;
	
	/*
	 * Coordinates are stored: {Z, X, Y}
	 * Directions are stored: {N, E, S, W, U, D}
	 */
	public Room(int[] coordinates, boolean[] directions) {
		for (int i=0; i<coordinates.length; i++) {
			this.coordinates[i] = coordinates[i];
		}
		for (int i=0; i<directions.length; i++) {
			this.directions[i] = directions[i];
		}
		if((coordinates[1] == 0 && coordinates[2] == 0)&&coordinates[0] == 0) {
			visited = true;
		} else {
			visited = false;
		}
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
		if (value == 'Z') {
			return coordinates[0];
		} else if (value == 'X') {
			return coordinates[1];
		} else if (value == 'Y') {
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